package br.com.ultracar.treinamento.repositorios;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;
import br.com.ultracar.treinamento.entidades.Usuario;
import br.com.ultracar.treinamento.entidades.enumeradores.Crud;
import br.com.ultracar.treinamento.repositorios.customizacao.UsuarioRepositoryCustom;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>, UsuarioRepositoryCustom{
	
	@Query("Select u From Usuario u Where u.senha = :senha ")
	public Optional<Usuario> findBySenha(String senha);
	
	@Query(		"Select u From Usuario u "
			+ 	"Inner Join u.gruposDeAcesso ga "
			+ 	"Inner Join ga.permissoesDeAcesso pa "
			+ 	"Inner Join pa.operacoes op	"
			+ 	"Where op.operacao = :operacao ")	
	public List<Usuario> findByUsuarioForOperacao(Crud operacao);
	
	@Nullable
	@Query(  	"Select usuario From Usuario usuario "
			+ 	"Where usuario.login = :login")	
	public Usuario findByUsuario(String login);

}
