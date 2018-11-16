package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.AgentMastModel;
import org.springframework.jdbc.core.RowMapper;

public class AgentMastRowMapper implements RowMapper<AgentMastModel>{

	@Override
	public AgentMastModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		AgentMastModel agentMastModel = new AgentMastModel();
		agentMastModel.setAgncls(rs.getString("agncls"));
//		agentMastModel.setBrnmanager(rs.getString("brnmanager"));
		agentMastModel.setLocation(rs.getString("location"));
		agentMastModel.setUnlcod(rs.getString("unlcod"));
		return agentMastModel;
	}

}
