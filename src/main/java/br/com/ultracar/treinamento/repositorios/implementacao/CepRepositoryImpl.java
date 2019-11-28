package br.com.ultracar.treinamento.repositorios.implementacao;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.ultracar.treinamento.entidades.Cep;
import br.com.ultracar.treinamento.repositorios.customizacao.CepRepositoryCustom;

public class CepRepositoryImpl implements CepRepositoryCustom {

	@PersistenceContext
	private EntityManager entity;
	
	public Page<Cep> findByCep(Cep cep, Pageable pageable) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> parameters = new HashMap<>();
		jpql.append("Select c From Cep c ");
		
		if(Objects.isNull(cep.getNumero())) {
			jpql.append("Where c.numero = :numero ");
			parameters.put("numero", cep.getNumero());
		}
		
		TypedQuery<Cep> query = entity.createQuery(jpql.toString(), Cep.class);
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		query.setFirstResult(pageable.getPageNumber());
		query.setMaxResults(pageable.getPageSize());
		
		return new PageImpl<Cep>(query.getResultList());
	}

}
