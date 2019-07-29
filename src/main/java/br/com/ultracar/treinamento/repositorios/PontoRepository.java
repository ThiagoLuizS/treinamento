package br.com.ultracar.treinamento.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.ultracar.treinamento.entidades.Ponto;
/**
 * @author Thiago LS
 * */

@Repository
public interface PontoRepository extends JpaRepository<Ponto, Long>{
	/*Quando se há um formato de query incomum especificado na query nativa, é necessário atribuir a esta anotacao um atributo não default*/
	@Modifying
	@Query(nativeQuery = true, value = "Delete From Ponto ponto "
			+ 	"Inner Join ponto.endereco endereco "
			+ 	"Inner Join endereco.bairro bairro "
			+ 	"Inner Join bairro.cidade cidade "
			+ 	"Where cidade.nome = : nomeCidade")
	public int deleteByPontoForCidade(String nomeCidade);
}
