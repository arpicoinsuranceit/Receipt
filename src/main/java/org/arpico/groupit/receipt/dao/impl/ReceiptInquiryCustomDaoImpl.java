package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.ReceiptInquiryCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.ReceiptDetailsRowMapper;
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
			return jdbcTemplate.query("select doccod,docnum,txndat,totprm,paymod,chqnum,chqrel,pprnum,polnum,ppdnam,advcod from marksys.intransactions where sbucod='450' order by creadt desc LIMIT "+limit+" OFFSET "+offset+";",
					new ReceiptDetailsRowMapper());
		}else {
			return jdbcTemplate.query("select doccod,docnum,txndat,totprm,paymod,chqnum,chqrel,pprnum,polnum,ppdnam,advcod from marksys.intransactions where sbucod='450' and loccod in ("+loccodes+") order by creadt desc LIMIT "+limit+" OFFSET "+offset+";",
					new ReceiptDetailsRowMapper());
		}
		
	}

}
