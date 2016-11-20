/*
 * Conselti s.r.l.
 */
package it.conselti.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.conselti.domain.Anagrafica;
import it.conselti.repository.AnagraficaRepository;


/**
 * @author onofr
 *
 */


@Service
public class AnagraficaService {
	
	private Anagrafica anagrafica;

	/**
	 * The public interface/repository.
	 */
	@Autowired
	private AnagraficaRepository anagraficaRepository;
	
	
	
	
	public List<Anagrafica> selectNomeAndCognomeAnagrafica() {
		return anagraficaRepository.findCognomeAndNomeAnagrafica();
	}
	
	
	/**
	 * 
	 * @param userCode the usercode.
	 * @return the Anagrafica list.
	 */
	public List<Anagrafica> selectAllAnagraficaByCodiceUtente(Integer userCode) {
		return anagraficaRepository.findAllAnagraficaByCodiceUtente(userCode);
	}
	

	/**
	 * 
	 * @param user the String.
	 * @return a String.
	 */
	public String selectCognomeByUsername(String user)
	{
		return anagraficaRepository.findCognomeByUsername(user);
	}
	
	
	
	
	public Anagrafica selectAllById(Integer id)
	{
		return anagraficaRepository.findAllAnagraficaById(id);
	}
	
	
	
	
	/**
	 * 
	 * @param user the input string.
	 * @return a string.
	 */
	public String selectNomeByUsername(String user)
	{
		return anagraficaRepository.findNomeByUsername(user);
	}
	
	
	/**
	 * 
	 * @return a List.
	 */
	public List<Anagrafica> selectAllAnagraficaUtente() {
		return anagraficaRepository.findAllAnagraficaUtente();
	}
	
	

	/**
	 * 
	 * @return the List.
	 */
	public List<Anagrafica> selectAllAnagrafica()
	{
		return anagraficaRepository.findAllAnagrafica();
	}
	
	
	
	
	

	/**
	 * 
	 * @param anagrafica the domain Anagrafica.
	 * @throws Exception the exception.
	 */
	@Transactional
	public void insertAnagrafica(Anagrafica anagrafica) throws Exception
	{
		anagraficaRepository.saveAnagrafica(anagrafica);
	}
	
	
	
	/**
	 * 
	 * @param anagrafica the user's anagrafica.
	 */
	@Transactional
	public void updateAnagrafica(Anagrafica anagrafica)
	{
		try {
			anagraficaRepository.updateAnagrafica(anagrafica);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	/**
	 * 
	 * @param anagrafica the user's anagrafica.
	 */
	@Transactional
	public void deleteAnagrafica(Anagrafica anagrafica) {
		try {
			anagraficaRepository.deleteAnagrafica(anagrafica);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	
	
	
	
	/* Getters and Setters */
	public Anagrafica getAnagrafica() {
		return anagrafica;
	}


	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}


	/**
	 * 
	 * @param anagraficaRepository the public interface.
	 */
	public void setAnagraficaRepository(AnagraficaRepository anagraficaRepository) {
		this.anagraficaRepository = anagraficaRepository;
	}
	
	
}
