package io.usa.doraemon.bus.zookeeper.watcher.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public class BusNodeWatcher extends AbstractNodeWatcher{
	private Logger logger = LoggerFactory.getLogger(BusNodeWatcher.class);
	
	@Override
	protected void processNodeChildren(String parentPath, List<String> children) {
		//slave注册
		if(this.isSlaveRegister(parentPath)){
			logger.info("register....path:"+parentPath+",children:"+children);
			this.processSlaveRegister(parentPath, children);
		}	
		
		//provider status
		if(this.isSlaveStatus(parentPath)){
			logger.info("status report....path:"+parentPath+",children:"+children);
			this.processSlaveStatus(parentPath, children);
		}
	}

	@Autowired
	private IZookeeperMessageHandler slaveNodeRegisterHandler;

	@Autowired
	private IZookeeperMessageHandler slaveNodeStatusHandler;
	
	
	protected void processSlaveStatus(String parentPath, List<String> children) {
		for(String child:children){
			try {
				String childrenPath = parentPath+"/"+child;
				String data = new String(this.getZookeeperClient().getData(childrenPath));

				if(!this.getZookeeperClient().isExist(childrenPath)){
					logger.info("The path is deleted!path:"+childrenPath);
					continue;
				}
				logger.info("data for path:"+childrenPath+"  is:"+data);	
				slaveNodeStatusHandler.handle(childrenPath,data);
			} catch (ZookeeperClientException e) {
				e.printStackTrace();
			}
		}
	}
	
	 

	protected boolean isSlaveStatus(String path) {
		return path.startsWith(NodePathHelper.getSlaveStatusPath());
	}

	/**
	 * 
	 * @param parentPath
	 * @param children
	 */
	protected void processSlaveRegister(String parentPath, List<String> children){
		if(children==null || children.isEmpty()){
			this.logger.info("children is empty.........................");
			slaveNodeRegisterHandler.handle(parentPath,null);
			return ;
		}
		for(String child:children){
			try {
				String childrenPath = parentPath+"/"+child;
				String data = new String(this.getZookeeperClient().getData(childrenPath));

				if(!this.getZookeeperClient().isExist(childrenPath)){
					logger.info("The path is deleted!path:"+childrenPath);
					continue;
				}
				logger.info("data for path:"+childrenPath+"  is:"+data);
				slaveNodeRegisterHandler.handle(childrenPath,data);
			} catch (ZookeeperClientException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected String[] getWatchedPath() {
		return new String[]{
				NodePathHelper.getSlaveRegisterPath(),
				NodePathHelper.getSlaveStatusPath()
		};
	}
 
 
}
