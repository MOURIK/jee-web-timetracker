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


public class Anagrafica implements Serializable{

	private static final long serialVersionUID = 3647233284813657927L;

	private Integer id;
	private String codiceFiscale;
	private String nome;
	private String cognome;
	private String sesso;
	private Date nascita;
	private String email;
	private String via;
	private Integer cap;
	private String city;
	private String cellulare;
	private String username;
	private String password;
	private String nomeRuolo;
	private Integer codiceRuolo;
	private Integer matricolaUtente;
	private Integer matricolaRuolo;
	
	
	public Anagrafica() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * 
	 * @param id the id.
	 * @param codiceFiscale the ssn.
	 * @param nome the name.
	 * @param cognome the surname.
	 * @param sesso the gender.
	 * @param nascita the born date.
	 * @param email the email.
	 * @param via the address.
	 * @param cap the address.
	 * @param city the home town.
	 * @param cellulare the mobile number.
	 * @param username the username.
	 * @param password the password.
	 * @param nomeRuolo the role name.
	 * @param codiceRuolo the role code.
	 * @param matricolaUtente the user roll id.
	 * @param matricolaRuolo the role roll id.
	 */
	public Anagrafica(Integer id, String codiceFiscale, String nome, String cognome, String sesso, Date nascita,
			String email, String via, Integer cap, String city, String cellulare, String username, String password,
			String nomeRuolo, Integer codiceRuolo, Integer matricolaUtente, Integer matricolaRuolo) {
		super();
		this.id = id;
		this.codiceFiscale = codiceFiscale;
		this.nome = nome;
		this.cognome = cognome;
		this.sesso = sesso;
		this.nascita = nascita;
		this.email = email;
		this.via = via;
		this.cap = cap;
		this.city = city;
		this.cellulare = cellulare;
		this.username = username;
		this.password = password;
		this.nomeRuolo = nomeRuolo;
		this.codiceRuolo = codiceRuolo;
		this.matricolaUtente = matricolaUtente;
		this.matricolaRuolo = matricolaRuolo;
	}



	
	
	/* Getters and Setters */
	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getCodiceFiscale() {
		return codiceFiscale;
	}



	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}



	public String getNome() {
		return nome;
	}



	public void setNome(String nome) {
		this.nome = nome;
	}



	public String getCognome() {
		return cognome;
	}



	public void setCognome(String cognome) {
		this.cognome = cognome;
	}



	public String getSesso() {
		return sesso;
	}



	public void setSesso(String sesso) {
		this.sesso = sesso;
	}



	public Date getNascita() {
		return nascita;
	}



	public void setNascita(Date nascita) {
		this.nascita = nascita;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getVia() {
		return via;
	}



	public void setVia(String via) {
		this.via = via;
	}



	public Integer getCap() {
		return cap;
	}



	public void setCap(Integer cap) {
		this.cap = cap;
	}



	public String getCity() {
		return city;
	}



	public void setCity(String city) {
		this.city = city;
	}



	public String getCellulare() {
		return cellulare;
	}



	public void setCellulare(String cellulare) {
		this.cellulare = cellulare;
	}



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



	public String getNomeRuolo() {
		return nomeRuolo;
	}



	public void setNomeRuolo(String nomeRuolo) {
		this.nomeRuolo = nomeRuolo;
	}



	public Integer getCodiceRuolo() {
		return codiceRuolo;
	}



	public void setCodiceRuolo(Integer codiceRuolo) {
		this.codiceRuolo = codiceRuolo;
	}



	public Integer getMatricolaUtente() {
		return matricolaUtente;
	}



	public void setMatricolaUtente(Integer matricolaUtente) {
		this.matricolaUtente = matricolaUtente;
	}


	public Integer getMatricolaRuolo() {
		return matricolaRuolo;
	}


	public void setMatricolaRuolo(Integer matricolaRuolo) {
		this.matricolaRuolo = matricolaRuolo;
	}
	
	


}
