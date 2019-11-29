package br.com.ultracar.treinamento.repositorios.implementacao;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.ultracar.treinamento.entidades.Bairro;
import br.com.ultracar.treinamento.repositorios.customizacao.BairroRepositoryCustom;

public class BairroRepositoryImpl implements BairroRepositoryCustom {

	@PersistenceContext
	private EntityManager entity;
	
	@Override
	public Page<Bairro> findByBairro(Bairro bairro, Pageable pageable) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> parameters = new HashMap<String, Object>();
		jpql.append("Select bairro From Bairro bairro Where 1=1 ");
		if(StringUtils.isNotBlank(bairro.getNome())) {
			jpql.append("And bairro.nome = :nome");
			parameters.put("nome", bairro.getNome());
		}
		TypedQuery<Bairro> query = entity.createQuery(jpql.toString(), Bairro.class);
		parameters.entrySet().stream().forEach(entry -> {
			query.setParameter(entry.getKey(), entry.getValue());
		});
		query.setFirstResult(pageable.getPageNumber());
		query.setMaxResults(pageable.getPageSize());
		return new PageImpl<Bairro>(query.getResultList());
	}

}
