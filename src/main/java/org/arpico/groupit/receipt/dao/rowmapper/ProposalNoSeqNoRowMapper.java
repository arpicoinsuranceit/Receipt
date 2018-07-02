package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.ProposalNoSeqNoModel;
import org.springframework.jdbc.core.RowMapper;

public class ProposalNoSeqNoRowMapper implements RowMapper<ProposalNoSeqNoModel>{

	@Override
	public ProposalNoSeqNoModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		ProposalNoSeqNoModel proposalNoSeqNoModel = new ProposalNoSeqNoModel();
		
		proposalNoSeqNoModel.setProposalNo(rs.getString("pprnum"));
		proposalNoSeqNoModel.setSeqNo(Integer.toString(rs.getInt("prpseq")));
		
		return proposalNoSeqNoModel;
	}

}
