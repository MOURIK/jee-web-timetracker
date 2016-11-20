/*
 * Conselti s.r.l.
 */
package it.conselti.managedBean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.security.RolesAllowed;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.conselti.application.AuthenticationService;

/**
 * @author onofr
 *
 */

@Component(value="headerBean")
@Scope(value="view")
public class HeaderBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 374413214000056845L;
	
	@Autowired
	private AuthenticationService authenticationService; // injected Spring defined service 

	
	
	
	
	@RolesAllowed({"ROLE_ADMIN", "ROLE_USER", "ROLE_SUPERUSER"})
	public void effettuaLogout() throws IOException {
		authenticationService.logout();
		FacesContext.getCurrentInstance().getExternalContext().redirect("logout.xhtml");
	}


	public void gotoLogin() throws IOException
	{
		FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
	}


	public void helpLogin() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Help", "Digita le tue credenziali"));
	}


	public void helpLogout() throws IOException
	{
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Help", "Clicca su Login per loggarti"));
	}
	
	
	/**
	 * The public method onIdle.
	 */
	public void onIdle()
	{
		 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
                 "What's happening?", "Dude, are you there?"));
	}
	
	
	/**
	 * The public method onActive.
	 */
	public void onActive() {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                "Welcome Back", "There you are at last!"));
		
	}
	
}
