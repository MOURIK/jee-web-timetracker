/*
 * Conselti s.r.l.
 */
package it.conselti.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.component.menuitem.MenuItem;
import org.primefaces.component.submenu.Submenu;
import org.primefaces.model.DefaultMenuModel;
import org.primefaces.model.MenuModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;


import it.conselti.domain.Menu;
import it.conselti.service.MenuService;




/**
 * @author onofr
 *
 */


@Component(value="menubarBean")
@Scope(value="view")
public class MenubarBean extends AbstractManagedBean implements Serializable {


	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 3509324422703044609L;



	@Autowired
	private MenuService menuService;
	
	@Autowired
	private HeaderBean headerBean;
	
	@Autowired
	private LoginBean loginBean;


	
	private List<Menu> listMenu;
	private List<Menu> listPadri;
	private Integer codiceUtenteMenu;
	private String ruoloUtenteMenu;


	
    /**
     * The public method init.
     * @throws Exception
     */
	@PostConstruct
	 public void init() throws Exception {
		 injectBean();
	}
	 
	 
	
	
	 /**
	  * 
	  */
	 protected void injectBean() {
			try {
				ruoloUtenteMenu = loginBean.getRuoloUtente();
				if(this.codiceUtenteMenu != null && this.ruoloUtenteMenu != null) {
					logger.info("Bean injection MenubarBean.java [" + this.loginBean.getCodiceUtente() + ", " + this.loginBean.getRuoloUtente() + "]");
				}

			} catch (final NullPointerException e) {
				e.printStackTrace();
			}
	    }
	 
	 
	protected void refreshList()
	{
		listMenu = new ArrayList<Menu>();
		listPadri = new ArrayList<Menu>();
	}
	
	

	 
	/**
	 * 
	 * @return the MenuModel model.
	 * @throws Exception
	 */
    @PreAuthorize("isAuthenticated()")
 	public MenuModel getModel() throws Exception {

		MenuModel model = new DefaultMenuModel();

		refreshList();
		
		MenuItem item;
		Submenu submenu;

		
		listMenu = menuService.selectMenuByRuolo(ruoloUtenteMenu);
		
		for(Menu elem : listMenu)
		{
			if(elem.getPadre() == null)
			{
				listPadri.add(elem);
			}
		}
		
		try {
			for(Menu padre : listPadri)
			{
				submenu = new Submenu();
				submenu.setLabel(padre.getDescrizione());
				
				model.addSubmenu(submenu);


				for(Menu menu : listMenu)
				{
					
					if(menu.getPadre() != null && padre.getId().intValue() == menu.getPadre().intValue())
					{
						item = new MenuItem();
						item.setValue(menu.getDescrizione());
						item.setIcon(menu.getIcona());
						item.setOutcome(menu.getUrl());
						
						submenu.getChildren().add(item);
					}
				}	
			}
			return model;	
			
		} catch(final NullPointerException e) {
			logger.error("Error menubar ", e);
		}
		return null;
 	}

    
    
    
    
    
    /* Getters and Setters */
    /**
     * 
     * @param menuService the service.
     */
	public void setMenuService(MenuService menuService) {
		this.menuService = menuService;
	}


	public List<Menu> getListMenu() {
		return listMenu;
	}


	public void setListMenu(List<Menu> listMenu) {
		this.listMenu = listMenu;
	}

    
	/**
	 * 
	 * @return the List listPadri.
	 */
	public List<Menu> getListPadri() {
		return listPadri;
	}


	public void setListPadri(List<Menu> listPadri) {
		this.listPadri = listPadri;
	}

	public HeaderBean getHeaderBean() {
		return headerBean;
	}


	public void setHeaderBean(HeaderBean headerBean) {
		this.headerBean = headerBean;
	}
	
	 public LoginBean getLoginBean() {
		return loginBean;
	}


	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

    
	/**
	 * 
	 * @return the Integer codiceUtenteMenu.
	 */
	public Integer getCodiceUtenteRegistr() {
		return codiceUtenteMenu;
	}

    
	/**
	 * 
	 * @param codiceUtenteRegistr Integer variable.
	 */
	public void setCodiceUtenteRegistr(Integer codiceUtenteRegistr) {
		this.codiceUtenteMenu = codiceUtenteRegistr;
	}

 
	public String getRuoloUtenteRegistr() {
		return ruoloUtenteMenu;
	}

 
	/**
	 * 
	 * @param ruoloUtenteRegistr the user role.
	 */
	public void setRuoloUtenteRegistr(String ruoloUtenteRegistr) {
		this.ruoloUtenteMenu = ruoloUtenteRegistr;
	}

    /**
     * 
     * @return the service.
     */
	public MenuService getMenuService() {
		return menuService;
	}


    /**
     * 
     * @return the codiceUtenteMenu.
     */
	public Integer getCodiceUtenteMenu() {
		return codiceUtenteMenu;
	}


    /**
     * 
     * @param codiceUtenteMenu
     */
	public void setCodiceUtenteMenu(Integer codiceUtenteMenu) {
		this.codiceUtenteMenu = codiceUtenteMenu;
	}


	public String getRuoloUtenteMenu() {
		return ruoloUtenteMenu;
	}


    /**
     * 
     * @param ruoloUtenteMenu the user role.
     */
	public void setRuoloUtenteMenu(String ruoloUtenteMenu) {
		this.ruoloUtenteMenu = ruoloUtenteMenu;
	}


}
