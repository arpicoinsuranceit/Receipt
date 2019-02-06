package org.arpico.groupit.receipt.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.arpico.groupit.receipt.client.UserManagementClient;
import org.arpico.groupit.receipt.dao.AgentDao;
import org.arpico.groupit.receipt.dao.AgentInquiryDao;
import org.arpico.groupit.receipt.dto.AgentMasterDto;
import org.arpico.groupit.receipt.dto.AgnInqAgnListDto;
import org.arpico.groupit.receipt.dto.DesignationDto;
import org.arpico.groupit.receipt.dto.EducationDto;
import org.arpico.groupit.receipt.dto.HierarchyTransferDto;
import org.arpico.groupit.receipt.dto.SettlementDetailsDto;
import org.arpico.groupit.receipt.dto.TargetsDto;
import org.arpico.groupit.receipt.model.AgentMasterDetailsModel;
import org.arpico.groupit.receipt.model.AgnInqAgnListModel;
import org.arpico.groupit.receipt.model.DesignationModel;
import org.arpico.groupit.receipt.model.EducationModel;
import org.arpico.groupit.receipt.model.HierarchyTransferModel;
import org.arpico.groupit.receipt.model.SettlementDetailsModel;
import org.arpico.groupit.receipt.model.TargetsModel;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.AgentInquiryService;
import org.arpico.groupit.receipt.util.AppConstant;
import org.arpico.groupit.receipt.util.DaoParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgentInquiryServiceImpl implements AgentInquiryService {
	
	@Autowired
	private UserManagementClient userManagementClient;
	
	@Autowired
	private AgentDao agentDao;
	
	@Autowired
	private DaoParameters daoParameters; 
	
	@Autowired
	private AgentInquiryDao agentInquiryDao;

	@Override
	public List<AgnInqAgnListDto> getAgentListByBranch(String token, Integer page, Integer offset) throws Exception {
		
		String agentCode = new JwtDecoder().generate(token);

		String agentBranch = userManagementClient.getBranch(agentCode);
		
		System.out.println(agentBranch);

		String[] tempArr = agentBranch.split(",");
		
		List<String> agentBranchs = new ArrayList<>(Arrays.asList(tempArr));
		
		String locCodes = daoParameters.getParaForIn(agentBranchs);
		
		List<AgnInqAgnListDto> agnInqAgnListDtos = new ArrayList<>();

		List<AgnInqAgnListModel> agnInqAgnListModels = agentDao.getAgnInqList(locCodes, page, offset);

		agnInqAgnListModels.forEach(e -> {
			agnInqAgnListDtos.add(getAgnInqListDto(e));
		});

		return agnInqAgnListDtos;
	}

	public AgnInqAgnListDto getAgnInqListDto(AgnInqAgnListModel e) {
		AgnInqAgnListDto dto = new AgnInqAgnListDto();

		dto.setAgncod(e.getAgncod());
		dto.setAgndob(e.getAgndob());
		dto.setAgnnam(e.getAgnnam());
		dto.setAgnnic(e.getAgnnic());
		dto.setAgnrdt(e.getAgnrdt());
		dto.setAgnsta(e.getAgnsta());
		dto.setSliirg(e.getSliirg());
		dto.setSubdcd(e.getSubdcd());
		dto.setSupvid(e.getSupvid());

		return dto;
	}

	@Override
	public Integer getAgentListByBranchCountLength(String token) throws Exception {
		
		
		String agentCode = new JwtDecoder().generate(token);

		String agentBranch = userManagementClient.getBranch(agentCode);
		
		System.out.println(agentBranch);

		String[] tempArr = agentBranch.split(",");
		
		List<String> agentBranchs = new ArrayList<>(Arrays.asList(tempArr));
		
		String locCodes = daoParameters.getParaForIn(agentBranchs);
		
		Integer count = agentDao.getAgnInqListCount(locCodes);
		
		return count;
	}

	@Override
	public AgentMasterDto getAgentFullDetails(String agentCode) throws Exception {
		
		AgentMasterDetailsModel detailsModel=agentDao.getAgentMasterdetails(Integer.valueOf(agentCode));
		
		AgentMasterDto agentMasterDto=new AgentMasterDto();
		
		agentMasterDto.setAddress1(detailsModel.getAgnad1());
		agentMasterDto.setAddress2(detailsModel.getAgnad2());
		agentMasterDto.setAgnNat(detailsModel.getAgnnature());
		agentMasterDto.setAppoinmentDate(detailsModel.getAppdat());
		agentMasterDto.setBranch(detailsModel.getLoccod());
		agentMasterDto.setCity(detailsModel.getAgncty());
		agentMasterDto.setCivlStatus(detailsModel.getAgnsta());
		agentMasterDto.setCode(Integer.toString(detailsModel.getAgncod()));
		agentMasterDto.setContactEMail(detailsModel.getCnteml());
		agentMasterDto.setContactFax(detailsModel.getCntfax());
		agentMasterDto.setContactMobile(detailsModel.getCntmob());
		agentMasterDto.setContactPerson(detailsModel.getCntper());
		agentMasterDto.setContactTelOffice(detailsModel.getCntofn());
		agentMasterDto.setContactTelRecidence(detailsModel.getCntrsn());
		agentMasterDto.setContactTelx(detailsModel.getCnttlx());
		//agentMasterDto.setDateOfResign(detailsModel.get);
		agentMasterDto.setDesignation(detailsModel.getDescignation());
		agentMasterDto.setDob(detailsModel.getAgndob() != null ? new SimpleDateFormat("yyyy-MM-dd").format(detailsModel.getAgndob()) : null);
		agentMasterDto.setEfficenciveDate(detailsModel.getEfcdat() != null ? new SimpleDateFormat("yyyy-MM-dd").format(detailsModel.getEfcdat()) : null);
		agentMasterDto.setEmail(detailsModel.getAgneml());
		agentMasterDto.setEpf(detailsModel.getAgnepf());
		agentMasterDto.setFax(detailsModel.getAgnfax());
		agentMasterDto.setfName(detailsModel.getAgnnam());
		agentMasterDto.setGrantStatus(detailsModel.getGrntsta());
		agentMasterDto.setlName(detailsModel.getLasnam());
		agentMasterDto.setMisappropiate(detailsModel.getMisapp());
		agentMasterDto.setMissappRem(detailsModel.getMaprm());
		agentMasterDto.setmName(detailsModel.getMidnam());
		agentMasterDto.setMobile(detailsModel.getAgnmob());
		agentMasterDto.setNic(detailsModel.getAgnnic());
		agentMasterDto.setOcrStatus(detailsModel.getOrcrem());
		agentMasterDto.setOrcRem(detailsModel.getOrcrem());
		agentMasterDto.setRegion(detailsModel.getRgncod());
		//agentMasterDto.setRemark(detailsModel.getagn);
		//agentMasterDto.setRemarks(remarks);
		agentMasterDto.setSex(detailsModel.getAgnsex());
		agentMasterDto.setSliiRegNo(detailsModel.getSliirg());
		agentMasterDto.setsName(detailsModel.getShrtnm());
		agentMasterDto.setStatus(detailsModel.getAgnsta());
		agentMasterDto.setSubDesignation(detailsModel.getSubdcd());
		agentMasterDto.setSupervisor(detailsModel.getSuperisor());
		agentMasterDto.setTelOffice(detailsModel.getAgnofn());
		agentMasterDto.setTelRecidence(detailsModel.getAgnrsn());
		agentMasterDto.setTelX(detailsModel.getAgntlx());
		agentMasterDto.setTitle(detailsModel.getAgntit());
		agentMasterDto.setType(detailsModel.getType());
		agentMasterDto.setZone(detailsModel.getZoncod());
		agentMasterDto.setApprovedBy(detailsModel.getAppBy());
		agentMasterDto.setEnteredBy(detailsModel.getEntBy());
		
		List<SettlementDetailsModel> settlementDetailsModels=agentInquiryDao.getSettlementDetails(AppConstant.SBU_CODE, agentCode);
		List<SettlementDetailsDto> settlementDetailsDtos=new ArrayList<>();
		
		if(!settlementDetailsModels.isEmpty()) {
			settlementDetailsModels.forEach(settlement -> {
				
				SettlementDetailsDto settlementDetailsDto=new SettlementDetailsDto();
				
				settlementDetailsDto.setAccount(settlement.getAccount());
				settlementDetailsDto.setBank(settlement.getBank());
				settlementDetailsDto.setBranch(settlement.getBranch());
				settlementDetailsDto.setFromDate(settlement.getFromDate());
				settlementDetailsDto.setToDate(settlement.getToDate());
				settlementDetailsDto.setType(settlement.getType());
				
				settlementDetailsDtos.add(settlementDetailsDto);
			});
		}
		
		agentMasterDto.setSettlement(settlementDetailsDtos);
		
		
		List<TargetsModel> targetsModels=agentInquiryDao.getTargetDetails(AppConstant.SBU_CODE, agentCode);
		List<TargetsDto> targetsDtos=new ArrayList<>();
		
		if(!targetsModels.isEmpty()) {
			targetsModels.forEach(target -> {
				
				TargetsDto targetsDto=new TargetsDto();
				
				targetsDto.setAchAmount(target.getAchAmount());
				targetsDto.setCfAmount(target.getCfAmount());
				targetsDto.setMonth(target.getMonth());
				targetsDto.setOrRate(target.getOrRate());
				targetsDto.setPremium(target.getPremium());
				targetsDto.setTargetAmount(target.getTargetAmount());
				
				targetsDtos.add(targetsDto);
				
			});
		}
		
		agentMasterDto.setTarget(targetsDtos);
		
		List<HierarchyTransferModel> hierarchyTransferModels=agentInquiryDao.getHierarchyTransfer(AppConstant.SBU_CODE, agentCode);
		List<HierarchyTransferDto> hierarchyTransferDtos=new ArrayList<>();
		
		if(!hierarchyTransferModels.isEmpty()) {
			hierarchyTransferModels.forEach(hierarchy -> {
				HierarchyTransferDto hierarchyTransferDto=new HierarchyTransferDto();
				
				hierarchyTransferDto.setCls(hierarchy.getCls());
				hierarchyTransferDto.setFrom(hierarchy.getFrom());
				hierarchyTransferDto.setMasterSalesC(hierarchy.getMasterSalesC());
				hierarchyTransferDto.setName(hierarchy.getName());
				hierarchyTransferDto.setTo(hierarchy.getTo());
				
				hierarchyTransferDtos.add(hierarchyTransferDto);
				
			});
		}
		
		agentMasterDto.setHierarchy(hierarchyTransferDtos);
		
		List<DesignationModel> designationModels=agentInquiryDao.getDesignationDetails(AppConstant.SBU_CODE, agentCode);
		List<DesignationDto> designationDtos=new ArrayList<>();
		
		if(!designationModels.isEmpty()) {
			designationModels.forEach(designation -> {
				DesignationDto designationDto=new DesignationDto();
				
				designationDto.setDesCode(designation.getDesCode());
				designationDto.setFrom(designation.getFrom());
				designationDto.setName(designation.getName());
				designationDto.setTo(designation.getTo());
				
				designationDtos.add(designationDto);
				
			});
		}
		
		agentMasterDto.setDesignations(designationDtos);
		
		
		List<EducationModel> educationModels=agentInquiryDao.getEducationDetails(AppConstant.SBU_CODE, agentCode);
		List<EducationDto> educationDtos=new ArrayList<>();
		
		if(!educationModels.isEmpty()) {
			educationModels.forEach(education -> {
				EducationDto educationDto=new EducationDto();
				
				educationDto.setGrade(education.getGrade());
				educationDto.setQualification(education.getQualification());
				educationDto.setYear(education.getYear());
				
				educationDtos.add(educationDto);
				
			});
		}
		
		agentMasterDto.setEducation(educationDtos);
		
		//System.out.println(agentMasterDto.toString());
		
		return agentMasterDto;
	}

}