package io.usa.doraemon.commons.api.common.message.manage.bus;

import java.util.List;

import io.usa.doraemon.commons.api.common.message.manage.Command;

public class ChangeServiceNodeCommand extends Command {
	
	private boolean remove;
	private List<String> serviceNodeUrls;
	
	public boolean isRemove() {
		return remove;
	}
	public void setRemove(boolean remove) {
		this.remove = remove;
	}
	public List<String> getServiceNodeUrls() {
		return serviceNodeUrls;
	}
	public void setServiceNodeUrls(List<String> serviceNodeUrls) {
		this.serviceNodeUrls = serviceNodeUrls;
	}
	@Override
	public String getType() {
		return "ChangeServiceNode";
	}
	
	
	
}
