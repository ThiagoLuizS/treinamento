package br.com.ultracar.treinamento.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ultracar.treinamento.entidades.Endereco;

@Repository
public interface EnderecoRepository  extends JpaRepository<Endereco, Long>{
	
	
	
}
