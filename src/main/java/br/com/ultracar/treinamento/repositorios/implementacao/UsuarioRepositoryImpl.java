package br.com.ultracar.treinamento.repositorios.implementacao;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.ultracar.treinamento.entidades.Usuario;
import br.com.ultracar.treinamento.repositorios.customizacao.UsuarioRepositoryCustom;


public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom{

	@PersistenceContext
	private EntityManager em;
	
	public Page<Usuario> findByFilter(Usuario usuario, Pageable pageable) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> parameters = new HashMap<>();
		jpql.append("Select usuario From Usuario usuario ");
		
		if(StringUtils.isNotBlank(usuario.getLogin())) {
			jpql.append("Where usuario.login = :login ");
			parameters.put("login", usuario.getLogin());
		}
		
		TypedQuery<Usuario> query = this.em.createQuery(jpql.toString(), Usuario.class);
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		query.setFirstResult(pageable.getPageNumber());
		query.setMaxResults(pageable.getPageSize());
		
		
		return new PageImpl<Usuario>(query.getResultList());
	}

	@Override
	public Optional<Usuario> findByUsuarioForLogin(String login) {
		String jpql = "Select u From u"
				+ "	Where u.login = :login";
		
		Query query = em.createQuery(jpql);
		query.setParameter("login", login);
		Usuario usuario = (Usuario) query.getSingleResult();
		return usuario == null ? Optional.empty() : Optional.of(usuario);
	}

}
