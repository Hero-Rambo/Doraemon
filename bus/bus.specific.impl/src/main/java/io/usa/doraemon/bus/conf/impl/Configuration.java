package io.usa.doraemon.bus.conf.impl;

import java.util.ArrayList;
import java.util.List;

import io.usa.doraemon.bus.conf.IConfiguration;
import org.springframework.stereotype.Component;

/**
 * 
 * @author Rambo
 *
 */
@Component
public class Configuration implements IConfiguration {
	/**
	 * 用于处理服务调用的通用安全处理逻辑
	 */
	private List<String> commonFiltersChain = new ArrayList<String>();

	/**
	 * REST安全处理
	 */
	private List<String> restFiltersChain = new ArrayList<String>();
	
	/**
	 * SOAP安全处理
	 */
	private List<String> soapFiltersChain = new ArrayList<String>();

	public Configuration(){
		init();
	}
	
	protected void init(){
		commonFiltersChain.add("bus.filter.permission");
		commonFiltersChain.add("bus.filter.security");
	}
	
	
	public List<String> getCommonFiltersChain() {
		return commonFiltersChain;
	}
 
	public List<String> getRestFiltersChain() {
		return restFiltersChain;
	}
 

	public List<String> getSoapFiltersChain() {
		return soapFiltersChain;
	}
	
	
	public void setCommonFiltersChain(List<String> commonFiltersChain) {
		this.commonFiltersChain = commonFiltersChain;
	}

	public void setRestFiltersChain(List<String> restFiltersChain) {
		this.restFiltersChain = restFiltersChain;
	}

	public void setSoapFiltersChain(List<String> soapFiltersChain) {
		this.soapFiltersChain = soapFiltersChain;
	}

	@Override
	public boolean isRemoteServicePriority() {
		return true;
	}

	@Override
	public String getRouteAlgorithm() {
		return "roundRoubin";
	}

	private String serviceLogger = "defaultServiceLogger";
	@Override
	public String getServiceLogger() {
		return serviceLogger;
	}

	public void setServiceLogger(String serviceLogger) {
		this.serviceLogger = serviceLogger;
	}
	
	
	
}
