/*
 * Conselti s.r.l.
 */
package test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import it.conselti.domain.Utente;
import it.conselti.service.UtenteService;


/**
 * @author onofr
 *
 */


public class UtenteIT extends BaseIT {

	@Autowired
	@Qualifier("userService")
	private UtenteService userService;
	
	private Utente utente;
 
	
	
	
    @Before
	public void setUp() {
    	setUserService(new UtenteService());
    	setUtente(new Utente());
    
	}
    
    
    
   
	@Test
	@Transactional
	@Rollback
	public void testSaveAndRemoveUtente() throws Exception {
		utente = new Utente();
		utente.setIdAnagrafica(5);
		utente.setUsernameUtente("integration");
		utente.setPasswordUtente("test");
		utente.setEnabled("false");

		assertNotNull(utente.getUsernameUtente());
		
		userService.insertUtente(utente);
		Assert.assertTrue(userService != null);
		
		Assert.assertEquals("test", utente.getPasswordUtente());
		Assert.assertEquals(utente.getIdAnagrafica(), utente.getIdAnagrafica());
		
		
		userService.deleteUtente(utente);
	}

	
	 
	
	/**
     * 
     * @throws Exception if test fails.
     */
    @Test(expected=Exception.class)
    @Transactional(rollbackFor=Exception.class)
    public void testUpdateUtente() throws Exception
    {
    	utente = userService.selectUtenteByUsername("integration");
    	utente.setPasswordUtente("junit_test");
    	utente.setEnabled("true");
    	
    	assertTrue("true" == utente.getEnabled());
    	
    	
    	
    	assertNotNull(utente);
    	Assert.assertEquals("junit_test", utente.getPasswordUtente());
    	userService.updateUtente(utente);
    }


    
    @After
    public void destroy() {
    	utente = null;
    	userService = null;
    }
    
    
    


	public UtenteService getUserService() {
		return userService;
	}




	public void setUserService(UtenteService userService) {
		this.userService = userService;
	}




	public Utente getUtente() {
		return utente;
	}




	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	
}
