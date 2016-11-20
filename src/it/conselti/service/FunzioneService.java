/*
 * Conselti s.r.l.
 */
package it.conselti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.conselti.domain.Funzione;
import it.conselti.repository.FunzioneRepository;

/**
 * @author onofr
 *
 */

@Service
public class FunzioneService {
	
	private Funzione funzione;
	
	@Autowired
	private FunzioneRepository funzioneRepository;
	
	
	
	public List<Funzione> selectAllFunzione()
	{
		return funzioneRepository.findAllFunzione();
	}
	
	public Funzione selectFunzioneByCodiceUtente(Integer codice) {
		return funzioneRepository.findFunzioneByCodiceUtente(codice);
	}
	
	
	public List<Funzione> selectFunzioneByNomeRuolo(String nomeRuolo) {
		return funzioneRepository.findFunzioneByRuoloUtente(nomeRuolo);
	}
	
	
	@Transactional
	public void insertFunzione(Funzione funzione) throws Exception {
		funzioneRepository.saveFunzione(funzione);
	}
	
	
	@Transactional
	public void updateFunzione(Funzione funzione) {
		try {
			funzioneRepository.updateFunzione(funzione);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Transactional
	public void deleteFunzione(Funzione funzione) throws Exception {
		funzioneRepository.deleteFunzione(funzione);
	}

	
	
	
	
	
	
	/* Getters and Setters */
	public Funzione getFunzione() {
		return funzione;
	}

	public void setFunzione(Funzione funzione) {
		this.funzione = funzione;
	}

	/**
	 * 
	 * @param funzioneRepository the repository.
	 */
	public void setFunzioneRepository(FunzioneRepository funzioneRepository) {
		this.funzioneRepository = funzioneRepository;
	}
	
	
	

}
