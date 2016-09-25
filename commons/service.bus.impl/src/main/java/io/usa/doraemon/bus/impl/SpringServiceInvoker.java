package io.usa.doraemon.bus.impl;

import io.usa.doraemon.bus.conf.IConfiguration;
import io.usa.doraemon.bus.exception.SeriviceNotFoundException;
import io.usa.doraemon.bus.invoker.AbstractServiceInvoker;
import io.usa.doraemon.bus.provider.IBeanProvider;
import io.usa.doraemon.bus.route.INodeRouter;
import io.usa.doraemon.bus.route.ITransportClientHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.usa.doraemon.commons.api.common.bus.model.Port;
import io.usa.doraemon.commons.api.common.bus.model.ServiceNode;
import io.usa.doraemon.commons.api.common.exception.TransportException;
import io.usa.doraemon.commons.api.service.api.Service;
import io.usa.doraemon.commons.api.service.api.ServiceContext;
import io.usa.doraemon.commons.api.service.api.ServiceResult;
import io.usa.doraemon.commons.api.service.api.exception.ServiceException;
import io.usa.doraemon.commons.api.transport.client.ITransportClient;
import io.usa.doraemon.commons.api.transport.common.RequestWrapper;

/**
 * 
 * @author Rambo
 *
 */
@Component("serviceInvoker")
public class SpringServiceInvoker extends AbstractServiceInvoker {
	private  Logger logger = LoggerFactory.getLogger(SpringServiceInvoker.class);

	@Override
	public ServiceResult invoke(ServiceContext context) {
 
		if("bus".equals(context.getCategory())||"manage".equals(context.getCategory())){
			return this.localInvoke(context);
		}
 
		if(configuration.isRemoteServicePriority()){
			return this.remoteInvoke(context);
		}
 
		return this.localInvoke(context);
	}

	@Autowired
	private IConfiguration configuration;
	
	 
	protected ServiceResult localInvoke(ServiceContext context) {
		Service service = null;
		ServiceResult serviceResult = new ServiceResult();

		try {
			service = beanProvider.getService(context);
		} catch (SeriviceNotFoundException e) {
			serviceResult.setErrorCode("Service_Not_Found");
			serviceResult.setErrorMesssage(e.getMessage());
			return serviceResult;
		}	
		
		try {
			serviceResult.setResult(service.execute(context));			
		} catch (ServiceException e) {
			logger.error("Exception occured when invoke local service!",e);
			serviceResult.setErrorCode(e.getErrorCode());
			serviceResult.setErrorMesssage(e.getMessage());
		}
		return serviceResult;
	}
	
	
	private final static String ROUTE = "router."; 
	
	@Autowired
	private IBeanProvider beanProvider;
	
	@Autowired
	private ITransportClientHolder transportClientHolder;
	 
	protected ServiceResult remoteInvoke(ServiceContext context){
		String routeAlgorithm = configuration.getRouteAlgorithm();
		INodeRouter routeNodeProvider = (INodeRouter) beanProvider.getBean(ROUTE+routeAlgorithm);
		if(routeNodeProvider==null){
			routeNodeProvider =  (INodeRouter) beanProvider.getBean("router.roundRoubin");
		}
		logger.info("..................."+routeNodeProvider.getClass().getName()+".........................");
		ServiceNode node = routeNodeProvider.getTargetServiceNode(context);
		ServiceResult serviceResult = new ServiceResult();

		if(node==null){
			logger.info("No Service Node Found for service!");
			serviceResult.setErrorCode(ServiceException.NO_SERVICE_NODE_AVAILABLE);
			serviceResult.setErrorMesssage("No service node available!");
			return serviceResult;
		}
		Port port = node.getAvailablePort();
		
		ITransportClient transportClient = transportClientHolder.getServiceClient(port,node);		
		
		RequestWrapper wrapper = new RequestWrapper();

		wrapper.setRequest(context);
		wrapper.setId(Thread.currentThread().getId());
		try {
			serviceResult.setResult(transportClient.transport(wrapper));
		} catch (TransportException e) {
			logger.error("Exception occured when invoke remote service!",e);
			serviceResult.setErrorCode(e.getErrorCode());
			serviceResult.setErrorMesssage(e.getMessage());
		}
		return serviceResult;
	}
	
	
	
	protected void handleRemoteException(Exception e){
		
	}
}
