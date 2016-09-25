package io.usa.doraemon.commons.api.bus.report.service.v1.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.usa.doraemon.commons.api.common.cache.CacheConstants;
import io.usa.doraemon.commons.api.service.api.ServiceContext;
import io.usa.doraemon.commons.api.service.api.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;

import java.util.HashSet;
import java.util.Set;

/**
 * bus节点接受服务节点汇报服务
 * @author Rambo
 *
 */
public class NodeReportService {
 

	@Autowired
	private ObjectMapper jackson;
	
	@Autowired
	private CacheManager cacheManager;
	
	
	private Logger logger = LoggerFactory.getLogger(NodeReportService.class);

	
	public Object execute(ServiceContext context) throws ServiceException {
		try{
			StatusReportMessage report = this.jackson.readValue(context.getParams(), StatusReportMessage.class);
			logger.info("bus.NodeReportService.v1.............."+context.getParams());
			if("Start".equals(report.getStatus())){
				//node available
				this.updateAvailableNodes(report);				
			}else{
				//node stop
				this.removeNoneAvailableNodes(report);
			}
			
			return null;
		}catch(Exception e){
			e.printStackTrace();
			return "Error:"+e.getMessage();
		}

	}

	
	@SuppressWarnings("unchecked")
	protected void updateAvailableNodes(StatusReportMessage report){
		Cache availableNodeCache = cacheManager.getCache(CacheConstants.AVAILABLE_NODES_CACHE);

		ValueWrapper valueWrapper = availableNodeCache.get(CacheConstants.AVAILABLE_NODES);
		
		if(valueWrapper==null  ){
			Set<String>  availableNodes = new HashSet<String>();	
			availableNodeCache.put(CacheConstants.AVAILABLE_NODES, availableNodes);		
			if(!availableNodes.contains(report.getUrl())){
				availableNodes.add(report.getUrl());
			}
		}else{
			Set<String> availableNodes = (Set<String>) valueWrapper.get();
			if(!availableNodes.contains(report.getUrl())){
				availableNodes.add(report.getUrl());
			}
		}
		logAvailable();
	}
	
	@SuppressWarnings("unchecked")
	protected void removeNoneAvailableNodes(StatusReportMessage report){
		Cache availableNodeCache = cacheManager.getCache(CacheConstants.AVAILABLE_NODES_CACHE);
			
		ValueWrapper valueWrapper = availableNodeCache.get(CacheConstants.AVAILABLE_NODES);

		if(valueWrapper==null  ){
			return ;
		}else{
			Set<String> availableNodes = (Set<String>) valueWrapper.get();
			if(availableNodes.contains(report.getUrl())){
				availableNodes.remove(report.getUrl());
			}
		}
		
		logAvailable();
	}
	
	
	@SuppressWarnings("unchecked")
	protected void logAvailable(){
		Cache availableNodeCache = cacheManager.getCache(CacheConstants.AVAILABLE_NODES_CACHE);

		ValueWrapper valueWrapper = availableNodeCache.get(CacheConstants.AVAILABLE_NODES);
		if(valueWrapper!=null){
			
			Set<String>  availableNodes = (Set<String>) valueWrapper.get();
			logger.info("availableNodes.........."+availableNodes);
		}		
	}
}
