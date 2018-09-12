package org.arpico.groupit.receipt.service.impl;

import java.util.Date;
import java.util.List;
import org.arpico.groupit.receipt.dao.BranchUnderwriteDao;
import org.arpico.groupit.receipt.dao.InTransactionCustomDao;
import org.arpico.groupit.receipt.dao.ReceiptCancelationCustomDao;
import org.arpico.groupit.receipt.dao.ReceiptCancelationDao;
import org.arpico.groupit.receipt.dao.UserDao;
import org.arpico.groupit.receipt.model.CanceledReceiptModel;
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
				return receiptCancelationCustomDao.findReceiptLikeReceiptId(receiptId, locations);
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
				
				
				if(receiptCancelationDao.save(canceledReceiptModel) != null) {
					return new ResponseEntity<>("Success", HttpStatus.OK);
				}
				
				
			}
		}
		
		
		return null;
	}
	

}
