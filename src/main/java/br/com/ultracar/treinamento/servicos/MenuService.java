package br.com.ultracar.treinamento.servicos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.ultracar.treinamento.entidades.Menu;
import br.com.ultracar.treinamento.repositorios.MenuRepository;

@Service
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public class MenuService {
	
	@Autowired
	private MenuRepository menuRepository;
	
	public Menu saveByMenu(Menu menu) {
		return menuRepository.save(menu);
	}
	
	public List<Menu> findByMenuForUsuario(String login){
		return menuRepository.findByMenuForUsuario(login);
	}
}
