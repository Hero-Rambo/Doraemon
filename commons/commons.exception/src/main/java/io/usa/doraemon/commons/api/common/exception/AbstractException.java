package io.usa.doraemon.commons.api.common.exception;

/**
 * 
 * @author Rambo
 * @date Jan 23, 2014
 */
abstract public class AbstractException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String errorCode;
	public AbstractException(String errorCode,Exception e) {
		super(e);
		this.errorCode = errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorCode() {
		return errorCode;
	}

}
