package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.AgnInqAgnListModel;
import org.springframework.jdbc.core.RowMapper;

public class AgnInqAgnListRowMapper implements RowMapper<AgnInqAgnListModel>{

	@Override
	public AgnInqAgnListModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		AgnInqAgnListModel model = new AgnInqAgnListModel();
		
		model.setAgncod(rs.getInt("agncod"));
		model.setAgndob(rs.getDate("agndob"));
		model.setAgnnam(rs.getString("agnnam"));
		model.setAgnnic(rs.getString("agnnic"));
		model.setAgnrdt(rs.getDate("agnrdt"));
		model.setAgnsta(rs.getString("agnsta"));
		model.setSliirg(rs.getString("sliirg"));
		model.setSubdcd(rs.getString("subdcd"));
		model.setSupvid(rs.getString("supvid"));
		
		return model;
	}
	

}
