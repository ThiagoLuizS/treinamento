package br.com.ultracar.treinamento.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultracar.treinamento.entidades.Bairro;
import br.com.ultracar.treinamento.entidades.Cidade;
import br.com.ultracar.treinamento.repositorios.BairroRepository;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class BairroService {
	
	@Autowired
	private BairroRepository bairroRepository;
	
	public Bairro saveByBairro(Bairro bairro) {
		return bairroRepository.save(bairro);
	}
	
	public Bairro findByNome(String nome){
		return bairroRepository.findByNome(nome);
	}
	
	public List<Bairro> findByCidade(Cidade cidade){
		return bairroRepository.findByCidade(cidade);
	}
}
