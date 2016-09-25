package io.usa.doraemon.bus.adaptor.impl;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.usa.doraemon.bus.adaptor.AbstractServiceAdaptor;
import io.usa.doraemon.bus.adaptor.AdaptorType;
import io.usa.doraemon.bus.adaptor.IRestServiceAdaptor;


/**
 * 解决http 空格转加号问题：
 * 发送端采用URL Encode，然后把里面的加号替换为%20（例如，replaceAll("\\+", "%20"));
 * 服务器端一律采用URL Decode。
 * @author Rambo
 *
 */
@Service
@Path("/rest")
public class RestServiceAdaptor extends AbstractServiceAdaptor implements IRestServiceAdaptor{	
//	private Logger logger = LoggerFactory.getLogger(RestServiceAdaptor.class);

    @GET
    @Path("/{appId}/{category}/{name}/{version}/{params}")
    public Object getByPath(    		
    		@PathParam("appId") String appId,@PathParam("category") String category,
    		@PathParam("name") String name,@PathParam("version") String version,
    		@PathParam("params") String params,@HeaderParam("appToken")String appToken,
    		@HeaderParam("securityLevel")String securityLevel) throws Exception {
//    	logger.info("appToken");
    	
    	params = URLDecoder.decode(params, "UTF-8");
    	Object result = this.invokeService(appId, appToken, category, name, version, params,securityLevel);
//    	logger.info("result=="+result);
    	return result;
    }
    
 
    
    @POST
    @Path("/")
    public Object post(
    		@FormParam("appId") String appId,  @FormParam("category") String category,
    		@FormParam("name") String name,    @FormParam("version") String version,
    		@FormParam("params") String params,@HeaderParam("appToken")String appToken,
    		@HeaderParam("securityLevel")String securityLevel) throws UnsupportedEncodingException, JsonProcessingException {    	
//    	logger.info("appToken");
    	params = URLDecoder.decode(params, "UTF-8");
    	Object result = this.invokeService(appId, appToken, category, name, version, params,securityLevel);
//    	logger.info("result=="+result);
    	return  result;
    }
    
    @POST
    @Path("/{appId}/{category}/{name}/{version}/{params}")
    public Object postByPath(
    		@PathParam("appId") String appId,@PathParam("category") String category,
    		@PathParam("name") String name,@PathParam("version") String version,
    		@PathParam("params") String params,@HeaderParam("appToken")String appToken,
    		@HeaderParam("securityLevel")String securityLevel) throws UnsupportedEncodingException, JsonProcessingException {    	
//    	logger.info("appToken");
    	params = URLDecoder.decode(params, "UTF-8");
    	Object result = this.invokeService(appId, appToken, category, name, version, params,securityLevel);
//    	logger.info("result=="+result);
    	return  result;
    }
     

	@Override
	protected AdaptorType getAdaptorType() {
		return AdaptorType.REST;
	}

	@Override
	protected List<String> getExtendsFiltersChain() {
		return this.getConfiguration().getRestFiltersChain();
	}
	

 

//    @PUT
//    @Path("/customers/")
//    public Response updateCustomer(Customer customer) {
//        logger.info("----invoking updateCustomer, Customer name is: " + customer.getName());
//        Customer c = customers.get(customer.getId());
//        Response r;
//        if (c != null) {
//            customers.put(customer.getId(), customer);
//            r = Response.ok().build();
//        } else {
//            r = Response.notModified().build();
//        }
//
//        return r;
//    }



//    @DELETE
//    @Path("/customers/{id}/")
//    public Response deleteCustomer(@PathParam("id") String id) {
//        logger.info("----invoking deleteCustomer, Customer id is: " + id);
//        long idNumber = Long.parseLong(id);
//        Customer c = customers.get(idNumber);
//
//        Response r;
//        if (c != null) {
//            r = Response.ok().build();
//            customers.remove(idNumber);
//        } else {
//            r = Response.notModified().build();
//        }
//
//        return r;
//    }
}
