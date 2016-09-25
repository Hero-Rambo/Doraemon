package io.usa.doraemon.commons.api.role;

/**
 * 提供子系统的模块role，是bus还是registry还是slave
 * @author Rambo
 *
 */
public interface IRoleTypeProvider {
	
	public RoleType getRoleType();
}
