package br.com.ultracar.treinamento.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultracar.treinamento.entidades.Bairro;
import br.com.ultracar.treinamento.repositorios.BairroRepository;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class BairroService {
	
	@Autowired
	private BairroRepository bairroRepository;
	
	public Bairro saveByBairro(Bairro bairro) {
		return bairroRepository.save(bairro);
	}
}
