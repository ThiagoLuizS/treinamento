package br.com.ultracar.treinamento.controlador;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultracar.treinamento.entidades.Menu;
import br.com.ultracar.treinamento.evento.RecursoCriadoEvent;
import br.com.ultracar.treinamento.servicos.MenuService;

@RestController
@RequestMapping("/menus")
public class MenuControlador {
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveByMenu(@RequestBody Menu menu, HttpServletResponse response){
		Menu menuSave = this.menuService.saveByMenu(menu);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, menuSave.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(menuSave);
	}
	
	@RequestMapping(value = "/usuario/{login}", method = RequestMethod.GET)
	public ResponseEntity<List<Menu>> findByMenuForUsuario(@PathVariable("login") String login){
		List<Menu> menu = this.menuService.findByMenuForUsuario(login);
		
		return !CollectionUtils.isEmpty(menu) ? ResponseEntity.status(HttpStatus.OK).body(menu) 
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
