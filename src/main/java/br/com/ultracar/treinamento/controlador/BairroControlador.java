package br.com.ultracar.treinamento.controlador;

import java.util.List;

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

import br.com.ultracar.treinamento.entidades.Bairro;
import br.com.ultracar.treinamento.entidades.Cidade;
import br.com.ultracar.treinamento.evento.RecursoCriadoEvent;
import br.com.ultracar.treinamento.servicos.BairroService;

@RestController
@RequestMapping("/api/bairros")
public class BairroControlador {
	
	@Autowired
	private BairroService bairroService;

	@Autowired
	private ApplicationEventPublisher publisher;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveByBairro(@RequestBody Bairro bairro, HttpServletResponse response){
		Bairro bairroSave = bairroService.saveByBairro(bairro);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, bairroSave.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(bairroSave);
	}
	
	@RequestMapping(value = "/nome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<?> restFindByNome(@PathVariable("nome") String nome){
		Bairro bairro = bairroService.findByNome(nome);
		return ResponseEntity.status(HttpStatus.OK).body(bairro);
	}
	
	@RequestMapping(value = "/cidade/{cidade}", method = RequestMethod.GET)
	public ResponseEntity<List<?>> restFindByCidade(@PathVariable("cidade") Cidade cidade){
		List<Bairro> bairro = bairroService.findByCidade(cidade);
		return ResponseEntity.status(HttpStatus.OK).body(bairro);
	}
}
