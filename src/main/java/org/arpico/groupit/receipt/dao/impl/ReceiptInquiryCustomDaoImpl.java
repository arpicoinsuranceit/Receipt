package org.arpico.groupit.receipt.dao.impl;

import java.util.List;
import org.arpico.groupit.receipt.dao.ReceiptInquiryCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.AccountDetailsRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.BankDetailsRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.PolicyDetailsRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.ReceiptDetailsRowMapper;
import org.arpico.groupit.receipt.model.AccountDetailsModel;
import org.arpico.groupit.receipt.model.BankDetailsModel;
import org.arpico.groupit.receipt.model.PolicyDetailsModel;
import org.arpico.groupit.receipt.model.ReceiptDetailsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ReceiptInquiryCustomDaoImpl implements ReceiptInquiryCustomDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<ReceiptDetailsModel> getAllReceiptDetails(Integer offset,String loccodes,boolean isHO,Integer limit) throws Exception {
		if(isHO) {
			return jdbcTemplate.query("select doccod,docnum,txndat,totprm,paymod,chqnum,chqrel,pprnum,polnum,ppdnam,advcod from intransactions where sbucod='450' order by creadt desc LIMIT "+limit+" OFFSET "+offset+";",
					new ReceiptDetailsRowMapper());
		}else {
			return jdbcTemplate.query("select doccod,docnum,txndat,totprm,paymod,chqnum,chqrel,pprnum,polnum,ppdnam,advcod from intransactions where sbucod='450' and loccod in ("+loccodes+") order by creadt desc LIMIT "+limit+" OFFSET "+offset+";",
					new ReceiptDetailsRowMapper());
		}
		
	}

	@Override
	public Integer getAllReceiptCount(String loccodes, boolean isHO) throws Exception {
		Integer count=0;
		if(isHO) {
			count=jdbcTemplate.queryForObject("select count(*) from intransactions where sbucod='450' ", Integer.class);
			return count;
		}else {
			count=jdbcTemplate.queryForObject("select count(*) from intransactions where sbucod='450' and loccod in ("+loccodes+") ", Integer.class);
			return count;
		}
	}

	@Override
	public List<PolicyDetailsModel> getAllPolicyDetails(String docCode, Integer docNum) throws Exception {
		return jdbcTemplate.query("select b.polnum,b.pprnum,b.prdcod,b.pprsta,icpdat,concat(txnyer,'-',txnmth) insnum,b.txndat,(a.amount*-1) amount from inproposals b inner join " + 
				"(select * from inbillingtransactions where sbucod='450' and doccod='"+docCode+"' and docnum="+docNum+" and amount <> 0) a " + 
				"on b.sbucod=a.sbucod and b.pprnum=a.pprnum " + 
				"where b.pprsta <> 'INAC' ;",
				new PolicyDetailsRowMapper());
	}

	@Override
	public List<AccountDetailsModel> getAllAccountDetails(String docCode, Integer docNum) throws Exception {
		return jdbcTemplate.query("select a.dimm04,b.alacid,b.descri,sum(if(amount>0,amount,0)) dramnt,sum(if(amount<0,amount,0)) cramnt from gltrantemp a " + 
				"inner join glcharofaccs b on a.sbucod=b.sbucod and a.interid=b.interid " + 
				"where a.sbucod='450' and a.doccod='"+docCode+"' and a.docnum="+docNum+" " + 
				"group by b.interid,b.alacid,b.descri " + 
				"union all " + 
				"select a.dimm04,b.alacid,b.descri,sum(if(amount>0,amount,0)) dramnt,sum(if(amount<0,amount,0)) cramnt from gltran a " + 
				"inner join glcharofaccs b on a.sbucod=b.sbucod and a.interid=b.interid " + 
				"where a.sbucod='450' and a.doccod='"+docCode+"' and a.docnum="+docNum+" " + 
				"group by b.interid,b.alacid,b.descri;",
				new AccountDetailsRowMapper());
	}

	@Override
	public BankDetailsModel getBankDetails(String docCode, Integer docNum) throws Exception {
		return jdbcTemplate.queryForObject("select paymod,bancod,chqnum,chqbnk,txndat,totprm,rctsta,remark from intransactions where  sbucod='450' and docnum="+docNum+" and doccod='"+docCode+"'  and totprm > 0 ; ",
				new BankDetailsRowMapper());
	}

}
