package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.InPropSurrenderValsModel;
import org.arpico.groupit.receipt.model.pk.InPropSurrenderValsPK;
import org.springframework.jdbc.core.RowMapper;

public class InPropSurrenderValsRowMapper implements RowMapper<InPropSurrenderValsModel>{

	@Override
	public InPropSurrenderValsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		InPropSurrenderValsPK inPropSurrenderValsPK = new InPropSurrenderValsPK();
		
		inPropSurrenderValsPK.setPadtrm(rs.getString(""));
		inPropSurrenderValsPK.setPolyer(rs.getInt(""));
		inPropSurrenderValsPK.setPprnum(rs.getString(""));
		inPropSurrenderValsPK.setPrpseq(rs.getInt(""));
		inPropSurrenderValsPK.setQuonum(rs.getInt(""));
		inPropSurrenderValsPK.setSbucod(rs.getString(""));
		
		InPropSurrenderValsModel model = new InPropSurrenderValsModel();
		
		model.setInPropSurrenderValsPK(inPropSurrenderValsPK);
		model.setAdvcod(rs.getString(""));
		model.setIsumas(rs.getDouble(""));
		model.setMature(rs.getDouble(""));
		model.setPaidup(rs.getDouble(""));
		model.setPolnum(rs.getString(""));
		model.setPrmpad(rs.getDouble(""));
		model.setPrmpyr(rs.getDouble(""));
		model.setSurrnd(rs.getDouble(""));
		
		return model;
	}

}
