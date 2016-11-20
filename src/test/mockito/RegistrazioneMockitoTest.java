/**
 * 
 */
package test.mockito;

import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import it.conselti.domain.Registrazione;
import it.conselti.repository.RegistrazioneRepository;
import it.conselti.service.RegistrazioneService;

/**
 * @author onofr
 *
 */


public class RegistrazioneMockitoTest extends BaseMockitoTest {
	
private static final long UserId = 123l;
	
	
	@InjectMocks
    @Autowired
    private RegistrazioneService registrazioneService;

    @Mock
    private RegistrazioneRepository registrazioneRepository;

    @Spy
    private Registrazione registrazione;
    
    
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        registrazioneService = Mockito.mock(RegistrazioneService.class);
        registrazioneRepository = Mockito.mock(RegistrazioneRepository.class);
        registrazione = Mockito.mock(Registrazione.class);

    }
    
    
    
    @Test
    @Transactional
    public void testInsertAndUpdateRegistrazione() throws Exception 
    {
    	Calendar c = new GregorianCalendar(2016,04,30);
    	Date d = c.getTime();
    	registrazione.setData(d);
        registrazione.setPermesso(2);
        registrazione.setTrasferta(6);
    	
    	assertNotNull(registrazione);
    	registrazioneService.insertRegistrazione(registrazione);
    	registrazione = null;
    	
    	c = new GregorianCalendar(2016,04,29);
    	d = c.getTime();
    	registrazione.setData(d);
    	registrazioneService.updateRegistrazione(registrazione);
    }
    

	public static long getUserid() {
		return UserId;
	}

}
