package io.usa.doraemon.provider.report.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.usa.doraemon.commons.api.common.node.watcher.NodePathHelper;
import io.usa.doraemon.commons.api.common.utils.IServerInfoProvider;
import io.usa.doraemon.commons.api.common.utils.ServerInfo;
import io.usa.doraemon.commons.zookeeper.client.ZookeeperClient;
import io.usa.doraemon.provider.report.IReportMessageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;


/**
 * 
 * @author Rambo
 * @2014-1-21
 */
@Component
public class ReportSchedule    {
	
	private Logger logger = LoggerFactory.getLogger(ReportSchedule.class);
	
	@Autowired
	private ZookeeperClient client;	
	
	@Autowired
	private IReportMessageProvider reportMessageProvider;
	

	@Autowired
	private IServerInfoProvider serverInfoProvider;
	
	@Autowired
	private ObjectMapper jackson;
 
	//第一种方式  
    //fixedDelay延时多少毫秒，多少毫秒执行一次  
//    @Scheduled(cron="0 * * * * *")     //第二种方式  
  
    /* 
        1 Seconds (0-59) 
        2 Minutes (0-59) 
        3 Hours (0-23) 
        4 Day of month (1-31) 
        5 Month (1-12 or JAN-DEC) 
        6 Day of week (1-7 or SUN-SAT) 
        7 Year (1970-2099) 
        取值：可以是单个值，如6； 
            也可以是个范围，如9-12； 
            也可以是个列表，如9,11,13 
            也可以是任意取值，使用* 
    */  
    //0 * * * * * 代表每分钟执行一次  
    /* 
        2011-09-07 09:23:00 
        2011-09-07 09:24:00 
        2011-09-07 09:25:00 
        2011-09-07 09:26:00 
     */  
	@Scheduled(fixedDelay=30000)
	public void report(){
		ServerInfo serverInfo = serverInfoProvider.getServerInfo();
		String slaveStatusPath = this.getSlaveStatusPath(serverInfo);

		try {
			String slaveStautsMessage = jackson.writeValueAsString(reportMessageProvider.getStautsReportMessage());
			logger.info("slaveStautsMessage==="+slaveStautsMessage);
			logger.info("this.client.isExist(slaveStatusPath)==="+this.client.isExist(slaveStatusPath));
			if(!this.client.isExist(NodePathHelper.getSlaveStatusPath())){
				this.client.ensurePath(NodePathHelper.getSlaveStatusPath());
			}
			if(this.client.isExist(slaveStatusPath)){
				logger.info("the status path is exist!Delete it first!slaveStatusPath="+slaveStatusPath);
				this.client.delete(slaveStatusPath);
			}
			client.create(slaveStatusPath, slaveStautsMessage.getBytes(Charset.forName("UTF-8")));
			logger.info("..................report..........");
		} catch (Exception e) {
			logger.error("Slave report status error:",e);
		}		
	}
	
	
	protected String getSlaveStatusPath(ServerInfo serverInfo){
		String context = serverInfo.getContextRoot().substring(1, serverInfo.getContextRoot().length());
		return NodePathHelper.getSlaveStatusPath()+"/"+serverInfo.getIp()+"_"+serverInfo.getPort()+"_"+context;
	}


	
	
}