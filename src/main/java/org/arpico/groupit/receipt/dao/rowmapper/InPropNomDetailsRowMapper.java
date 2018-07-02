package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.InPropNomDetailsModel;
import org.arpico.groupit.receipt.model.pk.InPropNomDetailsModelPK;
import org.springframework.jdbc.core.RowMapper;

public class InPropNomDetailsRowMapper implements RowMapper<InPropNomDetailsModel>{

	@Override
	public InPropNomDetailsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		InPropNomDetailsModelPK modelPK = new InPropNomDetailsModelPK();
		
		modelPK.setLoccod(rs.getString("loccod"));
		modelPK.setNomnam(rs.getString("nomnam"));
		modelPK.setPprnum(rs.getInt("pprnum"));
		modelPK.setPrpseq(rs.getInt("prpseq"));
		modelPK.setSbucod(rs.getString("sbucod"));
		
		InPropNomDetailsModel model = new InPropNomDetailsModel();
		
		model.setInPropNomDetailsModelPK(modelPK);
		model.setGurdnm(rs.getString("gurdnm"));
		model.setGurdob(rs.getDate("gurdob"));
		model.setGurnic(rs.getString("gurnic"));
		model.setGurrel(rs.getString("gurrel"));
		model.setLockin(rs.getDate("lockin"));
		model.setNomdob(rs.getDate("nomdob"));
		model.setNomnic(rs.getString("nomnic"));
		model.setNomrel(rs.getString("nomrel"));
		model.setNomshr(rs.getDouble("nomshr"));
		model.setNomsum(rs.getDouble("nomsum"));
		model.setNomtyp(rs.getString("nomtyp"));
		
		return model;
	}

}
