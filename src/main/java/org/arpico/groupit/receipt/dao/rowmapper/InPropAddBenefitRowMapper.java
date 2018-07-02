package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.InPropAddBenefitModel;
import org.arpico.groupit.receipt.model.pk.InPropAddBenefitModelPK;
import org.springframework.jdbc.core.RowMapper;

public class InPropAddBenefitRowMapper implements RowMapper<InPropAddBenefitModel> {

	@Override
	public InPropAddBenefitModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		InPropAddBenefitModelPK modelPK = new InPropAddBenefitModelPK();
		
		modelPK.setLoccod(rs.getString("loccod"));
		modelPK.setPprnum(rs.getInt("pprnum"));
		modelPK.setPrpseq(rs.getInt("prpseq"));
		modelPK.setRidcod(rs.getString("ridcod"));
		modelPK.setSbucod(rs.getString("sbucod"));
		
		InPropAddBenefitModel model = new InPropAddBenefitModel();
		
		model.setInPropAddBenefitPK(modelPK);
		model.setAgemax(rs.getInt("agemax"));
		model.setAgemin(rs.getInt("agemin"));
		model.setDisord(rs.getInt("disord"));
		model.setExpdat(rs.getDate("expdat"));
		model.setGrdord(rs.getInt("grdord"));
		model.setGrprid(rs.getString("grprid"));
		model.setInstyp(rs.getString("instyp"));
		model.setLockin(rs.getDate("lockin"));
		model.setMedgrd(rs.getString("medgrd"));
		model.setPrmhlf(rs.getDouble("prmhlf"));
		model.setPrmmth(rs.getDouble("pprnum"));
		model.setPrmonl(rs.getString("prmonl"));
		model.setPrmqat(rs.getDouble("prmqat"));
		model.setPrmyer(rs.getDouble("prmyer"));
		model.setRdrprm(rs.getDouble("rdrprm"));
		model.setRidnam(rs.getString("ridnam"));
		model.setRidtrm(rs.getInt("ridtrm"));
		model.setRidtyp(rs.getString("ridtyp"));
		model.setSumasu(rs.getDouble("sumasu"));
		
		return model;
	}

}
