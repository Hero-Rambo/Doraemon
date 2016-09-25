package io.usa.doraemon.commons.api.service.api;

public class ServiceContext {
	private String appId;
//	private String appToken;
	private String category;
	private String name;
	private String version;
	private String params;
	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
//
//	public String getAppToken() {
//		return appToken;
//	}
//
//	public void setAppToken(String appToken) {
//		this.appToken = appToken;
//	}

}
