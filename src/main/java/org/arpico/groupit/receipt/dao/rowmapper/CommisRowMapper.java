package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.CommisModel;
import org.springframework.jdbc.core.RowMapper;

public class CommisRowMapper implements RowMapper<CommisModel>{

	@Override
	public CommisModel mapRow(ResultSet rst, int arg1) throws SQLException {
	
		CommisModel commisModel = new CommisModel();
		commisModel.setComper(rst.getDouble("comper"));
		commisModel.setComsin(rst.getDouble("comsin"));
		
		return commisModel;
	
	}

	

}
