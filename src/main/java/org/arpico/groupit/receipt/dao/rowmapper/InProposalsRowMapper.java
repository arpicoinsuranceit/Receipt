package org.arpico.groupit.receipt.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.model.pk.InProposalsModelPK;
import org.springframework.jdbc.core.RowMapper;

public class InProposalsRowMapper implements RowMapper<InProposalsModel>{

	@Override
	public InProposalsModel mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		InProposalsModelPK inProposalsModelPK = new InProposalsModelPK();

		inProposalsModelPK.setLoccod(rs.getString("loccod"));
		inProposalsModelPK.setPrpseq(rs.getInt("prpseq"));
		inProposalsModelPK.setSbucod(rs.getString("sbucod"));
		inProposalsModelPK.setDoccod(rs.getString("doccod"));
		inProposalsModelPK.setPprnum(rs.getString("pprnum"));
		
		InProposalsModel inProposalsModel = new InProposalsModel();
		
		inProposalsModel.setInProposalsModelPK(inProposalsModelPK);
		inProposalsModel.setAccnum(rs.getString("accnum"));
		inProposalsModel.setAdmfee(rs.getDouble("admfee"));
		inProposalsModel.setAdvcod(rs.getString("advcod"));
		inProposalsModel.setAgmcod(rs.getString("agmcod"));
		inProposalsModel.setBan_no(rs.getString("ban_no"));
		inProposalsModel.setBassum(rs.getDouble("bassum"));
		inProposalsModel.setBrncod(rs.getString("brncod"));
		inProposalsModel.setCanadf(rs.getDouble("canadf"));
		inProposalsModel.setCanmdc(rs.getDouble("canmdc"));
		inProposalsModel.setCanoth(rs.getDouble("canoth"));
		inProposalsModel.setChgtyp(rs.getString("chgtyp"));
		inProposalsModel.setComdat(rs.getDate("comdat"));
		inProposalsModel.setCreaby(rs.getString("creaby"));
		inProposalsModel.setCreadt(rs.getDate("creadt"));
		inProposalsModel.setCrmnum(rs.getString("crmnum"));
		inProposalsModel.setCrmsts(rs.getString("crmsts"));
		inProposalsModel.setCscode(rs.getString("cscode"));
		inProposalsModel.setCurusr(rs.getString("curusr"));
		inProposalsModel.setDoclvl(rs.getString("doclvl"));
		inProposalsModel.setEdttim(rs.getDate("edttim"));
		inProposalsModel.setEndmod(rs.getString("endmod"));
		inProposalsModel.setEndnum(rs.getInt("endnum"));
		inProposalsModel.setEnfdat(rs.getDate("enfdat"));
		inProposalsModel.setExclut(rs.getString("exclut"));
		inProposalsModel.setExpdat(rs.getDate("expdat"));
		inProposalsModel.setGrppol(rs.getString("grppol"));
		inProposalsModel.setGrsprm(rs.getDouble("grsprm"));
		inProposalsModel.setHighcm(rs.getDouble("highcm"));
		inProposalsModel.setIcpdat(rs.getDate("icpdat"));
		inProposalsModel.setInsnum(rs.getInt("insnum"));
		inProposalsModel.setIntrat(rs.getDouble("intrat"));
		inProposalsModel.setInvpos(rs.getDouble("invpos"));
		inProposalsModel.setJlfsex(rs.getString("jlfsex"));
		inProposalsModel.setLifpos(rs.getDouble("lifpos"));
		inProposalsModel.setLinmon(rs.getInt("linmon"));
		inProposalsModel.setLinyer(rs.getInt("linyer"));
		inProposalsModel.setLockin(rs.getDate("lockin"));
		inProposalsModel.setLpsdat(rs.getDate("lpsdat"));
		inProposalsModel.setLsripd(rs.getDate("lsripd"));
		inProposalsModel.setLstchd(rs.getDate("lstchd"));
		inProposalsModel.setMnagad(rs.getString("mnagad"));
		inProposalsModel.setMthinc(rs.getDouble("mthinc"));
		inProposalsModel.setNetref(rs.getDouble("netref"));
		inProposalsModel.setNewnic(rs.getString("newnic"));
		inProposalsModel.setNtitle(rs.getString("ntitle"));
		inProposalsModel.setNumchl(rs.getInt("numchl"));
		inProposalsModel.setOcupat(rs.getString("ocupat"));
		inProposalsModel.setOldprm(rs.getDouble("oldprm"));
		inProposalsModel.setOtham1(rs.getDouble("otham1"));
		inProposalsModel.setOtham2(rs.getDouble("otham2"));
		inProposalsModel.setOtham3(rs.getDouble("otham3"));
		inProposalsModel.setOtham4(rs.getDouble("otham4"));
		inProposalsModel.setPayamt(rs.getDouble("payamt"));
		inProposalsModel.setPaymth(rs.getString("paymth"));
		inProposalsModel.setPaynam(rs.getString("paynam"));
		inProposalsModel.setPaytrm(rs.getString("paytrm"));
		inProposalsModel.setPoldat(rs.getDate("poldat"));
		inProposalsModel.setPolfee(rs.getDouble("polfee"));
		inProposalsModel.setPolnum(rs.getString("polnum"));
		inProposalsModel.setPoltyp(rs.getString("poltyp"));
		inProposalsModel.setPpdad1(rs.getString("ppdad1"));
		inProposalsModel.setPpdad2(rs.getString("ppdad2"));
		inProposalsModel.setPpdad3(rs.getString("ppdad3"));
		inProposalsModel.setPpdani(rs.getDouble("ppdani"));
		inProposalsModel.setPpdcst(rs.getString("ppdcst"));
		inProposalsModel.setPpddob(rs.getDate("ppddob"));
		inProposalsModel.setPpdeml(rs.getString("ppdeml"));
		inProposalsModel.setPpdini(rs.getString("ppdini"));
		inProposalsModel.setPpdmob(rs.getString("ppdmob"));
		inProposalsModel.setPpdnag(rs.getInt("ppdnag"));
		inProposalsModel.setPpdnam(rs.getString("ppdnam"));
		inProposalsModel.setPpdndu(rs.getString("ppdndu"));
		inProposalsModel.setPpdnic(rs.getString("ppdnic"));
		inProposalsModel.setPpdocu(rs.getString("ppdocu"));
		inProposalsModel.setPpdsex(rs.getString("ppdsex"));
		inProposalsModel.setPpdtel(rs.getString("ppdtel"));
		inProposalsModel.setPprsta(rs.getString("pprsta"));
		inProposalsModel.setPrdcod(rs.getString("prdcod"));
		inProposalsModel.setPrdnam(rs.getString("prdnam"));
		inProposalsModel.setPremum(rs.getDouble("premum"));
		inProposalsModel.setPrflng(rs.getString("prflng"));
		inProposalsModel.setPrmhlf(rs.getDouble("prmhlf"));
		inProposalsModel.setPrmhlt(rs.getDouble("prmhlt"));
		inProposalsModel.setPrmmth(rs.getDouble("prmmth"));
		inProposalsModel.setPrmmtt(rs.getDouble("prmmtt"));
		inProposalsModel.setPrmqat(rs.getDouble("prmqat"));
		inProposalsModel.setPrmqtt(rs.getDouble("prmqtt"));
		inProposalsModel.setPrmyer(rs.getDouble("prmyer"));
		inProposalsModel.setPrmyet(rs.getDouble("prmyet"));
		inProposalsModel.setPrncnt(rs.getInt("prncnt"));
		inProposalsModel.setProsta(rs.getString("prosta"));
		inProposalsModel.setPrpdat(rs.getDate("prpdat"));
		inProposalsModel.setPspndt(rs.getDate("pspndt"));
		inProposalsModel.setPspntp(rs.getString("pspntp"));
		inProposalsModel.setPspnvl(rs.getInt("pspnvl"));
		inProposalsModel.setQuonum(rs.getInt("quonum"));
		inProposalsModel.setQuosta(rs.getString("quosta"));
		inProposalsModel.setRevlof(rs.getString("revlof"));
		inProposalsModel.setRlftrm(rs.getString("rlftrm"));
		inProposalsModel.setSagnxt(rs.getInt("sagnxt"));
		inProposalsModel.setSeqnum(rs.getInt("sbucod"));
		inProposalsModel.setSeqnum(rs.getInt("seqnum"));
		inProposalsModel.setShighc(rs.getDouble("shighc"));
		inProposalsModel.setSinprm(rs.getString("sinprm"));
		inProposalsModel.setSmksta(rs.getString("smksta"));
		inProposalsModel.setSndapp(rs.getString("sndapp"));
		inProposalsModel.setSpagad(rs.getString("spagad"));
		inProposalsModel.setSpoani(rs.getDouble("spoani"));
		inProposalsModel.setSpodob(rs.getDate("spodob"));
		inProposalsModel.setSpoeml(rs.getString("spoeml"));
		inProposalsModel.setSpoini(rs.getString("spoini"));
		inProposalsModel.setSpomob(rs.getString("spomob"));
		inProposalsModel.setSponam(rs.getString("sponam"));
		inProposalsModel.setSpondu(rs.getString("spondu"));
		inProposalsModel.setSponic(rs.getString("sponic"));
		inProposalsModel.setSpoocu(rs.getString("spoocu"));
		inProposalsModel.setSpotel(rs.getString("spotel"));
		inProposalsModel.setStitle(rs.getString("stitle"));
		inProposalsModel.setSumrkm(rs.getDouble("sumrkm"));
		inProposalsModel.setSumrks(rs.getDouble("sumrks"));
		inProposalsModel.setSwighk(rs.getDouble("swighk"));
		inProposalsModel.setTaxamt(rs.getDouble("taxamt"));
		inProposalsModel.setToptrm(rs.getInt("toptrm"));
		inProposalsModel.setTotprm(rs.getDouble("totprm"));
		inProposalsModel.setTrgprm(rs.getDouble("trgprm"));
		inProposalsModel.setTxndat(rs.getDate("txndat"));
		inProposalsModel.setUnddec(rs.getString("unddec"));
		inProposalsModel.setWighkg(rs.getDouble("wighkg"));
		
		return inProposalsModel;
	}

}
