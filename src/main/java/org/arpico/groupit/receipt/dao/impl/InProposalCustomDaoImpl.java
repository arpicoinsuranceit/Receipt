package org.arpico.groupit.receipt.dao.impl;

import java.util.List;
import org.arpico.groupit.receipt.dao.InProposalCustomDao;
import org.arpico.groupit.receipt.dao.rowmapper.InPolicyBasicRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.InPropPreviousPolsCompleteRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.InPropPreviousPolsRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.InProposalBasicRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.InProposalsRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.PendingReqRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.ProposalL3RowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.ProposalNoSeqNoRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.ShortPremiumRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.WorkFlowPolicyGridRowMapper;
import org.arpico.groupit.receipt.dto.ProposalL3Dto;
import org.arpico.groupit.receipt.model.InPropPreviousPolModel;
import org.arpico.groupit.receipt.model.InProposalBasicsModel;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.ProposalNoSeqNoModel;
import org.arpico.groupit.receipt.model.ShortPremiumModel;
import org.arpico.groupit.receipt.model.WorkFlowPolicyGridModel;
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
						+ "%' and pprsta not in ('PLISU', 'PLAPS', 'INAC', 'EXPI', 'MATU', 'PCAN', 'PDECL', 'PDECS', 'PLDEC', 'PRCAN', 'PREDL', 'PSRND')",
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
				"select advcod, ppdnam, prdcod, pprnum, ntitle, prpseq, totprm, ppdmob, pprsta, polnum from inproposals "
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

		//System.out.println(models.size());

		if (!models.isEmpty()) {
			//System.out.println("if");
			return models.get(0);
		}

		return null;
	}

	@Override
	public List<ProposalL3Dto> checkL3(Integer propId) throws Exception {
		List<ProposalL3Dto> models = jdbcTemplate.query(
				"SELECT x.pprnum, (x.totprm + x.polfee) totprm, x.payment, y.reqcnt,x.spiamt FROM "
						+ "	(SELECT  a.sbucod, a.pprnum, a.prpseq, a.totprm, a.polfee, "
						+ "		(SELECT SUM(totprm) FROM intransactions b WHERE a.sbucod = b.sbucod AND a.pprnum = b.pprnum "
						+ "         AND if(b.paymod<>'CQ',1,if(b.paymod='CQ' and b.chqrel='Y',1,0)) = 1) payment, s.spiamt "
						+ "			FROM inproposals a "
						+ "		INNER JOIN inshort_premium_act_product s ON a.sbucod = s.sbucod AND a.prdcod = s.prdcod WHERE "
						+ "			a.sbucod = '450' AND a.pprsta IN ('L3') AND a.pprnum = '" + propId + "' "
						+ "     AND s.status = 'ACT') x " + "        INNER JOIN "
						+ "			(SELECT sbucod, pprnum, prpseq, medcod, "
						+ "				(SELECT COUNT(*) FROM inpropmedicalreq a WHERE a.sbucod = b.sbucod AND a.loccod = b.loccod "
						+ "                        AND a.prpseq = b.prpseq AND a.pprnum = b.pprnum AND a.medcod LIKE 'AD%' AND a.tessta = 'N' "
						+ "                        AND addnot NOT LIKE 'Premium Short%') reqcnt "
						+ "			FROM inpropmedicalreq b WHERE sbucod = '450' AND pprnum = '" + propId
						+ "' AND medcod LIKE 'AD%' AND addnot LIKE 'Premium Short%' " + "	AND tessta = 'N') "
						+ "y ON x.sbucod = y.sbucod AND x.pprnum = y.pprnum AND x.prpseq = y.prpseq "
						+ "WHERE ((x.totprm + x.polfee) - x.spiamt) <= x.payment AND reqcnt < 1 GROUP BY x.pprnum",
				new ProposalL3RowMapper());

		return models;
	}

	@Override
	public List<ProposalNoSeqNoModel> getPolicyNoSeqNoModelList(String val) throws Exception {
		List<ProposalNoSeqNoModel> list = jdbcTemplate.query("select polnum as pprnum, prpseq from inproposals "
				+ "where sbucod = '450' and  polnum like '" + val + "%' and  pprsta in ('plisu', 'plaps', 'lamd', 'plapp', 'plnrv')",
				new ProposalNoSeqNoRowMapper());
//		
//		List<ProposalNoSeqNoModel> list = jdbcTemplate.query(
//				"select polnum as pprnum, prpseq from inproposals where  pprsta in ('plisu', 'plaps')  and sbucod = '450' and polnum like '"
//						+ val + "%'",
//				new ProposalNoSeqNoRowMapper());
		return list;
	}
	
	@Override
	public List<ProposalNoSeqNoModel> getPolicyNoSeqNoModelListLoanRcpt(String val) throws Exception {
		List<ProposalNoSeqNoModel> list = jdbcTemplate.query("select polnum as pprnum, prpseq from inproposals "
				+ "where sbucod = '450' and  polnum like '" + val + "%' and  pprsta <> 'INAC'",
				new ProposalNoSeqNoRowMapper());

		return list;
	}

	@Override
	public InProposalBasicsModel geInPolicyBasics(int polNo, int seqNo) throws Exception {
//		List<InProposalBasicsModel> basicsModels = jdbcTemplate.query(
//				"select advcod, ppdnam, prdcod, polnum, ntitle, " + "prpseq, totprm from inproposals where polnum = '"
//						+ polNo + "' and prpseq = '" + seqNo + "' and sbucod = '450'",
//				new InPolicyBasicRowMapper());

		List<InProposalBasicsModel> basicsModels = jdbcTemplate.query(
				"select advcod, ppdnam, prdcod, pprnum, ntitle, prpseq, totprm, ppdmob, pprsta, polnum from inproposals "
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

		//System.out.println(models.size());

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
		InProposalsModel models = jdbcTemplate
				.queryForObject("select * from inproposals " + "where sbucod = '450' and pprnum = '" + pprnum
						+ "' and pprsta <> 'INAC' order by prpseq desc limit 1", new InProposalsRowMapper());

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
		
//		System.out.println("SELECT  " + "    p.sbucod, " + "    p.loccod, "
//				+ "    p.ppdnam, " + "    p.ppdini, " + "    p.ppdad1, " + "    p.ppdad2, " + "    p.ppdad3, "
//				+ "    p.ppddob, " + "    p.ppdnag, " + "    p.ppdnic, " + "    p.ppdsex, " + "    p.ppdcst, "
//				+ "    p.ppdtel, " + "    p.ppdeml, " + "    o.ocunam as ppdocu, " + "    p.ppdndu, " + "    p.pprnum, "
//				+ "    p.toptrm, " + "    p.paytrm, " + "    p.paymth, " + "    p.bassum, " + "    p.premum, "
//				+ "    p.highcm, " + "    p.wighkg, " + "    p.sponam, "
//				+ "    (select ocunam from inpropoccupation oc where oc.sbucod = '450' and oc.ocucod = p.spoocu ) AS spoocu, "
//				+ "    p.ocupat, " + "    p.mthinc, " + "    p.prpdat, " + "    p.brncod, " + "    p.agmcod, "
//				+ "    CONCAT(u.USER_NAME, ' / ', p.advcod) AS advcod, " + "    p.revlof, " + "    p.numchl, "
//				+ "    p.polnum, " + "    p.doccod, " + "    p.creaby, " + "    p.creadt, " + "    p.poltyp, "
//				+ "    CONCAT(pr.prdnam, ' / ', p.prdcod) AS prdcod, " + "    p.quonum, " + "    p.seqnum, "
//				+ "    p.ntitle, " + "    p.lockin, " + "    p.sinprm, " + "    st.stadsc AS pprsta, "
//				+ "    p.prosta, " + "    p.ppdmob, " + "    p.ppdani, " + "    p.stitle, " + "    p.spoini, "
//				+ "    p.spomob, " + "    p.spoeml, " + "    p.spoani, " + "    p.spondu, " + "    p.sponic, "
//				+ "    p.cscode, " + "    p.spodob, " + "    p.sagnxt, " + "    p.spotel, " + "    p.totprm, "
//				+ "    p.sumrks, " + "    p.sumrkm, " + "    p.prmqtt, " + "    p.prmmtt, " + "    p.prmmth, "
//				+ "    p.prmhlt, " + "    p.prmyet, " + "    p.prmqat, " + "    p.prmhlf, " + "    p.prmyer, "
//				+ "    p.trgprm, " + "    p.quosta, " + "    p.prdnam, " + "    p.polfee, " + "    p.comdat, "
//				+ "    p.expdat, " + "    p.prpseq, " + "    p.sndapp, " + "    p.curusr, " + "    p.doclvl, "
//				+ "    p.unddec, " + "    p.icpdat, " + "    p.exclut, " + "    p.edttim, " + "    p.shighc, "
//				+ "    p.swighk, " + "    p.poldat, " + "    p.insnum, " + "    p.txndat, " + "    p.linyer, "
//				+ "    p.linmon, " + "    p.admfee, " + "    p.taxamt, " + "    p.otham1, " + "    p.otham2, "
//				+ "    p.otham3, " + "    p.otham4, " + "    p.intrat, " + "    p.endnum, " + "    p.grsprm, "
//				+ "    p.smksta, " + "    p.prflng, " + "    p.grppol, " + "    p.endmod, " + "    p.oldprm, "
//				+ "    p.enfdat, " + "    p.lstchd, " + "    p.chgtyp, " + "    p.lsripd, " + "    p.prncnt, "
//				+ "    p.lpsdat, " + "    p.canadf, " + "    p.canmdc, " + "    p.canoth, " + "    p.netref, "
//				+ "    p.payamt, " + "    p.paynam, " + "    p.mnagad, " + "    p.spagad, " + "    p.pspnvl, "
//				+ "    p.pspntp, " + "    p.pspndt, " + "    p.invpos, " + "    p.lifpos, " + "    p.rlftrm, "
//				+ "    p.jlfsex, " + "    p.ban_no, " + "    p.accnum, " + "    p.newnic, " + "    p.crmnum, "
//				+ "    p.crmsts " + "FROM " + "    inproposals p " + "        INNER JOIN "
//				+ "    smtrxnstatus st ON st.sbucod = p.sbucod AND st.statid = p.pprsta " + "        INNER JOIN "
//				+ "    inproducts pr ON pr.sbucod = p.sbucod AND p.prdcod = pr.prdcod " + "        INNER JOIN "
//				+ "    rms_users u ON u.SBU_CODE = p.sbucod AND u.USER_ID = p.advcod " + "		INNER JOIN "
//				+ "	inpropoccupation o ON o.sbucod = p.sbucod AND o.ocucod = p.ppdocu " + "WHERE "
//				+ "    p.sbucod = '450' AND p.pprnum = '" + pprnum + "' " + "        AND p.pprsta <> 'INAC' "
//				+ "ORDER BY p.prpseq DESC " + "LIMIT 1");

		InProposalsModel models = jdbcTemplate.queryForObject("SELECT  " + "    p.sbucod, " + "    p.loccod, "
				+ "    p.ppdnam, " + "    p.ppdini, " + "    p.ppdad1, " + "    p.ppdad2, " + "    p.ppdad3, "
				+ "    p.ppddob, " + "    p.ppdnag, " + "    p.ppdnic, " + "    p.ppdsex, " + "    p.ppdcst, "
				+ "    p.ppdtel, " + "    p.ppdeml, " + "    o.ocunam as ppdocu, " + "    p.ppdndu, " + "    p.pprnum, "
				+ "    p.toptrm, " + "    p.paytrm, " + "    p.paymth, " + "    p.bassum, " + "    p.premum, "
				+ "    p.highcm, " + "    p.wighkg, " + "    p.sponam, "
				+ "    (select ocunam from inpropoccupation oc where oc.sbucod = '450' and oc.ocucod = p.spoocu ) AS spoocu, "
				+ "    p.ocupat, " + "    p.mthinc, " + "    p.prpdat, " + "    p.brncod, " + "    p.agmcod, "
				+ "    CONCAT(u.prnnam, ' / ', p.advcod) AS advcod, " + "    p.revlof, " + "    p.numchl, "
				+ "    p.polnum, " + "    p.doccod, " + "    p.creaby, " + "    p.creadt, " + "    p.poltyp, "
				+ "    CONCAT(pr.prdnam, ' / ', p.prdcod) AS prdcod, " + "    p.quonum, " + "    p.seqnum, "
				+ "    p.ntitle, " + "    p.lockin, " + "    p.sinprm, " + "    st.stadsc AS pprsta, "
				+ "    p.prosta, " + "    p.ppdmob, " + "    p.ppdani, " + "    p.stitle, " + "    p.spoini, "
				+ "    p.spomob, " + "    p.spoeml, " + "    p.spoani, " + "    p.spondu, " + "    p.sponic, "
				+ "    p.cscode, " + "    p.spodob, " + "    p.sagnxt, " + "    p.spotel, " + "    p.totprm, "
				+ "    p.sumrks, " + "    p.sumrkm, " + "    p.prmqtt, " + "    p.prmmtt, " + "    p.prmmth, "
				+ "    p.prmhlt, " + "    p.prmyet, " + "    p.prmqat, " + "    p.prmhlf, " + "    p.prmyer, "
				+ "    p.trgprm, " + "    p.quosta, " + "    p.prdnam, " + "    p.polfee, " + "    p.comdat, "
				+ "    p.expdat, " + "    p.prpseq, " + "    p.sndapp, " + "    p.curusr, " + "    p.doclvl, "
				+ "    p.unddec, " + "    p.icpdat, " + "    p.exclut, " + "    p.edttim, " + "    p.shighc, "
				+ "    p.swighk, " + "    p.poldat, " + "    p.insnum, " + "    p.txndat, " + "    p.linyer, "
				+ "    p.linmon, " + "    p.admfee, " + "    p.taxamt, " + "    p.otham1, " + "    p.otham2, "
				+ "    p.otham3, " + "    p.otham4, " + "    p.intrat, " + "    p.endnum, " + "    p.grsprm, "
				+ "    p.smksta, " + "    p.prflng, " + "    p.grppol, " + "    p.endmod, " + "    p.oldprm, "
				+ "    p.enfdat, " + "    p.lstchd, " + "    p.chgtyp, " + "    p.lsripd, " + "    p.prncnt, "
				+ "    p.lpsdat, " + "    p.canadf, " + "    p.canmdc, " + "    p.canoth, " + "    p.netref, "
				+ "    p.payamt, " + "    p.paynam, " + "    p.mnagad, " + "    p.spagad, " + "    p.pspnvl, "
				+ "    p.pspntp, " + "    p.pspndt, " + "    p.invpos, " + "    p.lifpos, " + "    p.rlftrm, "
				+ "    p.jlfsex, " + "    p.ban_no, " + "    p.accnum, " + "    p.newnic, " + "    p.crmnum, "
				+ "    p.crmsts " + "FROM " + "    inproposals p " + "        INNER JOIN "
				+ "    smtrxnstatus st ON st.sbucod = p.sbucod AND st.statid = p.pprsta " + "        INNER JOIN "
				+ "    inproducts pr ON pr.sbucod = p.sbucod AND p.prdcod = pr.prdcod " + "        INNER JOIN "
				+ "    inagentmast u ON u.sbucod = p.sbucod AND u.agncod = p.advcod " + "		INNER JOIN "
				+ "	inpropoccupation o ON o.sbucod = p.sbucod AND o.ocucod = p.ppdocu " + "WHERE "
				+ "    p.sbucod = '450' AND p.pprnum = '" + pprnum + "' " + "        AND p.pprsta <> 'INAC' "
				+ "ORDER BY p.prpseq DESC " + "LIMIT 1", new InProposalsRowMapper());
		
		

		return models;
	}

	@Override
	public List<InProposalsModel> getPoliciesToWorkFlowHO(String type) throws Exception {

		List<InProposalsModel> models = jdbcTemplate.query(
				"select * from inproposals where sbucod = '450' and pprsta = '" + type + "'",
				new InProposalsRowMapper());

		return models;
	}

	@Override
	public List<InProposalsModel> getPoliciesToWorkFlow(String brancheList, String type) throws Exception {
		List<InProposalsModel> models = jdbcTemplate
				.query("select * from inproposals where sbucod = '450' and loccod in (" + brancheList
						+ ") and pprsta = '" + type + "'", new InProposalsRowMapper());

		return models;
	}

	@Override
	public List<WorkFlowPolicyGridModel> getWorkFlowPolicyGrid(String status, String locCodes) throws Exception {
		List<WorkFlowPolicyGridModel> models = jdbcTemplate.query(
				"select p.pprnum, concat(p.prdcod,'/',p.polnum) policy,b.duedat,p.totprm,p.ppdini,concat(p.advcod,'-',a.prnnam) agent,b.brncod "
						+ "from inproposals p inner join inbillingtransactions b on p.sbucod=b.sbucod and p.pprnum=b.pprnum and p.pprsta='"
						+ status + "' " + "inner join inagentmast a on a.sbucod=p.sbucod and a.agncod=p.advcod "
						+ "where b.sbucod='450' and p.loccod in (" + locCodes
						+ ") and b.duedat between current_date() and date_add(current_date(),interval 5 day) group by b.pprnum,b.txnyer,b.txnmth having sum(b.amount) > 0 order by b.duedat",
				new WorkFlowPolicyGridRowMapper());
		return models;
	}

	@Override
	public List<WorkFlowPolicyGridModel> getWorkFlowPolicyGridHo(String status) throws Exception {
		List<WorkFlowPolicyGridModel> models = jdbcTemplate.query(
				"select p.pprnum, concat(p.prdcod,'/',p.polnum) policy,b.duedat,p.totprm,p.ppdini,concat(p.advcod,'-',a.prnnam) agent,b.brncod "
						+ "from inproposals p inner join inbillingtransactions b on p.sbucod=b.sbucod and p.pprnum=b.pprnum and p.pprsta='"
						+ status + "' " + "inner join inagentmast a on a.sbucod=p.sbucod and a.agncod=p.advcod "
						+ "where b.sbucod='450' and b.duedat between current_date() and date_add(current_date(),interval 5 day) group by b.pprnum,b.txnyer,b.txnmth having sum(b.amount) > 0 order by b.duedat",
				new WorkFlowPolicyGridRowMapper());
		return models;
	}

	@Override
	public List<InProposalsModel> searchProposal(String sql) throws Exception {

		//System.out.println("select * from inproposals where sbucod = '450' and " + sql);

		List<InProposalsModel> models = jdbcTemplate.query("select * from inproposals where sbucod = '450' and " + sql,
				new InProposalsRowMapper());

		return models;
	}

	@Override
	public List<ShortPremiumModel> getShortPremium(String sql, Integer page, Integer offset) throws Exception {
		
//		System.out.println("select p.quonum, p.pprnum, p.prpseq, concat(p.advcod,' - ',a.prnnam) agent, p.loccod, m.addnot from inproposals p  "
//						+ "	   inner join inpropmedicalreq m on p.sbucod=m.sbucod and p.pprnum=m.pprnum and p.prpseq=m.prpseq and p.pprsta='L3' "
//						+ "    inner join inagentmast a on a.sbucod=p.sbucod and a.agncod=p.advcod "
//						+ "      where m.sbucod='450' " + sql 
//						+ "      and m.medcod like 'AD%' and tessta = 'N' and addnot like 'Premium Short%' limit "+ (page) + ", " + offset);
//		
		List<ShortPremiumModel> models = jdbcTemplate.query(
				"select p.quonum, p.pprnum, p.prpseq, concat(p.advcod,' - ',a.prnnam) agent, p.loccod, m.addnot from inproposals p  "
						+ "	   inner join inpropmedicalreq m on p.sbucod=m.sbucod and p.pprnum=m.pprnum and p.prpseq=m.prpseq and p.pprsta='L3' "
						+ "    inner join inagentmast a on a.sbucod=p.sbucod and a.agncod=p.advcod "
						+ "      where m.sbucod='450' " + sql 
						+ "      and m.medcod like 'AD%' and tessta = 'N' and addnot like 'Premium Short%' limit "+ (page) + ", " + offset,
				new ShortPremiumRowMapper());

		return models;
	}

	@Override
	public Integer getShortPremiumCount(String sql) throws Exception {
		Integer count = jdbcTemplate.queryForObject("select count(m.addnot) from inproposals p  "
				+ "	inner join inpropmedicalreq m on p.sbucod=m.sbucod and p.pprnum=m.pprnum and p.prpseq=m.prpseq and p.pprsta='L3' "
				+ "where m.sbucod='450' " + sql
				+ " and m.medcod like 'AD%' and tessta = 'N' and addnot like 'Premium Short%'",
				Integer.class);

		return count;
	}
	
	@Override
	public List<ShortPremiumModel> getPendingReq(String sql, Integer page, Integer offset) throws Exception {
		List<ShortPremiumModel> models = jdbcTemplate.query("SELECT  " + 
				"    p.quonum, " + 
				"    p.pprnum, " + 
				"    p.prpseq, " + 
				"    CONCAT(p.advcod, ' - ', a.prnnam) agent, " + 
				"    p.loccod, " + 
				"    COUNT(m.addnot) as reqcnt " + 
				"FROM " + 
				"    inproposals p " + 
				"        INNER JOIN " + 
				"    inpropmedicalreq m ON p.sbucod = m.sbucod " + 
				"        AND p.pprnum = m.pprnum " + 
				"        AND p.prpseq = m.prpseq " + 
				"        AND p.pprsta IN ('L0' , 'L1', 'L2', 'L3') " + 
				"        INNER JOIN " + 
				"    inagentmast a ON a.sbucod = p.sbucod " + 
				"        AND a.agncod = p.advcod " + 
				"WHERE " + 
				"    m.sbucod = '450' "+ sql +" AND tessta = 'N' group by p.pprnum limit " + page + "," + offset ,
				new PendingReqRowMapper());

		return models;
	}

	@Override
	public Integer getPendingReqCount(String sql) throws Exception {
		Integer count = jdbcTemplate.queryForObject("SELECT    " + 
				"    SUM(x.rowcon)   " + 
				"FROM   " + 
				"    (SELECT    " + 
				"        1 rowcon   " + 
				"    FROM   " + 
				"        inproposals p   " + 
				"    INNER JOIN inpropmedicalreq m ON p.sbucod = m.sbucod   " + 
				"        AND p.pprnum = m.pprnum   " + 
				"        AND p.prpseq = m.prpseq   " + 
				"        AND p.pprsta IN ('L0' , 'L1', 'L2', 'L3')   " + 
				"    WHERE   " + 
				"        m.sbucod = '450' "+ sql +" AND tessta = 'N'   " + 
				"    GROUP BY p.pprnum) x",
				Integer.class);

		return count;
	}

	@Override
	public List<WorkFlowPolicyGridModel> getWorkFlowPolicylaps(String type, Integer date1, Integer date2, String type2) {
		
		String query = "SELECT  " + 
				"    n.policy, " + 
				"    MAX(n.duedat) duedat, " + 
				"    (n.totprm * 2) totprm, " + 
				"    n.ppdini, " + 
				"    n.agent, " + 
				"    n.brncod, " + 
				"    n.pprnum, " + 
				"    (SELECT  " + 
				"            GROUP_CONCAT(ab.ridcod) " + 
				"        FROM " + 
				"            inpropaddbenefit ab " + 
				"        WHERE " + 
				"            n.sbucod = ab.sbucod " + 
				"                AND n.pprnum = ab.pprnum " + 
				"                AND n.prpseq = ab.prpseq " + 
				"                AND ab.ridcod IN ('HRB' , 'HRBC', " + 
				"                'HRBS', " + 
				"                'SUHRB', " + 
				"                'SUHRBS', " + 
				"                'SUHRBC', " + 
				"                'HCBI', " + 
				"                'HCBIC', " + 
				"                'HCBIS', " + 
				"                'HCBF', " + 
				"                'HCBFS', " + 
				"                'HCBFC', " + 
				"                'SHCBI', " + 
				"                'SHCBIC', " + 
				"                'SHCBIS', " + 
				"                'HB', " + 
				"                'HBS', " + 
				"                'HBC') " + 
				"                AND ab.sumasu > 0.0) health " + 
				"FROM " + 
				"    (SELECT  " + 
				"        x.policy, " + 
				"            x.sbucod, " + 
				"            x.pprnum, " + 
				"            x.polnum, " + 
				"            x.pprsta, " + 
				"            MAX(x.duedat) duedat, " + 
				"            x.agent, " + 
				"            x.ppdini, " + 
				"            x.brncod, " + 
				"            x.prpseq, " + 
				"            x.totprm, " + 
				"            IF(DATEDIFF(SYSDATE(), DATE_ADD(MAX(x.duedat), INTERVAL x.addmth MONTH)) > "+ date1 +" " + 
				"                AND DATEDIFF(SYSDATE(), DATE_ADD(MAX(x.duedat), INTERVAL x.addmth MONTH)) <= "+date2+", '"+type+"', 'PLISU') updsta, " + 
				"            x.advcod " + 
				"    FROM " + 
				"        (SELECT  " + 
				"        b.sbucod, " + 
				"            b.pprnum, " + 
				"            b.polnum, " + 
				"            b.pprsta, " + 
				"            b.prpseq, " + 
				"            b.advcod, " + 
				"            CONCAT(b.advcod, '-', am.prnnam) agent, " + 
				"            b.ppdini, " + 
				"            a.brncod, " + 
				"            b.totprm, " + 
				"            CONCAT(a.txnyer,'-',if(length(a.txnmth)=1,CONCAT('0',a.txnmth),a.txnmth),'-',if(day(b.comdat) > 28,28,if(length(day(b.comdat))=1,CONCAT('0',day(b.comdat)),day(b.comdat))))  duedat, " + 
				"            CASE " + 
				"                WHEN b.paytrm = 12 THEN 1 " + 
				"                WHEN b.paytrm = 4 THEN 3 " + 
				"                WHEN b.paytrm = 2 THEN 6 " + 
				"                WHEN b.paytrm = 1 THEN 12 " + 
				"            END addmth, " + 
				"            CONCAT(b.prdcod, '/', b.polnum) policy " + 
				"    FROM " + 
				"        inbillingtransactions a " + 
				"    INNER JOIN inproposals b ON a.sbucod = b.sbucod " + 
				"        AND a.pprnum = b.pprnum " + 
				"        AND b.pprsta = '"+type2+"' " + 
				"        AND (b.sinprm IS NULL OR b.sinprm = '') " + 
				"    INNER JOIN inagentmast am ON am.sbucod = b.sbucod " + 
				"        AND am.agncod = b.advcod " + 
				"    WHERE " + 
				"        a.sbucod = '450' " + 
				"            AND DATE_FORMAT(a.creadt, '%Y-%m-%d') <= CURRENT_DATE() " + 
				"            AND a.amount <> 0 " + 
				"    GROUP BY a.sbucod , a.pprnum , a.polnum , a.txnyer , a.txnmth " + 
				"    HAVING (SUM(a.amount) + (MAX(a.otham1) + MAX(a.otham2) + MAX(a.otham3) + MAX(a.otham4))) = 0) x " + 
				"    GROUP BY x.sbucod , x.polnum) n " + 
				"WHERE " + 
				"    CONVERT( n.updsta USING UTF8) != n.pprsta;";
		
		//System.out.println(query);
		List<WorkFlowPolicyGridModel> models = jdbcTemplate.query(query,
				new WorkFlowPolicyGridRowMapper());
		return models;
	}

	@Override
	public List<WorkFlowPolicyGridModel> getWorkFlowPolicylaps(String type, String paraForIn, Integer date1,
			Integer date2, String type2) {
		
		//System.out.println("Branch");
		
		String query = "SELECT  " + 
				"    n.policy, " + 
				"    MAX(n.duedat) duedat, " + 
				"    (n.totprm * 2) totprm, " + 
				"    n.ppdini, " + 
				"    n.agent, " + 
				"    n.brncod, " + 
				"    n.pprnum, " + 
				"    (SELECT  " + 
				"            GROUP_CONCAT(ab.ridcod) " + 
				"        FROM " + 
				"            inpropaddbenefit ab " + 
				"        WHERE " + 
				"            n.sbucod = ab.sbucod " + 
				"                AND n.pprnum = ab.pprnum " + 
				"                AND n.prpseq = ab.prpseq " + 
				"                AND ab.ridcod IN ('HRB' , 'HRBC', " + 
				"                'HRBS', " + 
				"                'SUHRB', " + 
				"                'SUHRBS', " + 
				"                'SUHRBC', " + 
				"                'HCBI', " + 
				"                'HCBIC', " + 
				"                'HCBIS', " + 
				"                'HCBF', " + 
				"                'HCBFS', " + 
				"                'HCBFC', " + 
				"                'SHCBI', " + 
				"                'SHCBIC', " + 
				"                'SHCBIS', " + 
				"                'HB', " + 
				"                'HBS', " + 
				"                'HBC') " + 
				"                AND ab.sumasu > 0.0) health " + 
				"FROM " + 
				"    (SELECT  " + 
				"        x.policy, " + 
				"            x.sbucod, " + 
				"            x.pprnum, " + 
				"            x.polnum, " + 
				"            x.pprsta, " + 
				"            MAX(x.duedat) duedat, " + 
				"            x.agent, " + 
				"            x.ppdini, " + 
				"            x.brncod, " + 
				"            x.prpseq, " + 
				"            x.totprm, " + 
				"            IF(DATEDIFF(SYSDATE(), DATE_ADD(MAX(x.duedat), INTERVAL x.addmth MONTH)) > "+ date1 +" " + 
				"                AND DATEDIFF(SYSDATE(), DATE_ADD(MAX(x.duedat), INTERVAL x.addmth MONTH)) <= "+date2+", '"+type+"', 'PLISU') updsta, " + 
				"            x.advcod " + 
				"    FROM " + 
				"        (SELECT  " + 
				"        b.sbucod, " + 
				"            b.pprnum, " + 
				"            b.polnum, " + 
				"            b.pprsta, " + 
				"            b.prpseq, " + 
				"            b.advcod, " + 
				"            CONCAT(b.advcod, '-', am.prnnam) agent, " + 
				"            b.ppdini, " + 
				"            a.brncod, " + 
				"            b.totprm, " + 
				"            CONCAT(a.txnyer,'-',if(length(a.txnmth)=1,CONCAT('0',a.txnmth),a.txnmth),'-',if(day(b.comdat) > 28,28,if(length(day(b.comdat))=1,CONCAT('0',day(b.comdat)),day(b.comdat))))  duedat, " + 
				"            CASE " + 
				"                WHEN b.paytrm = 12 THEN 1 " + 
				"                WHEN b.paytrm = 4 THEN 3 " + 
				"                WHEN b.paytrm = 2 THEN 6 " + 
				"                WHEN b.paytrm = 1 THEN 12 " + 
				"            END addmth, " + 
				"            CONCAT(b.prdcod, '/', b.polnum) policy " + 
				"    FROM " + 
				"        inbillingtransactions a " + 
				"    INNER JOIN inproposals b ON a.sbucod = b.sbucod " + 
				"        AND a.pprnum = b.pprnum " + 
				"        AND b.pprsta = '"+type2+"' " + 
				"        AND (b.sinprm IS NULL OR b.sinprm = '') " + 
				"    INNER JOIN inagentmast am ON am.sbucod = b.sbucod " + 
				"        AND am.agncod = b.advcod " + 
				"    WHERE " + 
				"        a.sbucod = '450' " + 
				"            AND b.loccod IN ("+paraForIn+") " + 
				"            AND DATE_FORMAT(a.creadt, '%Y-%m-%d') <= CURRENT_DATE() " + 
				"            AND a.amount <> 0 " + 
				"    GROUP BY a.sbucod , a.pprnum , a.polnum , a.txnyer , a.txnmth " + 
				"    HAVING (SUM(a.amount) + (MAX(a.otham1) + MAX(a.otham2) + MAX(a.otham3) + MAX(a.otham4))) = 0) x " + 
				"    GROUP BY x.sbucod , x.polnum) n " + 
				"WHERE " + 
				"    CONVERT( n.updsta USING UTF8) != n.pprsta;";
		
		//System.out.println(query);
		
		List<WorkFlowPolicyGridModel> models = jdbcTemplate.query(query,
				new WorkFlowPolicyGridRowMapper());
		return models;
	}

	@Override
	public List<ProposalNoSeqNoModel> getPolicyNoSeqNoModel(String polNum) throws Exception {
		List<ProposalNoSeqNoModel> list = jdbcTemplate.query(
				"select polnum as pprnum, prpseq from inproposals " + "where sbucod = '450' and polnum = '" + polNum
						+ "' and pprsta in ('plisu', 'plaps', 'lamd', 'plapp', 'plnrv')",
				new ProposalNoSeqNoRowMapper());
		return list;
	}


}
