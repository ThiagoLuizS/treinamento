package br.com.ultracar.treinamento.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultracar.treinamento.entidades.Ponto;
import br.com.ultracar.treinamento.repositorios.PontoRepository;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class PontoService {
	
	@Autowired
	private PontoRepository pontoRepository;
	
	public Ponto saveByPonto(Ponto ponto) {
		return pontoRepository.save(ponto);
	}
	
	public int deleteByPontoForCidade(String nomeCidade) {
		return pontoRepository.deleteByPontoForCidade(nomeCidade);
	}
}
