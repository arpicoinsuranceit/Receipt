package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.DesignationModel;
import org.springframework.jdbc.core.RowMapper;

public class DesignationRowMapper implements RowMapper<DesignationModel>{

	@Override
	public DesignationModel mapRow(ResultSet rs, int row) throws SQLException {
		
		DesignationModel designationModel=new DesignationModel();
		
		designationModel.setDesCode(rs.getString("subdcd"));
		designationModel.setFrom(rs.getString("frmdat"));
		designationModel.setName(rs.getString("subdes"));
		designationModel.setTo(rs.getString("todate"));
		
		return designationModel;
	}

}
