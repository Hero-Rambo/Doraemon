package io.usa.doraemon.commons.api.service.api.exception;

import io.usa.doraemon.commons.api.common.exception.AbstractException;

/**
 * 
 * @author Rambo
 * @date Jan 23, 2014
 */
public class ServiceException extends AbstractException {

	public ServiceException(String errorCode, Exception e) {
		super(errorCode, e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String NO_SERVICE_NODE_AVAILABLE = "NO_SERVICE_NODE_AVAILABLE";

}
