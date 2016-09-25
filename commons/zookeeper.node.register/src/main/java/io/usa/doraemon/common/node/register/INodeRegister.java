package io.usa.doraemon.common.node.register;


/**
 * Register Node
 * @author Rambo
 * @2014-1-7
 */
public interface INodeRegister {
	
	void register(String data) throws ZookeeperException;
	
}
