package br.com.ultracar.treinamento.repositorios.customizacao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ultracar.treinamento.entidades.Endereco;

public interface EnderecoRepositoryCustom {
	Page<Endereco> findByEndereco(Endereco endereco, Pageable pageable);
}
