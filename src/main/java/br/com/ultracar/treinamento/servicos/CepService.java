package br.com.ultracar.treinamento.servicos;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultracar.treinamento.entidades.Bairro;
import br.com.ultracar.treinamento.entidades.Cep;
import br.com.ultracar.treinamento.entidades.Cidade;
import br.com.ultracar.treinamento.entidades.Endereco;
import br.com.ultracar.treinamento.entidades.Estado;
import br.com.ultracar.treinamento.entidades.dto.CepDto;
import br.com.ultracar.treinamento.entidades.enumeradores.TipoLocal;
import br.com.ultracar.treinamento.repositorios.CepRepository;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class CepService {
	
	@Autowired
	private CepRepository repository;
	@Autowired
	private CidadeService cidadeService;
	@Autowired
	private EstadoService estadoService;
	
	public Page<Cep> findByCep(Cep cep, Pageable pageable){
		Page<Cep> cepPage = repository.findByCep(cep, pageable);
		if(cepPage.isEmpty()) {
			findBySaveCep(cep);
		}
		return cepPage;
	}
	
	private Cep findBySaveCep(Cep cep) {
		Cep cepSave = new Cep();
		CepDto cepDto = ApiCepService.findBySaveCep(cep);
		if(!Objects.isNull(cepDto)) {
			cepSave = saveByCep(montaObjectCep(cepDto, cep));
		}
		return cepSave;
	}
	
	private Cep montaObjectCep(CepDto cepDto, Cep cep) {
		Cep cepSave = new Cep();
		Endereco endereco = new Endereco();
		Bairro bairro = new Bairro();
		Set<Bairro> bairroSet = new HashSet<Bairro>();
		Estado estado = estadoService.findByEstadoForUf(cepDto.getUf());
		if(Objects.isNull(estado)) {
			estado = new Estado();
			estado.setSigla(cepDto.getUf());
		}
		Cidade cidade = cidadeService.findByCidadeForNomeUnique(cepDto.getCidade());
		if(Objects.isNull(cidade)) {
			cidade = new Cidade();
			cidade.setNome(cepDto.getCidade());
			cidade.setEstado(Objects.isNull(estado.getId()) ? estadoService.saveByEstado(estado) : estado);
		}
		bairro.setNome(cepDto.getBairro());
		bairroSet.add(bairro);
		bairro.setCidade(Objects.isNull(cidade.getId()) ? cidadeService.saveByCidade(cidade) : cidade);
		endereco.setLogradouro(cepDto.getLogradouro());
		endereco.setTipoLocal(TipoLocal.RUA);
		endereco.setBairro(bairroSet);
		cepSave.setNumero(cep.getNumero());
		cepSave.setEndereco(endereco);
		return cepSave;
	}

	public Cep saveByCep(Cep cep) {
		return repository.save(cep);
	}
	
	public void saveByCepApi(Cep cep) {
		repository.save(cep);
	}
}
