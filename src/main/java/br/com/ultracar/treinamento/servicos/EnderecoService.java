package br.com.ultracar.treinamento.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultracar.treinamento.entidades.Endereco;
import br.com.ultracar.treinamento.repositorios.EnderecoRepository;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repository;

	public Page<Endereco> findByEndereco(Endereco endereco, Pageable pageable){
		return repository.findByEndereco(endereco, pageable);
	}
	
	public Endereco saveByEndereco(Endereco endereco) {
		return repository.save(endereco);
	}
}
