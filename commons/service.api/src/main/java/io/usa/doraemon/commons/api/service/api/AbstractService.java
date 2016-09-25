package io.usa.doraemon.commons.api.service.api;


abstract public class AbstractService implements Service{
 
 
	@Override
	public String getServiceId() {
		return new StringBuilder().append( this.getCategory()).
		                           append('.').
		                           append(this.getName()).
		                           append('.').
		                           append(this.getVersion()).toString();
	}
 

}
