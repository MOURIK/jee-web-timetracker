/**
 * 
 */
package test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import it.conselti.domain.Ruolo;
import it.conselti.repository.RuoloRepository;

/**
 * @author onofr
 *
 */
public class RuoloIT extends BaseIT {
	
	@Resource
	@Qualifier("ruoloRepository")
	private RuoloRepository ruoloRepository;
	
	private Ruolo ruolo;
	
	
	
	
	
	
	@Before
	public void setUp() {
    	setRuolo(new Ruolo());
    
	}
	
	
	
	
	@Test
	@Transactional(readOnly=true, rollbackFor=NullPointerException.class)
	public void testGetAllRuoloByName() {
		ruolo = new Ruolo();
		ruolo = ruoloRepository.findRuoloByName("ROLE_DBA");
		
		
		assertNull(ruolo);
	}
	
	
	
	
	/**
	 * 
	 * @throws Exception if test fails.
	 */
	@Test
	@Transactional
	@Rollback
	public void testUpdateAndGetRuolo() throws Exception {
		ruolo = ruoloRepository.findRuoloByName("ROLE_ADMIN");
		assertTrue(!ruolo.equals(null));
		
		ruolo.setDescrizioneRuolo("Amministratore webapp");
		ruoloRepository.updateRuolo(ruolo);
		
		setRuolo(new Ruolo());
		ruolo = ruoloRepository.findRuoloByDescription("Amministratore webapp");
		assertNull(ruolo);
	}
	
	
	
	
	@Test
	@Transactional
	public void testDeleteRuolo() {
		ruolo = new Ruolo();
		ruolo = ruoloRepository.findRuoloByCode(2004);
		
		try {
			ruoloRepository.deleteRuolo(ruolo);
		} catch (final Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	@After
    public void destroy() {
    	ruolo = null;
    }
	
	
	
	
	
	
	
	
	public RuoloRepository getRuoloRepository() {
		return ruoloRepository;
	}

	public void setRuoloRepository(RuoloRepository ruoloRepository) {
		this.ruoloRepository = ruoloRepository;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

}
