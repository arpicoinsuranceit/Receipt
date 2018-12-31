package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.ShortPremiumModel;
import org.springframework.jdbc.core.RowMapper;

public class PendingReqRowMapper implements RowMapper<ShortPremiumModel>{

	@Override
	public ShortPremiumModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		ShortPremiumModel model = new ShortPremiumModel();
		
		model.setAgent(rs.getString("agent"));
		model.setQuonum(rs.getInt("quonum"));
		model.setPprnum(rs.getString("pprnum"));
		model.setPrpseq(rs.getInt("prpseq"));
		model.setLoccod(rs.getString("loccod"));
		model.setReqcnt(rs.getInt("reqcnt"));
		
		
		return model;
	}

}
