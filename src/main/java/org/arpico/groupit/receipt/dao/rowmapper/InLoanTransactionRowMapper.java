package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.InLoanTransactionsModel;
import org.arpico.groupit.receipt.model.pk.InLoanTransactionsModelPK;
import org.springframework.jdbc.core.RowMapper;

public class InLoanTransactionRowMapper implements RowMapper<InLoanTransactionsModel>{
	
	@Override
	public InLoanTransactionsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		InLoanTransactionsModelPK pk = new InLoanTransactionsModelPK();
		
		pk.setDoccod(rs.getString("doccod"));
		pk.setDocnum(rs.getInt("docnum"));
		pk.setLinnum(rs.getInt("linnum"));
		pk.setLoccod(rs.getString("loccod"));
		pk.setSbucod(rs.getString("sbucod"));
		
		InLoanTransactionsModel model = new InLoanTransactionsModel();
		
		model.setInLoanTransactionsModelPK(pk);
		
		model.setAdvcod(rs.getString("advcod"));
		model.setAmtwrd(rs.getString("amtwrd"));
		model.setBancod(rs.getString("bancod"));
		model.setBildat(rs.getDate("bildat"));
		model.setBilpik(rs.getString("bilpik"));
		model.setCcdnum(rs.getString("ccdnum"));
		model.setChqbnk(rs.getString("chqbnk"));
		model.setChqdat(rs.getDate("chqdat"));
		model.setChqnum(rs.getString("chqnum"));
		model.setChqrel(rs.getString("chqrel"));
		model.setCompad(rs.getString("compad"));
		model.setCreaby(rs.getString("creaby"));
		model.setCreadt(rs.getDate("creadt"));
		model.setCscode(rs.getString("cscode"));
		model.setLockin(rs.getDate("lockin"));
		model.setNtitle(rs.getString("ntitle"));
		model.setPaymod(rs.getString("paymod"));
		model.setPolnum(rs.getInt("polnum"));
		model.setPpdad1(rs.getString("ppdad1"));
		model.setPpdad2(rs.getString("ppdad2"));
		model.setPpdad3(rs.getString("ppdad3"));
		model.setPpdnam(rs.getString("ppdnam"));
		model.setPprnum(rs.getString("pprnum"));
		model.setQuonum(rs.getInt("quonum"));
		model.setRctsta(rs.getString("rctsta"));
		model.setReldat(rs.getDate("reldat"));
		model.setRemark(rs.getString("remark"));
		model.setSeqnum(rs.getInt("seqnum"));
		model.setTotprm(rs.getDouble("totprm"));
		model.setTxndat(rs.getDate("txndat"));
		model.setFclnum(rs.getInt("fclnum"));
		
		return model;
	}
	
	
	

}
