package br.com.ultracar.treinamento.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultracar.treinamento.entidades.Cidade;
import br.com.ultracar.treinamento.repositorios.CidadeRepository;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, noRollbackFor = Exception.class)
public class CidadeService {
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	public Cidade saveByCidade(Cidade cidade) {
		return cidadeRepository.save(cidade);
	}
	
	public List<Cidade> findByCidadeForNome(String nome){
		return cidadeRepository.findByCidadeForNome(nome);
	}
}
