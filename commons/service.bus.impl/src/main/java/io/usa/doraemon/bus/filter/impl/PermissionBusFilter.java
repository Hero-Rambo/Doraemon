package io.usa.doraemon.bus.filter.impl;

import io.usa.doraemon.bus.filter.AbstractBusFilter;
import io.usa.doraemon.bus.filter.FilterContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.usa.doraemon.bus.filter.FilterResult;
import io.usa.doraemon.bus.filter.permission.IPermissionService;

/**
 * 
 * @author Rambo
 *
 */
@Component("bus.filter.permission")
public class PermissionBusFilter extends AbstractBusFilter {
	private Logger logger = LoggerFactory.getLogger(PermissionBusFilter.class);
	@Autowired
	private IPermissionService permissionService;
	
	@Override
	public FilterResult doFilter(FilterContext filterContext) {
		logger.info("bus.filter.permission................");
		if(!permissionService.isPermit(filterContext)){
			FilterResult result = new FilterResult();
			result.setErrorCode("error.service.app.NoPermission");
			result.setErrorMessage("No Permission Error!");
		}
		return null;
	}
 
}
