package org.arpico.groupit.receipt.dao.impl;

import java.util.List;

import org.arpico.groupit.receipt.dao.AgentInquiryDao;
import org.arpico.groupit.receipt.dao.rowmapper.DesignationRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.EducationRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.HierarchyTransferRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.SettlementDetailsRowMapper;
import org.arpico.groupit.receipt.dao.rowmapper.TargetsRowMapper;
import org.arpico.groupit.receipt.model.DesignationModel;
import org.arpico.groupit.receipt.model.EducationModel;
import org.arpico.groupit.receipt.model.HierarchyTransferModel;
import org.arpico.groupit.receipt.model.SettlementDetailsModel;
import org.arpico.groupit.receipt.model.TargetsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AgentInquiryDaoImpl implements AgentInquiryDao{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<SettlementDetailsModel> getSettlementDetails(String sbucode, String agncode) throws Exception {
		
		List<SettlementDetailsModel> settlementDetailsModels=jdbcTemplate.
				query("select a.acctyp,b.bandes,c.branam,a.accnum,a.frmdat,a.todate from  smbank b inner join smbankbranch c on c.sbucod=b.sbucod and c.bancod=b.bancod " + 
				"right outer join inagentsettlement a " + 
				"on a.sbucod=c.sbucod and a.bancod=c.bancod and a.bracod=c.bracod " + 
				"where a.sbucod='"+sbucode+"' and a.agncod='"+agncode+"' order by todate desc", new SettlementDetailsRowMapper());
		
		return settlementDetailsModels;
	}

	@Override
	public List<TargetsModel> getTargetDetails(String sbucode, String agncode) throws Exception {
	
		List<TargetsModel> targetsModels=jdbcTemplate.query("select trgmon,trgamt,trgach,trgorc,trgtcfa,trgaca from inagentachievements "
				+ "where sbucod='"+sbucode+"' and agncod='"+agncode+"' order by yeronl,mononl" , new TargetsRowMapper());
		
		return targetsModels;
	}

	@Override
	public List<HierarchyTransferModel> getHierarchyTransfer(String sbucode, String agncode) throws Exception {
		
		List<HierarchyTransferModel> hierarchyTransferModels=jdbcTemplate.query("select a.unlcod,b.shrtnm,b.agncls,a.frmdat,a.todate from inagentbistrans a inner join inagentmast b on a.unlcod=b.agncod where a.sbucod='"+sbucode+"' and a.agncod='"+agncode+"' order by todate desc", 
				new HierarchyTransferRowMapper());
		
		return hierarchyTransferModels;
	}

	@Override
	public List<DesignationModel> getDesignationDetails(String sbucode, String agncode) throws Exception {
		
		List<DesignationModel> designationModels=jdbcTemplate.query("select a.agncod,a.subdcd,b.subdes,a.frmdat,a.todate from inagentdesignation a inner join insubdesignation b on a.sbucod=b.sbucod and a.subdcd=b.subdcd " + 
				"where a.sbucod='"+sbucode+"' and a.agncod='"+agncode+"' order by todate desc", new DesignationRowMapper());
		
		return designationModels;
	}

	@Override
	public List<EducationModel> getEducationDetails(String sbucode, String agncode) throws Exception {
		
		List<EducationModel>  educationModels=jdbcTemplate.query("select qulfic,qlfyer,qgrade from inagentqualification where sbucod='"+sbucode+"' and agncod='"+agncode+"'", new EducationRowMapper());
		
		return educationModels;
	}
	
	

}
