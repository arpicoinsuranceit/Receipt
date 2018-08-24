package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.arpico.groupit.receipt.model.PolicyDetailsModel;
import org.springframework.jdbc.core.RowMapper;

public class PolicyDetailsRowMapper implements RowMapper<PolicyDetailsModel>{

	@Override
	public PolicyDetailsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		PolicyDetailsModel model = new PolicyDetailsModel();
		
		model.setComDate(rs.getDate("icpdat"));
		model.setInsMonth(rs.getString("insnum"));
		model.setPolType(rs.getString("prdcod"));
		model.setPolnum(rs.getString("polnum"));
		model.setPprnum(rs.getString("pprnum"));
		model.setAmount(rs.getDouble("amount"));
		model.setStatus(rs.getString("pprsta"));
		model.setDate(rs.getDate("txndat"));
		
		return model;
	}

}
