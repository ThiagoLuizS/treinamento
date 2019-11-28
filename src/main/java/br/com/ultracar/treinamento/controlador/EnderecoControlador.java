package br.com.ultracar.treinamento.controlador;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultracar.treinamento.entidades.Endereco;
import br.com.ultracar.treinamento.evento.RecursoCriadoEvent;
import br.com.ultracar.treinamento.servicos.EnderecoService;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoControlador {
	
	@Autowired
	private EnderecoService service;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Endereco>> restFindByEndereco(Endereco endereco, Pageable pageable){
		Page<Endereco> enderecoPage = service.findByEndereco(endereco, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(enderecoPage);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveByBairro(@RequestBody Endereco endereco, HttpServletResponse response){
		Endereco EnderecoSave = service.saveByEndereco(endereco);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, endereco.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(EnderecoSave);
	}
}
