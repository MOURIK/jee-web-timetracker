/*
 * Conselti s.r.l.
 */
package it.conselti.domain;

import java.io.Serializable;

/**
 * @author onofr
 *
 */

public class Utente implements Serializable {
	
	private static final long serialVersionUID = 3647233284813657927L;
	
	
	private Integer codiceUtente;
	private Integer idAnagrafica;
	private String usernameUtente;
	private String passwordUtente;
	private String enabled;
	
	
	public Utente()
	{
		super();
	}
	      
	/**
	 * 
	 * @param matricola the roll id.
	 * @param id the id.
	 * @param user the username.
	 * @param pass the password.
	 * @param enabled enabled or not.
	 */
	public Utente(Integer matricola, Integer id, String user, String pass, String enabled)
	{
		this.codiceUtente = matricola;
		this.idAnagrafica = id;
		this.usernameUtente = user;
		this.passwordUtente = pass;
		this.setEnabled(enabled);
	}
	
	
	
	
	/* Getters and Setters */
	public Integer getCodiceUtente() {
		return codiceUtente;
	}
	
	public void setCodiceUtente(Integer matricolaUtente) {
		this.codiceUtente = matricolaUtente;
	}
	
	public Integer getIdAnagrafica() {
		return idAnagrafica;
	}
	
	public void setIdAnagrafica(Integer idAnagrafica) {
		this.idAnagrafica = idAnagrafica;
	}
	
	public String getUsernameUtente() {
		return usernameUtente;
	}
	
	public void setUsernameUtente(String username) {
		this.usernameUtente = username;
	}
	
	public String getPasswordUtente() {
		return passwordUtente;
	}
	
	public void setPasswordUtente(String password) {
		this.passwordUtente = password;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
}