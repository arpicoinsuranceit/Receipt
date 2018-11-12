package org.arpico.groupit.receipt.dao.impl;

import java.util.List;
import org.arpico.groupit.receipt.dao.InProposalCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.InPolicyBasicRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.InPropPreviousPolsCompleteRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.InPropPreviousPolsRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.InProposalBasicRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.InProposalsRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.ProposalL3RowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.ProposalNoSeqNoRowMapper;
import org.arpico.groupit.receipt.dto.ProposalL3Dto;
import org.arpico.groupit.receipt.model.InPropPreviousPolModel;
import org.arpico.groupit.receipt.model.InProposalBasicsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.ProposalNoSeqNoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class InProposalCustomDaoImpl implements InProposalCustomDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<ProposalNoSeqNoModel> getProposalNoSeqNoModelList(String val) throws Exception {
		List<ProposalNoSeqNoModel> list = jdbcTemplate.query(
				"select pprnum, prpseq from inproposals where sbucod = '450' and pprnum like '" + val + "%' and pprsta not in ('PLISU', 'PLAPS', 'INAC', 'EXPI', 'MATU')", new ProposalNoSeqNoRowMapper());
		return list;
	}

	@Override
	public InProposalBasicsModel geInProposalBasics(Integer pprNo, Integer prpseq) throws Exception {
		List<InProposalBasicsModel> basicsModels = jdbcTemplate.query("select advcod, ppdnam, prdcod, pprnum, ntitle, "
				+ "prpseq, totprm from inproposals where pprnum = '"+pprNo+"' and prpseq = '"+prpseq+"' and sbucod = '450'", new InProposalBasicRowMapper());
		
		if(!basicsModels.isEmpty()) {
			return basicsModels.get(0);
		}
		
		return null;
	}

	@Override
	public InProposalsModel getProposal(Integer propId, Integer propSeq) throws Exception {
		List<InProposalsModel> models = jdbcTemplate.query("select * from inproposals where pprnum = '"+propId+"' and prpseq = '"+propSeq+"' and sbucod = '450'", new InProposalsRowMapper());
		
		System.out.println(models.size());
		
		System.out.println(models.size());
		
		if(!models.isEmpty()) {
			System.out.println("if");
			return models.get(0);
		}
		
		return null;
	}

	@Override
	public List<ProposalL3Dto> checkL3(Integer propId) throws Exception {
		List<ProposalL3Dto> models = jdbcTemplate.query("SELECT x.pprnum, (x.totprm + x.polfee) totprm, x.payment, y.reqcnt FROM " + 
				"	(SELECT  a.sbucod, a.pprnum, a.prpseq, a.totprm, a.polfee, " + 
				"		(SELECT SUM(totprm) FROM intransactions b WHERE a.sbucod = b.sbucod AND a.pprnum = b.pprnum) payment, s.spiamt " + 
				"			FROM inproposals a " + 
				"		INNER JOIN inshort_premium_act_product s ON a.sbucod = s.sbucod AND a.prdcod = s.prdcod WHERE " + 
				"			a.sbucod = '450' AND a.pprsta IN ('L3') AND a.pprnum = '"+propId+"' AND s.status = 'ACT') x " + 
				"        INNER JOIN " + 
				"			(SELECT sbucod, pprnum, prpseq, medcod, " + 
				"				(SELECT COUNT(*) FROM inpropmedicalreq a WHERE a.sbucod = b.sbucod AND a.loccod = b.loccod " + 
				"                        AND a.prpseq = b.prpseq AND a.pprnum = b.pprnum AND a.medcod LIKE 'AD%' AND a.tessta = 'N' " + 
				"                        AND addnot NOT LIKE 'Premium Short%') reqcnt " + 
				"			FROM inpropmedicalreq b WHERE sbucod = '450' AND medcod LIKE 'AD%' AND addnot LIKE 'Premium Short%' " + 
				"	AND tessta = 'N') " + 
				"y ON x.sbucod = y.sbucod AND x.pprnum = y.pprnum AND x.prpseq = y.prpseq " + 
				"WHERE ((x.totprm + x.polfee) - x.spiamt) <= x.payment AND reqcnt < 1 GROUP BY x.pprnum", new ProposalL3RowMapper());
		
		return models;
	}

	@Override
	public List<ProposalNoSeqNoModel> getPolicyNoSeqNoModelList(String val) throws Exception {
		List<ProposalNoSeqNoModel> list = jdbcTemplate.query(
				"select polnum as pprnum, prpseq from inproposals where  pprsta in ('plisu', 'plaps')  and sbucod = '450' and polnum like '" + val + "%'", new ProposalNoSeqNoRowMapper());
		return list;
	}

	@Override
	public InProposalBasicsModel geInPolicyBasics(int polNo, int seqNo) throws Exception {
		List<InProposalBasicsModel> basicsModels = jdbcTemplate.query("select advcod, ppdnam, prdcod, polnum, ntitle, "
				+ "prpseq, totprm from inproposals where polnum = '"+polNo+"' and prpseq = '"+seqNo+"' and sbucod = '450'", new InPolicyBasicRowMapper());
		
		if(!basicsModels.isEmpty()) {
			return basicsModels.get(0);
		}
		
		return null;
	}

	@Override
	public InProposalsModel getProposalBuPolicy(Integer polId, Integer propSeq) throws Exception {
		List<InProposalsModel> models = jdbcTemplate.query("select * from inproposals where polnum = '"+polId+"' and prpseq = '"+propSeq+"' and sbucod = '450'", new InProposalsRowMapper());
		
		System.out.println(models.size());
		
		if(!models.isEmpty()) {
			return models.get(0);
		}
		
		return null;
	}

	@Override
	public List<InPropPreviousPolModel> getPreviousPolicies(String sbu, String nic) throws Exception {
		List<InPropPreviousPolModel> models = jdbcTemplate.query("select prdcod,polnum,bassum,sumrkm,case when pprsta in ('PLISU','LAMD') " + 
				"then 'Y' else 'N' end pplinf from inproposals a " + 
				"where a.sbucod='" + sbu + "' and a.ppdnic='"+ nic + "' "+ 
				"and a.pprsta <> 'INAC' and (a.polnum is not null or " + 
				"polnum <> '') and TIMESTAMPDIFF(YEAR,icpdat,sysdate()) <= 2", new InPropPreviousPolsRowMapper());
		
		
		return models;
	}

	@Override
	public List<ProposalNoSeqNoModel> getProposalNoSeqNoModel(String pprNo) throws Exception {
		List<ProposalNoSeqNoModel> list = jdbcTemplate.query(
				"select pprnum, prpseq from inproposals where sbucod = '450' and pprnum = '" + pprNo + "' and pprsta not in ('PLISU', 'PLAPS', 'INAC', 'EXPI', 'MATU')", new ProposalNoSeqNoRowMapper());
		return list;
	}
	
	@Override
	public List<InPropPreviousPolModel> getAllPreviousPolicies(String sbu, String nic) throws Exception {
		List<InPropPreviousPolModel> models = jdbcTemplate.query("select prdcod,polnum,pprnum,prpseq,bassum,sumrkm,case when pprsta in ('PLISU','LAMD') " + 
				"then 'Y' else 'N' end pplinf from inproposals a " + 
				"where a.sbucod='" + sbu + "' and (a.ppdnic='"+ nic + "' or a.sponic='"+ nic + "') "+ 
				"and a.pprsta <> 'INAC' and (a.polnum is not null or " + 
				"polnum <> '') and TIMESTAMPDIFF(YEAR,icpdat,sysdate()) <= 2", new InPropPreviousPolsCompleteRowMapper());
		
		
		return models;
	}
	
	@Override
	public InProposalsModel getProposalFromPprnum(Integer pprnum) throws Exception {
		InProposalsModel models = jdbcTemplate.queryForObject("select * from inproposals where sbucod = '450' and pprnum = '"+pprnum+"' order by prpseq desc limit 1", new InProposalsRowMapper());

		return models;
	}
	
	@Override
	public InProposalsModel getProposalFromPolnum(Integer polnum) throws Exception {
		InProposalsModel models = jdbcTemplate.queryForObject("select * from inproposals where sbucod = '450' and polnum = '"+polnum+"' and pprsta <> 'INAC' ", new InProposalsRowMapper());

		return models;
	}

}
