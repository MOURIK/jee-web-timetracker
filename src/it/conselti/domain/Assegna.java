/*
 * Conselti s.r.l.
 */
package it.conselti.domain;

import java.io.Serializable;

/**
 * @author onofr
 *
 */

 public class Assegna implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -89977626068281795L;

  private Integer matricolaUtente;
  private Integer matricolaRuolo;


  public Assegna()
  {
    super();
  }


  /**
   * 
   * @param matricola the roll id.
   * @param codice the code.
   */
  public Assegna(Integer matricola, Integer codice)
  {
    this.setMatricolaUtente(matricola);
    this.setMatricolaRuolo(codice);
  }
  
  
  
  
  
  
  /* Getters and Setters */
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