package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.InPropAddBenefitModel;
import org.arpico.groupit.receipt.model.pk.InPropAddBenefitModelPK;
import org.springframework.jdbc.core.RowMapper;

public class InProdAddBenefictCustomRowMapper implements RowMapper<InPropAddBenefitModel>{

	@Override
	public InPropAddBenefitModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		InPropAddBenefitModelPK addBenefitModelPK = new InPropAddBenefitModelPK();
		addBenefitModelPK.setRidcod(rs.getString("ridcod"));
		addBenefitModelPK.setSbucod(rs.getString("sbucod"));;
		//addBenefitModelPK.setLoccod(rs.getString("loccod"));
		//addBenefitModelPK.setPprnum(rs.getInt("pprnum"));
		//addBenefitModelPK.setPrpseq(rs.getInt("prpseq"));
		
		
		InPropAddBenefitModel addBenefitModel = new InPropAddBenefitModel();
		
		addBenefitModel.setRidnam(rs.getString("ridnam"));
		addBenefitModel.setDisord(rs.getInt("disord"));
		addBenefitModel.setPrmonl(rs.getString("prmonl"));
		addBenefitModel.setAgemin(rs.getInt("agemin"));
		addBenefitModel.setAgemax(rs.getInt("agemax"));
		addBenefitModel.setGrdord(rs.getInt("grdord"));
		addBenefitModel.setGrprid(rs.getString("grprid"));
		addBenefitModel.setMedgrd(rs.getString("medgrd"));
		addBenefitModel.setRidtyp(rs.getString("ridtyp"));
		addBenefitModel.setInstyp(rs.getString("instyp"));
		addBenefitModel.setInPropAddBenefitPK(addBenefitModelPK);
		return addBenefitModel;
	}

}
