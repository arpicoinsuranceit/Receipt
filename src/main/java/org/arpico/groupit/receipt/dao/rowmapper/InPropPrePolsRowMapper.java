package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.InPropPrePolsModel;
import org.arpico.groupit.receipt.model.pk.InPropPrePolsModelPK;
import org.springframework.jdbc.core.RowMapper;

public class InPropPrePolsRowMapper implements RowMapper<InPropPrePolsModel>{

	@Override
	public InPropPrePolsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		InPropPrePolsModelPK modelPK = new InPropPrePolsModelPK();
		
		modelPK.setLoccod(rs.getString("loccod"));
		modelPK.setPolnum(rs.getString("polnum"));
		modelPK.setPprnum(rs.getInt("pprnum"));
		modelPK.setPrpseq(rs.getInt("prpseq"));
		modelPK.setSbucod(rs.getString("sbucod"));
		
		InPropPrePolsModel model = new InPropPrePolsModel();
		
		model.setInPropPrePolsModelPK(modelPK);
		model.setInsrer(rs.getString("insrer"));
		model.setPplinf(rs.getString("pplinf"));
		model.setPrdcod(rs.getString("prdcod"));
		model.setSumrkm(rs.getInt("sumrkm"));
		
		return model;
	}

}
