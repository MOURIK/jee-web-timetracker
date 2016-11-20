/*
 * Conselti s.r.l.
 */
package it.conselti.application;

import javax.annotation.security.RolesAllowed;

/**
 * @author onofr
 *
 */


public interface AuthenticationService {
	
	public boolean login(String username, String password);

	@RolesAllowed({"ROLE_ADMIN","ROLE_USER", "ROLE_SUPERUSER"})
	public void logout();
}

