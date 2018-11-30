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
				"select pprnum, prpseq from inproposals where sbucod = '450' and pprnum like '" + val
						+ "%' and pprsta not in ('PLISU', 'PLAPS', 'INAC', 'EXPI', 'MATU')",
				new ProposalNoSeqNoRowMapper());

//		List<ProposalNoSeqNoModel> list = jdbcTemplate.query(
//				"select pprnum, prpseq from inproposals where sbucod = '450' and pprnum like '" + val + "%' and pprsta not in ('PLISU', 'PLAPS', 'INAC', 'EXPI', 'MATU')", new ProposalNoSeqNoRowMapper());
		return list;
	}

	@Override
	public InProposalBasicsModel geInProposalBasics(Integer pprNo, Integer prpseq) throws Exception {
//		List<InProposalBasicsModel> basicsModels = jdbcTemplate.query(
//				"select advcod, ppdnam, prdcod, pprnum, ntitle, " + "prpseq, totprm from inproposals where pprnum = '"
//						+ pprNo + "' and prpseq = '" + prpseq + "' and sbucod = '450'",
//				new InProposalBasicRowMapper());

		List<InProposalBasicsModel> basicsModels = jdbcTemplate.query(
				"select advcod, ppdnam, prdcod, pprnum, ntitle, prpseq, totprm from inproposals "
						+ "where sbucod = '450' and pprnum = '" + pprNo + "' and prpseq = '" + prpseq + "'",
				new InProposalBasicRowMapper());

		if (!basicsModels.isEmpty()) {
			return basicsModels.get(0);
		}

		return null;
	}

	@Override
	public InProposalsModel getProposal(Integer propId, Integer propSeq) throws Exception {
//		List<InProposalsModel> models = jdbcTemplate
//				.query("select * from inproposals where sbucod = '450' and pprnum = '" + propId + "' and prpseq = '"
//						+ propSeq + "' ", new InProposalsRowMapper());

		List<InProposalsModel> models = jdbcTemplate
				.query("select * from inproposals where sbucod = '450' and pprnum = '" + propId + "' and prpseq = '"
						+ propSeq + "'", new InProposalsRowMapper());

		System.out.println(models.size());

		if (!models.isEmpty()) {
			System.out.println("if");
			return models.get(0);
		}

		return null;
	}

	@Override
	public List<ProposalL3Dto> checkL3(Integer propId) throws Exception {
		List<ProposalL3Dto> models = jdbcTemplate.query(
				"SELECT x.pprnum, (x.totprm + x.polfee) totprm, x.payment, y.reqcnt FROM "
						+ "	(SELECT  a.sbucod, a.pprnum, a.prpseq, a.totprm, a.polfee, "
						+ "		(SELECT SUM(totprm) FROM intransactions b WHERE a.sbucod = b.sbucod AND a.pprnum = b.pprnum) payment, s.spiamt "
						+ "			FROM inproposals a "
						+ "		INNER JOIN inshort_premium_act_product s ON a.sbucod = s.sbucod AND a.prdcod = s.prdcod WHERE "
						+ "			a.sbucod = '450' AND a.pprsta IN ('L3') AND a.pprnum = '" + propId
						+ "' AND s.status = 'ACT') x " + "        INNER JOIN "
						+ "			(SELECT sbucod, pprnum, prpseq, medcod, "
						+ "				(SELECT COUNT(*) FROM inpropmedicalreq a WHERE a.sbucod = b.sbucod AND a.loccod = b.loccod "
						+ "                        AND a.prpseq = b.prpseq AND a.pprnum = b.pprnum AND a.medcod LIKE 'AD%' AND a.tessta = 'N' "
						+ "                        AND addnot NOT LIKE 'Premium Short%') reqcnt "
						+ "			FROM inpropmedicalreq b WHERE sbucod = '450' AND medcod LIKE 'AD%' AND addnot LIKE 'Premium Short%' "
						+ "	AND tessta = 'N') "
						+ "y ON x.sbucod = y.sbucod AND x.pprnum = y.pprnum AND x.prpseq = y.prpseq "
						+ "WHERE ((x.totprm + x.polfee) - x.spiamt) <= x.payment AND reqcnt < 1 GROUP BY x.pprnum",
				new ProposalL3RowMapper());

		return models;
	}

	@Override
	public List<ProposalNoSeqNoModel> getPolicyNoSeqNoModelList(String val) throws Exception {
		List<ProposalNoSeqNoModel> list = jdbcTemplate.query("select polnum as pprnum, prpseq from inproposals "
				+ "where sbucod = '450' and  polnum like '" + val + "%' and  pprsta in ('plisu', 'plaps')",
				new ProposalNoSeqNoRowMapper());
//		
//		List<ProposalNoSeqNoModel> list = jdbcTemplate.query(
//				"select polnum as pprnum, prpseq from inproposals where  pprsta in ('plisu', 'plaps')  and sbucod = '450' and polnum like '"
//						+ val + "%'",
//				new ProposalNoSeqNoRowMapper());
		return list;
	}

	@Override
	public InProposalBasicsModel geInPolicyBasics(int polNo, int seqNo) throws Exception {
//		List<InProposalBasicsModel> basicsModels = jdbcTemplate.query(
//				"select advcod, ppdnam, prdcod, polnum, ntitle, " + "prpseq, totprm from inproposals where polnum = '"
//						+ polNo + "' and prpseq = '" + seqNo + "' and sbucod = '450'",
//				new InPolicyBasicRowMapper());

		List<InProposalBasicsModel> basicsModels = jdbcTemplate.query(
				"select advcod, ppdnam, prdcod, polnum, ntitle, prpseq, totprm from inproposals "
						+ "where sbucod = '450' and polnum = '" + polNo + "' and prpseq = '" + seqNo + "'",
				new InPolicyBasicRowMapper());

		if (!basicsModels.isEmpty()) {
			return basicsModels.get(0);
		}

		return null;
	}

	@Override
	public InProposalsModel getProposalBuPolicy(Integer polId, Integer propSeq) throws Exception {
//		List<InProposalsModel> models = jdbcTemplate.query("select * from inproposals where polnum = '" + polId
//				+ "' and prpseq = '" + propSeq + "' and sbucod = '450'", new InProposalsRowMapper());

		List<InProposalsModel> models = jdbcTemplate.query("select * from inproposals "
				+ "where sbucod = '450' and polnum = '" + polId + "' and prpseq = '" + propSeq + "'",
				new InProposalsRowMapper());

		System.out.println(models.size());

		if (!models.isEmpty()) {
			return models.get(0);
		}

		return null;
	}

	@Override
	public List<InPropPreviousPolModel> getPreviousPolicies(String sbu, String nic) throws Exception {
//		List<InPropPreviousPolModel> models = jdbcTemplate.query(
//				"select prdcod,polnum,bassum,sumrkm,case when pprsta in ('PLISU','LAMD') "
//						+ "then 'Y' else 'N' end pplinf from inproposals a " + "where a.sbucod='" + sbu
//						+ "' and a.ppdnic='" + nic + "' " + "and a.pprsta <> 'INAC' and (a.polnum is not null or "
//						+ "polnum <> '') and TIMESTAMPDIFF(YEAR,icpdat,sysdate()) <= 2",
//				new InPropPreviousPolsRowMapper());

		List<InPropPreviousPolModel> models = jdbcTemplate.query("select prdcod,polnum,bassum,sumrkm,case "
				+ "when pprsta in ('PLISU','LAMD') then 'Y' else 'N' end pplinf from inproposals a "
				+ "	where a.sbucod='" + sbu + "' and a.ppdnic='" + nic
				+ "' and a.pprsta <> 'INAC' and (a.polnum is not null or polnum <> '') and TIMESTAMPDIFF(YEAR,icpdat,sysdate()) <= 2",
				new InPropPreviousPolsRowMapper());

		return models;
	}

	@Override
	public List<ProposalNoSeqNoModel> getProposalNoSeqNoModel(String pprNo) throws Exception {
		/*
		 * List<ProposalNoSeqNoModel> list = jdbcTemplate.query(
		 * "select pprnum, prpseq from inproposals where sbucod = '450' and pprnum = '"
		 * + pprNo + "' and pprsta not in ('PLISU', 'PLAPS', 'INAC', 'EXPI', 'MATU')",
		 * new ProposalNoSeqNoRowMapper());
		 */

		List<ProposalNoSeqNoModel> list = jdbcTemplate.query(
				"select pprnum, prpseq from inproposals " + "where sbucod = '450' and pprnum = '" + pprNo
						+ "' and pprsta not in ('PLISU', 'PLAPS', 'INAC', 'EXPI', 'MATU')",
				new ProposalNoSeqNoRowMapper());
		return list;
	}

	@Override
	public List<InPropPreviousPolModel> getAllPreviousPolicies(String sbu, String nic) throws Exception {
//		List<InPropPreviousPolModel> models = jdbcTemplate.query(
//				"select prdcod,polnum,pprnum,prpseq,bassum,sumrkm,case when pprsta in ('PLISU','LAMD') "
//						+ "then 'Y' else 'N' end pplinf from inproposals a " + "where a.sbucod='" + sbu
//						+ "' and (a.ppdnic='" + nic + "' or a.sponic='" + nic + "') "
//						+ "and a.pprsta <> 'INAC' and (a.polnum is not null or "
//						+ "polnum <> '') and TIMESTAMPDIFF(YEAR,icpdat,sysdate()) <= 2",
//				new InPropPreviousPolsCompleteRowMapper());

		List<InPropPreviousPolModel> models = jdbcTemplate.query(
				"select prdcod,polnum,pprnum,prpseq,bassum,sumrkm,case "
						+ "when pprsta in ('PLISU','LAMD') then 'Y' else 'N' end pplinf from inproposals a  "
						+ "	where a.sbucod='" + 450
						+ "' and (a.polnum is not null or polnum <> '') and a.pprsta <> 'INAC' " + "and (a.ppdnic='"
						+ nic + "' or a.sponic='" + nic + "')  and TIMESTAMPDIFF(YEAR,icpdat,sysdate()) <= 2",
				new InPropPreviousPolsCompleteRowMapper());

		return models;
	}

	@Override
	public InProposalsModel getProposalFromPprnum(Integer pprnum) throws Exception {
		InProposalsModel models = jdbcTemplate.queryForObject("select * from inproposals "
				+ "where sbucod = '450' and pprnum = '" + pprnum + "' and pprsta <> 'INAC' order by prpseq desc limit 1",
				new InProposalsRowMapper());
//		
//		InProposalsModel models = jdbcTemplate
//				.queryForObject("select * from inproposals where sbucod = '450' and pprnum = '" + pprnum
//						+ "' order by prpseq desc limit 1", new InProposalsRowMapper());

		return models;
	}

	@Override
	public InProposalsModel getProposalFromPolnum(Integer polnum) throws Exception {
//		InProposalsModel models = jdbcTemplate.queryForObject(
//				"select * from inproposals where sbucod = '450' and polnum = '" + polnum + "' and pprsta <> 'INAC' ",
//				new InProposalsRowMapper());

		InProposalsModel models = jdbcTemplate.queryForObject(
				"select * from inproposals where sbucod = '450' and polnum = '" + polnum + "' and pprsta <> 'INAC'",
				new InProposalsRowMapper());

		return models;
	}

	@Override
	public InProposalsModel getProposalFromPprnumWorkFolw(Integer pprnum) throws Exception {
		
		
		InProposalsModel models = jdbcTemplate.queryForObject(
				"SELECT  " + 
				"    p.sbucod, " + 
				"    p.loccod, " + 
				"    p.ppdnam, " + 
				"    p.ppdini, " + 
				"    p.ppdad1, " + 
				"    p.ppdad2, " + 
				"    p.ppdad3, " + 
				"    p.ppddob, " + 
				"    p.ppdnag, " + 
				"    p.ppdnic, " + 
				"    p.ppdsex, " + 
				"    p.ppdcst, " + 
				"    p.ppdtel, " + 
				"    p.ppdeml, " + 
				"    p.ppdocu, " + 
				"    p.ppdndu, " + 
				"    p.pprnum, " + 
				"    p.toptrm, " + 
				"    p.paytrm, " + 
				"    p.paymth, " + 
				"    p.bassum, " + 
				"    p.premum, " + 
				"    p.highcm, " + 
				"    p.wighkg, " + 
				"    p.sponam, " + 
				"    p.spoocu, " + 
				"    p.ocupat, " + 
				"    p.mthinc, " + 
				"    p.prpdat, " + 
				"    p.brncod, " + 
				"    p.agmcod, " + 
				"    concat(u.USER_NAME , ' / ', p.advcod) AS advcod, " + 
				"    p.revlof, " + 
				"    p.numchl, " + 
				"    p.polnum, " + 
				"    p.doccod, " + 
				"    p.creaby, " + 
				"    p.creadt, " + 
				"    p.poltyp, " + 
				"    concat(pr.prdnam , ' / ', p.prdcod) AS prdcod, " + 
				"    p.quonum, " + 
				"    p.seqnum, " + 
				"    p.ntitle, " + 
				"    p.lockin, " + 
				"    p.sinprm, " + 
				"    st.stadsc AS pprsta, " + 
				"    p.prosta, " + 
				"    p.ppdmob, " + 
				"    p.ppdani, " + 
				"    p.stitle, " + 
				"    p.spoini, " + 
				"    p.spomob, " + 
				"    p.spoeml, " + 
				"    p.spoani, " + 
				"    p.spondu, " + 
				"    p.sponic, " + 
				"    p.cscode, " + 
				"    p.spodob, " + 
				"    p.sagnxt, " + 
				"    p.spotel, " + 
				"    p.totprm, " + 
				"    p.sumrks, " + 
				"    p.sumrkm, " + 
				"    p.prmqtt, " + 
				"    p.prmmtt, " + 
				"    p.prmmth, " + 
				"    p.prmhlt, " + 
				"    p.prmyet, " + 
				"    p.prmqat, " + 
				"    p.prmhlf, " + 
				"    p.prmyer, " + 
				"    p.trgprm, " + 
				"    p.quosta, " + 
				"    p.prdnam, " + 
				"    p.polfee, " + 
				"    p.comdat, " + 
				"    p.expdat, " + 
				"    p.prpseq, " + 
				"    p.sndapp, " + 
				"    p.curusr, " + 
				"    p.doclvl, " + 
				"    p.unddec, " + 
				"    p.icpdat, " + 
				"    p.exclut, " + 
				"    p.edttim, " + 
				"    p.shighc, " + 
				"    p.swighk, " + 
				"    p.poldat, " + 
				"    p.insnum, " + 
				"    p.txndat, " + 
				"    p.linyer, " + 
				"    p.linmon, " + 
				"    p.admfee, " + 
				"    p.taxamt, " + 
				"    p.otham1, " + 
				"    p.otham2, " + 
				"    p.otham3, " + 
				"    p.otham4, " + 
				"    p.intrat, " + 
				"    p.endnum, " + 
				"    p.grsprm, " + 
				"    p.smksta, " + 
				"    p.prflng, " + 
				"    p.grppol, " + 
				"    p.endmod, " + 
				"    p.oldprm, " + 
				"    p.enfdat, " + 
				"    p.lstchd, " + 
				"    p.chgtyp, " + 
				"    p.lsripd, " + 
				"    p.prncnt, " + 
				"    p.lpsdat, " + 
				"    p.canadf, " + 
				"    p.canmdc, " + 
				"    p.canoth, " + 
				"    p.netref, " + 
				"    p.payamt, " + 
				"    p.paynam, " + 
				"    p.mnagad, " + 
				"    p.spagad, " + 
				"    p.pspnvl, " + 
				"    p.pspntp, " + 
				"    p.pspndt, " + 
				"    p.invpos, " + 
				"    p.lifpos, " + 
				"    p.rlftrm, " + 
				"    p.jlfsex, " + 
				"    p.ban_no, " + 
				"    p.accnum, " + 
				"    p.newnic, " + 
				"    p.crmnum, " + 
				"    p.crmsts " + 
				"FROM " + 
				"    inproposals p " + 
				"        INNER JOIN " + 
				"    smtrxnstatus st ON st.statid = p.pprsta " + 
				"        INNER JOIN " + 
				"    inproducts pr ON p.prdcod = pr.prdcod " + 
				"		INNER JOIN " + 
				"    rms_users u ON u.USER_ID = p.advcod " + 
				"WHERE " + 
				"    p.sbucod = '450' AND p.pprnum = '" + pprnum + "' " + 
				"        AND p.pprsta <> 'INAC' " + 
				"ORDER BY p.prpseq DESC " + 
				"LIMIT 1",
				new InProposalsRowMapper());

		return models;
	}

}
