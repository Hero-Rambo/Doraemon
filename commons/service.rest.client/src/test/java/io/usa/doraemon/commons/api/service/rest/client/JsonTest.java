package io.usa.doraemon.commons.api.service.rest.client;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonTest {
//	String json = "{'category':'permission','name':'PermissionService','version':'v1','params':{'city':'Beijing','street':' Chaoyang Road ','postcode':100025}}";

	String json = "{category:\"permission\",name:\"PermissionService\",version:\"v1\"}";
	ObjectMapper mapper = new ObjectMapper();
	@Test
	public void testJson()throws Exception{
		ServiceInfo s = new ServiceInfo();
		s.setCategory("permission");
		s.setName("PermissionService");
		s.setVersion("v1");
		s.setParams("{\"city\":\"Beijing\",\"street\":\" Chaoyang Road \",\"postcode\":100025}");
		String r = mapper.writeValueAsString(s);		
		System.out.println(r);
		ServiceInfo si = mapper.readValue(r, ServiceInfo.class);
		String r1 = mapper.writeValueAsString(si);		
		System.out.println(r1);
		
	}
}

class ServiceInfo{
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
	
	
}
