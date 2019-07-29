package br.com.ultracar.treinamento.repositorios;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ultracar.treinamento.entidades.GrupoAcesso;
import br.com.ultracar.treinamento.entidades.Usuario;

@Repository
public interface GrupoAcessoRepository extends JpaRepository<GrupoAcesso, Long>{
	
	Page<GrupoAcesso> findTop10ByUsuario(Usuario usuario, Pageable pageable);
	
	List<GrupoAcesso> findFirst10ByUsuario(Usuario usuario, Sort sort);
	
}
