package io.usa.doraemon.global.node.register.biz.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.usa.doraemon.global.UUIDProvider;
import io.usa.doraemon.global.node.register.biz.dao.INodeServiceRepository;
import io.usa.doraemon.global.node.register.model.NodeService;
import io.usa.doraemon.global.node.register.model.ServiceInfo;
import io.usa.doraemon.global.node.register.model.ServiceNode;
import org.springframework.stereotype.Repository;

import io.usa.doraemon.commons.api.db.jdbc.AbstractRepository;

@Repository
public class NodeServiceRepository extends AbstractRepository<NodeService> implements INodeServiceRepository {


	@Override
	protected String getInsertSql() {
		return "(id,serviceNodeId,serviceId)values(:id,:serviceNodeId,:serviceId)";
	}
 
 
	@Override
	public Map<String,Object>  getAddParams(NodeService model) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		model.setId(UUIDProvider.getUUID());

		paramMap.put("id", model.getId());
		paramMap.put("serviceNodeId", model.getServiceNodeId());
		paramMap.put("serviceId", model.getServiceId());
		return paramMap;
	}

	@Override
	public void update(NodeService model) {
 		
	}
	
 

	@Override
	public void query(NodeService model) {
 		
	}

	@Override
	public void delete(NodeService model) {
 		
	}

	protected String getExistSql(){
		return "select id from "+this.getTable()+" where serviceNodeId=:serviceNodeId and serviceId=:serviceId";
	}
	protected boolean isExist(NodeService nodeService) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("serviceNodeId", nodeService.getServiceNodeId());
		paramMap.put("serviceId", nodeService.getServiceId());

		
		List<String> nodeServiceIds = this.jdbcTemplate.queryForList(getExistSql(), paramMap,String.class);
		if(nodeServiceIds.isEmpty()){
			return false;
		}else{
			nodeService.setId(nodeServiceIds.get(0));			
			return true;
		}
	}
	
	@Override
	public void register(ServiceNode node, List<ServiceInfo> serviceInfos) {
		for(ServiceInfo service:serviceInfos){
			NodeService model = new NodeService();
			model.setServiceNodeId(node.getId());
			model.setServiceId(service.getId());
			if(!this.isExist(model)){
				this.add(model);
			}
		}
	}

	protected String getUnregisterSql(){
		return "delete from "+this.getTable()+" where serviceNodeId=:serviceNodeId";
	}
	@Override
	public void unregister(ServiceNode node) {
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("serviceNodeId", node.getId());
 		this.jdbcTemplate.update(getUnregisterSql(), paramMap); 		
	}

 


}