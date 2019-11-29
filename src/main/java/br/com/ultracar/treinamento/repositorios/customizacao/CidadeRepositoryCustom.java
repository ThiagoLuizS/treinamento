package br.com.ultracar.treinamento.repositorios.customizacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ultracar.treinamento.entidades.Cidade;

public interface CidadeRepositoryCustom {
	Page<Cidade> findByCidade(Cidade cidade, Pageable pageable);
}
