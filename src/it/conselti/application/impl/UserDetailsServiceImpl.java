/*
 * Conselti s.r.l.
 */
package it.conselti.application.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.conselti.domain.Utente;
import it.conselti.service.RuoloService;
import it.conselti.service.UtenteService;


/**
 * @author onofr
 *
 */



/*
 * Spring-security requires an implementation of UserDetailService. 
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
		
	@Autowired
	private RuoloService ruoloService;
	
	@Autowired
	private UtenteService utenteService;
	
	
	
	private Utente utente;
	
	@PostConstruct
	public void init() {
		setUtente(new Utente());
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			  setUtente(utenteService.selectUtenteByUsernameDecoded(username));
		        if (utente == null) {
		            throw new NullPointerException("UserAccount for name \"" + username + "\" not found");
		            
		        } else if (utente.getEnabled().equals("false")) {
		        	throw new DisabledException("UserAccount for name \"" + username + "\" is disabled");
		        }

		        
		        boolean enabled = true;
		        boolean accountNonExpired = true;
		        boolean credentialsNonExpired = true;
		        boolean accountNonLocked = true;
		        
		        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		        List<String> roles = ruoloService.selectRuoloByUsername(username);
		        for (String role : roles) {
		            authorities.add(new SimpleGrantedAuthority(role));
		        }
		        return new User(username, this.utente.getPasswordUtente().toLowerCase(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	
	
	
	
	
	
	/* Getters and Setters */
	public void setRuoloService(RuoloService ruoloService) {
		this.ruoloService = ruoloService;
	}




	public void setUtenteService(UtenteService utenteService) {
		this.utenteService = utenteService;
	}




	public Utente getUtente() {
		return utente;
	}


	public void setUtente(Utente utente) {
		this.utente = utente;
	}



	/**
	 * Retrieves a collection of {@link GrantedAuthority} based on a numerical role
	 * @param role the numerical role
	 * @return a collection of {@link GrantedAuthority}
	 */
	public Collection<? extends GrantedAuthority> getAuthorities(Integer role) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(role));
		return authList;
	}
	
	
	
	/**
	 * Converts a numerical role to an equivalent list of roles
	 * @param role the numerical role
	 * @return list of roles as as a list of {@link String}
	 */
	public List<String> getRoles(Integer role) {
		List<String> roles = new ArrayList<String>();
		//roles.addAll(ruoloService.selectRuoloByUsername(utente.getUsernameUtente()));
		return roles;
	}
	
	
	
	/**
	 * Wraps {@link String} roles to {@link SimpleGrantedAuthority} objects
	 * @param roles {@link String} of roles
	 * @return list of granted authorities
	 */
	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}
	
	
}
	


