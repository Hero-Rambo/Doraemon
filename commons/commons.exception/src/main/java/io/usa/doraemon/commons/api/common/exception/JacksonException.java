package io.usa.doraemon.commons.api.common.exception;


/**
 * 
 * @author Rambo
 * @date Jan 23, 2014
 */
public class JacksonException extends Exception {
	private String errorCode;
 
	public JacksonException(String errorCode,Exception e) {
		super(e);
		this.errorCode = errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String WRITE_VALUE_AS_STRING_ERROR = "JACKSON_WRITE_VALUE_AS_STRING_ERROR";
	public static final String READ_VALUE_ERROR = "JACKSON_READ_VALUE_ERROR";
}
