package io.doraemon.business.demo.service.permission.v1.impl;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Service;

import io.usa.doraemon.commons.api.service.api.AbstractSpringService;
import io.usa.doraemon.commons.api.service.api.ServiceContext;
import io.usa.doraemon.commons.api.service.api.exception.ServiceException;

@Service("permission.PermissionService.v1")
public class PermissionService extends AbstractSpringService implements BeanNameAware{

	@Override
	public String getCategory() {
		return "permission";
	}
 
 
	@Override
	public String getName() {
		return "PermissionService";
	}

	@Override
	public String getVersion() {
		return "v1";
	}
 
	 

//	{"applicatioin":"EMS","password":"ChaoyangRoad ","userId":100025}
	@Override
	public Object execute(ServiceContext context) throws ServiceException {	
//		System.out.println("params==="+context.getParams());	
//		List<Permission> permissions = new ArrayList<Permission>();
		return context;
	}
	
//	class Permission{
//		private String name;
//		private String description;
//		public String getName() {
//			return name;
//		}
//		public void setName(String name) {
//			this.name = name;
//		}
//		public String getDescription() {
//			return description;
//		}
//		public void setDescription(String description) {
//			this.description = description;
//		}
//		
//		
//	}
// 
 

}
