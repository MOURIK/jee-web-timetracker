/*
 * Conselti s.r.l.
 */
package it.conselti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.conselti.domain.Ruolo;
import it.conselti.repository.RuoloRepository;

/**
 * @author onofr
 *
 */

@Service
public class RuoloService {
	
	private Ruolo ruolo;
	
	
	/**
	 * The public interface.
	 */
	@Autowired
	private RuoloRepository ruoloRepository;
	
	
	/**
	 * 
	 * @return the list.
	 */
	public List<Ruolo> selectAllRuolo()
	{
		return ruoloRepository.findAllRuolo();
	}
	
	/**
	 * 
	 * @param nome the input parameter.
	 * @return the Ruolo object.
	 */
	public Ruolo selectRuoloByName(String nome)
	{
		return ruoloRepository.findRuoloByName(nome);
	}
	
	
	/**
	 * 
	 * @param codice the input parameter.
	 * @return the Ruolo object.
	 */
	public Ruolo selectRuoloByCode(Integer codice)
	{
		return ruoloRepository.findRuoloByCode(codice);
	}
	
	
	
	/**
	 * 
	 * @param user the username.
	 * @param pass the password.
	 * @return a String.
	 */
	public String selectRuoloByUsernameAndPassword(String user, String pass) {
		return ruoloRepository.findRuoloByUsernameAndPassword(user, pass);
	}
	
	
	/**
	 * 
	 * @param user the username.
	 * @return a list.
	 */
	public List<String> selectRuoloByUsername(String user) {
		return ruoloRepository.findRuoloByUsername(user);
	}
	
	
	
	
	/**
	 * 
	 * @param username the username.
	 * @param psw the password.
	 * @return a string.
	 */
	public String selectDescrizioneRuoloByUsernameAndPassword(String username, String psw) {
		return ruoloRepository.findDescrizioneRuoloByUsernameAndPassword(username, psw);
	}
	
	
	@Transactional
	public void insertRuolo(Ruolo ruolo)
	{
		try {
			
			ruoloRepository.saveRuolo(ruolo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Transactional
	public void updateRuolo(Ruolo ruolo)
	{
		try {
			ruoloRepository.updateRuolo(ruolo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 * @param ruolo the role.
	 * @throws Exception if fails.
	 */
	@Transactional
	public void deleteRuolo(Ruolo ruolo) throws Exception {
		ruoloRepository.deleteRuolo(ruolo);
	}
	
	
	
	

	
	
	/* Getters and Setters */
	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	public void setRuoloRepository(RuoloRepository ruoloRepository) {
		this.ruoloRepository = ruoloRepository;
	}
	
	
	

}
