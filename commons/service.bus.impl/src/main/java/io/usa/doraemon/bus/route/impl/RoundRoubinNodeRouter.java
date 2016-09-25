package io.usa.doraemon.bus.route.impl;

import java.util.Set;

import io.usa.doraemon.bus.route.AbstractNodeRouter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import io.usa.doraemon.commons.api.common.bus.model.ServiceNode;
import io.usa.doraemon.commons.api.common.cache.CacheConstants;


/**
 * 
 * @author Rambo
 * @date Jan 23, 2014
 */
@Component("router.roundRoubin")
public class RoundRoubinNodeRouter extends AbstractNodeRouter {

	private Logger logger = LoggerFactory.getLogger(RoundRoubinNodeRouter.class);
	
	
	@Autowired
	private CacheManager cacheManager;
	
	
	/**
	 * 先判断
	 */
	@Override
	public ServiceNode getTargetServiceNode(String serviceId) {
		Cache serviceRegistry = cacheManager.getCache(CacheConstants.SERVICES_REGISTRY);

		logger.info("serviceId========================"+serviceId);
		ValueWrapper nodesValueWrapper = serviceRegistry.get(serviceId);
		
		if(nodesValueWrapper!=null){
			@SuppressWarnings("unchecked")
			Set<String> nodes = (Set<String>) nodesValueWrapper.get();
			Cache nodeRegistry = cacheManager.getCache(CacheConstants.NODES_REGISTRY);
			Cache availableNodesCache = cacheManager.getCache(CacheConstants.AVAILABLE_NODES_CACHE);
			for(String hostUrl : nodes){
				logger.info("hostUrl============================"+hostUrl);
				nodesValueWrapper = nodeRegistry.get(hostUrl);
				if(nodesValueWrapper!=null){
					logger.info("nodesValueWrapper======"+(nodesValueWrapper.get()));
					ValueWrapper availableNodesWrapper = availableNodesCache.get(CacheConstants.AVAILABLE_NODES);

					logger.info("availableNodesWrapper=null====="+(availableNodesWrapper==null));
					if(availableNodesWrapper==null){
						continue;
					}

					@SuppressWarnings("unchecked")
					Set<String> availableNodes = (Set<String>) availableNodesWrapper.get();
					if(availableNodes.contains(hostUrl)){
						return (ServiceNode) nodesValueWrapper.get();
					}
//					logger.info("valueWrapper============================"+valueWrapper);
				}
			}
		}
		return null;
	}

}
