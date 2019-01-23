package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.EducationModel;
import org.springframework.jdbc.core.RowMapper;

public class EducationRowMapper implements RowMapper<EducationModel>{

	@Override
	public EducationModel mapRow(ResultSet rs, int row) throws SQLException {
		
		EducationModel educationModel=new EducationModel();
		
		educationModel.setGrade(rs.getString("qgrade"));
		educationModel.setQualification(rs.getString("qulfic"));
		educationModel.setYear(rs.getString("qlfyer"));
		
		return educationModel;
	}

}
