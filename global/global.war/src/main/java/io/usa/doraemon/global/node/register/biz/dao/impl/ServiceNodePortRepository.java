package io.usa.doraemon.global.node.register.biz.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.usa.doraemon.global.node.register.model.ServiceNode;
import org.springframework.stereotype.Repository;

import io.usa.doraemon.commons.api.db.jdbc.AbstractRepository;
import io.usa.doraemon.global.UUIDProvider;
import io.usa.doraemon.global.node.register.biz.dao.IServiceNodePortRepository;
import io.usa.doraemon.global.node.register.model.ServiceNodePort;

@Repository
public class ServiceNodePortRepository extends AbstractRepository<ServiceNodePort> implements IServiceNodePortRepository{

	@Override
	protected Map<String, Object> getAddParams(ServiceNodePort model) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		model.setId(UUIDProvider.getUUID());
		paramMap.put("id", model.getId());
		paramMap.put("serviceNodeId", model.getServiceNodeId());
		paramMap.put("port", model.getPort());
		paramMap.put("transportType", model.getTransportType());
		return paramMap;
	}

	@Override
	protected String getInsertSql() {
		return "(id,serviceNodeId,port,transportType)values(:id,:serviceNodeId,:port,:transportType)";
	}
	
 
	@Override
	public void update(ServiceNodePort model) {
 		
	}

	@Override
	public void query(ServiceNodePort model) {
 		
	}

	@Override
	public void delete(ServiceNodePort model) {
 		
	}

	
	@Override
	public void register(List<ServiceNodePort> nodePorts) {
		for(ServiceNodePort p:nodePorts){
			if(!this.isExist(p)){
				this.add(p);
			}
		}
	}
	
	
	protected String getExistSql(){
		return  "select id from "+this.getTable()+" where serviceNodeId=:serviceNodeId and port=:port and transportType=:transportType";
	}
	protected boolean isExist(ServiceNodePort p) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("serviceNodeId", p.getServiceNodeId());
		paramMap.put("port", p.getPort());
		paramMap.put("transportType", p.getTransportType());
		
		List<String> nodePortIds = this.jdbcTemplate.queryForList(getExistSql(), paramMap,String.class);
		if(nodePortIds.isEmpty()){
			return false;
		}else{
			p.setId(nodePortIds.get(0));			
			return true;
		}
	}

	protected String getUnregisterSql() {
		return "update "+this.getTable()+" set status=:status where serviceNodeId=:serviceNodeId";
	}
	@Override
	public void unregister(ServiceNode node) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("serviceNodeId", node.getId());
		paramMap.put("status", node.getStatus());

		this.jdbcTemplate.update(getUnregisterSql(), paramMap);
	}



}
