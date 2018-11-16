package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.InAgentMastDao;
import org.arpico.groupit.receipt.dao.rowmapper.AgentMastRowMapper;
import org.arpico.groupit.receipt.model.AgentMastModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InAgentMastDaoImpl implements InAgentMastDao{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public List<AgentMastModel> getAgentDetails(String agenCode) throws Exception {
//		return (List<AgentMastModel>) jdbcTemplate.query("select agncls,unlcod, loccod as location, " + 
//				"(select agncod from inagentmast where loccod = location and agncls = 'BRN' and agnsta = 'ACT') as brnmanager " + 
//				"from inagentmast where agncod= '"+agenCode+"'", new AgentMastRowMapper());
		
		return (List<AgentMastModel>) jdbcTemplate.query("select agncls,unlcod, loccod as location from inagentmast where agncod= '"+agenCode+"'",
				new AgentMastRowMapper());
	}

}
