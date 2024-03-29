package br.com.ultracar.treinamento.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultracar.treinamento.entidades.Bairro;
import br.com.ultracar.treinamento.entidades.Cidade;
import br.com.ultracar.treinamento.repositorios.customizacao.CidadeRepositoryCustom;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long>, CidadeRepositoryCustom {

	@Query("Select e From Cidade e Where e.nome Like %?1% ")
	public List<Cidade> findByCidadeForNome(String nome);
	
	@Query("Select e From Cidade e Where e.nome = :nome ")
	public Cidade findByCidadeForNomeUnique(String nome);
	
	@Query("Select e From Bairro e Where e.cidade = :cidade ")
	public List<Bairro> findByCidade(Cidade cidade);
	
}
