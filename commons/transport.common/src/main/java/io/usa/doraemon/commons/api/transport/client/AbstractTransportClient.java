package io.usa.doraemon.commons.api.transport.client;

import io.usa.doraemon.commons.api.common.exception.JacksonException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


/**
 * 
 * @author Rambo
 * @date Jan 23, 2014
 */
abstract public class AbstractTransportClient implements ITransportClient,DisposableBean,InitializingBean {
	private int port;
	private String host;

	@Autowired
	private ObjectMapper jackson = new ObjectMapper();

	public AbstractTransportClient() {
		
 	}

	
	public AbstractTransportClient(String host,int port){
		this.host = host;
		this.port = port;
	}
	


	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}


	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	abstract protected String getTransportType();

	
	
	

//	@Override
//	public String transport(String category, String name, String version,
//			String params) throws Exception {
//		StringBuilder sb = this.buildJson(category, name, version, params);
//		return this.transport(sb.toString());
//	}
//	
//	protected StringBuilder buildJson(String category, String name, String version,
//			String params){
//		StringBuilder sb = new StringBuilder();
//		sb.append('{');
//		this.buildJson("category", category, sb);
//		this.buildJson("name", name, sb);
//		this.buildJson("version", version, sb);
//		this.buildJson("params", params, sb);
////		if(headers!=null && !headers.isEmpty()){
////			this.buildJson("headers", headers, sb);
////		}
//		sb.append('}');
//		return sb;
//	}
//	
//	protected void buildJson(String key, Map<String, String> headers,
//			StringBuilder sb) {
//		sb.append('\'');
//		sb.append(key);
//		sb.append("':");
//		{
//			sb.append('{');		
//			for(Entry<String,String> entry:headers.entrySet()){
//				this.buildJson(entry.getKey(), entry.getValue(), sb);
//			}
//			sb.append('}');
//		}
//		sb.append(',');
//		
//	}
//
//	protected void buildJson(String key,String value,StringBuilder sb){
//		sb.append('\'');
//		sb.append(key);
//		sb.append("':");
//		sb.append(value);
//		sb.append(',');		
//	}

	protected void init(){
		
	}

	@Override
	public void afterPropertiesSet() throws Exception {
 		init();
	}

	protected void close(){
		
	}

	@Override
	public void destroy() throws Exception {
 		close();
	}

	protected String getStringValue(Object obj)throws JacksonException {
		try {
			return jackson.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new JacksonException(JacksonException.WRITE_VALUE_AS_STRING_ERROR,e);
		}
 	}

 

	
	
	
}
