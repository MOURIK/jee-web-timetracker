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
public class VisualizzaRegistrazione implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -2869881632453743968L;


	private Integer id;
	private String nome;
	private String cognome;
	private Integer totaleLavorate;
	private Integer totaleFerie;
	private Integer totalePermesso;
	private Integer totaleMalattia;
	private Integer totaleTrasferta;
	private Integer totale;
	private Integer codiceUtente;
	
	private Date dateFrom;
	private Date dateTo;
	private Integer mese;
	private Integer anno;


	/**
	 * 
	 */
	public VisualizzaRegistrazione() {
		super();
	}
	


	/**
	 * 
	 * @param id the id.
	 * @param nome the name.
	 * @param cognome the surname.
	 * @param totaleLavorate the total worked.
	 * @param totaleFerie the total holiday.
	 * @param totalePermesso the total permits.
	 * @param totaleMalattia the total illness.
	 * @param totaleTrasferta the total tranferts.
	 * @param codiceUtente the usercode.
	 * @param dateFrom the date from.
	 * @param dateTo the date to.
	 * @param mese the month.
	 * @param anno the year.
	 * @param totale the total.
	 */
	public VisualizzaRegistrazione(Integer id, String nome, String cognome, Integer totaleLavorate, Integer totaleFerie,
			Integer totalePermesso, Integer totaleMalattia, Integer totaleTrasferta, Integer codiceUtente,
			Date dateFrom, Date dateTo, Integer mese, Integer anno, Integer totale) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.totaleLavorate = totaleLavorate;
		this.totaleFerie = totaleFerie;
		this.totalePermesso = totalePermesso;
		this.totaleMalattia = totaleMalattia;
		this.totaleTrasferta = totaleTrasferta;
		this.codiceUtente = codiceUtente;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
		this.mese = mese;
		this.anno = anno;
		this.setTotale(totale);
	}





	/* Getters and Setters */
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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


	public Integer getTotaleFerie() {
		return totaleFerie;
	}


	public void setTotaleFerie(Integer totaleFerie) {
		this.totaleFerie = totaleFerie;
	}


	public Integer getTotalePermesso() {
		return totalePermesso;
	}


	public void setTotalePermesso(Integer totalePermesso) {
		this.totalePermesso = totalePermesso;
	}


	public Integer getTotaleMalattia() {
		return totaleMalattia;
	}


	public void setTotaleMalattia(Integer totaleMalattia) {
		this.totaleMalattia = totaleMalattia;
	}


	public Integer getTotaleTrasferta() {
		return totaleTrasferta;
	}


	public void setTotaleTrasferta(Integer totaleTrasferta) {
		this.totaleTrasferta = totaleTrasferta;
	}


	public Date getDateFrom() {
		return dateFrom;
	}


	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}


	public Date getDateTo() {
		return dateTo;
	}


	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}


	public Integer getMese() {
		return mese;
	}


	public void setMese(Integer mese) {
		this.mese = mese;
	}


	public Integer getAnno() {
		return anno;
	}


	public void setAnno(Integer anno) {
		this.anno = anno;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}




	public Integer getTotaleLavorate() {
		return totaleLavorate;
	}




	public void setTotaleLavorate(Integer totaleLavorate) {
		this.totaleLavorate = totaleLavorate;
	}




	public Integer getCodiceUtente() {
		return codiceUtente;
	}




	public void setCodiceUtente(Integer codiceUtente) {
		this.codiceUtente = codiceUtente;
	}




	public Integer getTotale() {
		return totale;
	}




	public void setTotale(Integer totale) {
		this.totale = totale;
	}


	
}
