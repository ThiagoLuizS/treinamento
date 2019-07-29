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

import br.com.ultracar.treinamento.entidades.GrupoAcesso;
import br.com.ultracar.treinamento.evento.RecursoCriadoEvent;
import br.com.ultracar.treinamento.servicos.GrupoAcessoService;

@RestController
@RequestMapping("/grupo-acesso")
public class GrupoAcessoControlador {
	
	@Autowired
	private GrupoAcessoService acessoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveByGrupoAcesso(@Valid @RequestBody GrupoAcesso grupoAcesso, HttpServletResponse response){
		GrupoAcesso acesso = this.acessoService.saveByGrupoAcesso(grupoAcesso);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, grupoAcesso.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(acesso);
	}
}
