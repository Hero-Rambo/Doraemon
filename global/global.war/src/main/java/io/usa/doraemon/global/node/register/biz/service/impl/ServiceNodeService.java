package io.usa.doraemon.global.node.register.biz.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import io.usa.doraemon.global.node.register.biz.dao.IServiceInfoRepository;
import io.usa.doraemon.global.node.register.biz.dao.IServiceNodePortRepository;
import io.usa.doraemon.global.node.register.biz.dao.IServiceNodeRepository;
import io.usa.doraemon.global.node.register.biz.service.IServiceNodeService;
import io.usa.doraemon.global.node.register.model.ServiceInfo;
import io.usa.doraemon.global.node.register.model.ServiceNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.usa.doraemon.commons.api.common.message.register.SlaveRegisterMessage;
import io.usa.doraemon.commons.api.common.message.register.ServiceMetadata;
import io.usa.doraemon.commons.api.db.jdbc.AbstractLogicService;
import io.usa.doraemon.global.node.register.biz.dao.INodeServiceRepository;
import io.usa.doraemon.global.node.register.model.ServiceNodePort;


/**
 * 
 * @author Rambo
 * @date Jan 22, 2014
 */
@Service
public class ServiceNodeService extends AbstractLogicService<ServiceNode> implements IServiceNodeService {
	@Autowired
	private IServiceNodeRepository serviceNodeRepository;
	
	@Autowired
	private IServiceInfoRepository serviceInfoRepository;
	
	@Autowired
	private INodeServiceRepository nodeServiceRepository;
	
	@Autowired
	private IServiceNodePortRepository serviceNodePortRepository;

	
	private  Logger logger = LoggerFactory.getLogger(ServiceNodeService.class);

	@Override
	public Object register(SlaveRegisterMessage nm) {
		logger.info("..........................do register...............");
		ServiceNode node = new ServiceNode();
		node.setUrl(nm.getUrl());
		node.setHost(nm.getHost());
		node.setStatus(nm.getStatus());
		node.setName(nm.getName());
		node.setDescription(nm.getDescription());
		
		serviceNodeRepository.register(node); 		
 		
 		List<ServiceInfo> serviceInfos = getServiceInfos(nm.getServices());
 		serviceInfoRepository.register(serviceInfos);
 		
 		nodeServiceRepository.register(node,serviceInfos);
 		
 		List<ServiceNodePort> nodePorts = getNodePorts(nm.getTransportInfos(),node);
 		serviceNodePortRepository.register(nodePorts);
 		
 		return true;
	}

	
	@Override
	public Object unregister(SlaveRegisterMessage nm) {
		ServiceNode node = new ServiceNode();
		node.setUrl(nm.getUrl());
		node.setHost(nm.getHost());
		node.setStatus(nm.getStatus());
		node.setName(nm.getName());
		node.setDescription(nm.getDescription());
		
		serviceNodeRepository.unregister(node);
		return true;
	}
	
	protected List<ServiceNodePort> getNodePorts(Map<String, Integer> transportInfos,ServiceNode node) {
		List<ServiceNodePort> ports = new ArrayList<ServiceNodePort>();
		for(Entry<String,Integer> transport:transportInfos.entrySet()){
			ServiceNodePort port = new ServiceNodePort();
			port.setServiceNodeId(node.getId());
			port.setPort(transport.getValue());
			port.setTransportType(transport.getKey());
			port.setStatus(node.getStatus());
			ports.add(port);
		}
		return ports;
	}

 

	protected List<ServiceInfo> getServiceInfos(List<ServiceMetadata> services) {
		List<ServiceInfo> serviceInfos = new ArrayList<ServiceInfo>();
		for(ServiceMetadata sm:services){
			ServiceInfo s = new ServiceInfo();
			s.setCategory(sm.getCategory());
			s.setName(sm.getName());
			s.setVersion(sm.getVersion());
 			serviceInfos.add(s);
		}
		return serviceInfos;
	}

 	
	
//	 
//
//	protected NodeInfo insertNodeInfo(NodeInfo node){
//
//		return node;
//	}
//	
//	protected void insertNodeService(NodeInfo node,ServiceInfo Service){
//		
//	}
//	
//	protected void deleteNodeService(NodeInfo node){
//		
//	}
//	
//	protected void insertNodePort(NodeInfo node,List<NodePort> nodePorts){
//		
//	}
//	
//	
//	protected void registerServiceInfo(List<ServiceInfo> serviceInfos){
//		
//	}
//	
//	
//	protected boolean isExist(NodeInfo node){
//		return nodeInfoDao.isExist(node);
//	}
//	
//	
//
//	protected void register(List<NodePort> nodePorts) {
// 		
//	}



}
