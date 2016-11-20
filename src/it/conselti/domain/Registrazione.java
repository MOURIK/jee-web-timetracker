/*
 * Conselti s.r.l.
 */
package it.conselti.domain;

import java.io.Serializable;
import java.util.Date;



/**
 * @author onofr
 *
 */

public class Registrazione implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5106088757657999959L;
	
	
	private Integer id;
	private Integer codice;
	private Date data;
	private String cognome;
	private String nome;
	private Integer lavorate;
	private Integer ferie;
	private Integer permesso;
	private Integer malattia;
	private Integer trasferta;
	private String note;
	
	
	
	public Registrazione()
	{
		super();
	}


	

	/**
	 * 
	 * @param id the id.
	 * @param codice the code.
	 * @param data the date.
	 * @param cognome the surname.
	 * @param nome the name.
	 * @param ferie holidays hours.
	 * @param permesso licence hours.
	 * @param malattia illness hours.
	 * @param trasferta transfert hours.
	 * @param note the notes.
	 */
	public Registrazione(Integer id, Integer codice, Date data, String cognome, String nome, Integer ferie,
			Integer permesso, Integer malattia, Integer trasferta, String note, Integer lavorate) {
		super();
		this.id = id;
		this.codice = codice;
		this.data = data;
		this.cognome = cognome;
		this.nome = nome;
		this.ferie = ferie;
		this.permesso = permesso;
		this.malattia = malattia;
		this.trasferta = trasferta;
		this.note = note;
		this.setLavorate(lavorate);
	}


	

	
	
	/* Getters and Setters */
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getCodice() {
		return codice;
	}



	public void setCodice(Integer codice) {
		this.codice = codice;
	}



	public Date getData() {
		return data;
	}



	public void setData(Date data) {
		this.data = data;
	}



	public String getCognome() {
		return cognome;
	}



	public void setCognome(String cognome) {
		this.cognome = cognome;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public Integer getFerie() {
		return ferie;
	}



	public void setFerie(Integer ferie) {
		this.ferie = ferie;
	}



	public Integer getPermesso() {
		return permesso;
	}



	public void setPermesso(Integer permesso) {
		this.permesso = permesso;
	}



	public Integer getMalattia() {
		return malattia;
	}



	public void setMalattia(Integer malattia) {
		this.malattia = malattia;
	}



	public Integer getTrasferta() {
		return trasferta;
	}



	public void setTrasferta(Integer trasferta) {
		this.trasferta = trasferta;
	}



	public String getNote() {
		return note;
	}



	public void setNote(String note) {
		this.note = note;
	}




	public Integer getLavorate() {
		return lavorate;
	}




	public void setLavorate(Integer lavorate) {
		this.lavorate = lavorate;
	}




	
}
