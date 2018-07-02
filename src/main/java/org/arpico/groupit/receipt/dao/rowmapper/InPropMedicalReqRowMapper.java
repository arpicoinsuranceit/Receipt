package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.InPropMedicalReqModel;
import org.arpico.groupit.receipt.model.pk.InPropMedicalReqModelPK;
import org.springframework.jdbc.core.RowMapper;

public class InPropMedicalReqRowMapper implements RowMapper<InPropMedicalReqModel>{

	@Override
	public InPropMedicalReqModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		InPropMedicalReqModelPK modelPK = new InPropMedicalReqModelPK();
		
		modelPK.setInstyp(rs.getString("instyp"));
		modelPK.setLoccod(rs.getString("loccod"));
		modelPK.setMedcod(rs.getString("medcod"));
		modelPK.setPprnum(rs.getInt("pprnum"));
		modelPK.setPrpseq(rs.getInt("prpseq"));
		modelPK.setSbucod(rs.getString("sbucod"));
		
		InPropMedicalReqModel model = new InPropMedicalReqModel();
		
		model.setInPropMedicalReqModelPK(modelPK);
		model.setAddnot(rs.getString("addnot"));
		model.setGlbtch(rs.getString("glbtch"));
		model.setHoscod(rs.getString("hoscod"));
		model.setLockin(rs.getDate("lockin"));
		model.setMednam(rs.getString("mednam"));
		model.setMedorg(rs.getString("medorg"));
		model.setPayamt(rs.getDouble("payamt"));
		model.setPaysta(rs.getString("paysta"));
		model.setRcddat(rs.getDate("rcddat"));
		model.setTessta(rs.getString("tessta"));
		
		return model;
	}

}
