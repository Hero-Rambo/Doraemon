package io.usa.doraemon.commons.api.transport.server;

public interface ITransportServer {	
	int getPort();
	void init()throws Exception;
//	void start()throws Exception;
	void stop() throws Exception;
}
