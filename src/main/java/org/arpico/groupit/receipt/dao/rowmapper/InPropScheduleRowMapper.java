package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.InPropSchedulesModel;
import org.arpico.groupit.receipt.model.pk.InPropSchedulesModelPK;
import org.springframework.jdbc.core.RowMapper;

public class InPropScheduleRowMapper implements RowMapper<InPropSchedulesModel>{

	@Override
	public InPropSchedulesModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		InPropSchedulesModelPK modelPK = new InPropSchedulesModelPK();
		
		modelPK.setLoccod(rs.getString("loccod"));
		modelPK.setPolyer(rs.getInt("polyer"));
		modelPK.setPprnum(rs.getInt("pprnum"));
		modelPK.setPrpseq(rs.getInt("prpseq"));
		modelPK.setSbucod(rs.getString("sbucod"));
		
		InPropSchedulesModel model = new InPropSchedulesModel();
		
		model.setInPropSchedulesPK(modelPK);
		model.setLonred(rs.getDouble("lonred"));
		model.setOutsum(rs.getDouble("outsum"));
		model.setOutyer(rs.getInt("outyer"));
		model.setPremum(rs.getDouble("premum"));
		model.setPrmrat(rs.getDouble("prmrat"));
		
		return model;
	}

}
