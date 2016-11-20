/*
 * Conselti s.r.l.
 */
package it.conselti.domain;

import java.io.Serializable;

/**
 * @author onofr
 *
 */
public class Menu implements Serializable {

	private static final long serialVersionUID = -917196653954261813L;
	/**
	 * 
	 */

	
	private Integer idMenu;
	private String ruoloMenu;
	private String descrizioneMenu;
	private String urlMenu;
	private Integer padreMenu;
	private Integer sequenzaOrdineMenu;
	private String icona;
	
	
	public Menu()
	{
		super();
	}
	
	
	/**
	 * 
	 * @param id the id.
	 * @param ruolo the role
	 * @param descrizione the description.
	 * @param url the url.
	 * @param padre the father.
	 * @param sequenzaOrdine the order sequence.
	 * @param icon the icon.
	 */
	public Menu(Integer id, String ruolo, String descrizione, String url, Integer padre, Integer sequenzaOrdine, String icon) {
		this.idMenu = id;
		this.ruoloMenu = ruolo;
		this.descrizioneMenu = descrizione;
		this.urlMenu = url;
		this.padreMenu = padre;
		this.sequenzaOrdineMenu = sequenzaOrdine;
		this.setIcona(icon);
	}


	
	
	
	
	/* Getters and Setters */
	public Integer getId() {
		return idMenu;
	}


	public void setId(Integer id) {
		this.idMenu = id;
	}


	public String getRuolo() {
		return ruoloMenu;
	}


	public void setRuolo(String ruolo) {
		this.ruoloMenu = ruolo;
	}


	public String getDescrizione() {
		return descrizioneMenu;
	}


	public void setDescrizione(String descrizione) {
		this.descrizioneMenu = descrizione;
	}


	public String getUrl() {
		return urlMenu;
	}


	public void setUrl(String url) {
		this.urlMenu = url;
	}


	public Integer getPadre() {
		return padreMenu;
	}


	public void setPadre(Integer padre) {
		this.padreMenu = padre;
	}


	public Integer getSequenzaOrdine() {
		return sequenzaOrdineMenu;
	}


	public void setSequenzaOrdine(Integer sequenzaOrdine) {
		this.sequenzaOrdineMenu = sequenzaOrdine;
	}


	public String getIcona() {
		return icona;
	}


	public void setIcona(String icona) {
		this.icona = icona;
	}

}
