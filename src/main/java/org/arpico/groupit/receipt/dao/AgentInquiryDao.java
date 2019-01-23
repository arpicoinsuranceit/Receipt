package org.arpico.groupit.receipt.dao;

import java.util.List;

import org.arpico.groupit.receipt.model.DesignationModel;
import org.arpico.groupit.receipt.model.EducationModel;
import org.arpico.groupit.receipt.model.HierarchyTransferModel;
import org.arpico.groupit.receipt.model.SettlementDetailsModel;
import org.arpico.groupit.receipt.model.TargetsModel;

public interface AgentInquiryDao {
	
	public List<SettlementDetailsModel> getSettlementDetails(String sbucode,String agncode)throws Exception;
	
	public List<TargetsModel> getTargetDetails(String sbucode,String agncode)throws Exception;
	
	public List<HierarchyTransferModel> getHierarchyTransfer(String sbucode,String agncode)throws Exception;
	
	public List<DesignationModel> getDesignationDetails(String sbucode,String agncode)throws Exception;
	
	public List<EducationModel> getEducationDetails(String sbucode,String agncode)throws Exception;

}
