package br.com.ultracar.treinamento.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ultracar.treinamento.entidades.Cep;
import br.com.ultracar.treinamento.repositorios.customizacao.CepRepositoryCustom;

@Repository
public interface CepRepository extends JpaRepository<Cep, Long>, CepRepositoryCustom {
	
}
