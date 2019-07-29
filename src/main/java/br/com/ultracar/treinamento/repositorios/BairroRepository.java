package br.com.ultracar.treinamento.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultracar.treinamento.entidades.Bairro;
import br.com.ultracar.treinamento.entidades.Cidade;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Long>{

	@Query("Select e From Bairro e Where e.nome = :nome ")
	public Bairro findByNome(String nome);
	
	@Query("Select e From Bairro e Where e.cidade = ?1 ")
	public List<Bairro> findByCidade(Cidade cidade);

	
}
