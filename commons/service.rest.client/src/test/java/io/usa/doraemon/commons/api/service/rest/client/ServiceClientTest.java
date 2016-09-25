package io.usa.doraemon.commons.api.service.rest.client;


import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = "classpath:service-test-beans.xml")
public class ServiceClientTest extends TestCase{
	String json = "{\"city\":\"Beijing\",\"street\":\" Chaoyang Road \",\"postcode\":100025}";		
	
    public void test(){
    	
    }
    
	protected Map<String, String> getHeaders() {
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("appId", "EMS");
		headers.put("appToken", "ssssssssss");
		headers.put("securityLevel", "2");		
		return headers;
	}
}
