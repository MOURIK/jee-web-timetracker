/*
 * Conselti s.r.l.
 */
package it.conselti.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.conselti.domain.Utente;
import it.conselti.repository.UtenteRepository;

/**
 * @author onofr
 *
 */



@Service
public class UtenteService {

	private Utente utente;

	
	/**
	 * The public interface.
	 */
    @Autowired
	private UtenteRepository utenteRepository;



    /**
     * 
     * @return the list.
     */
	public List<Utente> selectAllUtente()
	{
		return utenteRepository.findAllUtente();
	
	}

	
	/**
	 * 
	 * @param username the input username.
	 * @param password the input password.
	 * @return
	 */
	public Utente selectUtenteCredentials(String username, String password)
	{
		return utenteRepository.findUtenteByCredentials(username, password);
	}

	
	/**
	 * 
	 * @param user the input parameter.
	 * @return an Utente object.
	 */
	public Utente selectUtenteByUsername(String user)
	{
		return utenteRepository.findUtenteByUsername(user);
	}
	
	
	
	
	public Utente selectUtenteByUsernameDecoded(String user)
	{
		return utenteRepository.findUtenteByUsernameDecoded(user);
	}


	
	
	/**
	 * 
	 * @param pass the input password.
	 * @return an Utente object.
	 */
	public Utente selectUtenteByPassword(String pass)
	{
		return utenteRepository.findUtenteByPassword(pass);
	}


	
	/**
	 * 
	 * @param utente the input Utente object.
	 */
    @Transactional
	public void insertUtente(Utente utente)
	{
		try {
			utenteRepository.saveUtente(utente);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


    @Transactional
	public void updateUtente(Utente utente)
	{
		try {
			utenteRepository.updateUtente(utente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    
    /**
	 * 
	 * @param utente the input Utente object.
	 */
    @Transactional
	public void updatePasswordUtente(Utente utente) {
		try {
			utenteRepository.updatePasswordUtente(utente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

    
    /**
	 * 
	 * @param utente the input Utente object.
	 */
    @Transactional
	public void deleteUtente(Utente utente)
	{
		try {
			utenteRepository.deleteUtente(utente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


    
    
    
    
    
    /* Getters and Setters */
	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}


	public void setUtenteRepository(UtenteRepository utenteRepository) {
		this.utenteRepository = utenteRepository;
	}



}
