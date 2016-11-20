/*
 * Conselti s.r.l.
 */
package test.mockito;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import static org.mockito.Mockito.when;

import it.conselti.application.impl.AuthenticationServiceImpl;
import it.conselti.managedBean.HeaderBean;
import it.conselti.managedBean.LoginBean;

/**
 * @author onofr
 *
 */


public class LoginMockitoTest extends BaseMockitoTest {
	
	private LoginBean loginBean;
	private HeaderBean headerBean;
	private UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken;
	
	
	@Mock
	private AuthenticationManager authenticationManager;
	
	@Mock
	private AuthenticationServiceImpl authenticationService;
	
	@Mock
	private Authentication authentication;
	
	
	
	
	
	
	@Before
    public void setUp() {
        loginBean = new LoginBean();
        headerBean = new HeaderBean();
        authentication = new TestingAuthenticationToken(new Object(), new Object());
        setUsernamePasswordAuthenticationToken(new UsernamePasswordAuthenticationToken("tester", "tester"));
        loginBean.setUsername("tester");
        loginBean.setPassword("tester");
        loginBean.setAuthenticationService(authenticationService);
        authentication.setAuthenticated(true);
    }

	
    @After
    public void destroy() {
    }


    
    @Test
    public void testLogin() throws Exception {
    	when(authenticationManager.authenticate(usernamePasswordAuthenticationToken)).thenReturn(authentication);
    	loginBean.effettuaLogin();
    }
    
    
    @Test(expected=Exception.class)
    public void testLogout() throws Exception {
    	headerBean.effettuaLogout();
    }
    
    
    
    
    
    
    
	public UsernamePasswordAuthenticationToken getUsernamePasswordAuthenticationToken() {
		return usernamePasswordAuthenticationToken;
	}


	public void setUsernamePasswordAuthenticationToken(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
		this.usernamePasswordAuthenticationToken = usernamePasswordAuthenticationToken;
	}


}
