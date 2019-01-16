package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.AgentDao;
import org.arpico.groupit.receipt.dao.rowmapper.AgentFullRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.AgentRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.AgnInqAgnListRowMapper;
import org.arpico.groupit.receipt.model.AgentModel;
import org.arpico.groupit.receipt.model.AgnInqAgnListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AgentDaoImpl implements AgentDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<AgentModel> findAgentLikeAgentCode(Integer agentCode, String sql) throws Exception {

		String query = "SELECT agncod, shrtnm, loccod FROM inagentmast where sbucod = '450' " + sql
				+ " and agncod like '" + agentCode + "%' and agnsta IN ('ACT','INA') order by agncod";

		// System.out.println(query);

		return jdbcTemplate.query(query, new AgentRowMapper());
	}

	@Override
	public List<AgentModel> findAgentByCode(String agentCode) {
		return jdbcTemplate
				.query(" SELECT agncod, shrtnm, loccod FROM inagentmast " + "    where sbucod = '450' and agncod = '"
						+ agentCode + "' and agnsta IN ('ACT','INA') " + "    order by agncod", new AgentRowMapper());
	}

	@Override
	public List<AgentModel> findAgentByCodeAll(String advcod) throws Exception {
		return jdbcTemplate.query("SELECT agncod, shrtnm, loccod FROM inagentmast "
				+ "    where sbucod = '450' and agncod = '" + advcod + "' order by agncod", new AgentRowMapper());
	}

	@Override
	public AgentModel findPropAgent(String agentCode) throws Exception {
		return jdbcTemplate.queryForObject("SELECT agncod, prnnam, loccod ,agncls FROM inagentmast "
				+ "where sbucod = '450' and agncod = '" + agentCode + "' ;", new AgentFullRowMapper());
	}

	@Override
	public List<AgentModel> getAllAgents() throws Exception {
		return jdbcTemplate.query("SELECT agncod, prnnam, loccod ,agncls FROM inagentmast "
				+ "where sbucod = '450' and agnsta in ('ACT','INA')", new AgentRowMapper());
	}

	@Override
	public List<AgentModel> findAgentByLocations(String locCodes) throws Exception {
		return jdbcTemplate.query("SELECT agncod, shrtnm, loccod FROM inagentmast "
				+ "where sbucod = '450' and loccod in  (" + locCodes + ") and agnsta in ('ACT','INA') order by agncod;",
				new AgentRowMapper());
	}

	@Override
	public List<AgnInqAgnListModel> getAgnInqList(String locCodes, Integer page, Integer offset) throws Exception {
		return jdbcTemplate.query("select agncod, agnnam, agnnic, agnsta, sliirg, "
				+ "if(agncls = 'IC', unlcod, bancod) as supvid, agndob, subdcd, agnrdt "
				+ "from inagentmast where sbucod = '450' and loccod in (" + locCodes + ") limit " + page + ", " + offset
				+ ";", new AgnInqAgnListRowMapper());
	}

	@Override
	public Integer getAgnInqListCount(String locCodes) throws Exception {
		return jdbcTemplate.queryForObject(
				"select count(*)  from inagentmast where sbucod = '450' and loccod in (" + locCodes + ")", Integer.class);
	}

}
