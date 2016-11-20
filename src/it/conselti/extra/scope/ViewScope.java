/*
 * Conselti s.r.l.
 */
package it.conselti.extra.scope;

/**
 *
 * @author onofr
 */

import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

/**
 * The Class ViewScope.
 */
public class ViewScope implements Scope {

	/**
	 * Gets the.
	 * 
	 * @param name
	 *            the name.
	 * @param objectFactory
	 *            the object factory.
	 * @return the object.
	 */
	@SuppressWarnings("rawtypes")
	public Object get(String name, ObjectFactory objectFactory) {
		Map<String, Object> viewMap = FacesContext.getCurrentInstance()
				.getViewRoot().getViewMap();

		if (viewMap.containsKey(name)) {
			return viewMap.get(name);
		} else {
			Object object = objectFactory.getObject();
			viewMap.put(name, object);

			return object;
		}
	}

	/**
	 * Removes the.
	 * 
	 * @param name
	 *            the name.
	 * @return the object.
	 */
	public Object remove(String name) {
		return FacesContext.getCurrentInstance().getViewRoot().getViewMap()
				.remove(name);
	}

	/**
	 * Gets the conversation id.
	 * 
	 * @return the conversation id.
	 */
	public String getConversationId() {
		return null;
	}

	/**
	 * Register destruction callback.
	 * 
	 * @param name
	 *            the name.
	 * @param callback
	 *            the callback.
	 */
	public void registerDestructionCallback(String name, Runnable callback) {
		// Not supported
	}

	/**
	 * Resolve contextual object.
	 * 
	 * @param key
	 *            the key.
	 * @return the object.
	 */
	public Object resolveContextualObject(String key) {
		return null;
	}
}
