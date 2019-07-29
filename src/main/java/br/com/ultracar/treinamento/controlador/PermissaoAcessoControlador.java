package br.com.ultracar.treinamento.controlador;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultracar.treinamento.entidades.PermissaoAcesso;
import br.com.ultracar.treinamento.evento.RecursoCriadoEvent;
import br.com.ultracar.treinamento.servicos.PermissaoAcessoService;

@RestController
@RequestMapping("/permissao-acesso")
public class PermissaoAcessoControlador {
	
	@Autowired
	private PermissaoAcessoService acessoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveByPermissaoAcesso(@Valid @RequestBody PermissaoAcesso acesso, HttpServletResponse response){
		PermissaoAcesso permissaoAcesso = this.acessoService.saveByPermissaoAcesso(acesso);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, acesso.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(permissaoAcesso);
	}
}
