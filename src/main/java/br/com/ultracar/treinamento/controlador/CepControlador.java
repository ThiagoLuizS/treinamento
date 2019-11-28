package br.com.ultracar.treinamento.controlador;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ultracar.treinamento.entidades.Cep;
import br.com.ultracar.treinamento.servicos.CepService;

@RestController
@RequestMapping("/api/cep")
public class CepControlador {
	
	@Autowired
	private CepService service;
	
	@GetMapping
	public ResponseEntity<Page<Cep>> restByCep(Cep cep, Pageable pageable){
		Page<Cep> cepPage = service.findByCep(cep, pageable);
		return ResponseEntity.status(HttpStatus.OK).body(cepPage);
	}
}
