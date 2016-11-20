/*
 * Conselti s.r.l.
 */
package it.conselti.managedBean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import it.conselti.domain.Registrazione;
import it.conselti.domain.VisualizzaRegistrazione;
import it.conselti.service.RegistrazioneService;
import it.conselti.service.VisualizzaRegistrazioneService;


/**
 * @author onofr
 *
 */

@Component(value="visualizzaRegistrazioneBean")
@Scope(value="view")
public class VisualizzaRegistrazioneBean extends AbstractManagedBean implements Serializable {

	
	/**
	 *
	 */
	private static final long serialVersionUID = 7564889773466110630L;


	@Autowired
	private VisualizzaRegistrazioneService visualizzaService;
	
	@Autowired
	private RegistrazioneService registrazioneService;

	@Autowired
	private LoginBean loginBean;


	
	
	private VisualizzaRegistrazione selectedVisualizza;
	private VisualizzaRegistrazione visualizza;
	private List<VisualizzaRegistrazione> visualizzazioni;
	private List<VisualizzaRegistrazione> visualizzazioniPerPeriodo;
	private List<VisualizzaRegistrazione> filteredVisualizza;
	private List<Registrazione> dialogRegistrazioni;
	private List<Registrazione> filteredDialogRegistrazione;

	

	/**
	 * 
	 */
	private Integer codiceUtenteVisualiz;
	private String ruoloUtenteVisualiz;
	private Integer footerTotaleLavorate;
	private Integer footerTotalePermesso;
	private Integer footerTotaleMalattia;
	private Integer footerTotaleFerie;
	private Integer footerTotaleTrasferta;
	private Integer footerTotaleTotali;




	
	


	@PostConstruct
	public void init() {
		refreshList();
		injectBean();
		
        GregorianCalendar date = new GregorianCalendar();      
        Integer year = date.get(Calendar.YEAR);
        visualizza.setAnno(year);
        Integer month = date.get(Calendar.MONTH);
        visualizza.setMese(month + 1);
        
        
        /* PostConstruct */
        if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ROLE_ADMIN") || 
				FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ROLE_SUPERUSER")) {
        	visualizzazioni.addAll(visualizzaService.selectAllRegistrazioneForAdminsAndSuperusers());
        	visualizzazioniPerPeriodo.addAll(visualizzaService.selectAllRegistrazionePerPeriodo(visualizza));
        	setTableFooterValues(visualizzazioni);
        	
		} else if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ROLE_USER")) {
			visualizzazioni.addAll(visualizzaService.selectAllRegistrazioneForUsers(this.codiceUtenteVisualiz));
			setTableFooterValues(visualizzazioni);

		}
	}
	
	
	/**
	 * The protected method injectBean.
	 */
	protected void injectBean() {
		try {
			codiceUtenteVisualiz = loginBean.getCodiceUtente();
			ruoloUtenteVisualiz = loginBean.getRuoloUtente();
			if(this.codiceUtenteVisualiz != null && this.ruoloUtenteVisualiz != null) {
				logger.info("Bean injection: VisualizzaRegistrazioneBean.java [" + this.loginBean.getCodiceUtente() + ", " + this.loginBean.getRuoloUtente() + "]");
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	


	
	
    @RolesAllowed({"ROLE_USER", "ROLE_ADMIN", "ROLE_SUPERUSER"})
	public void ricercaTabella()
	{
    	visualizzazioni = new ArrayList<VisualizzaRegistrazione>();
		if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ROLE_ADMIN") || 
				FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ROLE_SUPERUSER")) 
		{
			try {
				populateRegistrazAdmin(visualizzazioni);
				//refreshList();
				
				notificationSuccess("selezione admin");
			} catch (Exception e) {
				notificationError(e, "selezione admin");
				e.printStackTrace();
			}
			
		} else if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ROLE_USER")) {
			try {
				populateRegistrazUser(visualizzazioni);
				//refreshList();
				
				notificationSuccess("selezione user");
			} catch (Exception e) {
				notificationError(e, "selezione user");
				e.printStackTrace();
			}
		}
	}
    
    
    public static String customFormatDate(Object date) {
 	   if (date != null) {
 	       DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
 	       return format.format(date);
 	    }
 	   return "";
 	}
    
    
    
    
    	
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPERUSER', 'ROLE_USER')")
	public boolean populateDialogRegistrazioni() throws Exception {
		dialogRegistrazioni = new ArrayList<Registrazione>();
		filteredDialogRegistrazione = new ArrayList<Registrazione>();
		
		if (visualizza.getDateFrom() != null && visualizza.getDateTo() != null) {
			dialogRegistrazioni.addAll(registrazioneService.selectAllRegistrazioneByFromAndTo(selectedVisualizza.getCodiceUtente(), visualizza));
			filteredDialogRegistrazione.addAll(dialogRegistrazioni);
		} else {
			dialogRegistrazioni.addAll(registrazioneService.selectRegistrazioneByUserCode(selectedVisualizza.getCodiceUtente()));
			filteredDialogRegistrazione.addAll(dialogRegistrazioni);
		}
		
		if(dialogRegistrazioni == null) {
			return false;
		} 
		return true;
	}
    
    
    
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	public boolean populateDialogRegistrazioniByPeriod() throws Exception {
    	dialogRegistrazioni = new ArrayList<Registrazione>();
		filteredDialogRegistrazione = new ArrayList<Registrazione>();
		
    	if (visualizza.getMese() != null && visualizza.getAnno() != null) {
     		dialogRegistrazioni.addAll(registrazioneService.selectAllRegistrazioneByYearAndMonth(selectedVisualizza.getCodiceUtente(), visualizza));
    		filteredDialogRegistrazione.addAll(dialogRegistrazioni);
    	} else if (visualizza.getMese() == null && visualizza.getAnno() != null ) {
    		dialogRegistrazioni.addAll(registrazioneService.selectRegistrazioneByUserCode(selectedVisualizza.getCodiceUtente()));
    		filteredDialogRegistrazione.addAll(dialogRegistrazioni);
    	}
    	
    	if(dialogRegistrazioni == null) {
			return false;
		} 
		return true;
    }
    
    
    
    
    
    private void setTableFooterValues(List<VisualizzaRegistrazione> list) {
    	setFooterTotaleLavorate(getTotaleOreLavorate(list));
		setFooterTotaleFerie(getTotaleOreFerie(list));
		setFooterTotaleMalattia(getTotaleOreMalattia(list));
		setFooterTotalePermesso(getTotaleOrePermesso(list));
		setFooterTotaleTrasferta(getTotaleOreTrasferta(list));
		setFooterTotaleTotali(getTotaleOreTotali(list));
    	
    }
    
    
    
    
    @RolesAllowed({"ROLE_ADMIN"})
    public void ricercaTabellaPerPeriodo() {
    	visualizzazioni = new ArrayList<VisualizzaRegistrazione>();
    	try {
			populateRegistrazByPeriod(visualizzazioniPerPeriodo);
			//refreshList();	
			
			notificationSuccess("selezione periodo");
		} catch (Exception e) {
			notificationError(e, "selezione periodo");
			e.printStackTrace();
		}
    	
    }
    
	
	
    
    
	public void notificationSuccess(String operation) {
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Operation <" + operation + "> success");
		FacesMessage msg = null;  
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification", "Operazione completata"); 
		FacesContext.getCurrentInstance().addMessage(null, msg);  
	}


	/**
	 * 
	 * @param e the Exception.
	 * @param operation the CRUD operation.
	 */
	public void notificationError(Exception e, String operation) {
		Logger.getLogger(this.getClass().getName()).log(Level.ERROR, "Operation <" + operation + "> Error", e);
		FacesMessage msg = null;  
		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notification", "Si è verificato un errore");  
		FacesContext.getCurrentInstance().addMessage(null, msg);  
	}
	
	
	
	
	/**
	 * 
	 * @param listAdmin a List.
	 */
	private void populateRegistrazAdmin(List<VisualizzaRegistrazione> listAdmin) {
		listAdmin.clear();
		listAdmin.addAll(visualizzaService.selectAllRegistrazioneAdminFiltered(visualizza));
		
		setTableFooterValues(listAdmin);
	}
	
	
	/**
	 * 
	 * @param listPeriod a List.
	 */
	private void populateRegistrazByPeriod(List<VisualizzaRegistrazione> listPeriod) {
		listPeriod.clear();
		listPeriod.addAll(visualizzaService.selectAllRegistrazioneByPeriodFiltered(visualizza));
		
		setTableFooterValues(listPeriod);
	}
	

	
	/**
	 * 
	 * @param listUser the listUser.
	 */
	private void populateRegistrazUser(List<VisualizzaRegistrazione> listUser) {
		listUser.clear();
		listUser.addAll(visualizzaService.selectAllRegistrazioneUserFiltered(visualizza, this.codiceUtenteVisualiz));
	
		setTableFooterValues(listUser);
	}
	
	
	
	protected void refreshList() {
		setVisualizza(new VisualizzaRegistrazione());
		setVisualizzazioni(new ArrayList<VisualizzaRegistrazione>());
		setVisualizzazioniPerPeriodo(new ArrayList<VisualizzaRegistrazione>());
	}


	
	
	/* Calculate all the totals */
	public int getTotaleOreLavorate(List<VisualizzaRegistrazione> list) {
		Integer total = 0;
        for(VisualizzaRegistrazione item : list) {
        	if(item.getTotaleLavorate() != null) {
        		total += item.getTotaleLavorate().intValue();
        	}
        }
		return total.intValue();
		
	}
	
	
	public int getTotaleOrePermesso(List<VisualizzaRegistrazione> list) {
		Integer total = 0;
        for(VisualizzaRegistrazione item : list) {
        	if(item.getTotalePermesso() != null) {
        		total += item.getTotalePermesso().intValue();
        	}
        }
		return total.intValue();
		
	}
	
	
	public int getTotaleOreFerie(List<VisualizzaRegistrazione> list) {
		Integer total = 0;
        for(VisualizzaRegistrazione item : list) {
        	if(item.getTotaleFerie() != null) {
        		total += item.getTotaleFerie().intValue();
        	}
        }
		return total.intValue();
		
	}
	
	
	public int getTotaleOreMalattia(List<VisualizzaRegistrazione> list) {
		Integer total = 0;
        for(VisualizzaRegistrazione item : list) {
        	if(item.getTotaleMalattia() != null) {
        		total += item.getTotaleMalattia().intValue();
        	}
        }
		return total.intValue();
		
	}
	
	
	public int getTotaleOreTrasferta(List<VisualizzaRegistrazione> list) {
		Integer total = 0;
        for(VisualizzaRegistrazione item : list) {
        	if(item.getTotaleTrasferta() != null) {
        		total += item.getTotaleTrasferta().intValue();
        	}
        }
		return total.intValue();
		
	}
	
	
	
	public int getTotaleOreTotali(List<VisualizzaRegistrazione> list) {
		Integer total = 0;
        for(VisualizzaRegistrazione item : list) {
        	if(item.getTotale() != null) {
        		total += item.getTotale().intValue();
        	}
        }
		return total.intValue();
		
	}
	
	
	
	
	
	
	
	
    /* Getters and Setters */
	public LoginBean getLoginBean() {
		return loginBean;
	}


	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}


	public VisualizzaRegistrazione getSelectedVisualizza() {
		return selectedVisualizza;
	}


	/**
	 * 
	 * @param selectedVisualizza the selected record.
	 */
	public void setSelectedVisualizza(VisualizzaRegistrazione selectedVisualizza) {
		this.selectedVisualizza = selectedVisualizza;
	}


	/**
	 * 
	 * @return the variable visualizza.
	 */
	public VisualizzaRegistrazione getVisualizza() {
		return visualizza;
	}


	public void setVisualizza(VisualizzaRegistrazione visualizza) {
		this.visualizza = visualizza;
	}


	/**
	 * 
	 * @return the list visualizzazioni.
	 */
	public List<VisualizzaRegistrazione> getVisualizzazioni() {
		if(visualizzazioni == null){
			visualizzazioni = new ArrayList<VisualizzaRegistrazione>();
		}
		return visualizzazioni;
	}


	public void setVisualizzazioni(List<VisualizzaRegistrazione> visualizzazioni) {
		this.visualizzazioni = visualizzazioni;
	}


	public Integer getCodiceUtenteVisualiz() {
		return codiceUtenteVisualiz;
	}


	public void setCodiceUtenteVisualiz(Integer codiceUtenteVisualiz) {
		this.codiceUtenteVisualiz = codiceUtenteVisualiz;
	}


	/**
	 * 
	 * @return the String ruoloUtenteVisualiz.
	 */
	public String getRuoloUtenteVisualiz() {
		return ruoloUtenteVisualiz;
	}


	public void setRuoloUtenteVisualiz(String ruoloUtenteVisualiz) {
		this.ruoloUtenteVisualiz = ruoloUtenteVisualiz;
	}


	/**
	 * 
	 * @param visualizzaService the service.
	 */
	public void setVisualizzaService(VisualizzaRegistrazioneService visualizzaService) {
		this.visualizzaService = visualizzaService;
	}




	public List<VisualizzaRegistrazione> getFilteredVisualizza() {
		return filteredVisualizza;
	}



	public void setFilteredVisualizza(List<VisualizzaRegistrazione> filteredVisualizza) {
		this.filteredVisualizza = filteredVisualizza;
	}



	public RegistrazioneService getRegistrazioneService() {
		return registrazioneService;
	}



	public void setRegistrazioneService(RegistrazioneService registrazioneService) {
		this.registrazioneService = registrazioneService;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	public VisualizzaRegistrazioneService getVisualizzaService() {
		return visualizzaService;
	}
	
	
	public List<Registrazione> getDialogRegistrazioni() {
		if(dialogRegistrazioni == null) {
			dialogRegistrazioni = new ArrayList<Registrazione>();
		}
		return dialogRegistrazioni;
	}


	public void setDialogRegistrazioni(List<Registrazione> dialogRegistrazioni) {
		this.dialogRegistrazioni = dialogRegistrazioni;
	}



	public List<Registrazione> getFilteredDialogRegistrazione() {
		return filteredDialogRegistrazione;
	}



	public void setFilteredDialogRegistrazione(List<Registrazione> filteredDialogRegistrazione) {
		this.filteredDialogRegistrazione = filteredDialogRegistrazione;
	}



	public Integer getFooterTotaleLavorate() {
		return footerTotaleLavorate;
	}



	public Integer getFooterTotalePermesso() {
		return footerTotalePermesso;
	}



	public Integer getFooterTotaleMalattia() {
		return footerTotaleMalattia;
	}



	public Integer getFooterTotaleFerie() {
		return footerTotaleFerie;
	}



	public Integer getFooterTotaleTrasferta() {
		return footerTotaleTrasferta;
	}



	public void setFooterTotaleLavorate(Integer footerTotaleLavorate) {
		this.footerTotaleLavorate = footerTotaleLavorate;
	}



	public void setFooterTotalePermesso(Integer footerTotalePermesso) {
		this.footerTotalePermesso = footerTotalePermesso;
	}



	public void setFooterTotaleMalattia(Integer footerTotaleMalattia) {
		this.footerTotaleMalattia = footerTotaleMalattia;
	}



	public void setFooterTotaleFerie(Integer footerTotaleFerie) {
		this.footerTotaleFerie = footerTotaleFerie;
	}



	public void setFooterTotaleTrasferta(Integer footerTotaleTrasferta) {
		this.footerTotaleTrasferta = footerTotaleTrasferta;
	}


	public Integer getFooterTotaleTotali() {
		return footerTotaleTotali;
	}


	public void setFooterTotaleTotali(Integer footerTotaleTotali) {
		this.footerTotaleTotali = footerTotaleTotali;
	}


	public List<VisualizzaRegistrazione> getVisualizzazioniPerPeriodo() {
		if(visualizzazioniPerPeriodo == null) {
			visualizzazioniPerPeriodo = new ArrayList<VisualizzaRegistrazione>();
		}
		return visualizzazioniPerPeriodo;
	}


	/**
	 * 
	 * @param visualizzazioniPerPeriodo the input list.
	 */
	public void setVisualizzazioniPerPeriodo(List<VisualizzaRegistrazione> visualizzazioniPerPeriodo) {
		this.visualizzazioniPerPeriodo = visualizzazioniPerPeriodo;
	}
	
	

}

