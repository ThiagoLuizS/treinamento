package br.com.ultracar.treinamento.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import br.com.ultracar.treinamento.entidades.Usuario;
import br.com.ultracar.treinamento.entidades.enumeradores.Crud;
import br.com.ultracar.treinamento.repositorios.UsuarioRepository;
import br.com.ultracar.treinamento.utils.GeradorSenhaCript;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Page<Usuario> findByFilter(Usuario usuario, Pageable pageable){
		return usuarioRepository.findByFilter(usuario, pageable);
	}
	
	@SuppressWarnings("static-access")
	public Usuario saveByUsuario(Usuario usuario) {
		String senhaCriptografada = new GeradorSenhaCript().gerarSenha(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		return usuarioRepository.save(usuario);
	}
	
	public List<Usuario> findByUsuarioForOperacao(Crud operacao){
		return usuarioRepository.findByUsuarioForOperacao(operacao);
	}
	
	public Usuario findByUsuario(String login) {
		return usuarioRepository.findByUsuario(login);
	}
}
