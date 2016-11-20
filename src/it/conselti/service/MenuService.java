/*
 * Conselti s.r.l.
 */
package it.conselti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.conselti.domain.Menu;
import it.conselti.repository.MenuRepository;

/**
 * @author onofr
 *
 */

@Service
public class MenuService {
	
	private Menu menu;
	
	/**
	 * The public interface.
	 */
    @Autowired
	private MenuRepository menuRepository;
	

	/**
	 * 
	 * @param padre an integer.
	 * @return a Menu object.
	 */
	public Menu selectMenuByFather(Integer padre)
	{
		return menuRepository.findMenuByPadre(padre);
	}
    
	
	/**
	 * 
	 * @param sequenza an integer.
	 * @return a Menu object.
	 */
	public Menu selectMenuBySequence(Integer sequenza)
	{
		return menuRepository.findMenuBySequenzaOrdine(sequenza);
	}
	

	/**
	 * 
	 * @param ruolo the role.
	 * @return a list.
	 */
	public List<Menu> selectMenuByRuolo(String ruolo)
	{
		return menuRepository.findMenuByRuolo(ruolo);
	}
	

	/**
	 * 
	 * @param descrizione the description.
	 * @return a Menu object.
	 */
	public Menu selectMenuByDescription(String descrizione)
	{
		return menuRepository.findMenuByDescrizione(descrizione);
	}
	
	
	/**
	 * 
	 * @return the list.
	 */
	public List<Menu> selectAllMenuOrdered()
	{
		return menuRepository.findAllMenuOrdered();
	}
	
	
	/**
	 * 
	 * @param menu the input object.
	 * @throws Exception if fails.
	 */
    @Transactional
	public void insertMenu(Menu menu) throws Exception {
    	menuRepository.saveMenu(menu);
	}
	
   
    
    @Transactional
	public void updateMenu(Menu menu)
	{
		try {
			menuRepository.updateMenu(menu);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
   
    
    @Transactional
	public void deleteMenu(Menu menu)
	{
		try {
			menuRepository.deleteMenu(menu);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    
    
    
    
    
    
    /* Getters and Setters */
	public Menu getMenu() {
		return menu;
	}

	/**
	 * 
	 * @param menu the object.
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public void setMenuRepository(MenuRepository menuRepository) {
		this.menuRepository = menuRepository;
	}
	

}
