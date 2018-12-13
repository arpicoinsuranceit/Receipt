package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.InOcuLoadDetModel;
import org.springframework.jdbc.core.RowMapper;

public class InOccuLoadDatRowMapper implements RowMapper<InOcuLoadDetModel>{

	@Override
	public InOcuLoadDetModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		InOcuLoadDetModel inOcuLoadDetModel = new InOcuLoadDetModel();
		
		inOcuLoadDetModel.setLockin(rs.getDate("lockin"));
		inOcuLoadDetModel.setLodcls(rs.getDouble("lodcls"));
		inOcuLoadDetModel.setRatmil(rs.getDouble("ratmil"));
		inOcuLoadDetModel.setEmrate(rs.getDouble("emrate"));
		inOcuLoadDetModel.setSubrat(rs.getDouble("subrat"));
		inOcuLoadDetModel.setOcucod(rs.getString("ocucod"));
		inOcuLoadDetModel.setRidcod(rs.getString("ridcod"));
		inOcuLoadDetModel.setSbucod(rs.getString("sbucod"));
		
		return inOcuLoadDetModel;
	}

}
