package br.com.ultracar.treinamento.repositorios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.ultracar.treinamento.entidades.PermissaoAcesso;
import br.com.ultracar.treinamento.repositorios.customizacao.PermissaoAcessoRepositoryCustom;

@Repository
public interface PermissaoAcessoRepository extends JpaRepository<PermissaoAcesso, Long>, PermissaoAcessoRepositoryCustom{
	
}
