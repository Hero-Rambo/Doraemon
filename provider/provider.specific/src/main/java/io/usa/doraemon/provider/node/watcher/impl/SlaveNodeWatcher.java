package io.usa.doraemon.provider.node.watcher.impl;

import java.util.List;

import io.usa.doraemon.commons.api.common.node.watcher.AbstractNodeWatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import io.usa.doraemon.commons.api.common.node.watcher.NodePathHelper;
import io.usa.doraemon.commons.zookeeper.client.ZookeeperClientException;



/**
 * 
 * @author Rambo
 * @2014-1-9
 */
@Component
public class SlaveNodeWatcher extends AbstractNodeWatcher {
	private Logger logger = LoggerFactory.getLogger(SlaveNodeWatcher.class);


	@Override
	protected void processNodeChildren(String parentPath, List<String> children) {		
//		//bus注册
//		if(isBusRegister(parentPath)){
//			this.processBusRegister(parentPath, children);
//		}	
	}

	/**
	 * @param parentPath
	 * @param children
	 */
	protected void processBusRegister(String parentPath, List<String> children){
		for(String child:children){
			try {
				String childrenPath = parentPath+"/"+child;
				String data = new String(this.getZookeeperClient().getData(childrenPath));

				if(!this.getZookeeperClient().isExist(childrenPath)){
					logger.info("The path is deleted!path:"+childrenPath);
					continue;
				}
				logger.info("data for path:"+childrenPath+"  is:"+data);					
			} catch (ZookeeperClientException e) {
				e.printStackTrace();
			}
		}
	}
 
	@Override
	protected String[] getWatchedPath() {
		return new String[]{NodePathHelper.getBusRegisterPath()};
	}
 
 
}
