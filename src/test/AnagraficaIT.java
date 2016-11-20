/**
 * 
 */
package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import it.conselti.domain.Anagrafica;
import it.conselti.service.AnagraficaService;
import junit.framework.Assert;


/**
 * @author onofr
 *
 */

public class AnagraficaIT extends BaseIT {
	
	private Anagrafica anagrafica;
	
	private AnagraficaService anagraficaService;
	
	@Autowired
	public void setAnagraficaService(AnagraficaService anagraficaService) {
		this.anagraficaService = anagraficaService;
	}

	
	
	
	
	
	@Before
	public void setUp() {
		setAnagrafica(new Anagrafica());
		setAnagraficaService(new AnagraficaService());
	}
	
	
	
	
	
	@Test(expected=Exception.class)
	@Transactional(rollbackFor=Exception.class)
	@Rollback
	public void testDeleteUpdateAndGetAnagrafica() throws Exception {
		anagrafica = anagraficaService.selectAllById(2);
		Assert.assertNotNull(anagrafica);
		
		anagraficaService.deleteAnagrafica(anagrafica);
		
		anagrafica.setEmail("TEST@MAIL.COM");
		anagrafica.setVia("TEST 1");
		anagrafica.setCap(80000);
		anagrafica.setCity("test city");
		anagrafica.setCellulare("(999) 999-9999");
		
		Assert.assertNotNull(anagrafica);
		Assert.assertSame("test city", anagrafica.getCity());
		
		
		anagraficaService.updateAnagrafica(anagrafica);
		
		List<Anagrafica> temp = new ArrayList<Anagrafica>();
		temp.addAll(anagraficaService.selectAllAnagrafica());
		
		assertFalse(temp.isEmpty());
		assertNotNull(temp);
	}
	
	
	
	
	
	
	
	@After
    public void destroy() {
    	anagrafica = null;
    	anagraficaService = null;
    }
    
	
	
	
	
	
	

	public Anagrafica getAnagrafica() {
		return anagrafica;
	}






	public void setAnagrafica(Anagrafica anagrafica) {
		this.anagrafica = anagrafica;
	}






	public AnagraficaService getAnagraficaService() {
		return anagraficaService;
	}

}
