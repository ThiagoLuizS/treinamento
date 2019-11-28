package br.com.ultracar.treinamento.repositorios.customizacao;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.ultracar.treinamento.entidades.Usuario;

public interface UsuarioRepositoryCustom {
	Page<Usuario> findByFilter(Usuario usuario, Pageable pageable);
	Optional<Usuario> findByUsuarioForLogin(String login);
}
