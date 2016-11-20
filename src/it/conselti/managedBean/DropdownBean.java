/*
 * Conselti s.r.l.
 */
package it.conselti.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedProperty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import it.conselti.domain.Anagrafica;
import it.conselti.domain.Ruolo;
import it.conselti.domain.TipoLavoro;
import it.conselti.service.AnagraficaService;
import it.conselti.service.RuoloService;
import it.conselti.service.TipoLavoroService;

/**
 * @author onofr
 *
 */

@Component(value="dropdownBean")
@Scope(value="view")
public class DropdownBean extends AbstractManagedBean implements Serializable {

	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 7562427776535412729L;



	@Autowired
	private TipoLavoroService lavoroService;
	
	@Autowired
	private RuoloService ruoloService;
	
	@Autowired
	private AnagraficaService anagraficaService;

	
	
	
	
    private List<TipoLavoro> tipoLavoro;
    private List<Ruolo> nomeRuoloUtente;
    private List<Anagrafica> listCognome;

    @ManagedProperty(value="descrizioneLavoro")
    private String descrizioneLavoro;
    
    @ManagedProperty(value="nameRuolo")
    private String nameRuolo;

    @ManagedProperty(value="idLavoroSelez")
    private Integer idLavoroSelez;

    @ManagedProperty(value="lavoroMap")
    private Map<Integer, String> lavoroMap;
    
    @ManagedProperty(value="monthMap")
    private Map<Integer, String> monthMap;
    
    @ManagedProperty(value="ruoloMap")
    private Map<Integer, String> ruoloMap;
    
    @ManagedProperty(value="anagraficaMap")
    private Map<String, String> anagraficaMap;

    
    

    
    
    protected void refreshList() {
    	setLavoroMap(new HashMap<Integer,String>());
		setTipoLavoro(new ArrayList<TipoLavoro>());
		setRuoloMap(new HashMap<Integer, String>());
		setNomeRuoloUtente(new ArrayList<Ruolo>());
		setMonthMap(new HashMap<Integer, String>());
		setListCognome(new ArrayList<Anagrafica>());
		setAnagraficaMap(new HashMap<String, String>());
    }



	@PostConstruct
    public void init() {
    	refreshList();
    	
    	nomeRuoloUtente = ruoloService.selectAllRuolo();
        tipoLavoro = lavoroService.selectAllTipoLavoro();
        listCognome = anagraficaService.selectNomeAndCognomeAnagrafica();
        
        
        /* dropdown Ruolo Utente */
        for(Ruolo r : nomeRuoloUtente) {
        	ruoloMap.put(r.getCodiceRuolo(), r.getDescrizioneRuolo());
        }
        /* dropdown Tipo Lavoro */
        for(TipoLavoro t : tipoLavoro) {
        	lavoroMap.put(t.getIdTipoLavoro(), t.getDescrizione());
        }
        
        /* dropdown Cognome */
        for(Anagrafica a : listCognome) {
        	anagraficaMap.put(a.getCognome(), a.getCognome());
        }
        

        /* dropdown Mese */
        Integer[] monthNumber = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
        String[] monthName = new String[] {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno",
            		"Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
            
            for(int i = 0; i < monthNumber.length; i++) {
            	monthMap.put(monthNumber[i], monthName[i]);
            }

            

        Iterator<Integer> iterator = lavoroMap.keySet().iterator();
        Iterator<Integer> iter = ruoloMap.keySet().iterator();
        
        while(iter.hasNext()) {
        	Integer key = (Integer) iter.next();
        	String value = ruoloMap.get(key);
        	
        	logger.info("Ruolo key: " + key + ", value: " + value);
        }

        while (iterator.hasNext()) {
           Integer key = (Integer) iterator.next();
           String value = lavoroMap.get(key);

          logger.info("TipoLavoro key: " + key + ", value: " + value);
        }
        
    }

    
    

	
	
	
	/* Getters and Setters */
    public String getNameRuolo() {
		return nameRuolo;
	}



	public void setNameRuolo(String nameRuolo) {
		this.nameRuolo = nameRuolo;
	}



	public Map<Integer, String> getMonthMap() {
		return monthMap;
	}



	public void setMonthMap(Map<Integer, String> monthMap) {
		this.monthMap = monthMap;
	}

    
    
	public List<TipoLavoro> getTipoLavoro() {
		return tipoLavoro;
	}


	public void setTipoLavoro(List<TipoLavoro> tipoLavoro) {
		this.tipoLavoro = tipoLavoro;
	}



	public Map<Integer, String> getLavoroMap() {
		return lavoroMap;
	}


	public void setLavoroMap(Map<Integer, String> lavoroMap) {
		this.lavoroMap = lavoroMap;
	}


	public Integer getIdLavoroSelez() {
		return idLavoroSelez;
	}


	public void setIdLavoroSelez(Integer idLavoroSelez) {
		this.idLavoroSelez = idLavoroSelez;
	}


	public void setLavoroService(TipoLavoroService lavoroService) {
		this.lavoroService = lavoroService;
	}


	public String getDescrizioneLavoro() {
		return descrizioneLavoro;
	}


	public void setDescrizioneLavoro(String descrizioneLavoro) {
		this.descrizioneLavoro = descrizioneLavoro;
	}


	public Map<Integer, String> getRuoloMap() {
		return ruoloMap;
	}


	public void setRuoloMap(Map<Integer, String> ruoloMap) {
		this.ruoloMap = ruoloMap;
	}

	public void setRuoloService(RuoloService ruoloService) {
		this.ruoloService = ruoloService;
	}


	public List<Ruolo> getNomeRuoloUtente() {
		return nomeRuoloUtente;
	}


	public void setNomeRuoloUtente(List<Ruolo> nomeRuoloUtente) {
		this.nomeRuoloUtente = nomeRuoloUtente;
	}

	
	@Override
	protected void injectBean() {
		// TODO Auto-generated method stub
		
	}

	public AnagraficaService getAnagraficaService() {
		return anagraficaService;
	}


	public void setAnagraficaService(AnagraficaService anagraficaService) {
		this.anagraficaService = anagraficaService;
	}


	public List<Anagrafica> getListCognome() {
		return listCognome;
	}


	public void setListCognome(List<Anagrafica> listCognome) {
		this.listCognome = listCognome;
	}


	public Map<String, String> getAnagraficaMap() {
		return anagraficaMap;
	}


	public void setAnagraficaMap(Map<String, String> anagraficaMap) {
		this.anagraficaMap = anagraficaMap;
	}
	
}
