/*
 * Conselti s.r.l.
 */
package it.conselti.managedBean;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author onofr
 *
 */


public abstract class AbstractManagedBean implements Serializable {
	
	/**
	 * The Constant serialVersionUID.
	 */
	private static final long serialVersionUID = 2997831986609422028L;
	
	/**
	 * Logger variable logger.
	 */
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * The abstract method refreshList.
	 */
	protected abstract void refreshList();
	
	/**
	 * The abstract method injectBean.
	 */
	protected abstract void injectBean();
	
}
