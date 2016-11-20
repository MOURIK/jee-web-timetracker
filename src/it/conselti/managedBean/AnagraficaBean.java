/*
 * Conselti s.r.l.
 */
package it.conselti.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import it.conselti.domain.Anagrafica;
import it.conselti.domain.Assegna;
import it.conselti.domain.Ruolo;
import it.conselti.domain.Utente;
import it.conselti.repository.AnagraficaRepository;
import it.conselti.service.AnagraficaService;
import it.conselti.service.AssegnaService;
import it.conselti.service.UtenteService;

/**
 * @author onofr
 *
 */

@Component(value="anagraficaBean")
@Scope(value="view")
public class AnagraficaBean extends AbstractManagedBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -8621121503362734696L;


	private Anagrafica anagrafica;
	private Anagrafica selectedAnagrafica;
	private List<Anagrafica> anagrafiche;
	private List<Utente> utenti;
	private Utente utente;
	private Ruolo ruolo;
	private Assegna assegna;



	private Integer codiceUtenteAnagraf;
	private String ruoloUtenteAnagraf;
	private String nameRuolo;


	
	
	

	@Autowired
	private LoginBean loginBean;

	@Autowired
	private AnagraficaService anagraficaService;

	@Autowired
	private UtenteService utenteService;

	@Autowired
	private AnagraficaRepository anagraficaRepository;

	@Autowired
	private AssegnaService assegnaService;


	
	


	/**
	 * 
	 * @throws Exception if fails.
	 */
	@PostConstruct
	public void init() throws Exception {
		refreshList();
		injectBean();

		if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ROLE_ADMIN") || 
				FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ROLE_SUPERUSER")) {
			populateAnagrafica(anagrafiche);

		} else if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ROLE_USER")) {
			populateUserAnagrafica(anagrafiche);
		}

	}


	protected void injectBean() {
		try {
			setCodiceUtenteAnagraf(loginBean.getCodiceUtente());
			setRuoloUtenteAnagraf(loginBean.getRuoloUtente());
			if(this.codiceUtenteAnagraf != null && this.ruoloUtenteAnagraf != null)
			{
				logger.info("Bean injection: AnagraficaBean.java [" + this.loginBean.getCodiceUtente() 
				+ ", " + this.loginBean.getRuoloUtente() + "]");
			}

		} catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	


	public void notificationSuccess(String operation) {
		Logger.getLogger(this.getClass().getName()).log(Level.INFO, "Operation <" + operation + "> success");
		FacesMessage msg = null;  
		msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Notification", "Operazione completata"); 
		FacesContext.getCurrentInstance().addMessage(null, msg);  
	}



	public void notificationError(Exception e, String operation) {
		Logger.getLogger(this.getClass().getName()).log(Level.ERROR, "Operation <" + operation + "> Error ", e);
		FacesMessage msg = null;  
		msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Notification", "Si è verificato un errore");  
		FacesContext.getCurrentInstance().addMessage(null, msg);  
	}

	
	
	
	
	@RolesAllowed({"ROLE_ADMIN"})
	public void inserisciAnagrafica() throws Exception
	{
		if (anagrafica != null && utente != null && assegna != null) 
		{
			try {
				anagraficaService.insertAnagrafica(anagrafica);
				
				utente.setIdAnagrafica(anagrafica.getId().intValue());
				utenteService.insertUtente(utente);
				
				assegna.setMatricolaUtente(utente.getCodiceUtente().intValue());
				assegnaService.insertAssegna(assegna);
				
				
				refreshList();
				
				populateAnagrafica(anagrafiche);				
				notificationSuccess("inserimento");
				
			} catch (final RuntimeException e) {
				notificationError(e, "inserimento");
				e.printStackTrace();
			}
			

		} else {
			throw new Exception("Eccezione in inserisciAnagrafica!");
		}
	}
	
	
	/**
	 * 
	 */
	protected void refreshList() {
		setAnagrafica(new Anagrafica());
		setUtente(new Utente());
		setAssegna(new Assegna());
		setRuolo(new Ruolo());
    	anagrafiche = new ArrayList<Anagrafica>();
    	utenti = new ArrayList<Utente>();
	}


	/**
	 * 
	 * @throws Exception if fails.
	 */
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void cancellaAnagrafica() throws Exception 
	{
		try {
			assegna = assegnaService.selectAssegnaByUserAndPsw(selectedAnagrafica.getUsername(), selectedAnagrafica.getPassword());
			assegnaService.deleteAssegna(assegna);

			utente.setIdAnagrafica(selectedAnagrafica.getId().intValue());
			utenteService.deleteUtente(utente);
			
			anagraficaService.deleteAnagrafica(selectedAnagrafica);
			
			refreshList();
			
			
			populateAnagrafica(anagrafiche);
			notificationSuccess("cancellazione");
		
		} catch (final RuntimeException e) {
			notificationError(e, "cancellazione");
			e.printStackTrace();
		}
	}

	

	/**
	 * 
	 * @throws Exception if fails.
	 */
	@PreAuthorize("isAuthenticated()")
	public void modificaAnagrafica() throws Exception {

		if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ROLE_USER")) {
			try {
				utente.setIdAnagrafica(selectedAnagrafica.getId().intValue());
				utente.setPasswordUtente(selectedAnagrafica.getPassword());

				utenteService.updatePasswordUtente(utente);
				anagraficaService.updateAnagrafica(selectedAnagrafica);

				assegnaService.updateAssegna(assegna);
				
				refreshList();

				
				populateUserAnagrafica(anagrafiche);
				notificationSuccess("modifica");
			
			} catch (RuntimeException e) {
				notificationError(e, "modifica");
				e.printStackTrace();
			}
			

		} else if (FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ROLE_ADMIN") || FacesContext.getCurrentInstance().getExternalContext().isUserInRole("ROLE_SUPERUSER"))
		{
			try {
				utente.setIdAnagrafica(selectedAnagrafica.getId());
				utente.setPasswordUtente(selectedAnagrafica.getPassword());

				utenteService.updatePasswordUtente(utente);
				anagraficaService.updateAnagrafica(selectedAnagrafica);

				
				refreshList();
				
				populateAnagrafica(anagrafiche);
				notificationSuccess("modifica");
				
			} catch (RuntimeException e) {
				notificationError(e, "modifica");
				e.printStackTrace();
			}
		}
	}


	/**
	 * 
	 * @param listAnagrafiche the input list.
	 */
	private void populateAnagrafica(List<Anagrafica> listAnagrafiche)
	{
		listAnagrafiche.clear();
		listAnagrafiche.addAll(anagraficaRepository.findAllAnagraficaUtente());
	}


	/**
	 * 
	 * @param listAnagrafiche the input list.
	 */
	private void populateUserAnagrafica(List<Anagrafica> listAnagrafiche) {
		listAnagrafiche.clear();
		listAnagrafiche.addAll(anagraficaRepository.findAllAnagraficaByCodiceUtente(this.codiceUtenteAnagraf));
	}

	
	
	/**
	 * 
	 * @param event a RowEditEvent event.
	 */
	public void onCancel(RowEditEvent event) {
		refreshList();
		populateAnagrafica(anagrafiche);
		populateUserAnagrafica(anagrafiche);
	}	
	

	
	
	
	
	
	
	/* Getters and Setters */
	public Anagrafica getAnagrafica() {
		return anagrafica;
	}


	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}


	public Anagrafica getSelectedAnagrafica() {
		return selectedAnagrafica;
	}


	public void setSelectedAnagrafica(Anagrafica selectedAnagrafica) {
		this.selectedAnagrafica = selectedAnagrafica;
	}


	public List<Anagrafica> getAnagrafiche() {
		return anagrafiche;
	}


	public void setAnagrafiche(List<Anagrafica> anagrafiche) {
		this.anagrafiche = anagrafiche;
	}


	public List<Utente> getUtenti() {
		return utenti;
	}


	/**
	 * 
	 * @param utenti the input list.
	 */
	public void setUtenti(List<Utente> utenti) {
		this.utenti = utenti;
	}


	public Utente getUtente() {
		return utente;
	}


	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	
	/**
	 * 
	 * @return the role.
	 */
	public Ruolo getRuolo() {
		return ruolo;
	}


	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}


	public Assegna getAssegna() {
		return assegna;
	}


	/**
	 * 
	 * @param assegna an Assegna object.
	 */
	public void setAssegna(Assegna assegna) {
		this.assegna = assegna;
	}


	public Integer getCodiceUtenteAnagraf() {
		return codiceUtenteAnagraf;
	}


	public void setCodiceUtenteAnagraf(Integer codiceUtenteAnagraf) {
		this.codiceUtenteAnagraf = codiceUtenteAnagraf;
	}


	public String getRuoloUtenteAnagraf() {
		return ruoloUtenteAnagraf;
	}


	public void setRuoloUtenteAnagraf(String ruoloUtenteAnagraf) {
		this.ruoloUtenteAnagraf = ruoloUtenteAnagraf;
	}


	/**
	 * 
	 * @return the role name.
	 */
	public String getNameRuolo() {
		return nameRuolo;
	}


	public void setNameRuolo(String nameRuolo) {
		this.nameRuolo = nameRuolo;
	}


	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}


	/**
	 * 
	 * @param anagraficaService the service.
	 */
	public void setAnagraficaService(AnagraficaService anagraficaService) {
		this.anagraficaService = anagraficaService;
	}


	public void setUtenteService(UtenteService utenteService) {
		this.utenteService = utenteService;
	}


	public void setAnagraficaRepository(AnagraficaRepository anagraficaRepository) {
		this.anagraficaRepository = anagraficaRepository;
	}


	public void setAssegnaService(AssegnaService assegnaService) {
		this.assegnaService = assegnaService;
	}


	

}
