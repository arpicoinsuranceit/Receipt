package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.InProposalBasicsModel;
import org.springframework.jdbc.core.RowMapper;

public class InPolicyBasicRowMapper implements RowMapper<InProposalBasicsModel>{

	@Override
	public InProposalBasicsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		InProposalBasicsModel basicsModel = new InProposalBasicsModel();
		
		basicsModel.setAgentCode(rs.getString("advcod"));
		basicsModel.setCustName(rs.getString("ppdnam"));
		basicsModel.setProduct(rs.getString("prdcod"));
		basicsModel.setProposalNo(Integer.parseInt(rs.getString("polnum")));
		basicsModel.setSeqNo(rs.getInt("prpseq"));
		basicsModel.setCustTitle(rs.getString("ntitle"));
		basicsModel.setPremium(rs.getDouble("totprm"));
		basicsModel.setMobNo(rs.getString("ppdmob"));
		basicsModel.setPrsta(rs.getString("pprsta"));
		basicsModel.setId2(Integer.parseInt(rs.getString("pprnum")));
		return basicsModel;
	}
}
