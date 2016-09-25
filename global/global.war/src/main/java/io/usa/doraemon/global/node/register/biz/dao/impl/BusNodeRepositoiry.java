package io.usa.doraemon.global.node.register.biz.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import io.usa.doraemon.commons.api.db.jdbc.AbstractRepository;
import io.usa.doraemon.global.UUIDProvider;
import io.usa.doraemon.global.node.register.biz.dao.IBusNodeRepositoiry;
import io.usa.doraemon.global.node.register.model.BusNode;

@Repository
public class BusNodeRepositoiry extends AbstractRepository<BusNode> implements IBusNodeRepositoiry{

	@Override
	protected Map<String, Object> getAddParams(BusNode model) {
        Map<String, Object> parameters = new HashMap<String, Object>(6);
        model.setId(UUIDProvider.getUUID());
        parameters.put("id",model.getId());
        parameters.put("host", model.getHost());
        parameters.put("url", model.getUrl());
        parameters.put("status",model.getStatus());
        parameters.put("name",model.getName());
        parameters.put("description",model.getDescription());
        return parameters;
	}



	@Override
	protected String getInsertSql() {
		return "(id,host,status,name,description,url) values(:id,:host,:status,:name,:description,:url)";
	}
 
    public void update(BusNode node){    	
        Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("url", node.getUrl());
        parameters.put("status",node.getStatus());        
        jdbcTemplate.update(getUpdateSql(), parameters);
    }
     
    protected String getUpdateSql(){
    	return  "update "+this.getTable()+" set status=:status where url=:url";
    }
    
    
	@Override
	public void query(BusNode model) {
		
	}
	@Override
	public void delete(BusNode model) {
		
	}

 


	@Override
	public boolean isExist(BusNode node) {
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
	
	protected String getExistSql(){
		return "select id from "+this.getTable()+" where url=:url";
	}

	@Override
	public boolean register(BusNode node) {
		if(this.isExist(node)){
			this.update(node);
		}else{
			this.add(node);
		}
		return true;
	}

 





	@Override
    public boolean unregister(BusNode node){    	
        Map<String, Object> parameters = new HashMap<String, Object>(2);
        parameters.put("url", node.getUrl());
        parameters.put("status",node.getStatus());        
        jdbcTemplate.update(getUnregisterSql(), parameters);
        return true;
    }
	




	protected String getUnregisterSql(){
		return "update "+this.getTable()+" set status=:status where url=:url";
	}


 



 

}
