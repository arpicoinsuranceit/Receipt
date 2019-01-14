package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.NotRelChequeModel;
import org.springframework.jdbc.core.RowMapper;

public class NotRelChequeRowMapper implements RowMapper<NotRelChequeModel>{

	@Override
	public NotRelChequeModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		NotRelChequeModel model = new NotRelChequeModel();
		
		System.out.println();
		
		model.setAgent(rs.getString("agent"));
		model.setChqbnk(rs.getString("chqbnk"));
		model.setChqdat(rs.getDate("chqdat"));
		model.setChqnum(rs.getString("chqnum"));
		model.setCreadt(rs.getDate("creadt"));
		model.setLoccod(rs.getString("loccod"));
		model.setPolnum(rs.getString("polnum"));
		model.setPpdnam(rs.getString("ppdnam"));
		model.setProposal(rs.getString("proposal"));
		model.setReceipt(rs.getString("receipt"));
		model.setTotprm(rs.getDouble("totprm"));
		
		return model;
	}

}
