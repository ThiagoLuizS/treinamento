package br.com.ultracar.treinamento.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ultracar.treinamento.entidades.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{

	
}
