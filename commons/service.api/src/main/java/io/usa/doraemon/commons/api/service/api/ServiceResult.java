package io.usa.doraemon.commons.api.service.api;


/**
 * 
 * @author Rambo
 * @date Jan 23, 2014
 */
public class ServiceResult {
	private String errorCode;
	private String errorMesssage;
//	private ServiceContext context = new ServiceContext();
	private Object result;

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMesssage() {
		return errorMesssage;
	}

	public void setErrorMesssage(String errorMesssage) {
		this.errorMesssage = errorMesssage;
	}

	 
}
