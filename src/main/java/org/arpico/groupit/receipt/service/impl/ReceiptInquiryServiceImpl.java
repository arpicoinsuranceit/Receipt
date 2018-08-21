package org.arpico.groupit.receipt.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.arpico.groupit.receipt.dao.BranchUnderwriteDao;
import org.arpico.groupit.receipt.dao.ReceiptInquiryCustomDao;
import org.arpico.groupit.receipt.dto.ReceiptDetailsDto;
import org.arpico.groupit.receipt.model.ReceiptDetailsModel;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.ReceiptInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReceiptInquiryServiceImpl implements ReceiptInquiryService{

	@Autowired
	private ReceiptInquiryCustomDao receiptInquiryCustomDao;
	
	@Autowired
	private BranchUnderwriteDao branchUnderwriteDao;

	@Override
	public List<ReceiptDetailsDto> getAllReceiptDetails(String token,Integer pageNum,Integer limit) throws Exception {
		
		String userCode=new JwtDecoder().generate(token);
		
		if(userCode!=null) {
			List<String> loccodes=branchUnderwriteDao.findLocCodes(userCode);
			String locations="";
			if(loccodes != null) {
				for (String string : loccodes) {
					locations+="'"+string+"'"+",";
				}
			}
			
			locations=locations.replaceAll(",$", "");
			
			//System.out.println(locations + " Branch Codes ------");
			
			if(locations != "") {
				
				Integer offset=pageNum*limit;
				
				List<ReceiptDetailsModel> detailsModels=receiptInquiryCustomDao.getAllReceiptDetails(offset,locations,loccodes.contains("HO"),limit);
				
				List<ReceiptDetailsDto> receiptDetailsDtos=new ArrayList<>();
				
				detailsModels.forEach(e -> {
					ReceiptDetailsDto detailsDto=new ReceiptDetailsDto();
					detailsDto.setCanDate("");
					detailsDto.setChqNo(e.getChqNo());
					detailsDto.setChqrel(e.getChqrel());
					detailsDto.setCredat(new SimpleDateFormat("yyyy-MM-dd").format(e.getCredat()));
					detailsDto.setDoccod(e.getDoccod());
					detailsDto.setDocnum(String.valueOf(e.getDocnum()));
					detailsDto.setName(e.getName());
					detailsDto.setPaymod(e.getPaymod());
					detailsDto.setPolnum(String.valueOf(e.getPolnum()));
					detailsDto.setPprnum(e.getPprnum());
					detailsDto.setTopprm(e.getTopprm());
					detailsDto.setUser(e.getUser());
					
					receiptDetailsDtos.add(detailsDto);
				});
				
				return receiptDetailsDtos;
				
			}
		}
		
		return new ArrayList<>();
		
		
	}
	


}
