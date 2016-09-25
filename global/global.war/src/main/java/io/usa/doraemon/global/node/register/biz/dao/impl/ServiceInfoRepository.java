package io.usa.doraemon.global.node.register.biz.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import io.usa.doraemon.commons.api.db.jdbc.AbstractRepository;
import io.usa.doraemon.global.UUIDProvider;
import io.usa.doraemon.global.node.register.biz.dao.IServiceInfoRepository;
import io.usa.doraemon.global.node.register.model.ServiceInfo;

@Repository
public class ServiceInfoRepository  extends AbstractRepository<ServiceInfo> implements IServiceInfoRepository{

	@Override
	protected String getInsertSql() {
		return "(id,category,version,name)values(:id,:category,:version,:name)";
	}

	@Override
	protected Map<String, Object> getAddParams(ServiceInfo model) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		model.setId(UUIDProvider.getUUID());
		paramMap.put("id", model.getId());
		paramMap.put("category", model.getCategory());
		paramMap.put("version", model.getVersion());
		paramMap.put("name", model.getName());
		
		return paramMap;
	}


 
	@Override
	public void update(ServiceInfo model) {
 		
	}

	@Override
	public void query(ServiceInfo model) {
 		
	}

	@Override
	public void delete(ServiceInfo model) {
 		
	}

	@Override
	public void register(List<ServiceInfo> serviceInfos) {
		for(ServiceInfo serviceInfo:serviceInfos){
			if(!this.isExist(serviceInfo)){
				this.add(serviceInfo);
			}
		}
	}

	
	protected String getExistSql(){
		return "select id from "+this.getTable()+" where category=:category and version=:version and name=:name"; 
	}
	protected boolean isExist(ServiceInfo model) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("category", model.getCategory());
		paramMap.put("version", model.getVersion());
		paramMap.put("name", model.getName());
 	
		List<String> serviceInfoIds = this.jdbcTemplate.queryForList(getExistSql(), paramMap, String.class);
		if(serviceInfoIds.isEmpty()){
			return false;
		}else{
			model.setId(serviceInfoIds.get(0));			
			return true;
		}
	}


}
