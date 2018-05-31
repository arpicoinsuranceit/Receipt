package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.SmSequenceModel;
import org.springframework.jdbc.core.RowMapper;

public class SmSequenceRowMapper implements RowMapper<SmSequenceModel>{

	@Override
	public SmSequenceModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		SmSequenceModel sequenceModel = new SmSequenceModel();
		
		sequenceModel.setCurrentValue(rs.getLong("curv"));
		sequenceModel.setCycle(rs.getString("cycl"));
		sequenceModel.setIncrementBy(rs.getLong("incby"));
		sequenceModel.setMaxValue(rs.getLong("maxv"));
		sequenceModel.setMinValue(rs.getLong("minv"));
		
		return sequenceModel;
	}

}
