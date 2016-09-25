package io.usa.doraemon.commons.api.common.message.manage.bus;

import java.util.List;

import io.usa.doraemon.commons.api.common.message.manage.Command;

public class ChangeBusNodeCommand extends Command {
	private boolean add;
	private List<String> busNodeList;
	
	public boolean isAdd() {
		return add;
	}
	public void setAdd(boolean add) {
		this.add = add;
	}
	public List<String> getBusNodeList() {
		return busNodeList;
	}
	public void setBusNodeList(List<String> busNodeList) {
		this.busNodeList = busNodeList;
	}
	@Override
	public String getType() {
		return "ChangeBusNode";
	}
	
	
	
}
