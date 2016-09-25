package io.usa.doraemon.global.node.register.biz.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.usa.doraemon.global.node.register.biz.dao.IServiceNodeRepository;
import io.usa.doraemon.global.node.register.model.ServiceNode;
import org.springframework.stereotype.Repository;

import io.usa.doraemon.commons.api.db.jdbc.AbstractRepository;
import io.usa.doraemon.global.UUIDProvider;

@Repository
public class ServiceNodeRepository  extends AbstractRepository<ServiceNode> implements IServiceNodeRepository {

	@Override
	protected Map<String, Object> getAddParams(ServiceNode model) {
		Map<String, Object> parameters = new HashMap<String, Object>(6);
		model.setId(UUIDProvider.getUUID());
		parameters.put("id", model.getId());
		parameters.put("host", model.getHost());
		parameters.put("url", model.getUrl());
		parameters.put("status", model.getStatus());
		parameters.put("name", model.getName());
		parameters.put("description", model.getDescription());
		return parameters;
	}


	@Override
	protected String getInsertSql() {
		return "(id,host,status,name,description,url) values(:id,:host,:status,:name,:description,:url)";
	}
     

 
    protected String getUpdateSql(){
    	return "update "+this.getTable()+" set status=:status where url=:url";
    }
    public void update(ServiceNode node){    	
        Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("url", node.getUrl());
        parameters.put("status",node.getStatus());        
        jdbcTemplate.update(getUpdateSql(), parameters);
    }
     
    
    
	@Override
	public void query(ServiceNode model) {
		
	}
	@Override
	public void delete(ServiceNode model) {
		
	}

	@Override
	public void updateStatus(ServiceNode node) {
 		
	}

	protected String getExistSql(){
		return  "select id from "+this.getTable()+" where url=:url";
	}
	@Override
	public boolean isExist(ServiceNode node) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("url", node.getUrl());
		List<String> nodeIds = this.jdbcTemplate.queryForList(getExistSql(), paramMap,String.class);
		if(nodeIds.isEmpty()){
			return false;
		}else{
			node.setId(nodeIds.get(0));			
			return true;
		}
	}

	@Override
	public void register(ServiceNode node) {
		if(this.isExist(node)){
			this.update(node);
		}else{
			this.add(node);
		}
	}

 
    protected String getUnregisterSql(){
    	return  "update "+this.getTable()+" set status=:status where url=:url";
    }
	@Override
    public void unregister(ServiceNode node){    	
        Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("url", node.getUrl());
        parameters.put("status",node.getStatus());        
        jdbcTemplate.update(getUnregisterSql(), parameters);
    }


	@Override
	public List<String> findServiceNodeByStatus(String status) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("status",status);
		return this.jdbcTemplate.queryForList(getFindServiceNodeByStatusSql(), paramMap, String.class);
	}

	protected String getFindServiceNodeByStatusSql(){
		return "select url from "+this.getTable()+" where status=:status";
	}


    
 
}