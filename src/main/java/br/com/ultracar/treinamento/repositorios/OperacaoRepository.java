package br.com.ultracar.treinamento.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import br.com.ultracar.treinamento.entidades.Operacao;

@Repository
public interface OperacaoRepository extends JpaRepository<Operacao, Long>{
	
	@Nullable
	@Query("Select e From Operacao e Where e.descricao = :descricao ")
	public Operacao findByDescricao(String descricao);
	
	@Modifying
	@Query("Delete From Operacao e Where e.descricao = :descricao ")
	public Integer deleteByDescricao(String descricao);
	
	@Modifying
	@Query("Update Operacao Set descricao = :descricao Where id = :id ")
	public Integer updateDescricaoById(String descricao, Long i);
	
}
