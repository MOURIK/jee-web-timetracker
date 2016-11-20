/*
 * Conselti s.r.l.
 */
package it.conselti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.conselti.domain.Assegna;
import it.conselti.repository.AssegnaRepository;

/**
 * @author onofr
 *
 */

@Service
public class AssegnaService {
	
	private Assegna assegna;
	
	@Autowired
	private AssegnaRepository assegnaRepository;
	
	
	/**
	 * 
	 * @param username the username.
	 * @param password the password.
	 * @return an Assegna object.
	 */
	public Assegna selectAssegnaByUserAndPsw(String username, String password) {
		return assegnaRepository.findAllAssegnaByUserAndPsw(username, password);
	}
	
	
	/**
	 * 
	 * @param assegna an Assegna object.
	 */
	@Transactional
	public void insertAssegna(Assegna assegna) {
		try {
			assegnaRepository.saveAssegna(assegna);
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	
	
	@Transactional
	public void updateAssegna(Assegna assegna) 
	{
		try {
			assegnaRepository.updateAssegna(assegna);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	@Transactional
	public void deleteAssegna(Assegna assegna) {
		try {
			assegnaRepository.deleteAssegna(assegna);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	/* Getters and Setters */
	public Assegna getAssegna() {
		return assegna;
	}

	public void setAssegna(Assegna assegna) {
		this.assegna = assegna;
	}

	/**
	 * 
	 * @param assegnaRepository the public repository.
	 */
	public void setAssegnaRepository(AssegnaRepository assegnaRepository) {
		this.assegnaRepository = assegnaRepository;
	}

	
}
