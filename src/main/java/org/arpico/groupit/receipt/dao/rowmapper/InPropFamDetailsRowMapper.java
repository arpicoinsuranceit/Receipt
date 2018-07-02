package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.InPropFamDetailsModel;
import org.arpico.groupit.receipt.model.pk.InPropFamDetailsModelPK;
import org.springframework.jdbc.core.RowMapper;

public class InPropFamDetailsRowMapper implements RowMapper<InPropFamDetailsModel>{

	@Override
	public InPropFamDetailsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		InPropFamDetailsModelPK modelPK = new InPropFamDetailsModelPK();
		
		modelPK.setFmlnam(rs.getString("fmlnam"));
		modelPK.setLoccod(rs.getString("loccod"));
		modelPK.setPprnum(rs.getInt("pprnum"));
		modelPK.setPrpseq(rs.getInt("prpseq"));
		modelPK.setSbucod(rs.getString("sbucod"));
		
		InPropFamDetailsModel model = new InPropFamDetailsModel();
		
		model.setInPropFamDetailsPK(modelPK);
		model.setCicapp(rs.getString("cicapp"));
		model.setFmlage(rs.getFloat("fmlage"));
		model.setFmldob(rs.getDate("fmldob"));
		model.setFmlnic(rs.getString("fmlnic"));
		model.setFmlrel(rs.getString("fmlrel"));
		model.setFmlsex(rs.getString("fmlsex"));
		model.setFmlshr(rs.getDouble("fmlshr"));
		model.setHbcapp(rs.getString("hbcapp"));
		model.setHrbapp(rs.getString("hrbapp"));
		model.setLockin(rs.getDate("lockin"));
		model.setShrbap(rs.getString("shrbap"));
		
		return model;
	}

}
