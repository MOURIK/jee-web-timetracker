/*
 * Conselti s.r.l.
 */
package it.conselti.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import it.conselti.domain.Registrazione;
import it.conselti.repository.RegistrazioneRepository;
import it.conselti.service.RegistrazioneService;

/**
 * @author onofr
 *
 */

@Component(value="registrazioneBean")
@Scope(value="view")
public class RegistrazioneBean extends AbstractManagedBean implements Serializable {


	/**
	 *
	 */
	private static final long serialVersionUID = -1576183674406327310L;



	private Registrazione registrazione;
	private Registrazione selectedRegistrazione;
	private List<Registrazione> registrazioni;
	private List<Registrazione> filteredRegistrazione;
	


	private Integer idLavoroSelez;
	private Integer codiceUtenteRegistr;
	private String ruoloUtenteRegistr;




	@Autowired
	private LoginBean loginBean;

	@Autowired
	private RegistrazioneService registrazioneService;

	@Autowired
	private RegistrazioneRepository registrazioneRepository;




	

    @PostConstruct
	public void init() {
    	refreshList();
    	injectBean();
	}
    
    
    protected void injectBean() {
		try {
			codiceUtenteRegistr = loginBean.getCodiceUtente();
			ruoloUtenteRegistr = loginBean.getRuoloUtente();
			if(this.codiceUtenteRegistr != null && this.ruoloUtenteRegistr != null) {
				logger.info("Bean injection RegistrazioneBean.java [" + this.loginBean.getCodiceUtente() + ", " + this.loginBean.getRuoloUtente() + "]");
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
    }
    
    /**
     * private method setCurrentDate.
     */
    private void setCurrentDate() {
    	Date today = new Date();
		today = Calendar.getInstance().getTime();
		registrazione.setData(today);
    }

    
	
    
	protected void refreshList() {
		setRegistrazione(new Registrazione());
    	registrazioni = new ArrayList<Registrazione>();
    	filteredRegistrazione = new ArrayList<Registrazione>();
    	setCurrentDate();
	}
	
	
	
	/**
	 * 
	 * @param operation the operation.
	 */
	public void notificationSuccess(String operation) {
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Operation <" + operation + "> success");
		FacesMessage msg = null;  
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification", "Operazione completata"); 
		FacesContext.getCurrentInstance().addMessage(null, msg);  
	}

    
	/**
	 * 
	 * @param e the Exception message.
	 * @param operation the operation.
	 */
	public void notificationError(Exception e, String operation) {
		Logger.getLogger(this.getClass().getName()).log(Level.ERROR, "Operation <" + operation + "> Error ", e);
		FacesMessage msg = null;  
		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notification", "Si � verificato un errore");  
		FacesContext.getCurrentInstance().addMessage(null, msg);  
	}

	
	

	/**
	 * 
	 * @throws Exception if fails.
	 */
	@PreAuthorize("isAuthenticated()")
	public void inserisciRegistrazione() throws Exception
	{
		if (registrazione.getData() != null 
				&& ((registrazione.getLavorate() != null && registrazione.getLavorate().intValue() > 0)
				|| (registrazione.getFerie() != null && registrazione.getFerie().intValue() > 0)
				|| (registrazione.getPermesso() != null && registrazione.getPermesso().intValue() > 0) 
				|| (registrazione.getMalattia() != null && registrazione.getMalattia().intValue() > 0)
				|| (registrazione.getTrasferta() != null && registrazione.getTrasferta().intValue() > 0))) 
		{
			try {
				/* codiceUtente from injectBean */
				registrazione.setCodice(this.codiceUtenteRegistr);
				registrazioneService.insertRegistrazione(registrazione);

				refreshList();
				/* populate the dataTable */
				populateRegistrazioni(registrazioni, filteredRegistrazione);
				notificationSuccess("inserimento");
				
			} catch (final RuntimeException e) {
				notificationError(e, "inserimento");
				e.printStackTrace();
			}

		} else {
			FacesMessage msg = null;  
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notification", "Almeno un campo compilato");  
			FacesContext.getCurrentInstance().addMessage(null, msg);  
			
		}
	}

	
	
	/**
	 * 
	 */
	@RolesAllowed({"ROLE_ADMIN"})
	public void cancellaRegistrazione() {
		try {
			registrazioneService.deleteRegistrazione(selectedRegistrazione);

			refreshList();
			/* populate the dataTable */ 
			populateRegistrazioni(registrazioni, filteredRegistrazione);
			notificationSuccess("cancellazione");
			
		} catch (RuntimeException e) {
			notificationError(e, "cancellazione");
			e.printStackTrace();
		}
	}
	
	public void reset() {
        RequestContext.getCurrentInstance().reset("form1:panel");
    }
	
	
	
	
	
	/**
	 * 
	 * @param event
	 */
	public void onCancel(RowEditEvent event) {
		refreshList();
		populateRegistrazioni(registrazioni, filteredRegistrazione);
	}


	
	
	
	@RolesAllowed({"ROLE_ADMIN", "ROLE_SUPERUSER", "ROLE_USER"})
	public void modificaRegistrazione() {
		if (selectedRegistrazione.getData() != null 
				&& ((selectedRegistrazione.getLavorate() != null && selectedRegistrazione.getLavorate().intValue() > 0)
				|| (selectedRegistrazione.getFerie() != null && selectedRegistrazione.getFerie().intValue() > 0)
				|| (selectedRegistrazione.getPermesso() != null && selectedRegistrazione.getPermesso().intValue() > 0) 
				|| (selectedRegistrazione.getMalattia() != null && selectedRegistrazione.getMalattia().intValue() > 0)
				|| (selectedRegistrazione.getTrasferta() != null && selectedRegistrazione.getTrasferta().intValue() > 0)))
		{
			try {
				/* codiceUtente from injectBean */
				selectedRegistrazione.setCodice(this.codiceUtenteRegistr);
				registrazioneService.updateRegistrazione(selectedRegistrazione);
				notificationSuccess("modifica");
				
				/* populate the dataTable */
				refreshList();
				populateRegistrazioni(registrazioni, filteredRegistrazione);
				
			} catch (final RuntimeException e) {
				notificationError(e, "modifica");
				e.printStackTrace();
			}
		} else {
			FacesMessage msg = null;  
			msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notification", "Almeno un campo compilato");  
			FacesContext.getCurrentInstance().addMessage(null, msg);  
			
			refreshList();
			populateRegistrazioni(registrazioni, filteredRegistrazione);
		}
	}

	
	

	/**
	 * 
	 * @param listRegistrazioni
	 */
	@PreAuthorize("isAuthenticated()")
	private void populateRegistrazioni(List<Registrazione> listRegistrazioni, List<Registrazione> listFilteredRegistrazioni)
	{
		listRegistrazioni.clear();
		listFilteredRegistrazioni.clear();
		
		if(FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ROLE_ADMIN")) {
			listRegistrazioni.addAll(registrazioneRepository.findAllRegistrazione());
			listFilteredRegistrazioni.addAll(listRegistrazioni);
			
		} else {
			listRegistrazioni.addAll(registrazioneRepository.findAllRegistrazioneByUserCode(this.codiceUtenteRegistr));
			listFilteredRegistrazioni.addAll(listRegistrazioni);
		}
	}

	
	
	public static Calendar DateToCalendar(Date date) { 
  	  Calendar cal = Calendar.getInstance();
  	  cal.setTime(date);
  	  return cal;
  }
	
	
	
	
	
	
	

	/* Getters and Setters */
	public Registrazione getRegistrazione() {
		return registrazione;
	}



	public void setRegistrazione(Registrazione registrazione) {
		this.registrazione = registrazione;
	}



	public Registrazione getSelectedRegistrazione() {
		return selectedRegistrazione;
	}


	/**
	 * 
	 * @param selectedRegistrazione
	 */
	public void setSelectedRegistrazione(Registrazione selectedRegistrazione) {
		this.selectedRegistrazione = selectedRegistrazione;
	}



	/**
	 * 
	 * @return
	 */
	public List<Registrazione> getRegistrazioni() {
		if(registrazioni == null){
			registrazioni = new ArrayList<Registrazione>();
		}
		return registrazioni;
	}



	public void setRegistrazioni(List<Registrazione> registrazioni) {
		this.registrazioni = registrazioni;
	}



	public Integer getIdLavoroSelez() {
		return idLavoroSelez;
	}



	public void setIdLavoroSelez(Integer idLavoroSelez) {
		this.idLavoroSelez = idLavoroSelez;
	}



	public Integer getCodiceUtenteRegistr() {
		return codiceUtenteRegistr;
	}



	public void setCodiceUtenteRegistr(Integer codiceUtenteRegistr) {
		this.codiceUtenteRegistr = codiceUtenteRegistr;
	}



	public LoginBean getLoginBean() {
		return loginBean;
	}



	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}



	public RegistrazioneService getRegistrazioneService() {
		return registrazioneService;
	}


	/**
	 * 
	 * @param registrazioneService
	 */
	public void setRegistrazioneService(RegistrazioneService registrazioneService) {
		this.registrazioneService = registrazioneService;
	}



	/**
	 * 
	 * @return
	 */
	public RegistrazioneRepository getRegistrazioneRepository() {
		return registrazioneRepository;
	}



	public void setRegistrazioneRepository(RegistrazioneRepository registrazioneRepository) {
		this.registrazioneRepository = registrazioneRepository;
	}



	public String getRuoloUtenteRegistr() {
		return ruoloUtenteRegistr;
	}


	public void setRuoloUtenteRegistr(String ruoloUtenteRegistr) {
		this.ruoloUtenteRegistr = ruoloUtenteRegistr;
	}


	public List<Registrazione> getFilteredRegistrazione() {
		return filteredRegistrazione;
	}


	public void setFilteredRegistrazione(List<Registrazione> filteredRegistrazione) {
		this.filteredRegistrazione = filteredRegistrazione;
	}



}
