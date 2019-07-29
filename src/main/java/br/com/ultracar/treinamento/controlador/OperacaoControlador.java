package br.com.ultracar.treinamento.controlador;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultracar.treinamento.entidades.Operacao;
import br.com.ultracar.treinamento.evento.RecursoCriadoEvent;
import br.com.ultracar.treinamento.servicos.OperacaoService;

@RestController
@RequestMapping("/operacoes")
public class OperacaoControlador {

	@Autowired
	private OperacaoService operacaoService;

	@Autowired
	private ApplicationEventPublisher publisher;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Operacao> saveByOperacao(@RequestBody Operacao operacao, HttpServletResponse response){
		Operacao operacaoSave = this.operacaoService.saveByOperacao(operacao);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, operacaoSave.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(operacaoSave);
	}
}
