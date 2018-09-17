package org.arpico.groupit.receipt.controller;

import java.util.ArrayList;
import java.util.Date;

import org.arpico.groupit.receipt.dto.InventoryDetailsDto;
import org.arpico.groupit.receipt.dto.ReceiptPrintDto;
import org.arpico.groupit.receipt.print.ItextReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
public class ReceiptPrintController {

	@Autowired
	private ItextReceipt itextReceipt;

	@RequestMapping(value = "/printReceipt", method = RequestMethod.POST, produces = "application/pdf")
	public ResponseEntity<Object> getReceiptDto(ReceiptPrintDto receiptPrintDto) {

		// receiptPrintDto.setRctStatus("DUP");

		receiptPrintDto.setCusCode(4952);
		receiptPrintDto.setCusTitle("MR");
		receiptPrintDto.setCusName("I M C Bandara");
		receiptPrintDto.setCusAddress1("NO 1,REKA NIWASA,");
		receiptPrintDto.setCusAddress2("SRI MAHINDA NAHIMI MAWATHA,");
		receiptPrintDto.setCusAddress3("MIDDENIYA");
		receiptPrintDto.setPayMode("DD");
		receiptPrintDto.setAmt(3140.00);
		receiptPrintDto.setAmtInWord("Three Thousand One Hundred Forty Only");

		receiptPrintDto.setDocCode("GLRC");
		receiptPrintDto.setDocNum(639257);

		Date date = new Date();

		receiptPrintDto.setRctDate(date);
		receiptPrintDto.setLocation("HO");
		receiptPrintDto.setQuoNum(20250);
		receiptPrintDto.setQdId(1);
		receiptPrintDto.setPropNum(76154);
		receiptPrintDto.setPolNum(57411);
		receiptPrintDto.setSettlement(1320.0);

		receiptPrintDto.setChqNo(299227);
		receiptPrintDto.setBankCode(7135052);
		receiptPrintDto.setChqDate("2018-08-18");

		receiptPrintDto.setAgtCode(4964);
		receiptPrintDto.setAgtName("M.P.L. Chandrasiri");

		receiptPrintDto.setUserName("M.H. Hiruni Nilushika");
		
		receiptPrintDto.setRemark("This is due to Renewal of the proposal");

		ArrayList<InventoryDetailsDto> invnt = receiptPrintDto.getInventoryDtl();

		if (invnt == null) {
			invnt = new ArrayList<InventoryDetailsDto>();
			InventoryDetailsDto ind1 = new InventoryDetailsDto();
			ind1.setItemCod(25);
			ind1.setItemName("Till");
			ind1.setQty(4);
			ind1.setUntPrice(150.0);
			ind1.setUntPriceTot(550.0);

			invnt.add(ind1);
			InventoryDetailsDto ind2 = new InventoryDetailsDto();

			ind2.setItemCod(23);
			ind2.setItemName("Book");
			ind2.setQty(5);
			ind2.setQtyTot(9);
			ind2.setSubTot(5540.0);
			ind2.setSubTotInWrd("Five hundred and Fifty Five Only");
			ind2.setUntPrice(250.0);
			ind2.setUntPriceTot(560.0);

			invnt.add(ind2);

			receiptPrintDto.setInventoryDtl(invnt);

		}

		try {

			byte[] pdf = itextReceipt.createReceipt(receiptPrintDto);
			ResponseEntity<Object> responseEntity = null;

			if (pdf == null) {
				responseEntity = new ResponseEntity<Object>(null, HttpStatus.METHOD_NOT_ALLOWED);
			} else {
				responseEntity = new ResponseEntity<Object>(pdf, HttpStatus.OK);
			}
			return responseEntity;

		} catch (Exception e) {

			e.printStackTrace();

			return new ResponseEntity<Object>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);

		}

	}

}
