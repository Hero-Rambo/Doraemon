package io.usa.doraemon.global.node.watcher.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.usa.doraemon.commons.api.common.node.watcher.AbstractNodeWatcher;
import io.usa.doraemon.commons.api.common.node.watcher.IZookeeperMessageHandler;
import io.usa.doraemon.commons.api.common.node.watcher.NodePathHelper;
import io.usa.doraemon.commons.zookeeper.client.ZookeeperClientException;



/**
 * 
 * @author Rambo
 * @2014-1-9
 */
@Component
public class GlobalNodeWatcher extends AbstractNodeWatcher{
	 
	@Autowired
	private IZookeeperMessageHandler busNodeRegisterHandler;
	
	@Autowired
	private IZookeeperMessageHandler SlaveNodeRegisterHandler;
	
	
	@Override
	protected void processNodeChildren(String parentPath, List<String> children) throws ZookeeperClientException {
		//slave注册
		if(isSlaveRegister(parentPath)){
			this.processSlaveNodeRegister(parentPath, children);
		}
		//bus注册
		if(isBusRegister(parentPath)){
			this.processBusNodeRegister(parentPath, children);
		}	
 
	}
	
	/**
	 * 
	 * @param parentPath
	 * @param children
	 * @throws ZookeeperClientException 
	 */
	protected void processBusNodeRegister(String parentPath, List<String> children) throws ZookeeperClientException {
 		for(String child:children){
 			String childPath = parentPath+"/"+child;
 			String data = new String(this.getZookeeperClient().getData(childPath));
 			this.busNodeRegisterHandler.handle(childPath, data);
 		}
	}
	

	/**
	 * 
	 * @param parentPath
	 * @param children
	 * @throws ZookeeperClientException 
	 */
	protected void processSlaveNodeRegister(String parentPath, List<String> children) throws ZookeeperClientException {
 		for(String child:children){
 			String childPath = parentPath+"/"+child;
 			String data = new String(this.getZookeeperClient().getData(childPath));
 			this.SlaveNodeRegisterHandler.handle(childPath, data);
 		}
	}




	@Override
	protected String[] getWatchedPath() {
		return new String[]{NodePathHelper.getBusRegisterPath(),NodePathHelper.getSlaveRegisterPath()};
	}


 
 
}
