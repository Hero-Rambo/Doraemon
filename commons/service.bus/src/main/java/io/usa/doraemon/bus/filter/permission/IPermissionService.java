package io.usa.doraemon.bus.filter.permission;

import io.usa.doraemon.bus.filter.FilterContext;


public interface IPermissionService {
	boolean isPermit(FilterContext filterContext);
}
