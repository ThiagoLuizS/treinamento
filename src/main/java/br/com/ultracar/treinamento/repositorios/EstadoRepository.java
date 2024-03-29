package br.com.ultracar.treinamento.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultracar.treinamento.entidades.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long>{
	
	public List<Estado> findAll();
	
	@Query("Select estado From Estado estado Where estado.sigla = :uf ")
	public Estado findByEstadoForUf(String uf);
}
