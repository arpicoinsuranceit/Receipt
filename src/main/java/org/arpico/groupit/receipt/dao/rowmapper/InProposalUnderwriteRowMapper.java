package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.arpico.groupit.receipt.model.InProposalUnderwriteModel;
import org.springframework.jdbc.core.RowMapper;

public class InProposalUnderwriteRowMapper implements RowMapper<InProposalUnderwriteModel>{

	@Override
	public InProposalUnderwriteModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		InProposalUnderwriteModel basicsModel = new InProposalUnderwriteModel();
		
		basicsModel.setProposalNo(Integer.parseInt(rs.getString("pprnum")));
		basicsModel.setSeqNo(rs.getInt("prpseq"));
		basicsModel.setPolNo(rs.getInt("polnum"));
		basicsModel.setCustCode(rs.getInt("cscode"));
		basicsModel.setCustName(rs.getString("ppdnam"));
		basicsModel.setAgentCode(rs.getString("advcod"));
		basicsModel.setPolBranch(rs.getString("loccod"));
		basicsModel.setAgentBranch(rs.getString("brncod"));
		basicsModel.setNic(rs.getString("ppdnic"));
		basicsModel.setProduct(rs.getString("prdcod"));
		
		return basicsModel;
	}

}
