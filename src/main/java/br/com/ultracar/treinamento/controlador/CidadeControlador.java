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

import br.com.ultracar.treinamento.entidades.Cidade;
import br.com.ultracar.treinamento.evento.RecursoCriadoEvent;
import br.com.ultracar.treinamento.servicos.CidadeService;

@RestController
@RequestMapping("/api/cidades")
public class CidadeControlador {
	
	@Autowired
	private CidadeService cidadeService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> saveByCidade(@RequestBody Cidade cidade, HttpServletResponse response){
		Cidade cidadeSave = this.cidadeService.saveByCidade(cidade);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, cidadeSave.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(cidadeSave);
	}
	
	@RequestMapping(value = "/nome/{nome}", method = RequestMethod.GET)
	public ResponseEntity<List<Cidade>> findByCidadeForNome(@PathVariable("nome") String nome){
		List<Cidade> cidade = this.cidadeService.findByCidadeForNome(nome);
		
		return !CollectionUtils.isEmpty(cidade) ? ResponseEntity.status(HttpStatus.OK).body(cidade)
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
