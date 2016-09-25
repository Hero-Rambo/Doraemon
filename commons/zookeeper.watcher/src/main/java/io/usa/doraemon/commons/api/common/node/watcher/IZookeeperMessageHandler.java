package io.usa.doraemon.commons.api.common.node.watcher;


/**
 * 
 * @author Rambo
 * @date Jan 22, 2014
 */
public interface IZookeeperMessageHandler {
	
	public void handle(String path,String message)  ;

}
