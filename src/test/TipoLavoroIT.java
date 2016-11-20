/**
 * 
 */
package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import it.conselti.domain.TipoLavoro;
import it.conselti.service.TipoLavoroService;



/**
 * @author onofr
 *
 */

public class TipoLavoroIT extends BaseIT {
	
	private TipoLavoro tipoLavoro;
	
	@Autowired
	private TipoLavoroService tipoLavoroService;
	
	
	
	
	
	@Before
	public void setUp() {
    	tipoLavoroService = new TipoLavoroService();
    	tipoLavoro = new TipoLavoro();
    
	}
	
	
	
	
	
	
	@Test
	@Transactional
	@Rollback
	public void testInsertAndUpdate() {
		tipoLavoro = new TipoLavoro();
		tipoLavoro.setIdTipoLavoro(6);
		tipoLavoro.setDescrizione("Lavoro_test");
		
		assertNotNull(tipoLavoro);
		tipoLavoroService.insertTipoLavoro(tipoLavoro);
		
		
		tipoLavoro.setDescrizione("LAVORO-TEST");
		assertTrue(tipoLavoro != null);
		
		tipoLavoroService.updateTipoLavoro(tipoLavoro);
	}


	
	@Test
	@Transactional
	public void testDeleteTipoLavoro() {
		assertNotNull(tipoLavoro);
		tipoLavoroService.deleteTipoLavoro(tipoLavoro);
	}
	
	
	
	
	@After
	public void destroy() 
	{
		tipoLavoro = null;
		tipoLavoroService = null;
	}
	

	
	
	
	
	
	public TipoLavoroService getTipoLavoroService() {
		return tipoLavoroService;
	}

	public void setTipoLavoroService(TipoLavoroService tipoLavoroService) {
		this.tipoLavoroService = tipoLavoroService;
	}

	public TipoLavoro getTipoLavoro() {
		return tipoLavoro;
	}

	public void setTipoLavoro(TipoLavoro tipoLavoro) {
		this.tipoLavoro = tipoLavoro;
	}

}
