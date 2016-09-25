package io.usa.doraemon.provider.register.bus.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;


/**
 * 
 * @author Rambo
 * @date Jan 22, 2014
 */
@Component
public class BusDestinationProvider  {
 
	@Autowired
    protected NamedParameterJdbcTemplate jdbcTemplate;
	
	protected List<String> findBusNodeByStatus(String status){
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("status",status);
		return this.jdbcTemplate.queryForList(getFindBusNodeByStatusSql(), paramMap, String.class);
	}

	protected String getFindBusNodeByStatusSql(){
		return "select url from BusNode where status=:status";
	}

}
