package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.InPropPreviousPolModel;
import org.springframework.jdbc.core.RowMapper;

public class InPropPreviousPolsCompleteRowMapper implements RowMapper<InPropPreviousPolModel>{

	@Override
	public InPropPreviousPolModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		InPropPreviousPolModel model = new InPropPreviousPolModel();
		
		model.setPolnum(rs.getInt("polnum"));
		model.setPplinf(rs.getString("pplinf"));
		model.setPrdcod(rs.getString("prdcod"));
		model.setSumrkm(rs.getInt("sumrkm"));
		model.setBassum(rs.getInt("bassum"));
		model.setPprnum(rs.getInt("pprnum"));
		model.setPrpseq(rs.getInt("prpseq"));
		
		return model;
	}

}
