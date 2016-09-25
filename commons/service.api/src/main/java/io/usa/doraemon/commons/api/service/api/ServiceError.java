package io.usa.doraemon.commons.api.service.api;

public class ServiceError {
	private String errorCode;
	private String errorMesssage;
	private ServiceContext context = new ServiceContext();

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

	public ServiceContext getContext() {
		return context;
	}

	public void setContext(ServiceContext context) {
		this.context = context;
	}

	public String getCategory() {
		return context.getCategory();
	}

	public void setCategory(String category) {
		context.setCategory(category);
	}

	public String getName() {
		return context.getName();
	}

	public void setName(String name) {
		context.setName(name);
	}

	public String getVersion() {
		return context.getVersion();
	}

	public void setVersion(String version) {
		context.setVersion(version);
	}

	public String getParams() {
		return context.getParams();
	}

	public void setParams(String params) {
		context.setParams(params);
	}

	public String getAppId() {
		return context.getAppId();
	}

	public void setAppId(String appId) {
		context.setAppId(appId);
	}

//	public String getAppToken() {
//		return context.getAppToken();
//	}
//
//	public void setAppToken(String appToken) {
//		context.setAppToken(appToken);
//	}

}
