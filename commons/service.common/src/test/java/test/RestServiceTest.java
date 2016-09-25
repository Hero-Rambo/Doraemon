package test;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import io.usa.doraemon.commons.api.service.rest.client.IRestServiceClient;
import io.usa.doraemon.commons.api.service.rest.client.impl.RestServiceClient;

public class RestServiceTest  {
	String json = "{\"city\":\"Beijing\",\"street\":\" Chaoyang Road \",\"postcode\":100025}";		

	String restUrl = "http://localhost:8090/bus/services/rest/";
	int threads = 1;
	int ThreadSize = 1;
	
	@Test
	public void testService(){
		try {
//			NodeRegisterMessage m = new NodeRegisterMessage();
			String json1 = "{\"host\":\"localhost\",\"url\":\"http://localhost:8080/service\",\"name\":null,\"description\":null,\"transportInfos\":{\"XSocket\":9100},\"services\":[{\"category\":\"manage\",\"name\":\"ExecuteCommandService\",\"version\":\"v1\"},{\"category\":\"permission\",\"name\":\"PermissionService\",\"version\":\"v1\"},{\"category\":\"test\",\"name\":\"TestService\",\"version\":\"v1\"}],\"status\":\"Start\",\"weight\":0,\"availableTransport\":\"XSocket\"}";
//			String json = "{'city':'Beijing  ','street':'Chaoyang Road','postcode':100025,'index':1}";		
			Map<String,String> headers = getHeaders();
			IRestServiceClient rest = new RestServiceClient(restUrl);
			Object obj = rest.invokeByRest("EMS", "bus", "ServiceNodeRegisterService", "v1", json1,headers,"POST");
			System.out.println(obj);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private Map<String, String> getHeaders() {
		// TODO Auto-generated method stub
		return null;
	}


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
	
 

	public class ServiceRunner implements Runnable{
		private int index;
		public ServiceRunner(int i) {
			index = i;
 		}
		
		protected void test(){
			try {
				String json = "{'city':'Beijing               ','street':'Chaoyang Road','postcode':100025,'index':"+index+"}";		
				Map<String,String> headers = getHeaders();
				IRestServiceClient rest = new RestServiceClient(restUrl);
				Object obj = rest.invokeByRest("EMS", "permission", "PermissionService", "v1", json,headers,"GET");
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
					String json = "{'city':'Beijing               ','street':'Chaoyang Road','postcode':100025,'index':"+index+"}";		
					Map<String,String> headers = getHeaders();
					IRestServiceClient rest = new RestServiceClient(restUrl);
					Object obj = rest.invokeByRest("EMS", "permission", "PermissionService", "v1", json,headers,"GET");
					System.out.println("index====================="+index+",loop========="+i+",result=="+obj);
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
