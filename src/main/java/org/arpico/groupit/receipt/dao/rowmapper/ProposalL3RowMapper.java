package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.dto.ProposalL3Dto;
import org.springframework.jdbc.core.RowMapper;

public class ProposalL3RowMapper implements RowMapper<ProposalL3Dto>{

	@Override
	public ProposalL3Dto mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProposalL3Dto dto = new ProposalL3Dto();
		
		dto.setPayment(rs.getDouble("payment"));
		dto.setPprnum(rs.getString("pprnum"));
		dto.setReqcnt(rs.getInt("reqcnt"));
		dto.setTotprm(rs.getDouble("totprm"));
		dto.setRecovery(rs.getDouble("spiamt"));
		
		return dto;
	}

}
