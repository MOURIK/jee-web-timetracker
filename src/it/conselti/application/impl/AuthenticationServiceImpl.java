/*
 * Conselti s.r.l.
 */
package it.conselti.application.impl;

import javax.annotation.Resource;
import javax.faces.context.FacesContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * @author onofr
 *
 */

@Service("authenticationService")
public class AuthenticationServiceImpl implements it.conselti.application.AuthenticationService {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Resource(name="authenticationManager")
	private AuthenticationManager authenticationManager; // specific for Spring Security

	
	@Override
	public boolean login(String username, String password) {
		try {
			Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			if (authenticate.isAuthenticated()) {
				SecurityContextHolder.getContext().setAuthentication(authenticate);				
				return true;
			}
		} catch (AuthenticationException e) {			
			logger.error("Error login " + e);
		}
		return false;
	}

	
	@Override
	public void logout() {
		SecurityContextHolder.getContext().setAuthentication(null);
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	}

	
	
	

	/* Getters and Setters */
	public AuthenticationManager getAuthenticationManager() {
		return authenticationManager;
	}


	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	

}