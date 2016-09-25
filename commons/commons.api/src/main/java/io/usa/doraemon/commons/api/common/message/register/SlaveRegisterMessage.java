package io.usa.doraemon.commons.api.common.message.register;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * @author Rambo
 *
 */
public class SlaveRegisterMessage extends RegisterMessage{
	/**
	 * domain name or ip
	 */
	private String host;
	
	/**
	 * 唯一
	 */
	private String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	private String name;
	private String description;

	/**
	 * key:transport Type, for exampel,XSocket,Avro,Thrift,Netty port:transport
	 * port,common tcp port.
	 */
	private Map<String, Integer> transportInfos = new HashMap<String, Integer>();
	
	private List<ServiceMetadata> services = new ArrayList<ServiceMetadata>();
	
	private String status;
	
	private int weight;

	private String availableTransport;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Map<String, Integer> getTransportInfos() {
		return transportInfos;
	}

	public void setTransportInfos(Map<String, Integer> transportInfos) {
		this.transportInfos = transportInfos;
	}

	public List<ServiceMetadata> getServices() {
		return services;
	}

	public void setServices(List<ServiceMetadata> services) {
		this.services = services;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getAvailableTransport() {
		return availableTransport;
	}

	public void setAvailableTransport(String availableTransport) {
		this.availableTransport = availableTransport;
	}
	
	
	
	
	
}
