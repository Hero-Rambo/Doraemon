package io.usa.doraemon.bus.adaptor;

import java.util.List;

import io.usa.doraemon.bus.IServiceAdaptor;
import io.usa.doraemon.bus.conf.IConfiguration;
import io.usa.doraemon.bus.filter.FilterContext;
import io.usa.doraemon.bus.filter.IBusFilter;
import io.usa.doraemon.bus.invoker.IServiceInvoker;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.usa.doraemon.bus.ServiceError;
import io.usa.doraemon.bus.filter.FilterResult;
import io.usa.doraemon.bus.log.IServiceLogger;
import io.usa.doraemon.bus.provider.IBeanProvider;
import io.usa.doraemon.commons.api.service.api.ServiceContext;


/**
 * 
 * @author Rambo
 * @date Jan 23, 2014
 */
abstract public class AbstractServiceAdaptor implements IServiceAdaptor {
    
//    private Logger logger = LoggerFactory.getLogger(AbstractServiceAdaptor.class);	
	
	@Autowired
	private IServiceInvoker serviceInvoker;
	
	@Autowired
	private ObjectMapper jackson; 
		
    private IServiceLogger serviceLogger;
      
    @Autowired
    private IBeanProvider beanProvider;

    @Autowired 
    private IConfiguration configuration;

	protected IBeanProvider getBeanProvider() {
		return beanProvider;
	}

	protected IConfiguration getConfiguration() {
		return configuration;
	}
	

	protected String invokeService(
    		String appId, String appToken, String category, String name, 
    		String version, String params, String securityLevel) {
		
		FilterContext filterContext = new FilterContext();
		filterContext.setAppId(appId);
		filterContext.setAppToken(appToken);
		filterContext.setCategory(category);    	
		filterContext.setName(name);
		filterContext.setVersion(version);
		filterContext.setParams(params);
		filterContext.setSecurityLevel(securityLevel);
		
		this.doLog(filterContext);
		
    	FilterResult result = doFilter(filterContext);

    	
    	if(result ==null ||  result.isPass()){
        	ServiceContext serviceContext = new ServiceContext();
    		
    		serviceContext.setAppId(appId);
    		serviceContext.setCategory(category);    	
    		serviceContext.setName(name);
    		serviceContext.setVersion(version);
    		serviceContext.setParams(filterContext.getParams());
    		
    		try {
				return jackson.writeValueAsString( serviceInvoker.invoke(serviceContext));
			} catch (JsonProcessingException e) {
				return "";
			}		 
    	}else{
    		ServiceError error = this.handleError(filterContext,result);    		
    		try {
				return jackson.writeValueAsString(error);
			} catch (JsonProcessingException e) {
				return "";
			}
    	}
    }
	 
    
    protected void doLog(FilterContext filterContext){
    	if(serviceLogger==null){
        	serviceLogger = (IServiceLogger) beanProvider.getBean(configuration.getServiceLogger());
    	}    	
    	serviceLogger.log(filterContext);	
    }
	
    protected FilterResult doFilter(FilterContext filterContext){
    	List<String> commonFilters = getCommonFiltersChain();

    	List<String> extendsFilters = this.getExtendsFiltersChain();
		FilterResult result = this.doFilter(commonFilters, filterContext);
		if(result!=null && !result.isPass()){
			return result;
		}
    	return this.doFilter(extendsFilters, filterContext);
    }
    
    abstract protected List<String> getExtendsFiltersChain() ;

	 
    
	protected FilterResult doFilter(List<String> filters,FilterContext filterContext){
	  	for(String handlerBeanId:filters){
    		IBusFilter handler = (IBusFilter)beanProvider.getBean(handlerBeanId);
    		FilterResult result = handler.doFilter(filterContext);
    		if(result!=null && !result.isPass()){
    			return result;
    		}
    	}
	  	return null;
	}
    
    

    protected List<String> getCommonFiltersChain(){
    	return configuration.getCommonFiltersChain();
    }
    
    protected ServiceError handleError(FilterContext filterContext,FilterResult filterResult){
    	ServiceError error = new ServiceError();

    	error.setAppId(filterContext.getAppId());
    	error.setAppToken(filterContext.getAppToken());

    	error.setCategory(filterContext.getCategory());
    	error.setName(filterContext.getName());
    	
    	error.setVersion(filterContext.getVersion());
    	error.setSecurityLevel(filterContext.getSecurityLevel());

    	error.setErrorCode(filterResult.getErrorCode());
    	error.setErrorMessage(filterResult.getErrorMessage());
    	return error;
    }
    
    /**
     * 
     * @param e
     * @return
     */
    protected ServiceError handleError(FilterContext filterContext, Exception e){
    	ServiceError error = new ServiceError();

    	error.setAppId(filterContext.getAppId());
    	error.setAppToken(filterContext.getAppToken());

    	error.setCategory(filterContext.getCategory());
    	error.setName(filterContext.getName());
    	
    	error.setVersion(filterContext.getVersion());
    	error.setSecurityLevel(filterContext.getSecurityLevel());

//    	error.setErrorCode(e.getErrorCode());
    	error.setErrorMessage(e.getMessage());
    	return error;
    }
    abstract protected AdaptorType getAdaptorType();
    

}
