package br.com.ultracar.treinamento.repositorios.implementacao;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.ultracar.treinamento.entidades.Complemento;
import br.com.ultracar.treinamento.repositorios.customizacao.ComplementoRepositoryCustom;

public class ComplementoRepositoryImpl implements ComplementoRepositoryCustom {

	@PersistenceContext
	private EntityManager entity;

	@Override
	public Page<Complemento> findByComplemento(Complemento complemento, Pageable pageable) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> parameters = new HashMap<String, Object>();
		jpql.append("Select complemento From Complemento complemento Where 1=1 ");
		if(StringUtils.isNotBlank(complemento.getDescricao())) {
			jpql.append("And complemento.descricao = :descricao ");
			parameters.put("descricao", complemento.getDescricao());
		}
		if(!Objects.isNull(complemento.getNumero())) {
			jpql.append("And complemento.numero = :numero ");
			parameters.put("numero", complemento.getNumero());
		}		
		TypedQuery<Complemento> query = entity.createQuery(jpql.toString(), Complemento.class);
		 parameters.entrySet().stream().forEach(entry -> {
			 query.setParameter(entry.getKey(), entry.getValue());
		 });
		 query.setFirstResult(pageable.getPageNumber());
		 query.setMaxResults(pageable.getPageSize());
		return new PageImpl<Complemento>(query.getResultList());
	}

}
