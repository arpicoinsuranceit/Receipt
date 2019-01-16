package org.arpico.groupit.receipt.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.arpico.groupit.receipt.dao.BranchUnderwriteDao;
import org.arpico.groupit.receipt.dao.ReceiptInquiryCustomDao;
import org.arpico.groupit.receipt.dto.AccountDetailsDto;
import org.arpico.groupit.receipt.dto.BankDetailsDto;
import org.arpico.groupit.receipt.dto.LoadReceiptInquiryDetailsDto;
import org.arpico.groupit.receipt.dto.PolicyDetailsDto;
import org.arpico.groupit.receipt.dto.ReceiptDetailsDto;
import org.arpico.groupit.receipt.model.AccountDetailsModel;
import org.arpico.groupit.receipt.model.BankDetailsModel;
import org.arpico.groupit.receipt.model.PolicyDetailsModel;
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
	public LoadReceiptInquiryDetailsDto getAllReceiptDetails(String token,Integer pageNum,Integer limit) throws Exception {
		
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
			
			////System.out.println(locations + " Branch Codes ------");
			
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
				
				Integer count=receiptInquiryCustomDao.getAllReceiptCount(locations, loccodes.contains("HO"));
				
				LoadReceiptInquiryDetailsDto loadReceiptInquiryDetailsDto=new LoadReceiptInquiryDetailsDto();
				loadReceiptInquiryDetailsDto.setReceiptCount(count);
				loadReceiptInquiryDetailsDto.setReceiptDetailsDto(receiptDetailsDtos);
				
				return loadReceiptInquiryDetailsDto;
				
			}
		}
		
		return new LoadReceiptInquiryDetailsDto();
		
		
	}

	@Override
	public List<PolicyDetailsDto> getAllPolicyDetails(String docCode, Integer docNum) throws Exception {
		List<PolicyDetailsModel> policyDetailsModels=receiptInquiryCustomDao.getAllPolicyDetails(docCode, docNum);
		List<PolicyDetailsDto> policyDetailsDtos=new ArrayList<>();
		
		if(policyDetailsModels != null) {
			policyDetailsModels.forEach(p -> {
				
				PolicyDetailsDto detailsDto=new PolicyDetailsDto();
				detailsDto.setAmount(p.getAmount());
				detailsDto.setComDate(new SimpleDateFormat("yyyy-MM-dd").format(p.getComDate()));
				if(p.getDate() != null) {
					detailsDto.setDate(new SimpleDateFormat("yyyy-MM-dd").format(p.getDate()));
				}
				
				detailsDto.setInsMonth(p.getInsMonth());
				detailsDto.setPolnum(p.getPolnum());
				detailsDto.setPolType(p.getPolType());
				detailsDto.setPprnum(p.getPprnum());
				detailsDto.setStatus(p.getStatus());
				
				policyDetailsDtos.add(detailsDto);
				
			});
		}
		
		return policyDetailsDtos;
	}

	@Override
	public List<AccountDetailsDto> getAllAccountDetails(String docCode, Integer docNum) throws Exception {
		List<AccountDetailsModel> accountDetailsModels=receiptInquiryCustomDao.getAllAccountDetails(docCode, docNum);
		List<AccountDetailsDto> accountDetailsDtos=new ArrayList<>();
		
		if(accountDetailsModels!=null) {
			accountDetailsModels.forEach(a -> {
				AccountDetailsDto accountDetailsDto=new AccountDetailsDto();
				accountDetailsDto.setAccNO(a.getAccNO());
				accountDetailsDto.setBranch(a.getBranch());
				accountDetailsDto.setCr(a.getCr());
				accountDetailsDto.setDescription(a.getDescription());
				accountDetailsDto.setDr(a.getDr());
				
				accountDetailsDtos.add(accountDetailsDto);
				
			});
		}
		
		return accountDetailsDtos;
	}

	@Override
	public BankDetailsDto getBankDetails(String docCode, Integer docNum) throws Exception {
		BankDetailsDto bankDetailsDto=new BankDetailsDto();
		BankDetailsModel bankDetailsModel=receiptInquiryCustomDao.getBankDetails(docCode, docNum);
		
		if(bankDetailsModel!=null) {
			bankDetailsDto.setAmount(bankDetailsModel.getAmount());
			bankDetailsDto.setBranchCode(bankDetailsModel.getBranchCode());
			bankDetailsDto.setColBank(bankDetailsModel.getColBank());
			bankDetailsDto.setInsDate(new SimpleDateFormat("yyyy-MM-dd").format(bankDetailsModel.getInsDate()));
			bankDetailsDto.setInsNo(bankDetailsModel.getInsNo());
			bankDetailsDto.setInsType(bankDetailsModel.getInsType());
			bankDetailsDto.setRemarks(bankDetailsModel.getRemarks());
			bankDetailsDto.setStatus(bankDetailsModel.getStatus());
		}
		
		return bankDetailsDto;
	}
	


}
