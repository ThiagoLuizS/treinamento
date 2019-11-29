package br.com.ultracar.treinamento.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultracar.treinamento.entidades.Complemento;
import br.com.ultracar.treinamento.repositorios.ComplementoRepository;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class ComplementoService {
	
	@Autowired
	private ComplementoRepository repository;
	
	public Page<Complemento> findByComplemento(Complemento complemento, Pageable pageable){
		return repository.findByComplemento(complemento, pageable); 
	}
	
	public Complemento saveByComplemento(Complemento complemento) {
		return repository.save(complemento);
	}
}
