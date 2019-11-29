package br.com.ultracar.treinamento.controlador;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultracar.treinamento.entidades.Complemento;
import br.com.ultracar.treinamento.evento.RecursoCriadoEvent;
import br.com.ultracar.treinamento.servicos.ComplementoService;

@RestController
@RequestMapping("/api/complementos")
public class ComplementoControlador {
	
	@Autowired
	private ComplementoService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Complemento>> restFindByComplemento(Complemento complemento, Pageable pageable){
		Page<Complemento> complementoPage = service.findByComplemento(complemento, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(complementoPage);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Complemento> restSaveByComplemento(Complemento complemento, HttpServletResponse response){
		Complemento complementoSave = service.saveByComplemento(complemento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, complementoSave.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(complementoSave);
	}
}
