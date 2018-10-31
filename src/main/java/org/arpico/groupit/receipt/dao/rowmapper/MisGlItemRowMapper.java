package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.MisGlItemModel;
import org.springframework.jdbc.core.RowMapper;

public class MisGlItemRowMapper implements RowMapper<MisGlItemModel>{

	@Override
	public MisGlItemModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		MisGlItemModel model = new MisGlItemModel();
		
		model.setAmount(rs.getDouble("AMOUNT"));
		model.setDescription(rs.getString("DESCRI"));
		model.setDocNo(rs.getInt("DOCNUM"));
		model.setInterId(rs.getInt("INTERID"));
		model.setRemark(rs.getString("REMARK"));
		model.setBranch(rs.getString("DIMM04"));
		return model;
	}

}
