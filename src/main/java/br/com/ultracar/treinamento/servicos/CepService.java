package br.com.ultracar.treinamento.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultracar.treinamento.entidades.Cep;
import br.com.ultracar.treinamento.repositorios.CepRepository;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class CepService {
	
	@Autowired
	private CepRepository repository;
	
	public Page<Cep> findByCep(Cep cep, Pageable pageable){
		return repository.findByCep(cep, pageable);
	}
}
