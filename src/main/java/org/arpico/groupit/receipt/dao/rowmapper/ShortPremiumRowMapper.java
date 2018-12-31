package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.ShortPremiumModel;
import org.springframework.jdbc.core.RowMapper;

public class ShortPremiumRowMapper implements RowMapper<ShortPremiumModel>{

	@Override
	public ShortPremiumModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		ShortPremiumModel model = new ShortPremiumModel();
		
		model.setAddnot(rs.getString("addnot"));
		model.setAgent(rs.getString("agent"));
		model.setLoccod(rs.getString("loccod"));
		model.setPprnum(rs.getString("pprnum"));
		model.setPrpseq(rs.getInt("prpseq"));
		model.setQuonum(rs.getInt("quonum"));
		
		return model;
	}

}
