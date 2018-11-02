package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.arpico.groupit.receipt.dao.BranchUnderwriteDao;
import org.arpico.groupit.receipt.dao.CourierDetailsDao;
import org.arpico.groupit.receipt.dto.CourierDetailsDto;
import org.arpico.groupit.receipt.model.CourierDetailsModel;
import org.arpico.groupit.receipt.service.CourierDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CourierDetailsServiceImpl implements CourierDetailsService{
	
	@Autowired
	private CourierDetailsDao courierDetailsDao;
	
	@Autowired
	private BranchUnderwriteDao branchUnderwriteDao;

	@Override
	public String saveCourierDetail(CourierDetailsDto courierDetailsDto) throws Exception {
		CourierDetailsModel courierDetailsModel=new CourierDetailsModel();
		
		courierDetailsModel.setAgentCode(courierDetailsDto.getAgentCode());
		courierDetailsModel.setBranch(courierDetailsDto.getBranch());
		courierDetailsModel.setDepartment(courierDetailsDto.getDepartment());
		courierDetailsModel.setDocType(courierDetailsDto.getDocType());
		courierDetailsModel.setPolNo(courierDetailsDto.getPolNo());
		courierDetailsModel.setPrpNo(courierDetailsDto.getPrpNo());
		courierDetailsModel.setReferenceNumber(UUID.randomUUID().toString());
		courierDetailsModel.setRemarks(courierDetailsDto.getRemarks());
		courierDetailsModel.setStatus("NEW");
		courierDetailsModel.setUnderwriterEmail(courierDetailsDto.getUnderwriterEmail());
		courierDetailsModel.setUser(courierDetailsDto.getUser());
		courierDetailsModel.setCreateDate(new Date());
		//courierDetailsModel.setCreateBy(createBy);
			
		return courierDetailsDao.save(courierDetailsModel)!=null ? "200":"204";
	}

	@Override
	public List<CourierDetailsDto> findByBranchIn(String userCode) throws Exception {
		
		List<CourierDetailsDto> courierDetailsDtos=new ArrayList<>();
		
		if(userCode!=null) {
			List<String> loccodes=branchUnderwriteDao.findLocCodes(userCode);
			List<CourierDetailsModel> courierDetailsModels=courierDetailsDao.findByBranchIn(loccodes);
			
			courierDetailsModels.forEach(cu->{
				
				CourierDetailsDto courierDetailsDto=new CourierDetailsDto();
				courierDetailsDto.setAgentCode(cu.getAgentCode());
				courierDetailsDto.setBranch(cu.getBranch());
				courierDetailsDto.setDepartment(cu.getDepartment());
				courierDetailsDto.setDocType(cu.getDocType());
				courierDetailsDto.setPolNo(cu.getPolNo());
				courierDetailsDto.setPrpNo(cu.getPrpNo());
				courierDetailsDto.setReferenceNumber(cu.getReferenceNumber());
				courierDetailsDto.setRemarks(cu.getRemarks());
				courierDetailsDto.setStatus(cu.getStatus());
				courierDetailsDto.setUnderwriterEmail(cu.getUnderwriterEmail());
				courierDetailsDto.setUser(cu.getUser());
				
				courierDetailsDtos.add(courierDetailsDto);
				
			});
			
		}
		
		return courierDetailsDtos;
	}

}
