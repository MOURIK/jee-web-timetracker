/**
 * 
 */
package test.mockito;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.conselti.domain.Utente;
import it.conselti.repository.UtenteRepository;
import it.conselti.service.UtenteService;

/**
 * @author onofr
 *
 */

public class UtenteMockitoTest extends BaseMockitoTest {
	
	private static final long UserId = 123l;
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
    @InjectMocks
    @Autowired
    private UtenteService utenteService;

    @Mock
    private UtenteRepository utenteRepository;

    @Mock
    private Utente user;
	

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
	
	
	@Test
	@Transactional
	public void testSaveAndRemoveUtente() throws SQLException
	{
		this.user = new Utente();
		assertNotNull(user);
		
		this.user.setIdAnagrafica(5);
		this.user.setUsernameUtente("pippo");
		this.user.setPasswordUtente("pluto");
		
		assertNotNull("Username not null", user.getUsernameUtente());
		assertNotNull("Password not null", user.getPasswordUtente());
		assertNotNull("ID_anagrafica not null", user.getIdAnagrafica());

		assertNotNull(utenteService);
		utenteService.insertUtente(user);

		Assert.assertEquals("pippo", user.getUsernameUtente());
		Assert.assertEquals("pluto", user.getPasswordUtente());
		
		utenteService.deleteUtente(user);
			
	}
	
	
	public static long getUserid() {
		return UserId;
	}


	public Logger getLogger() {
		return logger;
	}


	public void setLogger(Logger logger) {
		this.logger = logger;
	}

}
