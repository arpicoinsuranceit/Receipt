package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.AgentModel;
import org.springframework.jdbc.core.RowMapper;

public class AgentRowMapper implements RowMapper<AgentModel>{

	@Override
	public AgentModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		AgentModel agentModel = new AgentModel();
		agentModel.setAgentCode(rs.getInt("agncod"));
		agentModel.setAgentName(rs.getString("shrtnm"));
		agentModel.setLocation(rs.getString("loccod"));
		
		return agentModel;
		
	}

}
