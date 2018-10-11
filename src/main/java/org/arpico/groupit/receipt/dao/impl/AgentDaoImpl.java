package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.AgentDao;
import org.arpico.groupit.receipt.dao.rowmapper.AgentRowMapper;
import org.arpico.groupit.receipt.model.AgentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AgentDaoImpl implements AgentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<AgentModel> findAgentLikeAgentCode(Integer agentCode) throws Exception {
		return jdbcTemplate.query("SELECT agncod, shrtnm, loccod FROM inagentmast "
				+ "where sbucod = '450' and agnsta = 'ACT' and agncod like '" + agentCode + "%' order by agncod;",
				new AgentRowMapper());
	}

	@Override
	public List<AgentModel> findAgentByCode(String agentCode) {
		return jdbcTemplate.query("SELECT agncod, shrtnm, loccod FROM inagentmast "
				+ "where sbucod = '450' and agnsta = 'ACT' and agncod = '" + agentCode + "' order by agncod;",
				new AgentRowMapper());
	}

	@Override
	public List<AgentModel> findAgentByCodeAll(String advcod) throws Exception {
		return jdbcTemplate.query("SELECT agncod, shrtnm, loccod FROM inagentmast "
				+ "where sbucod = '450' and agncod = '" + advcod + "' order by agncod;",
				new AgentRowMapper());
	}

}
