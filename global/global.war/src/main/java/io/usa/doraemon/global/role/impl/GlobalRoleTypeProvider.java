package io.usa.doraemon.global.role.impl;

import org.springframework.stereotype.Component;

import io.usa.doraemon.commons.api.role.IRoleTypeProvider;
import io.usa.doraemon.commons.api.role.RoleType;


/**
 * 
 * @author Rambo
 *
 */
@Component
public class GlobalRoleTypeProvider implements IRoleTypeProvider{

	@Override
	public RoleType getRoleType() {
		return RoleType.Global;
	}

}
