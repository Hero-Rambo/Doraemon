package io.usa.doraemon.commons.api.bus.report.service.v1.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import io.usa.doraemon.commons.api.common.bus.model.ServiceNode;
import io.usa.doraemon.commons.api.common.cache.CacheConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.usa.doraemon.commons.api.common.bus.model.Port;
import io.usa.doraemon.commons.api.common.message.register.ServiceMetadata;
import io.usa.doraemon.commons.api.common.message.register.SlaveRegisterMessage;
import io.usa.doraemon.commons.api.service.api.ServiceContext;
import io.usa.doraemon.commons.api.service.api.exception.ServiceException;


/**
 * bus节点接受服务节点注册服务
 * @author Rambo
 *
 */
public class ServiceNodeRegisterService {
 
	private Logger logger = LoggerFactory.getLogger(ServiceNodeRegisterService.class);
	
	@Autowired
	private ObjectMapper jackson;
	
	@Autowired
	private CacheManager cacheManager;
	
	
 	public Object execute(ServiceContext context) throws ServiceException {		
		try {			
			SlaveRegisterMessage msg = jackson.readValue(context.getParams(),SlaveRegisterMessage.class);
			logger.info("node status===="+msg.getStatus()+",node url==="+msg.getUrl());
			if("Start".equals(msg.getStatus())){
				//service node start
				this.registerSlaveNode(msg);
			}else{
				//service node stop
				this.unregisterSlaveNode(msg);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Error:"+e.getMessage();
		}	

		return null;
	}

	protected void unregisterSlaveNode(SlaveRegisterMessage msg){
		Cache serviceRegistry = cacheManager.getCache(CacheConstants.SERVICES_REGISTRY);
		Cache nodeRegistry =  cacheManager.getCache(CacheConstants.NODES_REGISTRY);
		//remove node from service nodes
		for(ServiceMetadata service : msg.getServices()){
			String serviceFullName = service.getCategory()+'.'+service.getName()+'.'+service.getVersion();
			
			ValueWrapper valueWrapper = serviceRegistry.get(serviceFullName);
			if(valueWrapper==null){
				continue;
			}else{
				@SuppressWarnings("unchecked")
				Set<String> nodeUrls = (Set<String>) valueWrapper.get();
				if(nodeUrls !=null && nodeUrls.contains(msg.getUrl())){
					nodeUrls.remove(msg.getUrl());
				}
			}		 		
		}
		
		//update node registry
		ValueWrapper valueWrapper =  nodeRegistry.get(msg.getUrl());
		if(valueWrapper!=null){
			ServiceNode serviceNode  = (ServiceNode) valueWrapper.get();
			serviceNode.setStatus(msg.getStatus());
		}

		this.removeAvailableNodeFromCache(msg.getUrl());
	}
	
	
	@SuppressWarnings("unchecked")
	protected void registerSlaveNode(SlaveRegisterMessage msg){
		Cache serviceRegistry = cacheManager.getCache(CacheConstants.SERVICES_REGISTRY);
		Cache nodeRegistry =  cacheManager.getCache(CacheConstants.NODES_REGISTRY);
		ValueWrapper valueWrapper = nodeRegistry.get(msg.getUrl());
		ServiceNode serviceNode = null;
		if(valueWrapper==null){
			serviceNode = new ServiceNode();			
			this.refreshServiceNode(serviceNode, msg);
			nodeRegistry.put(serviceNode.getUrl(), serviceNode);
		}else{
			serviceNode = (ServiceNode) valueWrapper.get();
			this.refreshServiceNode(serviceNode, msg);
		}
		//put node in node registry
		
						
		//add node and service relation  in  service registry cache
		for(ServiceMetadata service : msg.getServices()){
			String serviceId = service.getCategory()+'.'+service.getName()+'.'+service.getVersion();
			logger.info("service:"+serviceId+" is reistered!.......................");			
			valueWrapper = serviceRegistry.get(serviceId);
			Set<String> nodeUrls = null;
			if(valueWrapper==null){
				nodeUrls = new HashSet<String>();
				serviceRegistry.put(serviceId, nodeUrls);
			}else{
				nodeUrls = (Set<String>) valueWrapper.get();
			}
			nodeUrls.add(msg.getUrl());
			logger.info("msg.getUrl():"+msg.getUrl()+" is add to service registery!....serviceId=="+serviceId);
			
		}				
		
		this.putAvailableNodeInCache(msg.getUrl());
	}
	
	
	protected void refreshServiceNode(ServiceNode serviceNode,SlaveRegisterMessage msg){		
		serviceNode.setHost(msg.getHost());
		serviceNode.setUrl(msg.getUrl());
		serviceNode.setStatus(msg.getStatus());
		serviceNode.setWeight(msg.getWeight());
		Map<String,Port> ports = new HashMap<String,Port>();
		for(Entry<String,Integer> p : msg.getTransportInfos().entrySet()){
			Port port = new Port();
			port.setPort(p.getValue());
			port.setTransportType(p.getKey());
			ports.put(port.getTransportType(), port);						
		}
		serviceNode.setPorts(ports);
		Port availablePort = ports.get(msg.getAvailableTransport());
		serviceNode.setAvailablePort(availablePort);
	}
	
	
	@SuppressWarnings("unchecked")
	protected void putAvailableNodeInCache(String nodeUrl){
		Cache availableNodeCache =  cacheManager.getCache(CacheConstants.AVAILABLE_NODES_CACHE);	

		//put node in available node cache
		
		ValueWrapper valueWrapper = availableNodeCache.get(CacheConstants.AVAILABLE_NODES);
		Set<String> availableNodes = null;
		if(valueWrapper==null){
			availableNodes = new HashSet<String>();
			availableNodeCache.put(CacheConstants.AVAILABLE_NODES,availableNodes);
		}else{
			availableNodes = (Set<String>) valueWrapper.get();
		}
		
		if(!availableNodes.contains(nodeUrl)){
			availableNodes.add(nodeUrl);	
			logger.info("nodeUrl:"+nodeUrl+"  registered..................................");
			
			valueWrapper = availableNodeCache.get(CacheConstants.AVAILABLE_NODES);
			logger.info("AVAILABLE_NODES_CACHE content=================="+valueWrapper.get());
		}
	}
	
	@SuppressWarnings("unchecked")
	protected void removeAvailableNodeFromCache(String nodeUrl){
		Cache availableNodeCache =  cacheManager.getCache(CacheConstants.AVAILABLE_NODES_CACHE);	

		//put node in available node cache
		ValueWrapper valueWrapper = availableNodeCache.get(CacheConstants.AVAILABLE_NODES);
		Set<String> availableNodes = null;
		if(valueWrapper!=null){
			availableNodes = (Set<String>) valueWrapper.get();
			if(availableNodes.contains(nodeUrl)){
				availableNodes.remove(nodeUrl);
			}
		}
	 
	}
}
