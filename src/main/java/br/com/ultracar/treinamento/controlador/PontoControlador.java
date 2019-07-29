package br.com.ultracar.treinamento.controlador;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultracar.treinamento.entidades.Ponto;
import br.com.ultracar.treinamento.evento.RecursoCriadoEvent;
import br.com.ultracar.treinamento.servicos.PontoService;

@RestController
@RequestMapping("/pontos")
public class PontoControlador {
	
	@Autowired
	private PontoService pontoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveByPonto(@RequestBody Ponto ponto, HttpServletResponse response){
		Ponto pontoSave = this.pontoService.saveByPonto(ponto);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pontoSave.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(pontoSave);
	}
	
	@RequestMapping(value = "/cidade/{nomeCidade}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteByPontoForCidade(@PathVariable("nomeCidade") String nomeCidade){
		int result = this.pontoService.deleteByPontoForCidade(nomeCidade);
		return result == 1 ? ResponseEntity.status(HttpStatus.NO_CONTENT).body(result)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
