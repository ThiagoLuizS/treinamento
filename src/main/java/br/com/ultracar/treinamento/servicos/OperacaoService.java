package br.com.ultracar.treinamento.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultracar.treinamento.entidades.Operacao;
import br.com.ultracar.treinamento.repositorios.OperacaoRepository;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class OperacaoService {
	
	@Autowired
	private OperacaoRepository operacaoRepository;
	
	public Operacao saveByOperacao(Operacao operacao) {
		return operacaoRepository.save(operacao);
	}
}
