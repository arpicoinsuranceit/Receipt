package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.InTransactionsModel;
import org.arpico.groupit.receipt.model.pk.InTransactionsModelPK;
import org.springframework.jdbc.core.RowMapper;

public class InTransactionRowMapper implements RowMapper<InTransactionsModel>{
	
	@Override
	public InTransactionsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		InTransactionsModelPK pk = new InTransactionsModelPK();
		
		pk.setDoccod(rs.getString("doccod"));
		pk.setDocnum(rs.getInt("docnum"));
		pk.setLinnum(rs.getInt("linnum"));
		pk.setLoccod(rs.getString("loccod"));
		pk.setSbucod(rs.getString("sbucod"));
		
		InTransactionsModel model = new InTransactionsModel();
		
		model.setInTransactionsModelPK(pk);
		
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
		model.setCreadt(rs.getTimestamp("creadt"));
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
		
		return model;
	}
	
	
	

}
