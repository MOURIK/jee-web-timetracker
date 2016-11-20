/*
 * Conselti s.r.l.
 */
package it.conselti.domain;

import java.io.Serializable;

/**
 * @author onofr
 *
 */
public class Ruolo implements Serializable{

	
	private static final long serialVersionUID = 3120261539365076666L;
	/**
	 * 
	 */
	
	
	private Integer codiceRuolo;
	private String nomeRuolo;
	private String descrizioneRuolo;
	

	
	
	public Ruolo() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	


	/**
	 * 
	 * @param codiceRuolo the role code.
	 * @param nomeRuolo the role name.
	 * @param descrizioneRuolo the role description.
	 */
	public Ruolo(Integer codiceRuolo, String nomeRuolo, String descrizioneRuolo) {
		super();
		this.codiceRuolo = codiceRuolo;
		this.nomeRuolo = nomeRuolo;
		this.descrizioneRuolo = descrizioneRuolo;
	}





	/* Getters and Setters */
	public Integer getCodiceRuolo() {
		return codiceRuolo;
	}


	public void setCodiceRuolo(Integer codiceRuolo) {
		this.codiceRuolo = codiceRuolo;
	}


	public String getNomeRuolo() {
		return nomeRuolo;
	}


	public void setNomeRuolo(String nomeRuolo) {
		this.nomeRuolo = nomeRuolo;
	}


	public String getDescrizioneRuolo() {
		return descrizioneRuolo;
	}


	public void setDescrizioneRuolo(String descrizione) {
		this.descrizioneRuolo = descrizione;
	}



	
}
