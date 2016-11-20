/*
 * Conselti s.r.l.
 */
package test.mockito;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import it.conselti.domain.Menu;
import it.conselti.repository.MenuRepository;
import it.conselti.service.MenuService;
import junit.framework.Assert;

/**
 * @author onofr
 *
 */

public class MenuMockitoTest extends BaseMockitoTest {
	
	private static final long UserId = 123l;
	
	
	@InjectMocks
    @Autowired
    private MenuService menuService;

    @Mock
    private MenuRepository menuRepository;

    @Spy
    private Menu menu;
	

    @Before
    public void init() {
    	MockitoAnnotations.initMocks(this);
    	menuService = Mockito.mock(MenuService.class);
    	menu = Mockito.mock(Menu.class);
    }

    
    
    @Test
    @Transactional
    public void testRemoveAndSaveFunzione() throws Exception {
    	
    	menu = menuService.selectMenuByDescription("Registrazione");
    	menuService.deleteMenu(menu);
    	Assert.assertEquals(null, menu);
    	
    	menu = new Menu();
    	menu.setDescrizione("MENU-TEST");
    	menu.setUrl("#");
    	menu.setPadre(null);
    	menu.setSequenzaOrdine(29);
    	menu.setIcona(null);
    	
    	
    	Assert.assertNotNull(menu);
    	menuService.insertMenu(menu);
    }
    
    
    
    @After
    public void destroy() {
    }

   

	public static long getUserid() {
		return UserId;
	}

}
