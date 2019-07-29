package br.com.ultracar.treinamento.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultracar.treinamento.entidades.Servico;
import br.com.ultracar.treinamento.entidades.Solicitante;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long>{

	
	//Atualizar todos os servicos que tenham o solicitante 2 para 3
	@Modifying
	@Query("Update Servico s Set s.solicitante.id = ?1 Where s.solicitante.id = ?2 ")
	Integer updateAllBySolicitanteId(Long antigoSolicitante, Long novoSolicitante);
	
	@Modifying
	@Query("Update Servico s Set s.solicitante = ?1 Where s.solicitante.id = ?2 ")
	Integer updateAllBySolicitanteId(Solicitante solicitante, Long antigoSolicitante);
	
}
