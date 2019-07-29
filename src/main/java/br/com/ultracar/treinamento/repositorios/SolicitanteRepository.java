package br.com.ultracar.treinamento.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ultracar.treinamento.entidades.Solicitante;

@Repository
public interface SolicitanteRepository extends JpaRepository<Solicitante, Long>{
	
}
