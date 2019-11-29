package br.com.ultracar.treinamento.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ultracar.treinamento.entidades.Complemento;
import br.com.ultracar.treinamento.repositorios.customizacao.ComplementoRepositoryCustom;

@Repository
public interface ComplementoRepository extends JpaRepository<Complemento, Long>, ComplementoRepositoryCustom {

}
