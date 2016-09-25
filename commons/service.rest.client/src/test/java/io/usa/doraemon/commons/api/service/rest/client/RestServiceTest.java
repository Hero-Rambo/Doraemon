package io.usa.doraemon.commons.api.service.rest.client;


import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.usa.doraemon.commons.api.service.api.ServiceResult;
import io.usa.doraemon.commons.api.service.rest.client.impl.RestServiceClient;

/**
 * 
 * @author Rambo
 *
 */
public class RestServiceTest extends ServiceClientTest{
	String restUrl = "http://localhost:8080/microservice/services/rest/";
	int threads = 1;
	int ThreadSize = 1;
	
	public void testGet()throws Exception{
		List<Thread>  ts= new ArrayList<Thread>(threads);
		for(int i=0;i<threads;i++){
			Thread t = new Thread(new ServiceRunner(i));
			ts.add(t);
		}
		for(int i=0;i<threads;i++){			
			ts.get(i).start();
			System.out.println("........................."+i);
		}
	}
	
	public  static void main(String[]args){
		RestServiceTest t = new RestServiceTest();
		try {
			long start = System.nanoTime();
			
			t.testGet();
			long end = System.nanoTime();
			System.out.println((end-start)/1000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected Map<String, String> getHeaders() {
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("appId", "EMS");
		headers.put("appToken", "ssssssssss");
		headers.put("securityLevel", "4");		
		return headers;
	}

	public class ServiceRunner implements Runnable{
		private int index;
		public ServiceRunner(int i) {
			index = i;
 		}
		
		protected void test(){
			try {
				String json = "{'city':'Beijing','street':'Chaoyang Road','postcode':100025,'index':"+index+"}";		
				
				Map<String,String> headers = getHeaders();
				IRestServiceClient rest = new RestServiceClient(restUrl);
				String obj = rest.invokeByRest("EMS", "permission", "PermissionService", "v1", json,headers,"POST");
				ObjectMapper jackson = new ObjectMapper();
				
				ServiceResult serviceResult = jackson.readValue(obj, ServiceResult.class);
				
				System.out.println("result:="+serviceResult.getResult());
				System.out.println("index====================="+index+",result=="+obj);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			for(int i=0;i<ThreadSize;i++){
				try {
					String json = "{\"city\":\"Beijing+1               \",\"street\":\"Chaoyang Road\",\"postcode\":100025,\"index\":"+index+"}";		
					
					json = URLEncoder.encode(json, "UTF-8");
					json=json.replaceAll("\\+", "%20");
					System.out.println("json==="+json);
					Map<String,String> headers = getHeaders();
					IRestServiceClient rest = new RestServiceClient(restUrl);
					String obj = rest.invokeByRest("EMS", "permission", "PermissionService", "v1", json,headers,"POST");
					ObjectMapper jackson = new ObjectMapper();
					
					ServiceResult serviceResult = jackson.readValue(obj, ServiceResult.class);
					
					System.out.println("result:="+serviceResult.getResult());
					System.out.println("index====================="+index+",result=="+obj);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}


	
//	public void testPost()throws Exception{
//		Map<String,String> headers = getHeaders();
//		IRestServiceClient rest = new RestServiceClient(restUrl);
//		rest.invokeByRest("EMS", "permission", "PermissionService", "v1", json,headers,"POST");
//		
//	}
	
	
}
