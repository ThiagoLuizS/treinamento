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

import br.com.ultracar.treinamento.entidades.Estado;
import br.com.ultracar.treinamento.evento.RecursoCriadoEvent;
import br.com.ultracar.treinamento.servicos.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoControlador {
	
	@Autowired
	private EstadoService estadoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveByEstado(@RequestBody Estado estado, HttpServletResponse response){
		Estado estadoSave = this.estadoService.saveByEstado(estado);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, estadoSave.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(estadoSave);
	}
}
