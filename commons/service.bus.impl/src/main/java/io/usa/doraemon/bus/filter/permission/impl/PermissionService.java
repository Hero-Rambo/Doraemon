package io.usa.doraemon.bus.filter.permission.impl;

import io.usa.doraemon.bus.filter.FilterContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import io.usa.doraemon.bus.filter.permission.IPermissionService;

@Service
public class PermissionService implements IPermissionService{
	
	private final static String PERMISSION_CACHE = "service.permission.cache";
	
	@Autowired
	private CacheManager cacheManager;
	
	
	@Override
	public boolean isPermit(FilterContext filterContext) {
		Cache cache = cacheManager.getCache(PERMISSION_CACHE);
		if(cache==null){
			
		}else{
			
		}
		return true;
	}
	
	protected void initCache(){

	}

	
}
