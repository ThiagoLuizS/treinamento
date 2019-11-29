package br.com.ultracar.treinamento.repositorios.implementacao;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.ultracar.treinamento.entidades.Endereco;
import br.com.ultracar.treinamento.repositorios.customizacao.EnderecoRepositoryCustom;

public class EnderecoRepositoryImpl implements EnderecoRepositoryCustom {
	
	@PersistenceContext
	private EntityManager entity;
	
	@Override
	public Page<Endereco> findByEndereco(Endereco endereco, Pageable pageable) {
		StringBuilder jpql = new StringBuilder();
		Map<String, Object> parameters = new HashMap<>();
		jpql.append("Select endereco From Endereco endereco Where 1=1 ");
		
		if(StringUtils.isNotBlank(endereco.getLogradouro())) {
			jpql.append("And endereco.logradouro = :logradouro ");
			parameters.put("logradouro", endereco.getLogradouro());
		}
		if(!Objects.isNull(endereco.getTipoLocal())) {
			jpql.append("And endereco.tipoLocal = :name ");
			parameters.put("name", endereco.getTipoLocal());
		}
		
		TypedQuery<Endereco> query = entity.createQuery(jpql.toString(), Endereco.class);
		for (Entry<String, Object> entry : parameters.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		
		query.setFirstResult(pageable.getPageNumber());
		query.setMaxResults(pageable.getPageSize());
		
		return new PageImpl<Endereco>(query.getResultList());
	}

}
