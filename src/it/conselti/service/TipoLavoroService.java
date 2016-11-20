/*
 * Conselti s.r.l.
 */
package it.conselti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.conselti.domain.TipoLavoro;
import it.conselti.repository.TipoLavoroRepository;

/**
 * @author onofr
 *
 */

@Service
public class TipoLavoroService {
	
	private TipoLavoro tipoLavoro;

	
	@Autowired
	private TipoLavoroRepository tipoLavoroRepository;
	
	
	public List<TipoLavoro> selectAllTipoLavoro()
	{
		return tipoLavoroRepository.findAllTipoLavoro();
	}
	
	
	public TipoLavoro selectTipoLavoroByDescrizione(String descrizione)
	{
		return tipoLavoroRepository.findTipoLavoroByDescrizione(descrizione);
	}
	
	
	
	@Transactional
	public void insertTipoLavoro(TipoLavoro lavoro)
	{
		try {
			tipoLavoroRepository.saveTipoLavoro(lavoro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Transactional
	public void updateTipoLavoro(TipoLavoro lavoro)
	{
		try {
			tipoLavoroRepository.updateTipoLavoro(lavoro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	@Transactional
	public void deleteTipoLavoro(TipoLavoro lavoro)
	{
		try {
			tipoLavoroRepository.deleteTipoLavoro(lavoro);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	
	/* Getters and Setters */
	public TipoLavoro getTipoLavoro() {
		return tipoLavoro;
	}

	public void setTipoLavoro(TipoLavoro tipoLavoro) {
		this.tipoLavoro = tipoLavoro;
	}

	public void setTipoLavoroRepository(TipoLavoroRepository tipoLavoroRepository) {
		this.tipoLavoroRepository = tipoLavoroRepository;
	}
	
}
