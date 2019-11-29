package br.com.ultracar.treinamento.repositorios.implementacao;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.ultracar.treinamento.entidades.Cidade;
import br.com.ultracar.treinamento.repositorios.customizacao.CidadeRepositoryCustom;

public class CidadeRepositoryImpl implements CidadeRepositoryCustom {

	@PersistenceContext
	private EntityManager entity;
	
	@Override
	public Page<Cidade> findByCidade(Cidade cidade, Pageable pageable) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> parameters = new HashMap<>();
		jpql.append("Select cidade From Cidade cidade Where 1=1 ");
		if(Objects.isNull(cidade.getNome())) {
			jpql.append("And cidade.nome Like %:nome% ");
			parameters.put("nome", cidade.getNome());
		}
		TypedQuery<Cidade> query = entity.createQuery(jpql.toString(), Cidade.class);
		parameters.entrySet().stream().forEach(entry -> {
			query.setParameter(entry.getKey(), entry.getValue());
		});
		query.setFirstResult(pageable.getPageNumber());
		query.setMaxResults(pageable.getPageSize());
		return new PageImpl<Cidade>(query.getResultList());
	}
	
	
}
