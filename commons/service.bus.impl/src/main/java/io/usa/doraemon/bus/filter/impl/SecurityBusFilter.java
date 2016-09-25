package io.usa.doraemon.bus.filter.impl;

import io.usa.doraemon.bus.filter.AbstractBusFilter;
import io.usa.doraemon.bus.filter.FilterContext;
import io.usa.doraemon.bus.security.ISecurityHandler;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.usa.doraemon.bus.filter.FilterResult;
import io.usa.doraemon.bus.provider.IBeanProvider;

@Component("bus.filter.security")
public class SecurityBusFilter extends AbstractBusFilter {
	@Autowired
	private IBeanProvider beanProvider;
	
	@Override
	public FilterResult doFilter(FilterContext filterContext) {
		String securityLevel = filterContext.getSecurityLevel();
		if(StringUtils.isBlank(securityLevel) ){
			return null;			
		}
		
		ISecurityHandler handler = (ISecurityHandler) beanProvider.getBean("security.level."+securityLevel);
		return handler.checkSecurity(filterContext);
	}
 
}
