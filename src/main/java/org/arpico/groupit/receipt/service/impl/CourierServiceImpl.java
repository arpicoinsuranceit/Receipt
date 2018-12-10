package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;
import org.arpico.groupit.receipt.dao.BranchUnderwriteDao;
import org.arpico.groupit.receipt.dao.CourierDao;
import org.arpico.groupit.receipt.dao.DepartmentCourierDao;
import org.arpico.groupit.receipt.dao.SubDepartmentDocumentCourierDao;
import org.arpico.groupit.receipt.dto.CourierDetailsHelperDto;
import org.arpico.groupit.receipt.dto.CourierDto;
import org.arpico.groupit.receipt.dto.DepartmentHelperDto;
import org.arpico.groupit.receipt.dto.SubDepartmentDocumentCourierHelperDto;
import org.arpico.groupit.receipt.dto.SubDepartmentHelperDto;
import org.arpico.groupit.receipt.model.CourierModel;
import org.arpico.groupit.receipt.model.DepartmentCourierModel;
import org.arpico.groupit.receipt.model.SubDepartmentDocumentCourierModel;
import org.arpico.groupit.receipt.service.CourierService;
import org.arpico.groupit.receipt.service.NumberGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CourierServiceImpl implements CourierService{
	
	@Autowired
	private CourierDao courierDao;
	
	@Autowired
	private BranchUnderwriteDao branchUnderwriteDao;
	
	@Autowired
	private DepartmentCourierDao departmentCourierDao;
	
	@Autowired
	private SubDepartmentDocumentCourierDao subDepartmentDocumentCourierDao;
	
	@Autowired
	private NumberGenerator numberGenerator;
	
	boolean mustSave=true;

	private boolean isExistDepartment=false;
	
	private CourierModel courierModel2=null;
	
	private boolean allDocumentsReceived=true;

	@Override
	public List<CourierDto> findByCourierStatusAndBranchCodeIn(String userCode,Boolean isHoUser) throws Exception {
		
		List<String> branches=branchUnderwriteDao.findLocCodes(userCode);
		List<CourierModel> courierModels =new ArrayList<>();
		
		if(isHoUser) {
			courierModels=courierDao.findByCourierStatus("HO");
		}else {
			courierModels=courierDao.findByCourierStatusAndBranchCodeIn("BRANCH", branches);
			
		}
		
		List<CourierDto> courierDtos=new ArrayList<>();
		
		if(!courierModels.isEmpty()) {
			courierModels.forEach(co -> {
				System.out.println(co.getToken() + " Token ----- Token ");
				CourierDto courierDto=new CourierDto();
				
				courierDto.setBranchCode(co.getBranchCode());
				courierDto.setCourierId(co.getCourierId());
				courierDto.setCourierStatus(co.getCourierStatus());
				courierDto.setCreateBy(co.getCreateBy());
				courierDto.setCreateDate(co.getCreateDate());
				courierDto.setModifyBy(co.getModifyBy());
				courierDto.setModifyDate(co.getModifyDate());
				courierDto.setRemark(co.getRemark());
				courierDto.setToken(co.getToken());
				courierDto.setToBranch(co.getToBranch());
				courierDto.setSendBy(co.getSendBy());
				courierDto.setSendDate(co.getSendDate());
				courierDto.setCouType(co.getCouType());
				
				courierDtos.add(courierDto);
				
			});
		}
		
		
		return courierDtos;
	}

	@Override
	public String sendCourier(CourierDetailsHelperDto courierDetailsHelperDto, String userCode, String couType) throws Exception {

		List<SubDepartmentDocumentCourierModel> sendDepDocCou=new ArrayList<>();
		List<SubDepartmentDocumentCourierModel> notSendDepDocCou=new ArrayList<>();
		
		
		for (DepartmentHelperDto departmentHelperDto : courierDetailsHelperDto.getDepartmentHelperDtos()) {
			for (SubDepartmentHelperDto subDepHelperDto : departmentHelperDto.getSubDepartmentHelperDtos()) {
				for (SubDepartmentDocumentCourierHelperDto docCouDto : subDepHelperDto.getSubDepartmentDocumentCourierDtos()) {
					if(docCouDto.getIsChecked().equals("1")) {
						SubDepartmentDocumentCourierModel documentCourierModel=subDepartmentDocumentCourierDao.findOne(docCouDto.getSubDepartmentDocumentCourierId());
						if(documentCourierModel != null) {
							sendDepDocCou.add(documentCourierModel);
						}
					}else {
						SubDepartmentDocumentCourierModel documentCourierModel=subDepartmentDocumentCourierDao.findOne(docCouDto.getSubDepartmentDocumentCourierId());
						if(documentCourierModel != null) {
							notSendDepDocCou.add(documentCourierModel);
						}
					}
				}
			}
		}
		
		System.out.println(sendDepDocCou.size() + " sendDepDocCou.size()");
		System.out.println(notSendDepDocCou.size() + " notSendDepDocCou.size()");
		
		CourierModel newCourierModel=new CourierModel();
		
		CourierModel courierModel=courierDao.findOne(courierDetailsHelperDto.getCourierId());
		
		if(courierModel!=null && !sendDepDocCou.isEmpty()) {
			
			String[] numberGenCourier = numberGenerator.generateNewId("", "", "COURIER", "");
			
			if (numberGenCourier[0].equals("Success")) {
				
			if(!notSendDepDocCou.isEmpty()) {
				newCourierModel.setBranchCode(courierModel.getBranchCode());
				newCourierModel.setCourierStatus(courierModel.getCourierStatus());
				newCourierModel.setCreateBy(courierModel.getCreateBy());
				newCourierModel.setCreateDate(new Date());
				newCourierModel.setToBranch(courierModel.getToBranch());
				
				newCourierModel.setToken(courierModel.getToken().substring(0, courierModel.getToken().length()-7) + numberGenCourier[1]);
				
				courierModel2=courierDao.save(newCourierModel);
			}
			
			
			//CourierModel courierModel2=courierDao.save(newCourierModel);
			
			List<DepartmentCourierModel> departmentCourierModels=departmentCourierDao.findByCourier(courierModel);
			
			departmentCourierModels.forEach(dcm ->{
				String depCouStatus=dcm.getCourierStatus();
				mustSave=true;
				
				try {
					List<SubDepartmentDocumentCourierModel> subDepartmentDocumentCourierModels=subDepartmentDocumentCourierDao.findByDepartmentCourier(dcm);
					
					if(subDepartmentDocumentCourierModels != null) {
						dcm.setCourierStatus("SEND");
						
						subDepartmentDocumentCourierModels.forEach(sddc -> {
							
							if(sendDepDocCou.contains(sddc)) {
								sddc.setStatus("SEND");
								
								subDepartmentDocumentCourierDao.save(sddc);
							}
							
							if(notSendDepDocCou.contains(sddc)) {
								if(subDepartmentDocumentCourierModels.size() == 1) {
									dcm.setCourierStatus(depCouStatus);
									dcm.setCourier(courierModel2);
									
									DepartmentCourierModel departmentCourierModel=departmentCourierDao.save(dcm);
									mustSave=false;
									
									sddc.setDepartmentCourier(departmentCourierModel);
									
									subDepartmentDocumentCourierDao.save(sddc);
									
									notSendDepDocCou.remove(sddc);
									
								}
							}
							
							
							
						});
						
						if(mustSave) {
							departmentCourierDao.save(dcm);
						}
					}
					

				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			
			if(courierModel2 != null) {
				CourierModel courierModelNew=courierDao.findOne(courierModel2.getCourierId());
				List<DepartmentCourierModel> departmentCourierModelsNew=courierModelNew.getDepartmentCourier();
				
				notSendDepDocCou.forEach(notsend -> {
					isExistDepartment=false;
					DepartmentCourierModel departmentCourierModel=notsend.getDepartmentCourier();
					
					departmentCourierModelsNew.forEach(dep -> {
						if(dep.getDepartment().getDepartmentId().equals(departmentCourierModel.getDepartment().getDepartmentId())) {
							notsend.setDepartmentCourier(dep);
							subDepartmentDocumentCourierDao.save(notsend);
							isExistDepartment=true;
						}
					});
					
					if(!isExistDepartment) {
						DepartmentCourierModel departmentCourier=new DepartmentCourierModel();
						departmentCourier.setCourier(courierModel2);
						departmentCourier.setCourierStatus(courierModel2.getCourierStatus());
						departmentCourier.setCreateBy(departmentCourierModel.getCreateBy());
						departmentCourier.setCreateDate(new Date());
						departmentCourier.setDepartment(departmentCourierModel.getDepartment());
						
						
						try {
							String[] numberGenCourierDep = numberGenerator.generateNewId("", "", "COURIERDEP", "");
							
							if (numberGenCourierDep[0].equals("Success")) {
								departmentCourier.setToken("DEP-"+numberGenCourierDep[1]);
							}
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						DepartmentCourierModel departmentCourierModel2=departmentCourierDao.save(departmentCourier);
						notsend.setDepartmentCourier(departmentCourierModel2);
						subDepartmentDocumentCourierDao.save(notsend);
						
					}
					
					
				});
			}
			
			courierModel.setCourierStatus("SEND");
			courierModel.setSendBy(userCode);
			courierModel.setSendDate(new Date());
			courierModel.setCouType(couType);
			
			return courierDao.save(courierModel) != null ? "200":"204";
			}
		}
		
		return "204";
	}

	@Override
	public List<String> getBranches(String userCode) throws Exception {
		List<String> branches=branchUnderwriteDao.findLocCodes(userCode);
		return branches;
	}

	@Override
	public String updateStatus(Integer couId, String status) throws Exception {
		CourierModel courierModel=courierDao.findOne(couId);
		if(courierModel!=null) {
			
			List<DepartmentCourierModel> departmentCourierModels=departmentCourierDao.findByCourier(courierModel);
			
			departmentCourierModels.forEach(dcm ->{
				try {
					List<SubDepartmentDocumentCourierModel> subDepartmentDocumentCourierModels=subDepartmentDocumentCourierDao.findByDepartmentCourier(dcm);
					dcm.setCourierStatus(status);
					
					departmentCourierDao.save(dcm);
					
					subDepartmentDocumentCourierModels.forEach(sddc -> {
						
						sddc.setStatus(status);
						
						subDepartmentDocumentCourierDao.save(sddc);
						
					});
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			
			courierModel.setCourierStatus(status);
			return courierDao.save(courierModel) != null ? "200":"204";
		}
		return "204";
	}

	@Override
	public List<CourierDto> findByCourierStatusNotInAndBranchCodeIn(String userCode) throws Exception {
		List<String> branches=branchUnderwriteDao.findLocCodes(userCode);
		
		List<String> status=new ArrayList<>();
		status.add("BRANCH");
		status.add("HO");
		status.add("COMPLETED");
		
		List<CourierModel> courierModels=courierDao.findByCourierStatusNotInAndBranchCodeIn(status, branches);
		
		List<CourierDto> courierDtos=new ArrayList<>();
		
		
		courierModels.forEach(co -> {
			System.out.println(co.getToken() + " Token ----- Token ");
			CourierDto courierDto=new CourierDto();
			
			courierDto.setBranchCode(co.getBranchCode());
			courierDto.setCourierId(co.getCourierId());
			courierDto.setCourierStatus(co.getCourierStatus());
			courierDto.setCreateBy(co.getCreateBy());
			courierDto.setCreateDate(co.getCreateDate());
			courierDto.setModifyBy(co.getModifyBy());
			courierDto.setModifyDate(co.getModifyDate());
			courierDto.setRemark(co.getRemark());
			courierDto.setToken(co.getToken());
			courierDto.setToBranch(co.getToBranch());
			courierDto.setReceivedBy(co.getReceivedBy());
			courierDto.setReceivedDate(co.getReceivedDate());
			courierDto.setSendBy(co.getSendBy());
			courierDto.setSendDate(co.getSendDate());
			courierDto.setCouType(co.getCouType());
			
			courierDtos.add(courierDto);
			
		});
		
		return courierDtos;
	}

	@Override
	public List<CourierDto> findByCourierStatusAndToBranchIn(String userCode, Boolean isHoUser,String status) throws Exception {
		List<String> branches=branchUnderwriteDao.findLocCodes(userCode);
		List<CourierModel> courierModels =new ArrayList<>();
		
		courierModels=courierDao.findByCourierStatusAndToBranchIn(status, branches);
		
		List<CourierDto> courierDtos=new ArrayList<>();
		
		if(!courierModels.isEmpty()) {
			courierModels.forEach(co -> {
				System.out.println(co.getToken() + " Token ----- Token ");
				CourierDto courierDto=new CourierDto();
				
				courierDto.setBranchCode(co.getBranchCode());
				courierDto.setCourierId(co.getCourierId());
				courierDto.setCourierStatus(co.getCourierStatus());
				courierDto.setCreateBy(co.getCreateBy());
				courierDto.setCreateDate(co.getCreateDate());
				courierDto.setModifyBy(co.getModifyBy());
				courierDto.setModifyDate(co.getModifyDate());
				courierDto.setRemark(co.getRemark());
				courierDto.setToken(co.getToken());
				courierDto.setToBranch(co.getToBranch());
				courierDto.setReceivedBy(co.getReceivedBy());
				courierDto.setReceivedDate(co.getReceivedDate());
				courierDto.setSendBy(co.getSendBy());
				courierDto.setSendDate(co.getSendDate());
				courierDto.setCouType(co.getCouType());
				
				courierDtos.add(courierDto);
				
			});
		}
		
		
		return courierDtos;
	}

	@Override
	public String receiveCourier(CourierDetailsHelperDto courierDetailsHelperDto,String userCode) throws Exception {
		
		List<SubDepartmentDocumentCourierModel> receivedDepDocCou=new ArrayList<>();
		List<SubDepartmentDocumentCourierModel> notReceivedDepDocCou=new ArrayList<>();
		
		
		for (DepartmentHelperDto departmentHelperDto : courierDetailsHelperDto.getDepartmentHelperDtos()) {
			for (SubDepartmentHelperDto subDepHelperDto : departmentHelperDto.getSubDepartmentHelperDtos()) {
				for (SubDepartmentDocumentCourierHelperDto docCouDto : subDepHelperDto.getSubDepartmentDocumentCourierDtos()) {
					if(docCouDto.getIsChecked().equals("0")) {
						SubDepartmentDocumentCourierModel documentCourierModel=subDepartmentDocumentCourierDao.findOne(docCouDto.getSubDepartmentDocumentCourierId());
						if(documentCourierModel != null) {
							receivedDepDocCou.add(documentCourierModel);
						}
					}else {
						SubDepartmentDocumentCourierModel documentCourierModel=subDepartmentDocumentCourierDao.findOne(docCouDto.getSubDepartmentDocumentCourierId());
						if(documentCourierModel != null) {
							notReceivedDepDocCou.add(documentCourierModel);
						}
					}
				}
			}
		}
		
		CourierModel courierModel=courierDao.findOne(courierDetailsHelperDto.getCourierId());
		
		
		receivedDepDocCou.forEach(doc -> {
			SubDepartmentDocumentCourierModel  documentCourierModel=doc;
			
			DepartmentCourierModel departmentCourierModel=documentCourierModel.getDepartmentCourier();
			
			departmentCourierModel.setCourierStatus("RECEIVED");
			departmentCourierModel.setReceivedBy(userCode);
			departmentCourierModel.setReceivedDate(new Date());
			
			departmentCourierDao.save(departmentCourierModel);
			
			documentCourierModel.setStatus("RECEIVED");
			documentCourierModel.setReceivedBy(userCode);
			documentCourierModel.setReceivedDate(new Date());
			documentCourierModel.setCurrentUser(userCode);
			
			subDepartmentDocumentCourierDao.save(documentCourierModel);
			
		});
		
		allDocumentsReceived=true;
		
		notReceivedDepDocCou.forEach(notrec -> {
			if(!notrec.getStatus().equals("RECEIVED")) {
				allDocumentsReceived=false;
			}
		});

		if(courierModel != null) {
			if(allDocumentsReceived) {
				courierModel.setCourierStatus("COMPLETED");
			}else {
				courierModel.setCourierStatus("RECEIVED");
			}
			
			courierModel.setReceivedDate(new Date());
			courierModel.setReceivedBy(userCode);
			
			return courierDao.save(courierModel) != null ? "200":"204";
		}
		
		
		return "204";
	}

	@Override
	public String receiveCourierDocument(CourierDetailsHelperDto courierDetailsHelperDto, String userCode)
			throws Exception {
		List<SubDepartmentDocumentCourierModel> receivedDepDocCou=new ArrayList<>();
		List<SubDepartmentDocumentCourierModel> notReceivedDepDocCou=new ArrayList<>();
		
		
		for (DepartmentHelperDto departmentHelperDto : courierDetailsHelperDto.getDepartmentHelperDtos()) {
			for (SubDepartmentHelperDto subDepHelperDto : departmentHelperDto.getSubDepartmentHelperDtos()) {
				for (SubDepartmentDocumentCourierHelperDto docCouDto : subDepHelperDto.getSubDepartmentDocumentCourierDtos()) {
					if(docCouDto.getIsChecked().equals("0")) {
						SubDepartmentDocumentCourierModel documentCourierModel=subDepartmentDocumentCourierDao.findOne(docCouDto.getSubDepartmentDocumentCourierId());
						if(documentCourierModel != null) {
							receivedDepDocCou.add(documentCourierModel);
						}
					}else {
						SubDepartmentDocumentCourierModel documentCourierModel=subDepartmentDocumentCourierDao.findOne(docCouDto.getSubDepartmentDocumentCourierId());
						if(documentCourierModel != null) {
							notReceivedDepDocCou.add(documentCourierModel);
						}
					}
				}
			}
		}
		
		CourierModel courierModel=courierDao.findOne(courierDetailsHelperDto.getCourierId());
		
		if(!receivedDepDocCou.isEmpty()) {
			receivedDepDocCou.forEach(doc -> {
				SubDepartmentDocumentCourierModel  documentCourierModel=doc;
				
				DepartmentCourierModel departmentCourierModel=documentCourierModel.getDepartmentCourier();
				
				courierModel.getDepartmentCourier().forEach(dep -> {
					if(dep.getCourierDepartmentId().equals(departmentCourierModel.getCourierDepartmentId())) {
						if(!dep.getCourierStatus().equals("RECEIVED")) {
							departmentCourierModel.setCourierStatus("RECEIVED");
							departmentCourierModel.setReceivedBy(userCode);
							departmentCourierModel.setReceivedDate(new Date());
							
							departmentCourierDao.save(departmentCourierModel);
						}
					}
				});
				
				documentCourierModel.setStatus("RECEIVED");
				documentCourierModel.setReceivedBy(userCode);
				documentCourierModel.setReceivedDate(new Date());
				documentCourierModel.setCurrentUser(userCode);
				
				subDepartmentDocumentCourierDao.save(documentCourierModel);
				
			});
			
		}
		
		allDocumentsReceived=true;
		
		notReceivedDepDocCou.forEach(notrec -> {
			if(!notrec.getStatus().equals("RECEIVED")) {
				allDocumentsReceived=false;
			}
		});
		
		if(courierModel != null) {
			if(allDocumentsReceived) {
				courierModel.setCourierStatus("COMPLETED");
			}
			
			courierModel.setModifyDate(new Date());
			courierModel.setModifyBy(userCode);
			
			return courierDao.save(courierModel) != null ? "200":"204";
		}
		
		return "204";
		
	}

	@Override
	public String removeCourier(Integer id) throws Exception {
		
		subDepartmentDocumentCourierDao.delete(id);
		return "200";
	}

	@Override
	public List<CourierDto> findByCourierStatusAndBranchCodeIn(String userCode, String status) throws Exception {
		List<String> branches=branchUnderwriteDao.findLocCodes(userCode);
		List<CourierModel> courierModels =new ArrayList<>();
		
		courierModels=courierDao.findByCourierStatusAndBranchCodeIn(status, branches);
		
		List<CourierDto> courierDtos=new ArrayList<>();
		
		if(!courierModels.isEmpty()) {
			courierModels.forEach(co -> {
				System.out.println(co.getToken() + " Token ----- Token ");
				CourierDto courierDto=new CourierDto();
				
				courierDto.setBranchCode(co.getBranchCode());
				courierDto.setCourierId(co.getCourierId());
				courierDto.setCourierStatus(co.getCourierStatus());
				courierDto.setCreateBy(co.getCreateBy());
				courierDto.setCreateDate(co.getCreateDate());
				courierDto.setModifyBy(co.getModifyBy());
				courierDto.setModifyDate(co.getModifyDate());
				courierDto.setRemark(co.getRemark());
				courierDto.setToken(co.getToken());
				courierDto.setToBranch(co.getToBranch());
				courierDto.setSendBy(co.getSendBy());
				courierDto.setSendDate(co.getSendDate());
				courierDto.setCouType(co.getCouType());
				
				courierDtos.add(courierDto);
				
			});
		}
		
		
		return courierDtos;
	}

}
