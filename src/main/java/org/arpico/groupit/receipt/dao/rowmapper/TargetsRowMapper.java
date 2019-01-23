package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.TargetsModel;
import org.springframework.jdbc.core.RowMapper;

public class TargetsRowMapper implements RowMapper<TargetsModel>{

	@Override
	public TargetsModel mapRow(ResultSet rs, int row) throws SQLException {
		
		TargetsModel targetsModel=new TargetsModel();
		
		targetsModel.setAchAmount(rs.getDouble("trgach"));
		targetsModel.setCfAmount(rs.getDouble("trgtcfa"));
		targetsModel.setMonth(rs.getString("trgmon"));
		targetsModel.setOrRate(rs.getDouble("trgorc"));
		targetsModel.setPremium(rs.getDouble("trgamt"));
		targetsModel.setTargetAmount(rs.getDouble("trgamt"));
		
		return targetsModel;
	}

}
