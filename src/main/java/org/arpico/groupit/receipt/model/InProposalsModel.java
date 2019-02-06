package org.arpico.groupit.receipt.model;

import java.util.Date;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.arpico.groupit.receipt.model.pk.InProposalsModelPK;
import org.arpico.groupit.receipt.util.AppConstant;

@Entity
@Table(name = "inproposals")
public class InProposalsModel {
	private InProposalsModelPK inProposalsModelPK;
	private String ppdnam;
	private String ppdini;
	private String ppdad1;
	private String ppdad2;
	private String ppdad3;
	private Date ppddob;
	private Integer ppdnag;
	private String ppdnic;
	private String ppdsex;
	private String ppdcst;
	private String ppdtel;
	private String ppdeml;
	private String ppdocu;
	private String ppdndu;
	private Integer toptrm;
	private String paytrm;
	private String paymth; 
	private Double bassum = AppConstant.ZERO_FOR_DECIMAL;
	private Double premum = AppConstant.ZERO_FOR_DECIMAL;
	private Double highcm;
	private Double wighkg;
	private String sponam;
	private String spoocu;
	private String ocupat;
	private Double mthinc;
	private Date prpdat;
	private String brncod;
	private String agmcod;
	private String advcod;
	private String revlof = "N";
	private Integer numchl;
	private String polnum;
	private String creaby;
	private Date creadt;
	private String poltyp;
	private String prdcod;
	private Integer quonum;
	private Integer seqnum;
	private String ntitle;
	private Date lockin = new Date();
	private String sinprm;
	private String pprsta;
	private String prosta; 
	private String ppdmob;
	private Double ppdani;
	private String stitle;
	private String spoini;
	private String spomob;
	private String spoeml;
	private Double spoani;
	private String spondu;
	private String sponic;
	private String cscode;
	private Date spodob;
	private Integer sagnxt;
	private String spotel;
	private Double totprm; 
	private Double sumrks = AppConstant.ZERO_FOR_DECIMAL;
	private Double sumrkm = AppConstant.ZERO_FOR_DECIMAL;
	private Double prmqtt = AppConstant.ZERO_FOR_DECIMAL;
	private Double prmmtt = AppConstant.ZERO_FOR_DECIMAL;
	private Double prmmth = AppConstant.ZERO_FOR_DECIMAL;
	private Double prmhlt = AppConstant.ZERO_FOR_DECIMAL;
	private Double prmyet = AppConstant.ZERO_FOR_DECIMAL;
	private Double prmqat = AppConstant.ZERO_FOR_DECIMAL;
	private Double prmhlf = AppConstant.ZERO_FOR_DECIMAL;
	private Double prmyer = AppConstant.ZERO_FOR_DECIMAL;
	private Double trgprm = AppConstant.ZERO_FOR_DECIMAL;
	private String quosta;
	private String prdnam;
	private Double polfee = AppConstant.ZERO_FOR_DECIMAL;
	private Date comdat;
	private Date expdat;
	private String sndapp;
	private String curusr;
	private String doclvl;
	private String unddec;
	private Date icpdat;
	private String exclut;
	private Date edttim;
	private Double shighc;
	private Double swighk;
	private Date poldat;
	private Integer insnum = AppConstant.ZERO;
	private Date txndat;
	private Integer linyer;
	private Integer linmon;
	private Double admfee = AppConstant.ZERO_FOR_DECIMAL;
	private Double taxamt = AppConstant.ZERO_FOR_DECIMAL;
	private Double otham1 = AppConstant.ZERO_FOR_DECIMAL;
	private Double otham2 = AppConstant.ZERO_FOR_DECIMAL;
	private Double otham3 = AppConstant.ZERO_FOR_DECIMAL;
	private Double otham4 = AppConstant.ZERO_FOR_DECIMAL;
	private Double intrat = AppConstant.ZERO_TWO_DECIMAL;
	private Integer endnum = AppConstant.ZERO;
	private Double grsprm = AppConstant.ZERO_FOR_DECIMAL;
	private String smksta = "N";
	private String prflng;
	private String grppol = "N";
	private String endmod;
	private Double oldprm;
	private Date enfdat;
	private Date lstchd;
	private String chgtyp;
	private Date lsripd;
	private Integer prncnt = AppConstant.ZERO;
	private Date lpsdat;
	private Double canadf;
	private Double canmdc;
	private Double canoth;
	private Double netref;
	private Double payamt;
	private String paynam;
	private String mnagad;
	private String spagad;
	private Integer pspnvl;
	private String pspntp;
	private Date pspndt;
	private Double invpos = AppConstant.ZERO_FOR_DECIMAL;
	private Double lifpos = AppConstant.ZERO_FOR_DECIMAL;
	private String rlftrm = "0";
	private String jlfsex;
	private String ban_no;
	private String accnum;
	private String newnic;
	private String crmnum;
	private String crmsts = "N";

	@EmbeddedId
	public InProposalsModelPK getInProposalsModelPK() {
		return inProposalsModelPK;
	}

	public void setInProposalsModelPK(InProposalsModelPK inProposalsModelPK) {
		this.inProposalsModelPK = inProposalsModelPK;
	}

	public String getPpdnam() {
		return ppdnam;
	}

	public void setPpdnam(String ppdnam) {
		this.ppdnam = ppdnam;
	}

	public String getPpdini() {
		return ppdini;
	}

	public void setPpdini(String ppdini) {
		this.ppdini = ppdini;
	}

	public String getPpdad1() {
		return ppdad1;
	}

	public void setPpdad1(String ppdad1) {
		this.ppdad1 = ppdad1;
	}

	public String getPpdad2() {
		return ppdad2;
	}

	public void setPpdad2(String ppdad2) {
		this.ppdad2 = ppdad2;
	}

	public String getPpdad3() {
		return ppdad3;
	}

	public void setPpdad3(String ppdad3) {
		this.ppdad3 = ppdad3;
	}

	public Date getPpddob() {
		return ppddob;
	}

	public void setPpddob(Date ppddob) {
		this.ppddob = ppddob;
	}

	public Integer getPpdnag() {
		return ppdnag;
	}

	public void setPpdnag(Integer ppdnag) {
		this.ppdnag = ppdnag;
	}

	public String getPpdnic() {
		return ppdnic;
	}

	public void setPpdnic(String ppdnic) {
		this.ppdnic = ppdnic;
	}

	public String getPpdsex() {
		return ppdsex;
	}

	public void setPpdsex(String ppdsex) {
		this.ppdsex = ppdsex;
	}

	public String getPpdcst() {
		return ppdcst;
	}

	public void setPpdcst(String ppdcst) {
		this.ppdcst = ppdcst;
	}

	public String getPpdtel() {
		return ppdtel;
	}

	public void setPpdtel(String ppdtel) {
		this.ppdtel = ppdtel;
	}

	public String getPpdeml() {
		return ppdeml;
	}

	public void setPpdeml(String ppdeml) {
		this.ppdeml = ppdeml;
	}

	public String getPpdocu() {
		return ppdocu;
	}

	public void setPpdocu(String ppdocu) {
		this.ppdocu = ppdocu;
	}

	public String getPpdndu() {
		return ppdndu;
	}

	public void setPpdndu(String ppdndu) {
		this.ppdndu = ppdndu;
	}

	public Integer getToptrm() {
		return toptrm;
	}

	public void setToptrm(Integer toptrm) {
		this.toptrm = toptrm;
	}

	public String getPaytrm() {
		return paytrm;
	}

	public void setPaytrm(String paytrm) {
		this.paytrm = paytrm;
	}

	public String getPaymth() {
		return paymth;
	}

	public void setPaymth(String paymth) {
		this.paymth = paymth;
	}

	public Double getBassum() {
		return bassum;
	}

	public void setBassum(Double bassum) {
		this.bassum = bassum;
	}

	public Double getPremum() {
		return premum;
	}

	public void setPremum(Double premum) {
		this.premum = premum;
	}

	public Double getHighcm() {
		return highcm;
	}

	public void setHighcm(Double highcm) {
		this.highcm = highcm;
	}

	public Double getWighkg() {
		return wighkg;
	}

	public void setWighkg(Double wighkg) {
		this.wighkg = wighkg;
	}

	public String getSponam() {
		return sponam;
	}

	public void setSponam(String sponam) {
		this.sponam = sponam;
	}

	public String getSpoocu() {
		return spoocu;
	}

	public void setSpoocu(String spoocu) {
		this.spoocu = spoocu;
	}

	public String getOcupat() {
		return ocupat;
	}

	public void setOcupat(String ocupat) {
		this.ocupat = ocupat;
	}

	public Double getMthinc() {
		return mthinc;
	}

	public void setMthinc(Double mthinc) {
		this.mthinc = mthinc;
	}

	public Date getPrpdat() {
		return prpdat;
	}

	public void setPrpdat(Date prpdat) {
		this.prpdat = prpdat;
	}

	public String getBrncod() {
		return brncod;
	}

	public void setBrncod(String brncod) {
		this.brncod = brncod;
	}

	public String getAgmcod() {
		return agmcod;
	}

	public void setAgmcod(String agmcod) {
		this.agmcod = agmcod;
	}

	public String getAdvcod() {
		return advcod;
	}

	public void setAdvcod(String advcod) {
		this.advcod = advcod;
	}

	public String getRevlof() {
		return revlof;
	}

	public void setRevlof(String revlof) {
		this.revlof = revlof;
	}

	public Integer getNumchl() {
		return numchl;
	}

	public void setNumchl(Integer numchl) {
		this.numchl = numchl;
	}

	public String getPolnum() {
		return polnum;
	}

	public void setPolnum(String polnum) {
		this.polnum = polnum;
	}

	public String getCreaby() {
		return creaby;
	}

	public void setCreaby(String creaby) {
		this.creaby = creaby;
	}

	public Date getCreadt() {
		return creadt;
	}

	public void setCreadt(Date creadt) {
		this.creadt = creadt;
	}

	public String getPoltyp() {
		return poltyp;
	}

	public void setPoltyp(String poltyp) {
		this.poltyp = poltyp;
	}

	public String getPrdcod() {
		return prdcod;
	}

	public void setPrdcod(String prdcod) {
		this.prdcod = prdcod;
	}

	public Integer getQuonum() {
		return quonum;
	}

	public void setQuonum(Integer quonum) {
		this.quonum = quonum;
	}

	public Integer getSeqnum() {
		return seqnum;
	}

	public void setSeqnum(Integer seqnum) {
		this.seqnum = seqnum;
	}

	public String getNtitle() {
		return ntitle;
	}

	public void setNtitle(String ntitle) {
		this.ntitle = ntitle;
	}

	public Date getLockin() {
		return lockin;
	}

	public void setLockin(Date lockin) {
		this.lockin = lockin;
	}

	public String getSinprm() {
		return sinprm;
	}

	public void setSinprm(String sinprm) {
		this.sinprm = sinprm;
	}

	public String getPprsta() {
		return pprsta;
	}

	public void setPprsta(String pprsta) {
		this.pprsta = pprsta;
	}

	public String getProsta() {
		return prosta;
	}

	public void setProsta(String prosta) {
		this.prosta = prosta;
	}

	public String getPpdmob() {
		return ppdmob;
	}

	public void setPpdmob(String ppdmob) {
		this.ppdmob = ppdmob;
	}

	public Double getPpdani() {
		return ppdani;
	}

	public void setPpdani(Double ppdani) {
		this.ppdani = ppdani;
	}

	public String getStitle() {
		return stitle;
	}

	public void setStitle(String stitle) {
		this.stitle = stitle;
	}

	public String getSpoini() {
		return spoini;
	}

	public void setSpoini(String spoini) {
		this.spoini = spoini;
	}

	public String getSpomob() {
		return spomob;
	}

	public void setSpomob(String spomob) {
		this.spomob = spomob;
	}

	public String getSpoeml() {
		return spoeml;
	}

	public void setSpoeml(String spoeml) {
		this.spoeml = spoeml;
	}

	public Double getSpoani() {
		return spoani;
	}

	public void setSpoani(Double spoani) {
		this.spoani = spoani;
	}

	public String getSpondu() {
		return spondu;
	}

	public void setSpondu(String spondu) {
		this.spondu = spondu;
	}

	public String getSponic() {
		return sponic;
	}

	public void setSponic(String sponic) {
		this.sponic = sponic;
	}

	public String getCscode() {
		return cscode;
	}

	public void setCscode(String cscode) {
		this.cscode = cscode;
	}

	public Date getSpodob() {
		return spodob;
	}

	public void setSpodob(Date spodob) {
		this.spodob = spodob;
	}

	public Integer getSagnxt() {
		return sagnxt;
	}

	public void setSagnxt(Integer sagnxt) {
		this.sagnxt = sagnxt;
	}

	public String getSpotel() {
		return spotel;
	}

	public void setSpotel(String spotel) {
		this.spotel = spotel;
	}

	public Double getTotprm() {
		return totprm;
	}

	public void setTotprm(Double totprm) {
		this.totprm = totprm;
	}

	public Double getSumrks() {
		return sumrks;
	}

	public void setSumrks(Double sumrks) {
		this.sumrks = sumrks;
	}

	public Double getSumrkm() {
		return sumrkm;
	}

	public void setSumrkm(Double sumrkm) {
		this.sumrkm = sumrkm;
	}

	public Double getPrmqtt() {
		return prmqtt;
	}

	public void setPrmqtt(Double prmqtt) {
		this.prmqtt = prmqtt;
	}

	public Double getPrmmtt() {
		return prmmtt;
	}

	public void setPrmmtt(Double prmmtt) {
		this.prmmtt = prmmtt;
	}

	public Double getPrmmth() {
		return prmmth;
	}

	public void setPrmmth(Double prmmth) {
		this.prmmth = prmmth;
	}

	public Double getPrmhlt() {
		return prmhlt;
	}

	public void setPrmhlt(Double prmhlt) {
		this.prmhlt = prmhlt;
	}

	public Double getPrmyet() {
		return prmyet;
	}

	public void setPrmyet(Double prmyet) {
		this.prmyet = prmyet;
	}

	public Double getPrmqat() {
		return prmqat;
	}

	public void setPrmqat(Double prmqat) {
		this.prmqat = prmqat;
	}

	public Double getPrmhlf() {
		return prmhlf;
	}

	public void setPrmhlf(Double prmhlf) {
		this.prmhlf = prmhlf;
	}

	public Double getPrmyer() {
		return prmyer;
	}

	public void setPrmyer(Double prmyer) {
		this.prmyer = prmyer;
	}

	public Double getTrgprm() {
		return trgprm;
	}

	public void setTrgprm(Double trgprm) {
		this.trgprm = trgprm;
	}

	public String getQuosta() {
		return quosta;
	}

	public void setQuosta(String quosta) {
		this.quosta = quosta;
	}

	public String getPrdnam() {
		return prdnam;
	}

	public void setPrdnam(String prdnam) {
		this.prdnam = prdnam;
	}

	public Double getPolfee() {
		return polfee;
	}

	public void setPolfee(Double polfee) {
		this.polfee = polfee;
	}

	public Date getComdat() {
		return comdat;
	}

	public void setComdat(Date comdat) {
		this.comdat = comdat;
	}

	public Date getExpdat() {
		return expdat;
	}

	public void setExpdat(Date expdat) {
		this.expdat = expdat;
	}

	public String getSndapp() {
		return sndapp;
	}

	public void setSndapp(String sndapp) {
		this.sndapp = sndapp;
	}

	public String getCurusr() {
		return curusr;
	}

	public void setCurusr(String curusr) {
		this.curusr = curusr;
	}

	public String getDoclvl() {
		return doclvl;
	}

	public void setDoclvl(String doclvl) {
		this.doclvl = doclvl;
	}

	public String getUnddec() {
		return unddec;
	}

	public void setUnddec(String unddec) {
		this.unddec = unddec;
	}

	public Date getIcpdat() {
		return icpdat;
	}

	public void setIcpdat(Date icpdat) {
		this.icpdat = icpdat;
	}

	public String getExclut() {
		return exclut;
	}

	public void setExclut(String exclut) {
		this.exclut = exclut;
	}

	public Date getEdttim() {
		return edttim;
	}

	public void setEdttim(Date edttim) {
		this.edttim = edttim;
	}

	public Double getShighc() {
		return shighc;
	}

	public void setShighc(Double shighc) {
		this.shighc = shighc;
	}

	public Double getSwighk() {
		return swighk;
	}

	public void setSwighk(Double swighk) {
		this.swighk = swighk;
	}

	public Date getPoldat() {
		return poldat;
	}

	public void setPoldat(Date poldat) {
		this.poldat = poldat;
	}

	public Integer getInsnum() {
		return insnum;
	}

	public void setInsnum(Integer insnum) {
		this.insnum = insnum;
	}

	public Date getTxndat() {
		return txndat;
	}

	public void setTxndat(Date txndat) {
		this.txndat = txndat;
	}

	public Integer getLinyer() {
		return linyer;
	}

	public void setLinyer(Integer linyer) {
		this.linyer = linyer;
	}

	public Integer getLinmon() {
		return linmon;
	}

	public void setLinmon(Integer linmon) {
		this.linmon = linmon;
	}

	public Double getAdmfee() {
		return admfee;
	}

	public void setAdmfee(Double admfee) {
		this.admfee = admfee;
	}

	public Double getTaxamt() {
		return taxamt;
	}

	public void setTaxamt(Double taxamt) {
		this.taxamt = taxamt;
	}

	public Double getOtham1() {
		return otham1;
	}

	public void setOtham1(Double otham1) {
		this.otham1 = otham1;
	}

	public Double getOtham2() {
		return otham2;
	}

	public void setOtham2(Double otham2) {
		this.otham2 = otham2;
	}

	public Double getOtham3() {
		return otham3;
	}

	public void setOtham3(Double otham3) {
		this.otham3 = otham3;
	}

	public Double getOtham4() {
		return otham4;
	}

	public void setOtham4(Double otham4) {
		this.otham4 = otham4;
	}

	public Double getIntrat() {
		return intrat;
	}

	public void setIntrat(Double intrat) {
		this.intrat = intrat;
	}

	public Integer getEndnum() {
		return endnum;
	}

	public void setEndnum(Integer endnum) {
		this.endnum = endnum;
	}

	public Double getGrsprm() {
		return grsprm;
	}

	public void setGrsprm(Double grsprm) {
		this.grsprm = grsprm;
	}

	public String getSmksta() {
		return smksta;
	}

	public void setSmksta(String smksta) {
		this.smksta = smksta;
	}

	public String getPrflng() {
		return prflng;
	}

	public void setPrflng(String prflng) {
		this.prflng = prflng;
	}

	public String getGrppol() {
		return grppol;
	}

	public void setGrppol(String grppol) {
		this.grppol = grppol;
	}

	public String getEndmod() {
		return endmod;
	}

	public void setEndmod(String endmod) {
		this.endmod = endmod;
	}

	public Double getOldprm() {
		return oldprm;
	}

	public void setOldprm(Double oldprm) {
		this.oldprm = oldprm;
	}

	public Date getEnfdat() {
		return enfdat;
	}

	public void setEnfdat(Date enfdat) {
		this.enfdat = enfdat;
	}

	public Date getLstchd() {
		return lstchd;
	}

	public void setLstchd(Date lstchd) {
		this.lstchd = lstchd;
	}

	public String getChgtyp() {
		return chgtyp;
	}

	public void setChgtyp(String chgtyp) {
		this.chgtyp = chgtyp;
	}

	public Date getLsripd() {
		return lsripd;
	}

	public void setLsripd(Date lsripd) {
		this.lsripd = lsripd;
	}

	public Integer getPrncnt() {
		return prncnt;
	}

	public void setPrncnt(Integer prncnt) {
		this.prncnt = prncnt;
	}

	public Date getLpsdat() {
		return lpsdat;
	}

	public void setLpsdat(Date lpsdat) {
		this.lpsdat = lpsdat;
	}

	public Double getCanadf() {
		return canadf;
	}

	public void setCanadf(Double canadf) {
		this.canadf = canadf;
	}

	public Double getCanmdc() {
		return canmdc;
	}

	public void setCanmdc(Double canmdc) {
		this.canmdc = canmdc;
	}

	public Double getCanoth() {
		return canoth;
	}

	public void setCanoth(Double canoth) {
		this.canoth = canoth;
	}

	public Double getNetref() {
		return netref;
	}

	public void setNetref(Double netref) {
		this.netref = netref;
	}

	public Double getPayamt() {
		return payamt;
	}

	public void setPayamt(Double payamt) {
		this.payamt = payamt;
	}

	public String getPaynam() {
		return paynam;
	}

	public void setPaynam(String paynam) {
		this.paynam = paynam;
	}

	public String getMnagad() {
		return mnagad;
	}

	public void setMnagad(String mnagad) {
		this.mnagad = mnagad;
	}

	public String getSpagad() {
		return spagad;
	}

	public void setSpagad(String spagad) {
		this.spagad = spagad;
	}

	public Integer getPspnvl() {
		return pspnvl;
	}

	public void setPspnvl(Integer pspnvl) {
		this.pspnvl = pspnvl;
	}

	public String getPspntp() {
		return pspntp;
	}

	public void setPspntp(String pspntp) {
		this.pspntp = pspntp;
	}

	public Date getPspndt() {
		return pspndt;
	}

	public void setPspndt(Date pspndt) {
		this.pspndt = pspndt;
	}

	public Double getInvpos() {
		return invpos;
	}

	public void setInvpos(Double invpos) {
		this.invpos = invpos;
	}

	public Double getLifpos() {
		return lifpos;
	}

	public void setLifpos(Double lifpos) {
		this.lifpos = lifpos;
	}

	public String getRlftrm() {
		return rlftrm;
	}

	public void setRlftrm(String rlftrm) {
		this.rlftrm = rlftrm;
	}

	public String getJlfsex() {
		return jlfsex;
	}

	public void setJlfsex(String jlfsex) {
		this.jlfsex = jlfsex;
	}

	public String getBan_no() {
		return ban_no;
	}

	public void setBan_no(String ban_no) {
		this.ban_no = ban_no;
	}

	public String getAccnum() {
		return accnum;
	}

	public void setAccnum(String accnum) {
		this.accnum = accnum;
	}

	public String getNewnic() {
		return newnic;
	}

	public void setNewnic(String newnic) {
		this.newnic = newnic;
	}

	public String getCrmnum() {
		return crmnum;
	}

	public void setCrmnum(String crmnum) {
		this.crmnum = crmnum;
	}

	public String getCrmsts() {
		return crmsts;
	}

	public void setCrmsts(String crmsts) {
		this.crmsts = crmsts;
	}

	@Override
	public String toString() {
		return "InProposalsModel [ppdnam=" + ppdnam + ", ppdini=" + ppdini + ", ppdad1=" + ppdad1 + ", ppdad2=" + ppdad2
				+ ", ppdad3=" + ppdad3 + ", ppddob=" + ppddob + ", ppdnag=" + ppdnag + ", ppdnic=" + ppdnic
				+ ", ppdsex=" + ppdsex + ", ppdcst=" + ppdcst + ", ppdtel=" + ppdtel + ", ppdeml=" + ppdeml
				+ ", ppdocu=" + ppdocu + ", ppdndu=" + ppdndu + ", toptrm=" + toptrm + ", paytrm=" + paytrm
				+ ", paymth=" + paymth + ", bassum=" + bassum + ", premum=" + premum + ", highcm=" + highcm
				+ ", wighkg=" + wighkg + ", sponam=" + sponam + ", spoocu=" + spoocu + ", ocupat=" + ocupat
				+ ", mthinc=" + mthinc + ", prpdat=" + prpdat + ", brncod=" + brncod + ", agmcod=" + agmcod
				+ ", advcod=" + advcod + ", revlof=" + revlof + ", numchl=" + numchl + ", polnum=" + polnum
				+ ", creaby=" + creaby + ", creadt=" + creadt + ", poltyp=" + poltyp + ", prdcod=" + prdcod
				+ ", quonum=" + quonum + ", seqnum=" + seqnum + ", ntitle=" + ntitle + ", lockin=" + lockin
				+ ", sinprm=" + sinprm + ", pprsta=" + pprsta + ", prosta=" + prosta + ", ppdmob=" + ppdmob
				+ ", ppdani=" + ppdani + ", stitle=" + stitle + ", spoini=" + spoini + ", spomob=" + spomob
				+ ", spoeml=" + spoeml + ", spoani=" + spoani + ", spondu=" + spondu + ", sponic=" + sponic
				+ ", cscode=" + cscode + ", spodob=" + spodob + ", sagnxt=" + sagnxt + ", spotel=" + spotel
				+ ", totprm=" + totprm + ", sumrks=" + sumrks + ", sumrkm=" + sumrkm + ", prmqtt=" + prmqtt
				+ ", prmmtt=" + prmmtt + ", prmmth=" + prmmth + ", prmhlt=" + prmhlt + ", prmyet=" + prmyet
				+ ", prmqat=" + prmqat + ", prmhlf=" + prmhlf + ", prmyer=" + prmyer + ", trgprm=" + trgprm
				+ ", quosta=" + quosta + ", prdnam=" + prdnam + ", polfee=" + polfee + ", comdat=" + comdat
				+ ", expdat=" + expdat + ", sndapp=" + sndapp + ", curusr=" + curusr + ", doclvl=" + doclvl
				+ ", unddec=" + unddec + ", icpdat=" + icpdat + ", exclut=" + exclut + ", edttim=" + edttim
				+ ", shighc=" + shighc + ", swighk=" + swighk + ", poldat=" + poldat + ", insnum=" + insnum
				+ ", txndat=" + txndat + ", linyer=" + linyer + ", linmon=" + linmon + ", admfee=" + admfee
				+ ", taxamt=" + taxamt + ", otham1=" + otham1 + ", otham2=" + otham2 + ", otham3=" + otham3
				+ ", otham4=" + otham4 + ", intrat=" + intrat + ", endnum=" + endnum + ", grsprm=" + grsprm
				+ ", smksta=" + smksta + ", prflng=" + prflng + ", grppol=" + grppol + ", endmod=" + endmod
				+ ", oldprm=" + oldprm + ", enfdat=" + enfdat + ", lstchd=" + lstchd + ", chgtyp=" + chgtyp
				+ ", lsripd=" + lsripd + ", prncnt=" + prncnt + ", lpsdat=" + lpsdat + ", canadf=" + canadf
				+ ", canmdc=" + canmdc + ", canoth=" + canoth + ", netref=" + netref + ", payamt=" + payamt
				+ ", paynam=" + paynam + ", mnagad=" + mnagad + ", spagad=" + spagad + ", pspnvl=" + pspnvl
				+ ", pspntp=" + pspntp + ", pspndt=" + pspndt + ", invpos=" + invpos + ", lifpos=" + lifpos
				+ ", rlftrm=" + rlftrm + ", jlfsex=" + jlfsex + ", ban_no=" + ban_no + ", accnum=" + accnum
				+ ", newnic=" + newnic + ", crmnum=" + crmnum + ", crmsts=" + crmsts + "]";
	}

}
