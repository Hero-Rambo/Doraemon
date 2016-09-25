package io.usa.doraemon.common.node.register.impl;

import io.usa.doraemon.common.node.register.AbstractZookeeperNodeRegister;
import org.springframework.stereotype.Component;


/**
 * 
 * @author Rambo
 * @2014-1-7
 */
@Component
public class ZookeeperNodeRegister extends AbstractZookeeperNodeRegister {
	@Override
	protected String getRegisterPath() {
		return null;
	}
//
//	@Autowired
//	private IRoleTypeProvider roleTypeProvider;
//
//	@Autowired
//	private IServerInfoProvider serverInfoProvider;
//
//	@Override
//	protected String getRegisterPath() {
//		return  '/'+roleTypeProvider.getRoleType().toString()+'/'+serverInfoProvider.getHostUrl();
//	}
	
	
}
