package io.usa.doraemon.commons.api.common.message.report;

/**
 * 
 * @author Rambo
 * @date Jan 22, 2014
 */
public class SlaveStatusMessage extends ReportMessage {
	
	private String url;
	private String host;
	private String status;
	private double cpuUsePercent;
	private double memoryUsagePercent;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getCpuUsePercent() {
		return cpuUsePercent;
	}

	public void setCpuUsePercent(double cpuUsePercent) {
		this.cpuUsePercent = cpuUsePercent;
	}

	public double getMemoryUsagePercent() {
		return memoryUsagePercent;
	}

	public void setMemoryUsagePercent(double memoryUsagePercent) {
		this.memoryUsagePercent = memoryUsagePercent;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

}
