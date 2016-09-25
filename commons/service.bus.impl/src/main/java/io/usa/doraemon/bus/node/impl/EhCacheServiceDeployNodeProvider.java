package io.usa.doraemon.bus.node.impl;

import java.util.ArrayList;
import java.util.List;

import io.usa.doraemon.bus.node.IServiceDeployNodeProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.ehcache.EhCacheCacheManager;

import io.usa.doraemon.commons.api.common.bus.model.ServiceNode;

/**
 * 
 * @author Rambo
 *
 */
public class EhCacheServiceDeployNodeProvider implements IServiceDeployNodeProvider {
	@Autowired
	private EhCacheCacheManager cacheManager;
	
	private final static String SERVICE_CACHE_NAME="Services.Cache";
	
	@Override
	public List<ServiceNode> getServiceDeployNodes(String serviceId) {
		Cache cache = this.cacheManager.getCache(SERVICE_CACHE_NAME);
		if(cache==null){
			cache = this.cacheManager.getCache("default");
		}
		
		ValueWrapper result = cache.get(serviceId);
		
		@SuppressWarnings("unchecked")
		List<ServiceNode> serviceNodes = (List<ServiceNode>) result.get();
		if(serviceNodes==null){
			return new ArrayList<ServiceNode>();
		}
		return serviceNodes;
	}
 

}
