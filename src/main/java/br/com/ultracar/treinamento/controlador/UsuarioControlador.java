package br.com.ultracar.treinamento.controlador;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultracar.treinamento.entidades.Usuario;
import br.com.ultracar.treinamento.entidades.enumeradores.Crud;
import br.com.ultracar.treinamento.evento.RecursoCriadoEvent;
import br.com.ultracar.treinamento.servicos.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioControlador {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@RequestMapping(value = "/pageable", method = RequestMethod.GET)
	public ResponseEntity<Page<Usuario>> findByFilter(Usuario usuario, Pageable pageable){
		Page<Usuario> usuarioPage = usuarioService.findByFilter(usuario, pageable);
		
		return ResponseEntity.status(HttpStatus.OK).body(usuarioPage);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Usuario> saveByUsuario(@Valid @RequestBody Usuario usuario, HttpServletResponse response){
		Usuario usuarioSave = this.usuarioService.saveByUsuario(usuario);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, usuario.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSave);
	}
	
	@RequestMapping(value = "/operacoes/{operacao}", method = RequestMethod.GET)
	public ResponseEntity<?> findByUsuarioOperacao(@PathVariable("operacao") Crud operacao){
		List<Usuario> usuarioOperacoes = this.usuarioService.findByUsuarioForOperacao(operacao);
		return !CollectionUtils.isEmpty(usuarioOperacoes) ? 
				ResponseEntity.status(HttpStatus.OK).body(usuarioOperacoes) :
					ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@RequestMapping(value = "/login/{login}")
	public ResponseEntity<?> findByUsuarioForLogin(@PathVariable("login") String login){
		Usuario usuario = this.usuarioService.findByUsuario(login);
		return usuario != null ?
				ResponseEntity.status(HttpStatus.OK).body(usuario) :
					ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
}
