/*
 * Conselti s.r.l.
 */
package it.conselti.domain;

import java.io.Serializable;

/**
 * @author onofr
 *
 */
public class TipoLavoro implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1022112631375470369L;
	
	
	private Integer idTipoLavoro;
	private String descrizione;
	
	
	/**
	 * 
	 */
	public TipoLavoro() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * 
	 * @param idTipoLavoro
	 * @param descrizione
	 */
	public TipoLavoro(Integer idTipoLavoro, String descrizione) {
		this.idTipoLavoro = idTipoLavoro;
		this.descrizione = descrizione;
	}



	
	/* Getters and Setters */
	public Integer getIdTipoLavoro() {
		return idTipoLavoro;
	}


	public void setIdTipoLavoro(Integer idTipoLavoro) {
		this.idTipoLavoro = idTipoLavoro;
	}


	public String getDescrizione() {
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
