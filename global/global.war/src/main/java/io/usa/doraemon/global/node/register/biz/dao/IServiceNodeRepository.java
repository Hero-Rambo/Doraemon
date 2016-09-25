package io.usa.doraemon.global.node.register.biz.dao;

import java.util.List;

import io.usa.doraemon.global.node.register.model.ServiceNode;

public interface IServiceNodeRepository {
    public void updateStatus(ServiceNode node);

	public boolean isExist(ServiceNode node);

	public void register(ServiceNode node);

	public void unregister(ServiceNode node);
	
	List<String> findServiceNodeByStatus(String status) ;
	
}
