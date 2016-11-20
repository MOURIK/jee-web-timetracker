/*
 * Conselti s.r.l.
 */
package it.conselti.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.conselti.domain.Registrazione;
import it.conselti.domain.VisualizzaRegistrazione;
import it.conselti.repository.RegistrazioneRepository;

/**
 * @author onofr
 *
 */

@Service
public class RegistrazioneService {
	
	private Registrazione registrazione;

	@Autowired
	private RegistrazioneRepository registrazioneRepository;
	
	
	/**
	 * 
	 * @return
	 */
	public List<Registrazione> selectAllRegistrazione()
	{
		return registrazioneRepository.findAllRegistrazione();
	}
	
	
    /**
     * 
     * @param userCode
     * @return
     */
	public List<Registrazione> selectRegistrazioneByUserCode(Integer userCode) {
		return registrazioneRepository.findAllRegistrazioneByUserCode(userCode);
	}
	
	
	
	public List<Registrazione> selectAllRegistrazioneByYearAndMonth(Integer usercode, VisualizzaRegistrazione reg) {
		return registrazioneRepository.findAllRegistrazioneByYearAndMonth(usercode, reg);
	}
	
	
	
	public List<Registrazione> selectAllRegistrazioneByFromAndTo(Integer usercode, VisualizzaRegistrazione visual) {
		return registrazioneRepository.findAllRegistrazioneByFromAndTo(usercode, visual);
	}
	
	
	
	/**
	 * 
	 * @param registrazione
	 */
	@Transactional
	public void insertRegistrazione(Registrazione registrazione)
	{
		try {
			registrazioneRepository.saveRegistrazione(registrazione);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @param selectedRegistrazione
	 */
	@Transactional
	public void updateRegistrazione(Registrazione selectedRegistrazione)
	{
		try {
			registrazioneRepository.updateRegistrazione(selectedRegistrazione);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @param selectedRegistrazione
	 */
	@Transactional
	public void deleteRegistrazione(Registrazione selectedRegistrazione)
	{
		try {
			registrazioneRepository.deleteRegistrazione(selectedRegistrazione);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	


	
	
	/* Getters and Setters */
	public Registrazione getRegistrazione() {
		return registrazione;
	}


	public void setRegistrazione(Registrazione registrazione) {
		this.registrazione = registrazione;
	}


	/**
	 * 
	 * @param registrazioneRepository
	 */
	public void setRegistrazioneRepository(RegistrazioneRepository registrazioneRepository) {
		this.registrazioneRepository = registrazioneRepository;
	}


}
