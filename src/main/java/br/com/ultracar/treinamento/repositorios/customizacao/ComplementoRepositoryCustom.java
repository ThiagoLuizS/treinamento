package br.com.ultracar.treinamento.repositorios.customizacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ultracar.treinamento.entidades.Complemento;

public interface ComplementoRepositoryCustom {
	Page<Complemento> findByComplemento(Complemento complemento, Pageable pageable);
}
