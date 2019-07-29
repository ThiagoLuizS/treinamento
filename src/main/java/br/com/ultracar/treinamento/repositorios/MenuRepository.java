package br.com.ultracar.treinamento.repositorios;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import br.com.ultracar.treinamento.entidades.Menu;
import br.com.ultracar.treinamento.entidades.PermissaoAcesso;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>{

	@Query("Select e From Menu e Where e.menu = :menu Order by e.descricao Asc ")
	public Menu findByMenuOrderByDescricaoAsc(@Nullable Menu menu);
	
	@Query("Select e From Menu e Where e.permissaoAcesso = :permissaoAcesso Order by e.descricao Desc ")
	public Stream<Menu> findByPermissaoDeAcessoOrderByDescricaoDesc(@Nullable PermissaoAcesso permissaoAcesso);
	
	@Query(		"Select m From Menu m "
			+ 	"Inner Join m.permissaoAcesso pa "
			+ 	"Inner Join pa.grupoAcesso ga "
			+ 	"Inner Join ga.usuario u "
			+ 	"Where u.login = :login ")	
	public List<Menu> findByMenuForUsuario(String login);
	
}
