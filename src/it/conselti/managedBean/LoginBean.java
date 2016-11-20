/*
 * Conselti s.r.l.
 */
package it.conselti.managedBean;

import it.conselti.application.AuthenticationService;
import it.conselti.domain.Utente;
import it.conselti.service.AnagraficaService;
import it.conselti.service.RuoloService;
import it.conselti.service.UtenteService;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


/**
 * @author onofr
 *
 */

@Component(value="loginBean")
@Scope(value="session")
public class LoginBean extends AbstractManagedBean implements Serializable {

	
	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 4123256682698296503L;
    
	
	
    @Autowired
    private AnagraficaService anagraficaService;
    
    @Autowired
    private UtenteService utenteService;
    
    @Autowired
    private RuoloService ruoloService;
    
    @Autowired
	private AuthenticationService authenticationService; // injected Spring defined service 
    
    

	
	@ManagedProperty(value="#{username}")
	private String username;

	@ManagedProperty(value="#{password}")
	private String password;

	@ManagedProperty(value="#{codiceUtente}")
	private Integer codiceUtente;
    
    @ManagedProperty(value="#{ruoloUtente}")
    private String ruoloUtente;
    
    @ManagedProperty(value="#{cognomeUtente}")
    private String cognomeUtente;
    
    @ManagedProperty(value="#{nomeUtente}")
    private String nomeUtente;
    
    
	private Utente utente;
	private String nomeRuolo, descrizioneRuolo;
	
	

	
	
	
	
	@PostConstruct
	public void init() {
		refreshList();
	}

	
	/**
	 * 
	 * @throws IOException if login fails.
	 */
	public void effettuaLogin() throws IOException {
		logger.info("**- Starting login from LoginBean.java -**");
		logger.info("Credenziali digitate [" + this.username + " , " + this.password + "]");
		try {
			boolean success = authenticationService.login(this.username, this.password);
			if (success) {
				utente = utenteService.selectUtenteCredentials(this.username, this.password);
				this.nomeRuolo = ruoloService.selectRuoloByUsernameAndPassword(utente.getUsernameUtente(), utente.getPasswordUtente());
				this.setDescrizioneRuolo(ruoloService.selectDescrizioneRuoloByUsernameAndPassword(utente.getUsernameUtente(), utente.getPasswordUtente()));
				
	            this.cognomeUtente = anagraficaService.selectCognomeByUsername(username);
				this.nomeUtente = anagraficaService.selectNomeByUsername(username);
				
				
				this.codiceUtente = utente.getCodiceUtente();
				this.ruoloUtente = nomeRuolo;
				notificationSuccess();	
				
			} else {
				notificationError();
			}
			
		} catch (final NullPointerException e) {
			logger.error("Error login " + e);
		}
	}

	
	
	/**
	 * 
	 * @throws IOException if login fails.
	 */
	private void notificationSuccess() throws IOException {
		FacesContext.getCurrentInstance().getExternalContext().redirect("welcome.xhtml");
	}

	
	private void notificationError() {
		utente = new Utente();
		setUtente(utenteService.selectUtenteByUsername(username));
		
		if(utente.getEnabled().equals("false")) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Notification", "Account disabilitato"));
		} else {
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Notification", "Credenziali invalide"));
		}
		
	}

	
	
	
	protected void refreshList() {
		setUtente(new Utente());
        this.nomeRuolo = null;
		this.password = null;
		this.username = null;
		this.codiceUtente = null;
		this.ruoloUtente = null;
		this.cognomeUtente = null;
		this.nomeUtente = null;
	}

	
	/**
	 * The public PreDestroy method shutdown.
	 */
	@PreDestroy
	public void shutdown() {
		logger.info("**-  Sessione invalidata da LoginBean.java -**");
	}


	
	
	
	
	
	
	
	/* Getters and Setters */
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public Integer getCodiceUtente() {
		return codiceUtente;
	}


	public void setCodiceUtente(Integer codiceUtente) {
		this.codiceUtente = codiceUtente;
	}


	public String getRuoloUtente() {
		return ruoloUtente;
	}


	public void setRuoloUtente(String ruoloUtente) {
		this.ruoloUtente = ruoloUtente;
	}


	public String getCognomeUtente() {
		return cognomeUtente;
	}


	public void setCognomeUtente(String cognomeUtente) {
		this.cognomeUtente = cognomeUtente;
	}


	public String getNomeUtente() {
		return nomeUtente;
	}


	public void setNomeUtente(String nomeUtente) {
		this.nomeUtente = nomeUtente;
	}

	
	public Utente getUtente() {
		return utente;
	}


	public void setUtente(Utente utente) {
		this.utente = utente;
	}


	public String getNomeRuolo() {
		return nomeRuolo;
	}


	public void setNomeRuolo(String nomeRuolo) {
		this.nomeRuolo = nomeRuolo;
	}

	
	public void setAnagraficaService(AnagraficaService anagraficaService) {
		this.anagraficaService = anagraficaService;
	}



	public RuoloService getRuoloService() {
		return ruoloService;
	}



	public void setRuoloService(RuoloService ruoloService) {
		this.ruoloService = ruoloService;
	}



	public UtenteService getUtenteService() {
		return utenteService;
	}



	public AuthenticationService getAuthenticationService() 
	{
		return authenticationService;
	}



	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}



	public void setUtenteService(UtenteService utenteService) {
		this.utenteService = utenteService;
	}



	@Override
	protected void injectBean() {
		// TODO Auto-generated method stub
		
	}


	public String getDescrizioneRuolo() 
	{
		return descrizioneRuolo;
	}


	public void setDescrizioneRuolo(String descrizioneRuolo) {
		this.descrizioneRuolo = descrizioneRuolo;
	}
	
	
	
}