package br.com.ultracar.treinamento.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultracar.treinamento.entidades.PermissaoAcesso;
import br.com.ultracar.treinamento.repositorios.PermissaoAcessoRepository;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class PermissaoAcessoService {
	
	@Autowired
	private PermissaoAcessoRepository acessoRepository;
	
	public PermissaoAcesso saveByPermissaoAcesso(PermissaoAcesso acesso) {
		return this.acessoRepository.save(acesso);
	}
	
}
