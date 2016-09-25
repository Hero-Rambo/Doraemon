package io.usa.doraemon.commons.api.transport.server;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

abstract public class AbstractTransportServer implements ITransportServer,DisposableBean,InitializingBean {
	private int port;

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

 

	@Override
	public void afterPropertiesSet() throws Exception {
		this.init();
	}

	@Override
	public void destroy() throws Exception {
		this.stop();
	}
	
	
	
}
