package org.arpico.groupit.receipt.dao.impl;

import org.arpico.groupit.receipt.dao.SmSequenceDao;
import org.arpico.groupit.receipt.dao.rowmapper.SmSequenceRowMapper;
import org.arpico.groupit.receipt.model.SmSequenceModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SmSequenceDaoImpl implements SmSequenceDao{


	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public SmSequenceModel getSequence(String serial) throws Exception {
		return (SmSequenceModel) jdbcTemplate.queryForObject("select * from smsequence where seqid = '" + serial + "' and SBUCOD = '450'", new SmSequenceRowMapper());
	}

	@Override
	public void updateCurrentNumber(long current_number, String serial) {
		String sql =  "update smsequence set curv='" + current_number + "' where sbucod='450' and seqid='" + serial + "'"; 
		
		jdbcTemplate.update(sql);
	}

}
