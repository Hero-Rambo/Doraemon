package io.usa.doraemon.common.node.register;

import io.usa.doraemon.commons.zookeeper.client.ZookeeperClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * 
 * @author Rambo
 * @2014-1-7
 */
abstract public class AbstractZookeeperNodeRegister implements INodeRegister{
	private static final Logger LOG = LoggerFactory.getLogger(AbstractZookeeperNodeRegister.class);

	
	@Autowired	
	private ZookeeperClient client;
	
	
	@Override
	public void register(String data) throws ZookeeperException {
		String registerPath = this.getRegisterPath();
		try {
			if(client.isExist(registerPath)){
				LOG.info("path not exist! so create it.....");
				client.create(registerPath, data.getBytes());
			}else{
				LOG.info("path   exist! so update it.....");
				client.setData(registerPath, data.getBytes());	
			}
		} catch (Exception e) {
			throw new ZookeeperException(e);
		}
	}
	
 
	/**
	 * 
	 * @return
	 */
	protected abstract String getRegisterPath();
	
}
