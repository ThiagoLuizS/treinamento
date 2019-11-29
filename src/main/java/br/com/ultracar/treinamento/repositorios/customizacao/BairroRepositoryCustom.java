package br.com.ultracar.treinamento.repositorios.customizacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ultracar.treinamento.entidades.Bairro;

public interface BairroRepositoryCustom {
	Page<Bairro> findByBairro(Bairro bairro, Pageable pageable);
}
