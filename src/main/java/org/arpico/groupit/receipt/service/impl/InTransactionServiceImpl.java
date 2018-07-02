package org.arpico.groupit.receipt.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.arpico.groupit.receipt.dao.InTransactionCustomDao;
import org.arpico.groupit.receipt.dto.LastReceiptSummeryDto;
import org.arpico.groupit.receipt.model.LastReceiptSummeryModel;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.InTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InTransactionServiceImpl implements InTransactionService{

	@Autowired
	private InTransactionCustomDao inTransactionCustomDao;
	
	@Override
	public List<LastReceiptSummeryDto> getLastReceipts(String token) throws Exception {
		
		List<LastReceiptSummeryDto> lastReceiptSummeryDtos = new ArrayList<>();
		
		List<LastReceiptSummeryModel> lastReceiptSummeryModels =
				inTransactionCustomDao.getLastReceipts(new JwtDecoder().generate(token));
		
		lastReceiptSummeryModels.forEach(e -> lastReceiptSummeryDtos.add(getLastReceiptDto(e)));
		
		return lastReceiptSummeryDtos;
	}

	private LastReceiptSummeryDto getLastReceiptDto(LastReceiptSummeryModel e) {
		LastReceiptSummeryDto dto = new LastReceiptSummeryDto();
		
		dto.setAmount(e.getTotprm());
		dto.setCreadt(new SimpleDateFormat("yyyy/MM/dd").format(e.getCreadt()));
		dto.setDoccod(e.getDoccod());
		dto.setDoctyp(Integer.toString(e.getDocnum()));
		dto.setPolnum(Integer.toString(e.getPolnum()));
		dto.setPprnum(e.getPprnum());
		return dto;
	}

}
