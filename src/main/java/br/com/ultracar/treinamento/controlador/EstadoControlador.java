package br.com.ultracar.treinamento.controlador;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultracar.treinamento.entidades.Estado;
import br.com.ultracar.treinamento.evento.RecursoCriadoEvent;
import br.com.ultracar.treinamento.servicos.EstadoService;

@RestController
@RequestMapping("/api/estados")
public class EstadoControlador {
	
	@Autowired
	private EstadoService estadoService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@PostMapping
	public ResponseEntity<?> saveByEstado(@RequestBody Estado estado, HttpServletResponse response){
		Estado estadoSave = this.estadoService.saveByEstado(estado);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, estadoSave.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(estadoSave);
	}
	
	@GetMapping
	public ResponseEntity<List<Estado>> restByAll(){
		List<Estado> estado = estadoService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(estado);
	}
}
