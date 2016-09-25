package io.usa.doraemon.commons.api.common.exception;

/**
 * 
 * @author Rambo
 * @date Jan 23, 2014
 */
public class TransportException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String TRANSPORT_ERROR = "TRANSPORT_ERROR";
	private String errorCode;
	
	
	public String getErrorCode() {
		return errorCode;
	}


	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}


	public TransportException(String errorCode,Exception e){
		super(e);
		this.errorCode = errorCode;
	}
}
