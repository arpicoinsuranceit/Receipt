package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.AgnInqAgnListModel;
import org.springframework.jdbc.core.RowMapper;

public class AgnInqAgnListRowMapper implements RowMapper<AgnInqAgnListModel>{

	@Override
	public AgnInqAgnListModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		AgnInqAgnListModel model = new AgnInqAgnListModel();
		
		model.setAgncod(rs.getInt(""));
		model.setAgndob(rs.getDate(""));
		model.setAgnnam(rs.getString(""));
		model.setAgnnic(rs.getString(""));
		model.setAgnrdt(rs.getDate(""));
		model.setAgnsta(rs.getString(""));
		model.setSliirg(rs.getString(""));
		model.setSubdcd(rs.getString(""));
		model.setSupvid(rs.getString(""));
		
		return model;
	}
	

}
