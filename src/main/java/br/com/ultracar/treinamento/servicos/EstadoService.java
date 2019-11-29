package br.com.ultracar.treinamento.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultracar.treinamento.entidades.Estado;
import br.com.ultracar.treinamento.repositorios.EstadoRepository;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Estado saveByEstado(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	public List<Estado> findAll(){
		return estadoRepository.findAll();
	}
	
	public Estado findByEstadoForUf(String uf) {
		return estadoRepository.findByEstadoForUf(uf);
	}

}
