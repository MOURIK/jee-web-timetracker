/*
 * Conselti s.r.l.
 */
package it.conselti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.conselti.domain.VisualizzaRegistrazione;
import it.conselti.repository.VisualizzaRegistrazioneRepository;


/**
 * 
 * @author onofr
 *
 */

@Service
public class VisualizzaRegistrazioneService {
	
	
	/**
	 * The public interface.
	 */
	@Autowired
	private VisualizzaRegistrazioneRepository visualizzaRepository;

	private VisualizzaRegistrazione admin;
	
	
	
	
	public List<VisualizzaRegistrazione> selectAll() {
		return visualizzaRepository.findAll();
	}
	
	
	
	
	public List<VisualizzaRegistrazione> selectAllRegistrazioneForAdminsAndSuperusers() {
		return visualizzaRepository.findAllRegistrazioneForAdminsAndSuperusers();
	}
	
	
	/**
	 * 
	 * @param usercode
	 * @return
	 */
	public List<VisualizzaRegistrazione> selectAllRegistrazioneForUsers(Integer usercode) {
		return visualizzaRepository.findAllRegistrazioneForUsers(usercode);
	}
	
	
	
	/**
	 * 
	 * @param period
	 * @return
	 */
	public List<VisualizzaRegistrazione> selectAllRegistrazioneByPeriodFiltered(VisualizzaRegistrazione period) {
		return visualizzaRepository.findRegistrazioneByPeriodFiltered(period);
	}
	
	
	
	
	public List<VisualizzaRegistrazione> selectAllRegistrazionePerPeriodo(VisualizzaRegistrazione periodic) {
		return visualizzaRepository.findAllRegistrazionePerPeriodo(periodic);
	}
	
	
	
	/**
	 * 
	 * @param admin
	 * @return
	 */
	public List<VisualizzaRegistrazione> selectAllRegistrazioneAdminFiltered(VisualizzaRegistrazione admin)
	{
		return visualizzaRepository.findRegistrazioneAdminFiltered(admin);
	}
	
	
	/**
	 * 
	 * @param user
	 * @param codiceUtente
	 * @return
	 */
	public List<VisualizzaRegistrazione> selectAllRegistrazioneUserFiltered(VisualizzaRegistrazione user, Integer codiceUtente)
	{
		return visualizzaRepository.findRegistrazioneUserFiltered(user, codiceUtente);
	}

	
	
	
	
	
	
	/* Getters and Setters */
	public VisualizzaRegistrazione getAdmin() {
		return admin;
	}

	public void setAdmin(VisualizzaRegistrazione admin) {
		this.admin = admin;
	}

	/**
	 * 
	 * @param visualizzaRepository
	 */
	public void setVisualizzaRepository(VisualizzaRegistrazioneRepository visualizzaRepository) {
		this.visualizzaRepository = visualizzaRepository;
	}
	
	
}
