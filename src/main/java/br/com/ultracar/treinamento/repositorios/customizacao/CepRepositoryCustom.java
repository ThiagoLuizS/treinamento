package br.com.ultracar.treinamento.repositorios.customizacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ultracar.treinamento.entidades.Cep;

public interface CepRepositoryCustom {
	Page<Cep> findByCep(Cep cep, Pageable pageable);
}
