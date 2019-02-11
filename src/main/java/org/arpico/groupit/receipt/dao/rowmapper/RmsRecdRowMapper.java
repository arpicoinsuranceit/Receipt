package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.RmsRecdModel;
import org.arpico.groupit.receipt.model.pk.RmsRecdModelPK;
import org.springframework.jdbc.core.RowMapper;

public class RmsRecdRowMapper implements RowMapper<RmsRecdModel> {

	@Override
	public RmsRecdModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		RmsRecdModelPK pk = new RmsRecdModelPK();
		
		pk.setDocCode(rs.getString("DOC_CODE"));
		pk.setDocNo(rs.getInt("DOC_NO"));
		pk.setLocCode(rs.getString("LOC_CODE"));
		pk.setSbuCode(rs.getString("SBU_CODE"));
		pk.setSeqNo(rs.getInt("SEQ_NO"));
		
		RmsRecdModel model = new RmsRecdModel();
		
		model.setRmsRecdModelPK(pk);
		
		model.setAmtfcu(rs.getDouble("AMTFCU"));
		model.setCreDate(rs.getDate("CRE_DATE"));
		model.setDimm04(rs.getString("dimm04"));
		model.setDimm05(rs.getString("dimm05"));
		
		return model;
	}

}
