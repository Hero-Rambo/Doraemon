package io.usa.doraemon.provider.role.impl;

import org.springframework.stereotype.Component;

import io.usa.doraemon.commons.api.role.IRoleTypeProvider;
import io.usa.doraemon.commons.api.role.RoleType;

/**
 * 
 * @author Rambo
 * @2014-1-9
 */
@Component
public class SlaveRoleTypeProvider implements IRoleTypeProvider{

	@Override
	public RoleType getRoleType() {
		return RoleType.Slave;
	}

}
