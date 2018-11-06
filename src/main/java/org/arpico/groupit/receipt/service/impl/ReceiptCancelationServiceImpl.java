package org.arpico.groupit.receipt.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.arpico.groupit.receipt.client.InfosysWSClient;
import org.arpico.groupit.receipt.dao.BranchUnderwriteDao;
import org.arpico.groupit.receipt.dao.InTransactionCustomDao;
import org.arpico.groupit.receipt.dao.ReceiptCancelationCustomDao;
import org.arpico.groupit.receipt.dao.ReceiptCancelationDao;
import org.arpico.groupit.receipt.dao.UserDao;
import org.arpico.groupit.receipt.dto.CanceledReceiptDto;
import org.arpico.groupit.receipt.dto.EmailDto;
import org.arpico.groupit.receipt.dto.EmailResponseDto;
import org.arpico.groupit.receipt.model.CanceledReceiptModel;
import org.arpico.groupit.receipt.model.InTransactionsModel;
import org.arpico.groupit.receipt.model.LastReceiptSummeryModel;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.ReceiptCancelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ReceiptCancelationServiceImpl implements ReceiptCancelationService{

	@Autowired
	private BranchUnderwriteDao branchUnderwriteDao;
	
	@Autowired
	private ReceiptCancelationCustomDao receiptCancelationCustomDao;
	
	@Autowired
	private ReceiptCancelationDao receiptCancelationDao;
	
	@Autowired
	private InTransactionCustomDao inTransactionCustomDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private InfosysWSClient infosysWSClient;

	@Override
	public List<String> findReceiptLikeReceiptId(String receiptId,String token) throws Exception {
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
			
			System.out.println(locations);
			
			if(locations != "") {
				boolean isHo=false;
				if(loccodes.contains("HO")) {
					isHo=true;
					return receiptCancelationCustomDao.findReceiptLikeReceiptId(receiptId, locations,isHo);
				}
				
			}
		}
		
		return null;
	}

	@Override
	public ResponseEntity<Object> saveRequest(String receiptNo, String reason, String token) throws Exception {
		
		List<LastReceiptSummeryModel> lastReceiptSummeryModels= inTransactionCustomDao.getReceiptsByDocNum(receiptNo);
		
		String userCode=new JwtDecoder().generate(token);
		
		CanceledReceiptModel canceledReceiptModel=new CanceledReceiptModel();
		canceledReceiptModel.setReason(reason);
		canceledReceiptModel.setReceiptNo(receiptNo);
		
		InTransactionsModel inTransactionsModel=receiptCancelationCustomDao.findTransctionRow("450", receiptNo);
		
		if(userCode != null) {
			String locCode=userDao.getUserLocations(userCode);
			
			if(lastReceiptSummeryModels.size() > 0) {
				
				LastReceiptSummeryModel lastReceiptSummeryModel=lastReceiptSummeryModels.get(0);
				canceledReceiptModel.setCreateBy(userCode);
				canceledReceiptModel.setCreateDate(new Date());
				canceledReceiptModel.setPolNum(String.valueOf(lastReceiptSummeryModel.getPolnum()));
				canceledReceiptModel.setPprNum(lastReceiptSummeryModel.getPprnum());
				canceledReceiptModel.setRequestBy(userCode);
				canceledReceiptModel.setRequestDate(new Date());
				canceledReceiptModel.setLocCode(locCode);
				canceledReceiptModel.setSbuCode("450");
				canceledReceiptModel.setStatus("PENDING");
				canceledReceiptModel.setDocCode(inTransactionsModel.getInTransactionsModelPK().getDoccod());
				canceledReceiptModel.setAmount(inTransactionsModel.getTotprm());
				
				
				if(receiptCancelationDao.save(canceledReceiptModel) != null) {
					
					String toEmail=receiptCancelationCustomDao.findGMEmail("450", locCode);
					String fromEmail=userDao.getUserEmail(userCode);
					
					String body="";
					
					System.out.println(toEmail + " To Email"); 
					EmailDto emailDto=new EmailDto();
					if(toEmail != null && toEmail != "" && fromEmail != null && fromEmail != "") {
						emailDto.setAttachments(null);
						emailDto.setCcMails(null);
						emailDto.setFromMail(fromEmail);
						emailDto.setToMail(toEmail);
						emailDto.setToken(token);
						emailDto.setUserCode(userCode);
						emailDto.setSubject("Receipt Cancelation Approval Request");
						
						body+="Receipt Code : "+ inTransactionsModel.getInTransactionsModelPK().getDoccod() + "/n";
						body+="Receipt Number : "+ receiptNo + "/n";
						body+="Receipted Amount : "+ inTransactionsModel.getTotprm() + "/n";
						body+="Receipted Date : "+ inTransactionsModel.getCreadt() + "/n";
						
						if(inTransactionsModel.getChqnum() != null) {
							body+="Cheque Number : "+ inTransactionsModel.getChqnum() + "/n";
						}
						
						if(lastReceiptSummeryModel.getPprnum() != null) {
							body+="Proposal Number : "+ lastReceiptSummeryModel.getPprnum() + "/n";
						}
						
						if(lastReceiptSummeryModel.getPolnum() != null) {
							body+="Policy Number : "+ lastReceiptSummeryModel.getPolnum() + "/n";
						}
						
						body+="Cancellation Reason : "+ reason + "/n";
						
						emailDto.setBody(body);
						emailDto.setDepartment("Finance");
						
						System.out.println(canceledReceiptModel.toString());
						System.out.println(emailDto.toString());
						
//						EmailResponseDto responseDto=infosysWSClient.sendEmail(emailDto);
//						System.out.println(responseDto.toString());
						
					}
					
					return new ResponseEntity<>("Success", HttpStatus.OK);
				}
				
				
			}
		}
		
		
		return null;
	}

	@Override
	public List<CanceledReceiptDto> findPendingRequest(String token) throws Exception {
		
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
			
			System.out.println(locations);
			
			if(locations != "") {
				
				List<CanceledReceiptModel> canceledReceiptModels= receiptCancelationCustomDao.findPendingRequest(locations, "PENDING",loccodes.contains("HO"));
				List<CanceledReceiptDto> canceledReceiptDtos=new ArrayList<>();
				
				canceledReceiptModels.forEach(ca->{
					CanceledReceiptDto canceledReceiptDto=new CanceledReceiptDto();
					canceledReceiptDto.setLocCode(ca.getLocCode());
					canceledReceiptDto.setPolNum(ca.getPolNum());
					canceledReceiptDto.setPprNum(ca.getPprNum());
					canceledReceiptDto.setReason(ca.getReason());
					canceledReceiptDto.setReceiptNo(ca.getReceiptNo());
					canceledReceiptDto.setRequestBy(ca.getRequestBy());
					canceledReceiptDto.setRequestDate(ca.getRequestDate());
					canceledReceiptDto.setSbuCode(ca.getSbuCode());
					canceledReceiptDto.setStatus(ca.getStatus());
					canceledReceiptDto.setAmount(ca.getAmount());
					canceledReceiptDto.setDocCode(ca.getDocCode());
					
					canceledReceiptDtos.add(canceledReceiptDto);
				});
				
				return canceledReceiptDtos;
				
			}
		}
		
		return null;
	}
	
	@Override
	public List<CanceledReceiptDto> findCanceledRequest(String token) throws Exception {
		
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
			
			System.out.println(locations);
			
			if(locations != "") {
				
				List<CanceledReceiptModel> canceledReceiptModels= receiptCancelationCustomDao.findPendingRequest(locations, "CANCELED",loccodes.contains("HO"));
				List<CanceledReceiptDto> canceledReceiptDtos=new ArrayList<>();
				
				canceledReceiptModels.forEach(ca->{
					CanceledReceiptDto canceledReceiptDto=new CanceledReceiptDto();
					canceledReceiptDto.setLocCode(ca.getLocCode());
					canceledReceiptDto.setPolNum(ca.getPolNum());
					canceledReceiptDto.setPprNum(ca.getPprNum());
					canceledReceiptDto.setReason(ca.getReason());
					canceledReceiptDto.setReceiptNo(ca.getReceiptNo());
					canceledReceiptDto.setRequestBy(ca.getRequestBy());
					canceledReceiptDto.setRequestDate(ca.getRequestDate());
					canceledReceiptDto.setSbuCode(ca.getSbuCode());
					canceledReceiptDto.setStatus(ca.getStatus());
					canceledReceiptDto.setAmount(ca.getAmount());
					canceledReceiptDto.setDocCode(ca.getDocCode());
					
					canceledReceiptDtos.add(canceledReceiptDto);
				});
				
				return canceledReceiptDtos;
				
			}
		}
		
		return null;
	}
	

}
