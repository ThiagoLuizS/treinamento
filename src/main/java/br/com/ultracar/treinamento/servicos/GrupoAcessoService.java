package br.com.ultracar.treinamento.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultracar.treinamento.entidades.GrupoAcesso;
import br.com.ultracar.treinamento.repositorios.GrupoAcessoRepository;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class GrupoAcessoService {
	
	@Autowired
	private GrupoAcessoRepository acessoRepository;

	public GrupoAcesso saveByGrupoAcesso(GrupoAcesso grupoAcesso) {
		return this.acessoRepository.save(grupoAcesso);
	}
}
