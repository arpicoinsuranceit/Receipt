package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException; 
import org.arpico.groupit.receipt.model.WorkFlowPolicyGridModel;
import org.springframework.jdbc.core.RowMapper;

public class WorkFlowPolicyGridRowMapper implements RowMapper<WorkFlowPolicyGridModel>{

	@Override
	public WorkFlowPolicyGridModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		WorkFlowPolicyGridModel model = new WorkFlowPolicyGridModel();
		
		model.setAgent(rs.getString("agent"));
		model.setBrncod(rs.getString("brncod"));
		model.setDuedat(rs.getDate("duedat"));
		model.setPolicy(rs.getString("policy"));
		model.setPpdini(rs.getString("ppdini"));
		model.setTotprm(rs.getDouble("totprm"));
		
		return model;
	}
	
	

}
