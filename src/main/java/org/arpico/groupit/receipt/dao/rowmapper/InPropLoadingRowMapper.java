package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.InPropLoadingModel;
import org.arpico.groupit.receipt.model.pk.InPropLoadingModelPK;
import org.springframework.jdbc.core.RowMapper;

public class InPropLoadingRowMapper implements RowMapper<InPropLoadingModel>{

	@Override
	public InPropLoadingModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		InPropLoadingModelPK modelPK = new InPropLoadingModelPK();
		
		modelPK.setLoccod(rs.getString("loccod"));
		modelPK.setPprnum(rs.getInt("pprnum"));
		modelPK.setPrpseq(rs.getInt("prpseq"));
		modelPK.setRidcod(rs.getString("ridcod"));
		modelPK.setSbucod(rs.getString("sbucod"));
		
		InPropLoadingModel model = new InPropLoadingModel();
		
		model.setInPropLoadingPK(modelPK);
		model.setEmrate(rs.getDouble("emrate"));
		model.setGrdord(rs.getInt("grdord"));
		model.setInstyp(rs.getString("instyp"));
		model.setLockin(rs.getDate("lockin"));
		model.setOculod(rs.getDouble("oculod"));
		model.setOcuval(rs.getDouble("ocuval"));
		model.setOcuvhy(rs.getDouble("ocuvhy"));
		model.setOcuvmt(rs.getDouble("ocuvmt"));
		model.setOcuvqt(rs.getDouble("ocuvqt"));
		model.setOcuvyr(rs.getDouble("ocuvyr"));
		model.setRatmil(rs.getDouble("ratmil"));
		model.setRatval(rs.getDouble("ratval"));
		model.setRatvhy(rs.getDouble("ratvhy"));
		model.setRatvmt(rs.getDouble("ratvmt"));
		model.setRatvqt(rs.getDouble("ratvqt"));
		model.setRatvyr(rs.getDouble("ratvyr"));
		model.setRidnam(rs.getString("ridnam"));
		model.setRidtyp(rs.getString("ridtyp"));
		model.setSubrat(rs.getDouble("subrat"));
		model.setSubval(rs.getDouble("subval"));
		model.setSubvhy(rs.getDouble("subvhy"));
		model.setSubvmt(rs.getDouble("subvmt"));
		model.setSubvqt(rs.getDouble("subvqt"));
		model.setSubvyr(rs.getDouble("subvyr"));
		
		return model;
	}

}
