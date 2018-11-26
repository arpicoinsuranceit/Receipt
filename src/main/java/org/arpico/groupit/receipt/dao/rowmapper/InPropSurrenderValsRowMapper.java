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
		
		inPropSurrenderValsPK.setPadtrm(rs.getString("padtrm"));
		inPropSurrenderValsPK.setPolyer(rs.getInt("polyer"));
		inPropSurrenderValsPK.setPprnum(rs.getString("pprnum"));
		inPropSurrenderValsPK.setPrpseq(rs.getInt("prpseq"));
		inPropSurrenderValsPK.setQuonum(rs.getInt("quonum"));
		inPropSurrenderValsPK.setSbucod(rs.getString("sbucod"));
		
		InPropSurrenderValsModel model = new InPropSurrenderValsModel();
		
		model.setInPropSurrenderValsPK(inPropSurrenderValsPK);
		model.setAdvcod(rs.getString("advcod"));
		model.setIsumas(rs.getDouble("isumas"));
		model.setMature(rs.getDouble("mature"));
		model.setPaidup(rs.getDouble("paidup"));
		model.setPolnum(rs.getString("polnum"));
		model.setPrmpad(rs.getDouble("prmpad"));
		model.setPrmpyr(rs.getDouble("prmpyr"));
		model.setSurrnd(rs.getDouble("surrnd"));
		
		return model;
	}

}
