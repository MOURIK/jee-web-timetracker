/*
 * Conselti s.r.l.
 */
package it.conselti.domain;

import java.io.Serializable;

/**
 * @author onofr
 *
 */
public class Funzione implements Serializable{

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6401795548403379951L;
	
	
	
	private Integer codiceFunzione;
	private String nomeFunzione;
	private String descrizione;
	private String surname;
	private String roleName;
	
	
	
	public Funzione() {
		super();
		
	}

	

	public Funzione(Integer codiceFunzione, String nomeFunzione, String descrizione, String surname, String roleName) {
		super();
		this.codiceFunzione = codiceFunzione;
		this.nomeFunzione = nomeFunzione;
		this.descrizione = descrizione;
		this.surname = surname;
		this.roleName = roleName;
	}




	
	/* Getters and Setters */
	public Integer getCodiceFunzione() {
		return codiceFunzione;
	}

	public void setCodiceFunzione(Integer codiceFunzione) {
		this.codiceFunzione = codiceFunzione;
	}

	public String getNomeFunzione() {
		return nomeFunzione;
	}

	public void setNomeFunzione(String nomeFunzione) {
		this.nomeFunzione = nomeFunzione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	

}
