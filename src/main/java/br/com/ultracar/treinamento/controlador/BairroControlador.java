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

import br.com.ultracar.treinamento.entidades.Bairro;
import br.com.ultracar.treinamento.evento.RecursoCriadoEvent;
import br.com.ultracar.treinamento.servicos.BairroService;

@RestController
@RequestMapping("/bairros")
public class BairroControlador {
	
	@Autowired
	private BairroService bairroService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveByBairro(@RequestBody Bairro bairro, HttpServletResponse response){
		Bairro bairroSave = this.bairroService.saveByBairro(bairro);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, bairroSave.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(bairroSave);
	}
}
