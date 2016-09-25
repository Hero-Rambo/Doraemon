package io.usa.doraemon.bus.zookeeper.watcher.handler.impl;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.usa.doraemon.commons.api.common.cache.CacheConstants;
import io.usa.doraemon.commons.api.common.message.report.SlaveStatusMessage;
import io.usa.doraemon.commons.api.common.node.watcher.IZookeeperMessageHandler;

/**
 * bus节点接受服务节点汇报服务
 * @author Rambo
 *
 */
@Component
public class SlaveNodeStatusHandler implements IZookeeperMessageHandler{
	private Logger logger = LoggerFactory.getLogger(SlaveNodeStatusHandler.class);

	@Autowired
	private ObjectMapper jackson;
	
	@Autowired
	private CacheManager cacheManager;
	
	
	public void handle(String path,String slaveSstatusMessage) {
		logger.info("slaveSstatusMessage.............."+slaveSstatusMessage);

		try{
			SlaveStatusMessage report = this.jackson.readValue(slaveSstatusMessage, SlaveStatusMessage.class);
			if("Start".equals(report.getStatus())){
				//node available
				this.updateAvailableNodes(report);				
			}else{
				//node stop
				this.removeNoneAvailableNodes(report);
			}			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings("unchecked")
	protected void updateAvailableNodes(SlaveStatusMessage report){
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
	protected void removeNoneAvailableNodes(SlaveStatusMessage report){
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
