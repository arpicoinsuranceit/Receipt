package org.arpico.groupit.receipt.print.impl;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.arpico.groupit.receipt.dto.InventoryDetailsDto;
import org.arpico.groupit.receipt.dto.ReceiptPrintDto;
import org.arpico.groupit.receipt.print.ReceiptPrintService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;

@PropertySource("classpath:application.properties")
@Component
public class ReceiptPrintServiceImpl implements ReceiptPrintService {
	
	@Value("${prtinttest}")
	private String test;

	@Override
	public byte[] createNewBusinessReceipt(ReceiptPrintDto receiptPrintDto) throws Exception {

		//System.out.println(receiptPrintDto.toString());

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		DecimalFormat formatter = new DecimalFormat("###,###.00");
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		PageSize ps = new PageSize(580, 360);

		PdfWriter writer = new PdfWriter(baos);
		PdfDocument pdf = new PdfDocument(writer);
		Document document = new Document(pdf, ps);
		document.setMargins(17, 20, 20, 25);
		
		//System.out.println(receiptPrintDto.getRctStatus());

		document.add(new Paragraph(
				receiptPrintDto.getRctStatus() != null && receiptPrintDto.getRctStatus().equalsIgnoreCase("DUP")
						? "DUPPLICATE"
						: "").setFixedPosition(15, 320, 100));

		document.add(new Paragraph("ARPICO INSURANCE PLC.").setTextAlignment(TextAlignment.CENTER)
				.setCharacterSpacing(1).setFontSize(18));
		document.add(new Paragraph("Receipt New Business " + test).setTextAlignment(TextAlignment.CENTER)
				.setCharacterSpacing(1).setFixedLeading(1).setFontSize(14));

		document.add(new Paragraph(""));

		float[] customerDetailsTableWidth = { 100, 230, 100, 100 };
		Table cusTable = new Table(customerDetailsTableWidth);

		Cell code = new Cell();
		code.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		code.add(new Paragraph("Customer Code").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(code);
		Cell codeVal = new Cell();
		codeVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		codeVal.add(new Paragraph(
				receiptPrintDto.getCusCode() != null ? Integer.toString(receiptPrintDto.getCusCode()) : "")
						.setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(codeVal);
		
		Cell rctNo = new Cell();
		rctNo.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		rctNo.add(new Paragraph("Receipt No").setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(rctNo);
		Cell rctNoVal = new Cell();
		rctNoVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		rctNoVal.add(
				new Paragraph(receiptPrintDto.getDocCode() + " / " + Integer.toString(receiptPrintDto.getDocNum()))
						.setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(rctNoVal);
		
		

		Cell name = new Cell();
		name.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		name.add(new Paragraph("Name").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(name);
		Cell nameVal = new Cell();
		nameVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		nameVal.add(new Paragraph(receiptPrintDto.getCusName() != null
				? receiptPrintDto.getCusTitle().toUpperCase() + " " + receiptPrintDto.getCusName().toUpperCase()
				: "").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(nameVal);

		Cell rctDate = new Cell();
		rctDate.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		rctDate.add(new Paragraph("Receipt Date & Time").setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(rctDate);
		Cell rctDateVal = new Cell();
		rctDateVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		rctDateVal.add(new Paragraph(dateFormat.format(receiptPrintDto.getRctDate())).setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f)
				.setFontSize(10));
		cusTable.addCell(rctDateVal);
		
		
		
		Cell address = new Cell();
		address.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		address.add(new Paragraph("Address").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(address);
		Cell addressVal = new Cell();
		addressVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);

		String add1 = "";
		String add2 = "";
		String add3 = "";

		if (receiptPrintDto.getCusAddress1() != null) {
			add1 = receiptPrintDto.getCusAddress1();
		}
		if (receiptPrintDto.getCusAddress2() != null) {
			add2 = receiptPrintDto.getCusAddress2();
		}
		if (receiptPrintDto.getCusAddress3() != null) {
			add3 = receiptPrintDto.getCusAddress3();
		}

		addressVal.add(new Paragraph(add1 + " " + add2 + " " + add3).setFontSize(10));
		cusTable.addCell(addressVal);
		
		Cell brcCod = new Cell();
		brcCod.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		brcCod.add(new Paragraph("Branch Code").setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(brcCod);
		Cell brcCodVal = new Cell();
		brcCodVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		brcCodVal.add(new Paragraph(receiptPrintDto.getLocation()).setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(brcCodVal);
		

		Cell pyMod = new Cell();
		pyMod.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		pyMod.add(new Paragraph("Payment Mode").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(pyMod);

		Cell pyModVal = new Cell();

		String payMethod;
		//System.out.println(receiptPrintDto.getPayMode());
		if (receiptPrintDto.getPayMode() != null) {

			switch (receiptPrintDto.getPayMode()) {
			case "CQ":
				payMethod = "Cheque";
				break;
			case "CR":
				payMethod = "Credit Card";
				break;
			case "CS":
				payMethod = "Cash";
				break;
			case "DD":
				payMethod = "Direct Deposit";
				break;
			default:
				payMethod = " ";
				break;
			}

			pyModVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			pyModVal.add(new Paragraph(payMethod).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(pyModVal);

		}

		Cell quoNo = new Cell();
		quoNo.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		quoNo.add(new Paragraph("Quotation No").setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(quoNo);
		Cell quoNoVal = new Cell();
		quoNoVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		quoNoVal.add(new Paragraph(
				Integer.toString(receiptPrintDto.getQuoNum()) + " / " + Integer.toString(receiptPrintDto.getQdId()))
						.setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(quoNoVal);
		
		
		Cell amount = new Cell();
		amount.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		amount.add(new Paragraph("Amount").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(amount);
		Cell amountVal = new Cell();
		amountVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		amountVal.add(new Paragraph(
				receiptPrintDto.getAmt() != null ? "Rs. " + " " + formatter.format(receiptPrintDto.getAmt()) : " ")
						.setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(amountVal);
		
		Cell polNo = new Cell();
		polNo.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		polNo.add(new Paragraph("Proposal No").setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(polNo);
		Cell polNoVal = new Cell();
		polNoVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		polNoVal.add(new Paragraph(Integer.toString(receiptPrintDto.getPropNum())).setBorder(Border.NO_BORDER)
				.setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(polNoVal);

		Cell amtWrd = new Cell(0, 2);
		amtWrd.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		amtWrd.add(new Paragraph(receiptPrintDto.getAmtInWord() != null ? receiptPrintDto.getAmtInWord() : "")
				.setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(amtWrd);
		
		Cell settlement = new Cell();
		settlement.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		settlement.add(new Paragraph("Settlement").setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(settlement);
		Cell settlementVal = new Cell();
		settlementVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		settlementVal.add(new Paragraph(
				receiptPrintDto.getSettlement() != null ? "Rs. " + formatter.format(receiptPrintDto.getSettlement())
						: "null").setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cusTable.addCell(settlementVal);

		document.add(cusTable);

//			float[] receiptTableColoumWidth = { 150, 200 };
//			Table rctTable = new Table(receiptTableColoumWidth);
//			rctTable.setFixedPosition(320, 160, 350);
//
//			Cell rctNo = new Cell();
//			rctNo.setBorder(Border.NO_BORDER);
//			rctNo.add(new Paragraph("Receipt No").setBorder(Border.NO_BORDER).setFontSize(10));
//			rctTable.addCell(rctNo);
//			Cell rctNoVal = new Cell();
//			rctNoVal.setBorder(Border.NO_BORDER);
//			rctNoVal.add(
//					new Paragraph(receiptPrintDto.getDocCode() + " / " + Integer.toString(receiptPrintDto.getDocNum()))
//							.setBorder(Border.NO_BORDER).setFontSize(10));
//			rctTable.addCell(rctNoVal);
//
//			Cell rctDate = new Cell();
//			rctDate.setBorder(Border.NO_BORDER);
//			rctDate.add(new Paragraph("Receipt Date & Time").setBorder(Border.NO_BORDER).setFontSize(10));
//			rctTable.addCell(rctDate);
//			Cell rctDateVal = new Cell();
//			rctDateVal.setBorder(Border.NO_BORDER);
//			rctDateVal.add(new Paragraph(dateFormat.format(receiptPrintDto.getRctDate())).setBorder(Border.NO_BORDER)
//					.setFontSize(10));
//			rctTable.addCell(rctDateVal);
//
//			Cell brcCod = new Cell();
//			brcCod.setBorder(Border.NO_BORDER);
//			brcCod.add(new Paragraph("Branch Code").setBorder(Border.NO_BORDER).setFontSize(10));
//			rctTable.addCell(brcCod);
//			Cell brcCodVal = new Cell();
//			brcCodVal.setBorder(Border.NO_BORDER);
//			brcCodVal.add(new Paragraph(receiptPrintDto.getLocation()).setBorder(Border.NO_BORDER).setFontSize(10));
//			rctTable.addCell(brcCodVal);
//
//			Cell quoNo = new Cell();
//			quoNo.setBorder(Border.NO_BORDER);
//			quoNo.add(new Paragraph("Quotation No").setBorder(Border.NO_BORDER).setFontSize(10));
//			rctTable.addCell(quoNo);
//			Cell quoNoVal = new Cell();
//			quoNoVal.setBorder(Border.NO_BORDER);
//			quoNoVal.add(new Paragraph(
//					Integer.toString(receiptPrintDto.getQuoNum()) + " / " + Integer.toString(receiptPrintDto.getQdId()))
//							.setBorder(Border.NO_BORDER).setFontSize(10));
//			rctTable.addCell(quoNoVal);
//
//			Cell polNo = new Cell();
//			polNo.setBorder(Border.NO_BORDER);
//			polNo.add(new Paragraph("Proposal No").setBorder(Border.NO_BORDER).setFontSize(10));
//			rctTable.addCell(polNo);
//			Cell polNoVal = new Cell();
//			polNoVal.setBorder(Border.NO_BORDER);
//			polNoVal.add(new Paragraph(Integer.toString(receiptPrintDto.getPropNum())).setBorder(Border.NO_BORDER)
//					.setFontSize(10));
//			rctTable.addCell(polNoVal);
//
//			Cell settlement = new Cell();
//			settlement.setBorder(Border.NO_BORDER);
//			settlement.add(new Paragraph("Settlement").setBorder(Border.NO_BORDER).setFontSize(10));
//			rctTable.addCell(settlement);
//			Cell settlementVal = new Cell();
//			settlementVal.setBorder(Border.NO_BORDER);
//			settlementVal.add(new Paragraph(
//					receiptPrintDto.getSettlement() != null ? "Rs. " + formatter.format(receiptPrintDto.getSettlement())
//							: "null").setBorder(Border.NO_BORDER).setFontSize(10));
//			rctTable.addCell(settlementVal);
//
//			document.add(rctTable);

		document.add(new Paragraph(""));

		if (receiptPrintDto.getPayMode().equalsIgnoreCase("CQ")) {
			float[] bankDetailsColWidths = { 130, 130, 130 };
			Table bnkDetTable = new Table(bankDetailsColWidths);
			bnkDetTable.setBorder(new SolidBorder(1));

			Cell chqNo = new Cell();
			chqNo.setBorder(new SolidBorder(1));
			chqNo.add(new Paragraph("Cheque No").setFontSize(10));
			bnkDetTable.addCell(chqNo);

			Cell bnkName = new Cell();
			bnkName.setBorder(new SolidBorder(1));
			bnkName.add(new Paragraph("Bank Name").setFontSize(10));
			bnkDetTable.addCell(bnkName);

			Cell chqDate = new Cell();
			chqDate.setBorder(new SolidBorder(1));
			chqDate.add(new Paragraph("Cheque Date").setFontSize(10));
			bnkDetTable.addCell(chqDate);

			bnkDetTable.startNewRow();

			Cell chqNoVal = new Cell();
			chqNoVal.setBorder(new SolidBorder(1));
			chqNoVal.add(new Paragraph(
					receiptPrintDto.getChqNo() != null ? Integer.toString(receiptPrintDto.getChqNo()) : "")
							.setFontSize(10));
			bnkDetTable.addCell(chqNoVal);

			Cell bnkNameVal = new Cell();
			bnkNameVal.setBorder(new SolidBorder(1));
			bnkNameVal.add(new Paragraph(
					receiptPrintDto.getBankCode() != null ? Integer.toString(receiptPrintDto.getBankCode()) : "")
							.setFontSize(10));
			bnkDetTable.addCell(bnkNameVal);

			Cell chqDateVal = new Cell();
			chqDateVal.setBorder(new SolidBorder(1));
			chqDateVal.add(new Paragraph(receiptPrintDto.getChqDate() != null ? receiptPrintDto.getChqDate() : "")
					.setFontSize(10));
			bnkDetTable.addCell(chqDateVal);

			document.add(bnkDetTable);
			document.add(new Paragraph(""));
			document.add(new Paragraph(""));
			document.add(new Paragraph(""));
			document.add(new Paragraph(""));
			document.add(new Paragraph(""));
			document.add(new Paragraph(""));
			document.add(new Paragraph(""));
			
		}else {
			document.add(new Paragraph(""));
			document.add(new Paragraph(""));
			document.add(new Paragraph(""));
			document.add(new Paragraph(""));
			document.add(new Paragraph(""));
		}
		

		float[] saleDtlTblColmWidths = { 60, 60, 200 };
		Table saleDtlTbl = new Table(saleDtlTblColmWidths);

		Cell agent = new Cell();
		agent.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		agent.add(new Paragraph("Sales Code").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		saleDtlTbl.addCell(agent);

		Cell agtCode = new Cell();
		agtCode.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		agtCode.add(new Paragraph(
				receiptPrintDto.getAgtCode() != null ? Integer.toString(receiptPrintDto.getAgtCode()) : "")
						.setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		saleDtlTbl.addCell(agtCode);

		Cell agtName = new Cell();
		agtName.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		agtName.add(new Paragraph(receiptPrintDto.getAgtName() != null ? receiptPrintDto.getAgtName() : "")
				.setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		saleDtlTbl.addCell(agtName);

		document.add(saleDtlTbl);

		if (receiptPrintDto.getPayMode().equalsIgnoreCase("CQ")
				|| receiptPrintDto.getPayMode().equalsIgnoreCase("DD")) {
			document.add(new Paragraph(
					"This receipt is issued to acknowledge receipt of money and is subject to realization of cheque")
							.setFontSize(8));
		} else {
			document.add(new Paragraph(
					"This Receipt is issued to acknowledge receipt of money and does not confirm granting of a cover/Acceptance of Risk and is subject to realization of cheque or acceptance of proposal.")
							.setFontSize(8));
		}

		document.add(new Paragraph("This is a computer generated receipt and will not require a signature")
				.setFontSize(8));

		float[] cashierTblColsWidths = { 60, 270, 100, 100 };
		Table cashierTbl = new Table(cashierTblColsWidths);
		cashierTbl.setFixedPosition(25, 15, 460);

		Cell usr = new Cell();
		usr.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		usr.add(new Paragraph("User Name").setFontSize(9).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cashierTbl.addCell(usr);

		Cell usrName = new Cell();
		usrName.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		usrName.add(new Paragraph(receiptPrintDto.getUserName() != null ? receiptPrintDto.getUserName() : "")
				.setFontSize(9).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cashierTbl.addCell(usrName);

		Cell printDate = new Cell();
		printDate.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
		printDate.add(new Paragraph("Printed Date & Time").setFontSize(9).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
		cashierTbl.addCell(printDate);

		Cell printDateVal = new Cell();
		printDateVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);

		Date rctPrintDate = new Date();

		printDateVal.add(new Paragraph(dateFormat.format(rctPrintDate)).setFontSize(9));
		cashierTbl.addCell(printDateVal);

		document.add(cashierTbl);

		document.close();

		return baos.toByteArray();

	}

	@Override
	public byte[] createProposalReceipt(ReceiptPrintDto receiptPrintDto) throws Exception {
		if (receiptPrintDto != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			DecimalFormat formatter = new DecimalFormat("###,###.00");
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

			PageSize ps = new PageSize(580, 360);

			PdfWriter writer = new PdfWriter(baos);
			PdfDocument pdf = new PdfDocument(writer);
			Document document = new Document(pdf, ps);
			document.setMargins(17, 20, 20, 25);

			// PdfCanvas under = new PdfCanvas(pdf.getPage(0).newContentStreamBefore(), new
			// PdfResources(), pdf);
			//
			// PdfFont font =
			// PdfFontFactory.createFont(FontProgramFactory.createFont(FontConstants.HELVETICA));
			// Paragraph p = new Paragraph("This watermark is added UNDER the existing
			// content").setFont(font)
			// .setFontSize(15);
			// new Canvas(under, pdf, pdf.getDefaultPageSize()).showTextAligned(p, 297, 550,
			// 1, TextAlignment.CENTER,
			// VerticalAlignment.TOP, 0);
			//
			// PdfCanvas over = new PdfCanvas(pdf.getPage(0));
			// over.setFillColor(Color.BLACK);

			//System.out.println(receiptPrintDto.getRctStatus());

			document.add(new Paragraph(
					receiptPrintDto.getRctStatus() != null && receiptPrintDto.getRctStatus().equalsIgnoreCase("DUP")
							? "DUPPLICATE"
							: "").setFixedPosition(15, 320, 100));

			document.add(new Paragraph("ARPICO INSURANCE PLC.").setTextAlignment(TextAlignment.CENTER)
					.setCharacterSpacing(1).setFontSize(18));
			document.add(new Paragraph("Proposal Receipt " + test).setTextAlignment(TextAlignment.CENTER)
					.setCharacterSpacing(1).setFixedLeading(1).setFontSize(14));

			document.add(new Paragraph(""));

			float[] customerDetailsTableWidth = { 100, 180 };
			Table cusTable = new Table(customerDetailsTableWidth);

			Cell code = new Cell();
			code.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			code.add(new Paragraph("Customer Code").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(code);
			Cell codeVal = new Cell().setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			codeVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			codeVal.add(new Paragraph(
					receiptPrintDto.getCusCode() != null ? Integer.toString(receiptPrintDto.getCusCode()) : "")
							.setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(codeVal);

			Cell name = new Cell().setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			name.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			name.add(new Paragraph("Name").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(name);
			Cell nameVal = new Cell().setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			nameVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			nameVal.add(new Paragraph(receiptPrintDto.getCusName() != null
					? receiptPrintDto.getCusTitle().toUpperCase() + " " + receiptPrintDto.getCusName().toUpperCase()
					: "").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(nameVal);

			Cell address = new Cell().setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			address.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			address.add(new Paragraph("Address").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(address);
			Cell addressVal = new Cell().setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			addressVal.setBorder(Border.NO_BORDER);

			String add1 = "";
			String add2 = "";
			String add3 = "";

			if (receiptPrintDto.getCusAddress1() != null) {
				add1 = receiptPrintDto.getCusAddress1();
			}
			if (receiptPrintDto.getCusAddress2() != null) {
				add2 = receiptPrintDto.getCusAddress2();
			}
			if (receiptPrintDto.getCusAddress3() != null) {
				add3 = receiptPrintDto.getCusAddress3();
			}

			addressVal.add(new Paragraph(add1 + "\n" + add2 + "\n" + add3).setFontSize(10));
			cusTable.addCell(addressVal);

			Cell pyMod = new Cell().setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			pyMod.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			pyMod.add(new Paragraph("Payment Mode").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(pyMod);

			Cell pyModVal = new Cell().setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);

			String payMethod;
			//System.out.println(receiptPrintDto.getPayMode());
			if (receiptPrintDto.getPayMode() != null) {

				switch (receiptPrintDto.getPayMode()) {
				case "CQ":
					payMethod = "Cheque";
					break;
				case "CR":
					payMethod = "Credit Card";
					break;
				case "CS":
					payMethod = "Cash";
					break;
				case "DD":
					payMethod = "Direct Deposit";
					break;
				default:
					payMethod = " ";
					break;
				}

				pyModVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
				pyModVal.add(new Paragraph(payMethod).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
				cusTable.addCell(pyModVal);

			}

			Cell amount = new Cell();
			amount.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			amount.add(new Paragraph("Amount").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(amount);
			Cell amountVal = new Cell();
			amountVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			amountVal.add(new Paragraph(
					receiptPrintDto.getAmt() != null ? "Rs. " + " " + formatter.format(receiptPrintDto.getAmt()) : " ")
							.setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(amountVal);

			Cell amtWrd = new Cell(0, 2);
			amtWrd.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			amtWrd.add(new Paragraph(receiptPrintDto.getAmtInWord() != null ? receiptPrintDto.getAmtInWord() : "")
					.setFontSize(10));
			cusTable.addCell(amtWrd);

			document.add(cusTable);

			float[] receiptTableColoumWidth = { 150, 200 };
			Table rctTable = new Table(receiptTableColoumWidth);
			rctTable.setFixedPosition(320, 165, 250);
			

			Cell rctNo = new Cell();
			rctNo.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			rctNo.add(new Paragraph("Receipt No").setBorder(Border.NO_BORDER).setFontSize(10));
			rctTable.addCell(rctNo);
			Cell rctNoVal = new Cell();
			rctNoVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			rctNoVal.add(
					new Paragraph(receiptPrintDto.getDocCode() + " / " + Integer.toString(receiptPrintDto.getDocNum()))
							.setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			rctTable.addCell(rctNoVal);

			Cell rctDate = new Cell();
			rctDate.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			rctDate.add(new Paragraph("Receipt Date & Time").setBorder(Border.NO_BORDER).setFontSize(10));
			rctTable.addCell(rctDate);
			Cell rctDateVal = new Cell();
			rctDateVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			rctDateVal.add(new Paragraph(dateFormat.format(receiptPrintDto.getRctDate())).setBorder(Border.NO_BORDER)
					.setFontSize(10));
			rctTable.addCell(rctDateVal);

			Cell brcCod = new Cell();
			brcCod.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			brcCod.add(new Paragraph("Branch Code").setBorder(Border.NO_BORDER).setFontSize(10));
			rctTable.addCell(brcCod);
			Cell brcCodVal = new Cell();
			brcCodVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			brcCodVal.add(new Paragraph(receiptPrintDto.getLocation()).setBorder(Border.NO_BORDER).setFontSize(10));
			rctTable.addCell(brcCodVal);

			Cell quoNo = new Cell();
			quoNo.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			quoNo.add(new Paragraph("Quotation No").setBorder(Border.NO_BORDER).setFontSize(10));
			rctTable.addCell(quoNo);
			Cell quoNoVal = new Cell();
			quoNoVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			quoNoVal.add(new Paragraph(
					Integer.toString(receiptPrintDto.getQuoNum()) + " / " + Integer.toString(receiptPrintDto.getQdId()))
							.setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			rctTable.addCell(quoNoVal);

			Cell polNo = new Cell();
			polNo.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			polNo.add(new Paragraph("Proposal No").setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			rctTable.addCell(polNo);
			Cell polNoVal = new Cell();
			polNoVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			polNoVal.add(new Paragraph(Integer.toString(receiptPrintDto.getPropNum())).setBorder(Border.NO_BORDER)
					.setFontSize(10));
			rctTable.addCell(polNoVal);

			Cell settlement = new Cell();
			settlement.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			settlement.add(new Paragraph("Settlement").setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			rctTable.addCell(settlement);
			Cell settlementVal = new Cell();
			settlementVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			settlementVal.add(new Paragraph(
					receiptPrintDto.getSettlement() != null ? "Rs. " + formatter.format(receiptPrintDto.getSettlement())
							: "").setBorder(Border.NO_BORDER).setFontSize(10));
			rctTable.addCell(settlementVal);

			document.add(rctTable);

			document.add(new Paragraph(""));

			if (receiptPrintDto.getPayMode().equalsIgnoreCase("CQ")) {
				float[] bankDetailsColWidths = {  80, 80, 80  };
				Table bnkDetTable = new Table(bankDetailsColWidths);
				bnkDetTable.setBorder(new SolidBorder(1));

				Cell chqNo = new Cell();
				chqNo.setBorder(new SolidBorder(1));
				chqNo.add(new Paragraph("Cheque No").setFontSize(10));
				bnkDetTable.addCell(chqNo);

				Cell bnkName = new Cell();
				bnkName.setBorder(new SolidBorder(1));
				bnkName.add(new Paragraph("Bank Name").setFontSize(10));
				bnkDetTable.addCell(bnkName);

				Cell chqDate = new Cell();
				chqDate.setBorder(new SolidBorder(1));
				chqDate.add(new Paragraph("Cheque Date").setFontSize(10));
				bnkDetTable.addCell(chqDate);

				bnkDetTable.startNewRow();

				Cell chqNoVal = new Cell();
				chqNoVal.setBorder(new SolidBorder(1));
				chqNoVal.add(new Paragraph(
						receiptPrintDto.getChqNo() != null ? Integer.toString(receiptPrintDto.getChqNo()) : "")
								.setFontSize(10));
				bnkDetTable.addCell(chqNoVal);

				Cell bnkNameVal = new Cell();
				bnkNameVal.setBorder(new SolidBorder(1));
				bnkNameVal.add(new Paragraph(
						receiptPrintDto.getBankCode() != null ? Integer.toString(receiptPrintDto.getBankCode()) : "")
								.setFontSize(10));
				bnkDetTable.addCell(bnkNameVal);

				Cell chqDateVal = new Cell();
				chqDateVal.setBorder(new SolidBorder(1));
				chqDateVal.add(new Paragraph(receiptPrintDto.getChqDate() != null ? receiptPrintDto.getChqDate() : "")
						.setFontSize(10));
				bnkDetTable.addCell(chqDateVal);

				document.add(bnkDetTable);

				document.add(new Paragraph(""));
			} else {
				document.add(new Paragraph(""));
				document.add(new Paragraph(""));
				document.add(new Paragraph(""));
				document.add(new Paragraph(""));
				document.add(new Paragraph(""));
				
			}

			float[] saleDtlTblColmWidths = { 60, 60, 200 };
			Table saleDtlTbl = new Table(saleDtlTblColmWidths);

			Cell agent = new Cell();
			agent.setBorder(Border.NO_BORDER);
			agent.add(new Paragraph("Sales Code").setFontSize(10));
			saleDtlTbl.addCell(agent);

			Cell agtCode = new Cell();
			agtCode.setBorder(Border.NO_BORDER);
			agtCode.add(new Paragraph(
					receiptPrintDto.getAgtCode() != null ? Integer.toString(receiptPrintDto.getAgtCode()) : "")
							.setFontSize(10));
			saleDtlTbl.addCell(agtCode);

			Cell agtName = new Cell();
			agtName.setBorder(Border.NO_BORDER);
			agtName.add(new Paragraph(receiptPrintDto.getAgtName() != null ? receiptPrintDto.getAgtName() : "")
					.setFontSize(10));
			saleDtlTbl.addCell(agtName);

			document.add(saleDtlTbl);
			
			Paragraph p1=new Paragraph();
			Paragraph p2=new Paragraph();;
			Paragraph p3=new Paragraph();;
			
			float[] paraarphTblColmWidths = { 350 };
			Table paraarphsaleDtlTbl = new Table(paraarphTblColmWidths);

			if (receiptPrintDto.getPayMode().equalsIgnoreCase("CQ")
					|| receiptPrintDto.getPayMode().equalsIgnoreCase("DD")) {
				p1.add(new Paragraph(
						"This receipt is issued to acknowledge receipt of money and is subject to realization of cheque")
								.setFontSize(8));
			} else {
				p2.add(new Paragraph(
						"This Receipt is issued to acknowledge receipt of money and does not confirm granting of a cover/Acceptance of Risk and is subject to realization of cheque or acceptance of proposal.")
								.setFontSize(8));
			}

			p3.add(new Paragraph("This is a computer generated receipt and will not require a signature")
					.setFontSize(8));
			
			Cell cell1=new Cell();
			cell1.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			cell1.add(p1);
			paraarphsaleDtlTbl.addCell(cell1);
			
			Cell cell2=new Cell();
			cell2.setBorder(Border.NO_BORDER);
			cell2.add(p2);
			paraarphsaleDtlTbl.addCell(cell2);
			
			Cell cell3=new Cell();
			cell3.setBorder(Border.NO_BORDER);
			cell3.add(p3);
			paraarphsaleDtlTbl.addCell(cell3);
			
			
			document.add(paraarphsaleDtlTbl);

			float[] cashierTblColsWidths = { 60, 270, 100, 100 };
			Table cashierTbl = new Table(cashierTblColsWidths);
			cashierTbl.setFixedPosition(26, 15, 460);

			Cell usr = new Cell();
			usr.setBorder(Border.NO_BORDER);
			usr.add(new Paragraph("User Name").setFontSize(9));
			cashierTbl.addCell(usr);

			Cell usrName = new Cell();
			usrName.setBorder(Border.NO_BORDER);
			usrName.add(new Paragraph(receiptPrintDto.getUserName() != null ? receiptPrintDto.getUserName() : "")
					.setFontSize(9));
			cashierTbl.addCell(usrName);

			Cell printDate = new Cell();
			printDate.setBorder(Border.NO_BORDER);
			printDate.add(new Paragraph("Printed Date & Time").setFontSize(9));
			cashierTbl.addCell(printDate);

			Cell printDateVal = new Cell();
			printDateVal.setBorder(Border.NO_BORDER);

			Date rctPrintDate = new Date();

			printDateVal.add(new Paragraph(dateFormat.format(rctPrintDate)).setFontSize(9));
			cashierTbl.addCell(printDateVal);

			document.add(cashierTbl);

			document.close();

			return baos.toByteArray();

		} else {
			return null;
		}

	}

	@Override
	public byte[] createLoanReceipt(ReceiptPrintDto receiptPrintDto) throws Exception {
		if (receiptPrintDto != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			DecimalFormat formatter = new DecimalFormat("###,###.00");
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

			PageSize ps = new PageSize(580, 360);

			PdfWriter writer = new PdfWriter(baos);
			PdfDocument pdf = new PdfDocument(writer);
			Document document = new Document(pdf, ps);
			document.setMargins(20, 15, 20, 15);

			

			//System.out.println(receiptPrintDto.getRctStatus());

			document.add(new Paragraph(
					receiptPrintDto.getRctStatus() != null && receiptPrintDto.getRctStatus().equalsIgnoreCase("DUP")
							? "DUPPLICATE"
							: "").setFixedPosition(15, 320, 100));

			document.add(new Paragraph("ARPICO INSURANCE PLC.").setBold().setTextAlignment(TextAlignment.CENTER)
					.setCharacterSpacing(1).setFontSize(12));
			document.add(new Paragraph("Loan Receipt " + test).setBold().setTextAlignment(TextAlignment.CENTER)
					.setCharacterSpacing(1).setFixedLeading(1).setFontSize(10));

			document.add(new Paragraph(""));

			float[] customerDetailsTableWidth = { 100, 200 };
			Table cusTable = new Table(customerDetailsTableWidth);

			Cell code = new Cell();
			code.setBorder(Border.NO_BORDER);
			code.add(new Paragraph("Customer Code").setFontSize(10));
			cusTable.addCell(code);
			Cell codeVal = new Cell();
			codeVal.setBorder(Border.NO_BORDER);
			codeVal.add(new Paragraph(
					receiptPrintDto.getCusCode() != null ? Integer.toString(receiptPrintDto.getCusCode()) : "")
							.setFontSize(10));
			cusTable.addCell(codeVal);

			Cell name = new Cell();
			name.setBorder(Border.NO_BORDER);
			name.add(new Paragraph("Name").setFontSize(10));
			cusTable.addCell(name);
			Cell nameVal = new Cell();
			nameVal.setBorder(Border.NO_BORDER);
			nameVal.add(new Paragraph(receiptPrintDto.getCusName() != null
					? receiptPrintDto.getCusTitle().toUpperCase() + " " + receiptPrintDto.getCusName().toUpperCase()
					: "").setFontSize(10));
			cusTable.addCell(nameVal);

			Cell address = new Cell();
			address.setBorder(Border.NO_BORDER);
			address.add(new Paragraph("Address").setFontSize(10));
			cusTable.addCell(address);
			Cell addressVal = new Cell();
			addressVal.setBorder(Border.NO_BORDER);

			String add1 = "";
			String add2 = "";
			String add3 = "";

			if (receiptPrintDto.getCusAddress1() != null) {
				add1 = receiptPrintDto.getCusAddress1();
			}
			if (receiptPrintDto.getCusAddress2() != null) {
				add2 = receiptPrintDto.getCusAddress2();
			}
			if (receiptPrintDto.getCusAddress3() != null) {
				add3 = receiptPrintDto.getCusAddress3();
			}

			addressVal.add(new Paragraph(add1 + "\n" + add2 + "\n" + add3).setFontSize(10));
			cusTable.addCell(addressVal);

			Cell pyMod = new Cell();
			pyMod.setBorder(Border.NO_BORDER);
			pyMod.add(new Paragraph("Payment Mode").setFontSize(10));
			cusTable.addCell(pyMod);

			Cell pyModVal = new Cell();

			String payMethod;
			//System.out.println(receiptPrintDto.getPayMode());
			if (receiptPrintDto.getPayMode() != null) {

				switch (receiptPrintDto.getPayMode()) {
				case "CQ":
					payMethod = "Cheque";
					break;
				case "CR":
					payMethod = "Credit Card";
					break;
				case "CS":
					payMethod = "Cash";
					break;
				case "DD":
					payMethod = "Direct Deposit";
					break;
				default:
					payMethod = " ";
					break;
				}

				pyModVal.setBorder(Border.NO_BORDER);
				pyModVal.add(new Paragraph(payMethod).setFontSize(10));
				cusTable.addCell(pyModVal);

			}

			Cell amount = new Cell();
			amount.setBorder(Border.NO_BORDER);
			amount.add(new Paragraph("Amount").setFontSize(10));
			cusTable.addCell(amount);
			Cell amountVal = new Cell();
			amountVal.setBorder(Border.NO_BORDER);
			amountVal.add(new Paragraph(
					receiptPrintDto.getAmt() != null ? "Rs. " + " " + formatter.format(receiptPrintDto.getAmt()) : " ")
							.setFontSize(10));
			cusTable.addCell(amountVal);

			Cell amtWrd = new Cell(0, 2);
			amtWrd.setBorder(Border.NO_BORDER);
			amtWrd.add(new Paragraph(receiptPrintDto.getAmtInWord() != null ? receiptPrintDto.getAmtInWord() : "")
					.setFontSize(10));
			cusTable.addCell(amtWrd);

			document.add(cusTable);

			float[] receiptTableColoumWidth = { 150, 200 };
			Table rctTable = new Table(receiptTableColoumWidth);
			rctTable.setFixedPosition(320, 140, 350);

			Cell rctNo = new Cell();
			rctNo.setBorder(Border.NO_BORDER);
			rctNo.add(new Paragraph("Receipt No").setBorder(Border.NO_BORDER).setFontSize(10));
			rctTable.addCell(rctNo);
			Cell rctNoVal = new Cell();
			rctNoVal.setBorder(Border.NO_BORDER);
			rctNoVal.add(
					new Paragraph(receiptPrintDto.getDocCode() + " / " + Integer.toString(receiptPrintDto.getDocNum()))
							.setBorder(Border.NO_BORDER).setFontSize(10));
			rctTable.addCell(rctNoVal);

			Cell rctDate = new Cell();
			rctDate.setBorder(Border.NO_BORDER);
			rctDate.add(new Paragraph("Receipt Date & Time").setBorder(Border.NO_BORDER).setFontSize(10));
			rctTable.addCell(rctDate);
			Cell rctDateVal = new Cell();
			rctDateVal.setBorder(Border.NO_BORDER);
			rctDateVal.add(new Paragraph(dateFormat.format(receiptPrintDto.getRctDate())).setBorder(Border.NO_BORDER)
					.setFontSize(10));
			rctTable.addCell(rctDateVal);

			Cell brcCod = new Cell();
			brcCod.setBorder(Border.NO_BORDER);
			brcCod.add(new Paragraph("Branch Code").setBorder(Border.NO_BORDER).setFontSize(10));
			rctTable.addCell(brcCod);
			Cell brcCodVal = new Cell();
			brcCodVal.setBorder(Border.NO_BORDER);
			brcCodVal.add(new Paragraph(receiptPrintDto.getLocation()).setBorder(Border.NO_BORDER).setFontSize(10));
			rctTable.addCell(brcCodVal);

			Cell polNo = new Cell();
			polNo.setBorder(Border.NO_BORDER);
			polNo.add(new Paragraph("Policy No").setBorder(Border.NO_BORDER).setFontSize(10));
			rctTable.addCell(polNo);
			Cell polNoVal = new Cell();
			polNoVal.setBorder(Border.NO_BORDER);
			polNoVal.add(new Paragraph(Integer.toString(receiptPrintDto.getPolNum())).setBorder(Border.NO_BORDER)
					.setFontSize(10));
			rctTable.addCell(polNoVal);
			
			Cell quoNo = new Cell();
			quoNo.setBorder(Border.NO_BORDER);
			quoNo.add(new Paragraph("Loan No").setBorder(Border.NO_BORDER).setFontSize(10));
			rctTable.addCell(quoNo);
			Cell quoNoVal = new Cell();
			quoNoVal.setBorder(Border.NO_BORDER);
			quoNoVal.add(new Paragraph(
					Integer.toString(receiptPrintDto.getLoanNum()))
							.setBorder(Border.NO_BORDER).setFontSize(10));
			rctTable.addCell(quoNoVal);

			Cell settlement = new Cell();
			settlement.setBorder(Border.NO_BORDER);
			settlement.add(new Paragraph("Settlement").setBorder(Border.NO_BORDER).setFontSize(10));
			rctTable.addCell(settlement);
			Cell settlementVal = new Cell();
			settlementVal.setBorder(Border.NO_BORDER);
			settlementVal.add(new Paragraph(
					receiptPrintDto.getSettlement() != null ? "Rs. " + formatter.format(receiptPrintDto.getSettlement())
							: "").setBorder(Border.NO_BORDER).setFontSize(10));
			rctTable.addCell(settlementVal);

			document.add(rctTable);

			document.add(new Paragraph(""));

			if (receiptPrintDto.getPayMode().equalsIgnoreCase("CQ")) {
				float[] bankDetailsColWidths = { 130, 130, 130 };
				Table bnkDetTable = new Table(bankDetailsColWidths);
				bnkDetTable.setBorder(new SolidBorder(1));

				Cell chqNo = new Cell();
				chqNo.setBorder(new SolidBorder(1));
				chqNo.add(new Paragraph("Cheque No").setFontSize(10));
				bnkDetTable.addCell(chqNo);

				Cell bnkName = new Cell();
				bnkName.setBorder(new SolidBorder(1));
				bnkName.add(new Paragraph("Bank Name").setFontSize(10));
				bnkDetTable.addCell(bnkName);

				Cell chqDate = new Cell();
				chqDate.setBorder(new SolidBorder(1));
				chqDate.add(new Paragraph("Cheque Date").setFontSize(10));
				bnkDetTable.addCell(chqDate);

				bnkDetTable.startNewRow();

				Cell chqNoVal = new Cell();
				chqNoVal.setBorder(new SolidBorder(1));
				chqNoVal.add(new Paragraph(
						receiptPrintDto.getChqNo() != null ? Integer.toString(receiptPrintDto.getChqNo()) : "")
								.setFontSize(10));
				bnkDetTable.addCell(chqNoVal);

				Cell bnkNameVal = new Cell();
				bnkNameVal.setBorder(new SolidBorder(1));
				bnkNameVal.add(new Paragraph(
						receiptPrintDto.getBankCode() != null ? Integer.toString(receiptPrintDto.getBankCode()) : "")
								.setFontSize(10));
				bnkDetTable.addCell(bnkNameVal);

				Cell chqDateVal = new Cell();
				chqDateVal.setBorder(new SolidBorder(1));
				chqDateVal.add(new Paragraph(receiptPrintDto.getChqDate() != null ? receiptPrintDto.getChqDate() : "")
						.setFontSize(10));
				bnkDetTable.addCell(chqDateVal);

				document.add(bnkDetTable);

				document.add(new Paragraph(""));
			} else {

			}

			float[] saleDtlTblColmWidths = { 60, 60, 200 };
			Table saleDtlTbl = new Table(saleDtlTblColmWidths);

			Cell agent = new Cell();
			agent.setBorder(Border.NO_BORDER);
			agent.add(new Paragraph("Sales Code").setFontSize(10));
			saleDtlTbl.addCell(agent);

			Cell agtCode = new Cell();
			agtCode.setBorder(Border.NO_BORDER);
			agtCode.add(new Paragraph(
					receiptPrintDto.getAgtCode() != null ? Integer.toString(receiptPrintDto.getAgtCode()) : "")
							.setFontSize(10));
			saleDtlTbl.addCell(agtCode);

			Cell agtName = new Cell();
			agtName.setBorder(Border.NO_BORDER);
			agtName.add(new Paragraph(receiptPrintDto.getAgtName() != null ? receiptPrintDto.getAgtName() : "")
					.setFontSize(10));
			saleDtlTbl.addCell(agtName);

			document.add(saleDtlTbl);

			if (receiptPrintDto.getPayMode().equalsIgnoreCase("CQ")
					|| receiptPrintDto.getPayMode().equalsIgnoreCase("DD")) {
				document.add(new Paragraph(
						"This receipt is issued to acknowledge receipt of money and is subject to realization of cheque")
								.setFontSize(8));
			} else {
				document.add(new Paragraph(
						"This Receipt is issued to acknowledge receipt of money and does not confirm granting of a cover/Acceptance of Risk and is subject to realization of cheque or acceptance of proposal.")
								.setFontSize(8));
			}

			document.add(new Paragraph("This is a computer generated receipt and will not require a signature")
					.setFontSize(8));

			float[] cashierTblColsWidths = { 60, 270, 100, 100 };
			Table cashierTbl = new Table(cashierTblColsWidths);
			cashierTbl.setFixedPosition(15, 15, 460);

			Cell usr = new Cell();
			usr.setBorder(Border.NO_BORDER);
			usr.add(new Paragraph("User Name").setFontSize(9));
			cashierTbl.addCell(usr);

			Cell usrName = new Cell();
			usrName.setBorder(Border.NO_BORDER);
			usrName.add(new Paragraph(receiptPrintDto.getUserName() != null ? receiptPrintDto.getUserName() : "")
					.setFontSize(9));
			cashierTbl.addCell(usrName);

			Cell printDate = new Cell();
			printDate.setBorder(Border.NO_BORDER);
			printDate.add(new Paragraph("Printed Date & Time").setFontSize(9));
			cashierTbl.addCell(printDate);

			Cell printDateVal = new Cell();
			printDateVal.setBorder(Border.NO_BORDER);

			Date rctPrintDate = new Date();

			printDateVal.add(new Paragraph(dateFormat.format(rctPrintDate)).setFontSize(9));
			cashierTbl.addCell(printDateVal);

			document.add(cashierTbl);

			document.close();

			return baos.toByteArray();

		} else {
			return null;
		}

	}

	@Override
	public byte[] createPremiumReceipt(ReceiptPrintDto receiptPrintDto) throws Exception {
		
		//System.out.println(test + " : test testtesttesttesttesttesttesttesttest");
		
		if (receiptPrintDto != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			DecimalFormat formatter = new DecimalFormat("###,###.00");
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

			PageSize ps = new PageSize(580, 360);

			PdfWriter writer = new PdfWriter(baos);
			PdfDocument pdf = new PdfDocument(writer);
			Document document = new Document(pdf, ps);
			document.setMargins(17, 20, 20, 25);

			// PdfCanvas under = new PdfCanvas(pdf.getPage(0).newContentStreamBefore(), new
			// PdfResources(), pdf);
			//
			// PdfFont font =
			// PdfFontFactory.createFont(FontProgramFactory.createFont(FontConstants.HELVETICA));
			// Paragraph p = new Paragraph("This watermark is added UNDER the existing
			// content").setFont(font)
			// .setFontSize(15);
			// new Canvas(under, pdf, pdf.getDefaultPageSize()).showTextAligned(p, 297, 550,
			// 1, TextAlignment.CENTER,
			// VerticalAlignment.TOP, 0);
			//
			// PdfCanvas over = new PdfCanvas(pdf.getPage(0));
			// over.setFillColor(Color.BLACK);

			//System.out.println(receiptPrintDto.getRctStatus());

			document.add(new Paragraph(
					receiptPrintDto.getRctStatus() != null && receiptPrintDto.getRctStatus().equalsIgnoreCase("DUP")
							? "DUPPLICATE"
							: "").setFixedPosition(15, 320, 100));

			document.add(new Paragraph("ARPICO INSURANCE PLC.").setTextAlignment(TextAlignment.CENTER)
					.setCharacterSpacing(1).setFontSize(18));
			document.add(new Paragraph("Premium Receipt " + test).setTextAlignment(TextAlignment.CENTER)
					.setFixedLeading(1).setFontSize(14));

			

			float[] customerDetailsTableWidth = { 100, 200 };
			Table cusTable = new Table(customerDetailsTableWidth);
			Cell code = new Cell();
			code.setBorder(Border.NO_BORDER).setPadding(0f).setMarginBottom(0f).setMarginTop(0f).setMargin(0f);
			code.add(new Paragraph("Customer Code").setFontSize(10));
			cusTable.addCell(code);
			Cell codeVal = new Cell();
			codeVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			codeVal.add(new Paragraph(
					receiptPrintDto.getCusCode() != null ? Integer.toString(receiptPrintDto.getCusCode()) : "")
							.setFontSize(10));
			cusTable.addCell(codeVal);

			Cell name = new Cell();
			name.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			name.add(new Paragraph("Name").setFontSize(10));
			cusTable.addCell(name);
			Cell nameVal = new Cell();
			nameVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			nameVal.add(new Paragraph(receiptPrintDto.getCusName() != null
					? receiptPrintDto.getCusTitle().toUpperCase() + " " + receiptPrintDto.getCusName().toUpperCase()
					: "").setFontSize(10));
			cusTable.addCell(nameVal);

			Cell address = new Cell();
			address.setBorder(Border.NO_BORDER).setPadding(0f).setMarginBottom(0f).setMarginTop(0f);
			address.add(new Paragraph("Address").setFontSize(10));
			cusTable.addCell(address);
			Cell addressVal = new Cell();
			addressVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);

			String add1 = "";
			String add2 = "";
			String add3 = "";

			if (receiptPrintDto.getCusAddress1() != null) {
				add1 = receiptPrintDto.getCusAddress1();
			}
			if (receiptPrintDto.getCusAddress2() != null) {
				add2 = receiptPrintDto.getCusAddress2();
			}
			if (receiptPrintDto.getCusAddress3() != null) {
				add3 = receiptPrintDto.getCusAddress3();
			}

			addressVal.add(new Paragraph(add1 + "\n" + add2 + "\n" + add3).setFontSize(10));
			cusTable.addCell(addressVal);

			Cell pyMod = new Cell();
			pyMod.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			pyMod.add(new Paragraph("Payment Modes").setFontSize(10));
			cusTable.addCell(pyMod);
			
			
		
			Cell pyModVal = new Cell();

			String payMethod;
			//System.out.println(receiptPrintDto.getPayMode());
			if (receiptPrintDto.getPayMode() != null) {

				switch (receiptPrintDto.getPayMode()) {
				case "CQ":
					payMethod = "Cheque";
					break;
				case "CR":
					payMethod = "Credit Card";
					break;
				case "CS":
					payMethod = "Cash";
					break;
				case "DD":
					payMethod = "Direct Deposit";
					break;
				default:
					payMethod = " ";
					break;
				}

				pyModVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
				pyModVal.add(new Paragraph(payMethod).setFontSize(10));
				cusTable.addCell(pyModVal);

				}
			

			Cell amount = new Cell();
			amount.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			amount.add(new Paragraph("Amount").setFontSize(10));
			cusTable.addCell(amount);
			Cell amountVal = new Cell();
			amountVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			amountVal.add(new Paragraph(
					receiptPrintDto.getAmt() != null ? "Rs. " + " " + formatter.format(receiptPrintDto.getAmt()) : " ")
							.setFontSize(10));
			cusTable.addCell(amountVal);

			//System.out.println(formatter.format(receiptPrintDto.getAmt()).toString());

			Cell amtWrd = new Cell(1, 2);
			amtWrd.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			amtWrd.add(new Paragraph(receiptPrintDto.getAmtInWord() != null ? receiptPrintDto.getAmtInWord() : "")
					.setFontSize(10));
			cusTable.addCell(amtWrd);
			document.add(cusTable);

			float[] receiptTableColoumWidth = { 200, 150 };
			Table rctTable = new Table(receiptTableColoumWidth);
			rctTable.setFixedPosition(320, 165, 250);
			Cell rctNo = new Cell();
			rctNo.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			rctNo.add(new Paragraph("Receipt No").setBorder(Border.NO_BORDER).setFontSize(10));
			rctTable.addCell(rctNo);
			Cell rctNoVal = new Cell();
			rctNoVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			rctNoVal.add(
					new Paragraph(receiptPrintDto.getDocCode() + " / " + Integer.toString(receiptPrintDto.getDocNum()))
							.setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			rctTable.addCell(rctNoVal);

			Cell rctDate = new Cell();
			rctDate.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			rctDate.add(new Paragraph("Receipt Date & Time").setBorder(Border.NO_BORDER).setFontSize(10));
			rctTable.addCell(rctDate);
			Cell rctDateVal = new Cell();
			rctDateVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			rctDateVal.add(new Paragraph(dateFormat.format(receiptPrintDto.getRctDate())).setBorder(Border.NO_BORDER)
					.setFontSize(10));
			rctTable.addCell(rctDateVal);

			Cell brcCod = new Cell();
			brcCod.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			brcCod.add(new Paragraph("Branch Code").setBorder(Border.NO_BORDER).setFontSize(10));
			rctTable.addCell(brcCod);
			Cell brcCodVal = new Cell();
			brcCodVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			brcCodVal.add(new Paragraph(receiptPrintDto.getLocation()).setFontSize(10));
			rctTable.addCell(brcCodVal);

			Cell quoNo = new Cell();
			quoNo.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			quoNo.add(new Paragraph("Quotation ").setFontSize(10).setBorder(Border.NO_BORDER));
			rctTable.addCell(quoNo);
			Cell quoNoVal = new Cell();
			quoNoVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			quoNoVal.add(new Paragraph(
					Integer.toString(receiptPrintDto.getQuoNum()) + " / " + Integer.toString(receiptPrintDto.getQdId()))
							.setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			rctTable.addCell(quoNoVal);

			Cell polNo = new Cell();
			polNo.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			polNo.add(new Paragraph("Policy No").setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			rctTable.addCell(polNo);
			Cell polNoVal = new Cell();
			polNoVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			polNoVal.add(new Paragraph(Integer.toString(receiptPrintDto.getPolNum())).setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f)
					.setFontSize(10));
			rctTable.addCell(polNoVal);

			Cell settlement = new Cell(1,0);
			settlement.setBorder(Border.NO_BORDER);
			settlement.add(new Paragraph("Settlement")).setFontSize(10);
			rctTable.addCell(settlement);
			
			Cell settlementVal = new Cell(10,0);
			settlementVal.setBorder(Border.NO_BORDER);
			settlementVal.setWidth(100);
			settlementVal.setTextAlignment(TextAlignment.LEFT);
			
			Paragraph p=new Paragraph();
			try {
				
				receiptPrintDto.getSetOffs().forEach(e -> {
					p.add(" "+e.get("txnMonth") + " - " + e.get("txnYear")+" : "+e.get("amount")+" , "+" ").setFontSize(5);
					//settlementVal.add(p);
				});
				
			} catch (Exception e2) {
				System.out.println("SETOFF NOT FOUND");
			}
			settlementVal.add(p);
			
			
			
			/////// settltments ////////////////
			
			// TODO
			
//			float[] settlementTblWidth = { 80,5, 80 };
//			Table settlementTable = new Table(settlementTblWidth);
//			
//			receiptPrintDto.getSetOffs().forEach(e -> {
//				Cell txnMthYer = new Cell(15,0);
//				//txnMthYer.setBorder(Border.NO_BORDER);
//				txnMthYer.setPadding(1);
//				txnMthYer.add(new Paragraph(e.get("txnMonth") + " - " + e.get("txnYear")).setFontSize(5));
//				settlementTable.addCell(txnMthYer);
//				
//				Cell seperator = new Cell(15,0);
//				//seperator.setBorder(Border.NO_BORDER);
//				seperator.add(new Paragraph(" : ").setFontSize(5));
//				seperator.setPadding(1);
//				settlementTable.addCell(seperator);
//				
//				Cell setOffAmount = new Cell(15,0);
//			//	setOffAmount.setBorder(Border.NO_BORDER);
//				setOffAmount.setPadding(1);
//				setOffAmount.add(new Paragraph(e.get("amount")).setFontSize(5));
//				settlementTable.addCell(setOffAmount);
//			});
//			
//			float[] BottomTblWidth = { 300,300};
//			Table BottomTbl = new Table(BottomTblWidth);
//			BottomTbl.addCell(settlementTable);
//			BottomTbl.addCell(rctTable);
			
			
			
			//settlementVal.add(settlementTable);
			rctTable.addCell(settlementVal);
			
			rctTable.setPaddingTop(500);

			document.add(rctTable);

			
			
			if (receiptPrintDto.getPayMode().equalsIgnoreCase("CQ")) {
				document.add(new Paragraph(""));
				float[] bankDetailsColWidths = { 80, 80, 80 };
				Table bnkDetTable = new Table(bankDetailsColWidths);
				bnkDetTable.setFixedLayout();
				bnkDetTable.setBorder(new SolidBorder(1));

				Cell chqNo = new Cell();
				chqNo.setBorder(new SolidBorder(1));
				chqNo.add(new Paragraph("Cheque No").setFontSize(10));
				bnkDetTable.addCell(chqNo);

				Cell bnkName = new Cell();
				bnkName.setBorder(new SolidBorder(1));
				bnkName.add(new Paragraph("Bank Name").setFontSize(10));
				bnkDetTable.addCell(bnkName);

				Cell chqDate = new Cell();
				chqDate.setBorder(new SolidBorder(1));
				chqDate.add(new Paragraph("Cheque Date").setFontSize(10));
				bnkDetTable.addCell(chqDate);

				bnkDetTable.startNewRow();
				
				
				Cell chqNoVal = new Cell();
				chqNoVal.setBorder(new SolidBorder(1));
				chqNoVal.add(new Paragraph(
						receiptPrintDto.getChqNo() != null ? Integer.toString(receiptPrintDto.getChqNo()) : "")
								.setFontSize(10));
				bnkDetTable.addCell(chqNoVal);

				Cell bnkNameVal = new Cell();
				bnkNameVal.setBorder(new SolidBorder(1));
				bnkNameVal.add(new Paragraph(
						receiptPrintDto.getBankCode() != null ? Integer.toString(receiptPrintDto.getBankCode()) : "")
								.setFontSize(10));
				bnkDetTable.addCell(bnkNameVal);

				Cell chqDateVal = new Cell();
				chqDateVal.setBorder(new SolidBorder(1));
				chqDateVal.add(new Paragraph(receiptPrintDto.getChqDate() != null ? receiptPrintDto.getChqDate() : "")
						.setFontSize(10));
				bnkDetTable.addCell(chqDateVal);

				document.add(bnkDetTable);

				
			} else {
				document.add(new Paragraph(""));
				document.add(new Paragraph(""));
				document.add(new Paragraph(""));
				document.add(new Paragraph(""));
				document.add(new Paragraph(""));
				document.add(new Paragraph(""));
				
				
			}
			
			document.add(new Paragraph(""));
			float[] saleDtlTblColmWidths = { 60, 60, 200 };
			Table saleDtlTbl = new Table(saleDtlTblColmWidths);
			Cell agent = new Cell();
			agent.setBorder(Border.NO_BORDER);
			agent.add(new Paragraph("Sales Code").setFontSize(10).setPadding(0f).setMarginBottom(0f));
			saleDtlTbl.addCell(agent);

			Cell agtCode = new Cell();
			agtCode.setBorder(Border.NO_BORDER);
			agtCode.add(new Paragraph(
					receiptPrintDto.getAgtCode() != null ? Integer.toString(receiptPrintDto.getAgtCode()) : "")
							.setFontSize(10));
			saleDtlTbl.addCell(agtCode);

			Cell agtName = new Cell();
			agtName.setBorder(Border.NO_BORDER);
			agtName.add(new Paragraph(receiptPrintDto.getAgtName() != null ? receiptPrintDto.getAgtName() : "")
					.setFontSize(10));
			saleDtlTbl.addCell(agtName);

			document.add(saleDtlTbl);

			Paragraph p1=new Paragraph();
			Paragraph p2=new Paragraph();;
			Paragraph p3=new Paragraph();;
			float[] paraarphTblColmWidths = { 350 };
			Table paraarphsaleDtlTbl = new Table(paraarphTblColmWidths);

			if (receiptPrintDto.getPayMode().equalsIgnoreCase("CQ")
					|| receiptPrintDto.getPayMode().equalsIgnoreCase("DD")) {
				p1.add(
						"This receipt is issued to acknowledge receipt of money and is subject to realization of cheque")
								.setFontSize(9).setPadding(0f);
			} else {
				p2.add(
						"This Receipt is issued to acknowledge receipt of money and does not confirm granting of a cover/Acceptance of Risk and is subject to realization of cheque or acceptance of proposal.")
								.setFontSize(9).setPadding(0f);
			}

			p3.add("This is a computer generated receipt and will not require a signature")
					.setFontSize(9).setPadding(0f);
			
			Cell cell1=new Cell();
			cell1.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			cell1.add(p1);
			paraarphsaleDtlTbl.addCell(cell1);
			
			Cell cell2=new Cell();
			cell2.setBorder(Border.NO_BORDER);
			cell2.add(p2);
			paraarphsaleDtlTbl.addCell(cell2);
			
			Cell cell3=new Cell();
			cell3.setBorder(Border.NO_BORDER);
			cell3.add(p3);
			paraarphsaleDtlTbl.addCell(cell3);
			
			
			document.add(paraarphsaleDtlTbl);

			float[] cashierTblColsWidths = { 60, 270, 100, 100 };
			Table cashierTbl = new Table(cashierTblColsWidths);
			
			
			cashierTbl.setFixedPosition(28, 15, 460);

			Cell usr = new Cell();
			usr.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			usr.add(new Paragraph("User Name").setFontSize(9));
			cashierTbl.addCell(usr);

			Cell usrName = new Cell();
			usrName.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			usrName.add(new Paragraph(receiptPrintDto.getUserName() != null ? receiptPrintDto.getUserName() : "")
					.setFontSize(9));
			cashierTbl.addCell(usrName);

			Cell printDate = new Cell();
			printDate.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			printDate.add(new Paragraph("Printed Date & Time").setFontSize(9));
			cashierTbl.addCell(printDate);

			Cell printDateVal = new Cell();
			printDateVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);

			Date rctPrintDate = new Date();
				
			printDateVal.add(new Paragraph(dateFormat.format(rctPrintDate)).setFontSize(9));
			cashierTbl.addCell(printDateVal);

			
			document.add(cashierTbl);

			document.close();

			return baos.toByteArray();
			
			

		} else {
			return null;
		}
		
		

	}

	@Override
	public byte[] createMiscInvtReceipt(ReceiptPrintDto receiptPrintDto) throws Exception {
		if (receiptPrintDto != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			DecimalFormat formatter = new DecimalFormat("###,###.00");
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

			PageSize ps = new PageSize(580, 360);

			PdfWriter writer = new PdfWriter(baos);
			PdfDocument pdf = new PdfDocument(writer);
			Document document = new Document(pdf, ps);
			document.setMargins(17, 20, 20, 25);

			//System.out.println(receiptPrintDto.getRctStatus());

			document.add(new Paragraph(
					receiptPrintDto.getRctStatus() != null && receiptPrintDto.getRctStatus().equalsIgnoreCase("DUP")
							? "DUPPLICATE"
							: "").setFixedPosition(15, 320, 100));

			document.add(new Paragraph("ARPICO INSURANCE PLC.").setBold().setTextAlignment(TextAlignment.CENTER)
					.setCharacterSpacing(1).setFontSize(12));
			document.add(new Paragraph("MISCELLANEOUS RECEIPT " + test).setBold().setTextAlignment(TextAlignment.CENTER)
					.setCharacterSpacing(1).setFixedLeading(1).setFontSize(10));

			document.add(new Paragraph(""));

			float[] customerDetailsTableWidth = { 100, 230, 100, 100 };
			Table cusTable = new Table(customerDetailsTableWidth);

			Cell name = new Cell();

			name.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);

			name.add(new Paragraph("Name").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(name);
			Cell nameVal = new Cell();
			nameVal.setBorder(Border.NO_BORDER);
			nameVal.add(new Paragraph(receiptPrintDto.getAgtName() != null ? receiptPrintDto.getAgtName() : "")
					.setFontSize(10));
			cusTable.addCell(nameVal);

			Cell rctNo = new Cell();
			rctNo.setBorder(Border.NO_BORDER);
			rctNo.add(new Paragraph("Receipt No").setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(rctNo);
			Cell rctNoVal = new Cell();
			rctNoVal.setBorder(Border.NO_BORDER);
			rctNoVal.add(
					new Paragraph(receiptPrintDto.getDocCode() + " / " + Integer.toString(receiptPrintDto.getDocNum()))
							.setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(rctNoVal);

			Cell pyMod = new Cell();
			pyMod.setBorder(Border.NO_BORDER);
			pyMod.add(new Paragraph("Payment Mode").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(pyMod);

			Cell pyModVal = new Cell();

			String payMethod;
			//System.out.println(receiptPrintDto.getPayMode());
			if (receiptPrintDto.getPayMode() != null) {

				switch (receiptPrintDto.getPayMode()) {
				case "01.CHEQUE":
					payMethod = "Cheque";
					break;
				case "CR":
					payMethod = "Credit Card";
					break;
				case "02.CASH":
					payMethod = "Cash";
					break;
				case "DD":
					payMethod = "Direct Deposit";
					break;
				default:
					payMethod = " ";
					break;
				}

				pyModVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
				pyModVal.add(new Paragraph(payMethod).setFontSize(10));
				cusTable.addCell(pyModVal);

			}

			Cell rctDate = new Cell();
			rctDate.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			rctDate.add(new Paragraph("Receipt Date").setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(rctDate);
			Cell rctDateVal = new Cell();
			rctDateVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			rctDateVal.add(new Paragraph(dateFormat.format(receiptPrintDto.getRctDate())).setBorder(Border.NO_BORDER)
					.setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(rctDateVal);

			cusTable.startNewRow();

			Cell remark = new Cell();
			remark.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			remark.add(new Paragraph("Remark").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(remark);
			Cell remarkVal = new Cell();
			remarkVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			remarkVal.add(new Paragraph(receiptPrintDto.getRemark() != null ? receiptPrintDto.getRemark() : "")
					.setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(remarkVal);

			Cell brcCod = new Cell();
			brcCod.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			brcCod.add(new Paragraph("Branch").setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(brcCod);
			Cell brcCodVal = new Cell();
			brcCodVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			brcCodVal.add(new Paragraph(receiptPrintDto.getLocation()).setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(brcCodVal);

			cusTable.startNewRow();

			Cell cusTemt1 = new Cell();
			cusTemt1.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			cusTemt1.add(new Paragraph(receiptPrintDto.getChqNo() != null ? "Cheque No" : "").setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(cusTemt1);
			Cell cusTemt2 = new Cell();
			cusTemt2.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			cusTemt2.add(new Paragraph(receiptPrintDto.getChqNo() != null ? receiptPrintDto.getChqNo().toString() : "").setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(cusTemt2);

			Cell advCode = new Cell();
			advCode.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			advCode.add(new Paragraph("Advisor Code").setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(advCode);
			Cell advCodeVal = new Cell();
			advCodeVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			advCodeVal.add(new Paragraph(
					receiptPrintDto.getAgtCode() != null ? Integer.toString(receiptPrintDto.getAgtCode()) : "")
							.setBorder(Border.NO_BORDER).setFontSize(10));
			cusTable.addCell(advCodeVal);

			document.add(cusTable);

			document.add(new Paragraph(""));

			float[] itemDtlColWidths = { 100, 200, 50, 60, 130 };
			Table itemDtlTable = new Table(itemDtlColWidths);
			// itemDtlTable.setFixedPosition(15, 160, 540);

			Cell itmCod = new Cell();
			itmCod.setBorderLeft(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			itmCod.setBorderRight(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			itmCod.add(new Paragraph("Item Code").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			itemDtlTable.addCell(itmCod);

			Cell itmName = new Cell();
			itmName.setBorderLeft(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			itmName.setBorderRight(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			itmName.add(new Paragraph("Item").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			itemDtlTable.addCell(itmName);

			Cell qty = new Cell();
			qty.setBorderLeft(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			qty.setBorderRight(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			qty.add(new Paragraph("Qty").setFontSize(10).setTextAlignment(TextAlignment.RIGHT).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			itemDtlTable.addCell(qty);

			Cell untPrice = new Cell();
			untPrice.setBorderLeft(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			untPrice.setBorderRight(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			untPrice.add(new Paragraph("Unit Price").setFontSize(10).setTextAlignment(TextAlignment.RIGHT).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			itemDtlTable.addCell(untPrice);

			Cell tot = new Cell();
			tot.setBorderLeft(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			tot.setBorderRight(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			tot.add(new Paragraph("Total").setFontSize(10).setTextAlignment(TextAlignment.RIGHT));
			itemDtlTable.addCell(tot);

			itemDtlTable.startNewRow();

			List<InventoryDetailsDto> itmDetails = receiptPrintDto.getInventoryDtl();

			//System.out.println(itmDetails.size());
			Double qtTot = 0.0;
			Double subTot = 0.0;
			String amtinWd = "";

			if (itmDetails != null) {

				for (InventoryDetailsDto inventoryDetailsDto : itmDetails) {

					qtTot += inventoryDetailsDto.getQty();
					subTot = inventoryDetailsDto.getSubTot();
					amtinWd = inventoryDetailsDto.getSubTotInWrd();

					Cell itmCodVal = new Cell();
					itmCodVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
					itmCodVal.add(new Paragraph(inventoryDetailsDto.getItemCod()).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f)
							.setTextAlignment(TextAlignment.LEFT));
					itemDtlTable.addCell(itmCodVal);

					Cell itmNameVal = new Cell();
					itmNameVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
					itmNameVal.add(new Paragraph(inventoryDetailsDto.getItemName()).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f)
							.setTextAlignment(TextAlignment.LEFT));
					itemDtlTable.addCell(itmNameVal);

					Cell qtyVal = new Cell();
					qtyVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
					qtyVal.add(new Paragraph(Integer.toString(inventoryDetailsDto.getQty())).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f)
							.setTextAlignment(TextAlignment.RIGHT));
					itemDtlTable.addCell(qtyVal);

					Cell untPrcVal = new Cell();
					untPrcVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
					untPrcVal.add(new Paragraph(formatter.format(inventoryDetailsDto.getUntPrice())).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f)
							.setTextAlignment(TextAlignment.RIGHT));
					itemDtlTable.addCell(untPrcVal);

					Cell untTotalVal = new Cell();
					untTotalVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
					untTotalVal.add(new Paragraph(formatter.format(inventoryDetailsDto.getUntPriceTot())).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f)
							.setFontSize(10).setTextAlignment(TextAlignment.RIGHT));
					itemDtlTable.addCell(untTotalVal);

				}
			}

			itemDtlTable.startNewRow();

			Cell emt1 = new Cell();
			emt1.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			emt1.add(new Paragraph("").setFontSize(10).setTextAlignment(TextAlignment.RIGHT).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			itemDtlTable.addCell(emt1);

			Cell emt2 = new Cell();
			emt2.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			emt2.add(new Paragraph("").setFontSize(10).setTextAlignment(TextAlignment.RIGHT).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			itemDtlTable.addCell(emt2);

			Cell qtyTot = new Cell();
			qtyTot.setBorderLeft(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			qtyTot.setBorderRight(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			qtyTot.add(new Paragraph(Integer.toString(qtTot.intValue())).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f)
					.setTextAlignment(TextAlignment.RIGHT));
			itemDtlTable.addCell(qtyTot);

			Cell emt3 = new Cell();
			emt3.setBorder(Border.NO_BORDER);
			emt3.add(new Paragraph("").setFontSize(10).setTextAlignment(TextAlignment.RIGHT).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			itemDtlTable.addCell(emt3);

			Cell subTotal = new Cell(0, 2);
			subTotal.setBorderLeft(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			subTotal.setBorderRight(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			subTotal.add(new Paragraph(formatter.format(subTot)).setFontSize(10).setTextAlignment(TextAlignment.RIGHT).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			itemDtlTable.addCell(subTotal);

			itemDtlTable.startNewRow();

			Cell amt = new Cell();
			amt.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			amt.add(new Paragraph("Rupees").setFontSize(10).setTextAlignment(TextAlignment.LEFT).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			itemDtlTable.addCell(amt);

			Cell amtInWrd = new Cell();
			amtInWrd.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			amtInWrd.add(new Paragraph(amtinWd).setFontSize(10).setTextAlignment(TextAlignment.LEFT).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			itemDtlTable.addCell(amtInWrd);

			document.add(itemDtlTable);

			document.add(new Paragraph(""));

			if (receiptPrintDto.getPayMode().equalsIgnoreCase("01.CHEQUE1")) {
				float[] bankDetailsColWidths = { 130, 130, 130 };
				Table bnkDetTable = new Table(bankDetailsColWidths);
				// bnkDetTable.setBorder(new SolidBorder(1)).setFixedPosition(15, 50, 390);

				Cell chqNo = new Cell();
				chqNo.setBorder(new SolidBorder(1));
				chqNo.add(new Paragraph("Cheque No").setFontSize(10));
				bnkDetTable.addCell(chqNo);

				Cell bnkName = new Cell();
				bnkName.setBorder(new SolidBorder(1));
				bnkName.add(new Paragraph("Bank Name").setFontSize(10));
				bnkDetTable.addCell(bnkName);

				Cell chqDate = new Cell();
				chqDate.setBorder(new SolidBorder(1));
				chqDate.add(new Paragraph("Cheque Date").setFontSize(10));
				bnkDetTable.addCell(chqDate);

				bnkDetTable.startNewRow();

				Cell chqNoVal = new Cell();
				chqNoVal.setBorder(new SolidBorder(1));
				chqNoVal.add(new Paragraph(
						receiptPrintDto.getChqNo() != null ? Integer.toString(receiptPrintDto.getChqNo()) : "")
								.setFontSize(10));
				bnkDetTable.addCell(chqNoVal);

				Cell bnkNameVal = new Cell();
				bnkNameVal.setBorder(new SolidBorder(1));
				bnkNameVal.add(new Paragraph(
						receiptPrintDto.getBankCode() != null ? Integer.toString(receiptPrintDto.getBankCode()) : "")
								.setFontSize(10));
				bnkDetTable.addCell(bnkNameVal);

				Cell chqDateVal = new Cell();
				chqDateVal.setBorder(new SolidBorder(1));
				chqDateVal.add(new Paragraph(receiptPrintDto.getChqDate() != null ? receiptPrintDto.getChqDate() : "")
						.setFontSize(10));
				bnkDetTable.addCell(chqDateVal);

				document.add(bnkDetTable);

				document.add(new Paragraph(""));
			} else {

			}

			float[] cashierTblColsWidths = { 60, 270, 100, 100 };
			Table cashierTbl = new Table(cashierTblColsWidths);
			cashierTbl.setFixedPosition(15, 15, 460);

			Cell usr = new Cell();
			usr.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			usr.add(new Paragraph("User Name").setFontSize(9).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cashierTbl.addCell(usr);

			Cell usrName = new Cell();
			usrName.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			usrName.add(new Paragraph(
					receiptPrintDto.getUserName() != null ? receiptPrintDto.getUserName() + " - ............." : "")
					.setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f).setFontSize(9));
			cashierTbl.addCell(usrName);

			Cell printDate = new Cell();
			printDate.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			printDate.add(new Paragraph("Printed Date & Time").setFontSize(9).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cashierTbl.addCell(printDate);

			Cell printDateVal = new Cell();
			printDateVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);

			Date rctPrintDate = new Date();

			printDateVal.add(new Paragraph(dateFormat.format(rctPrintDate)).setFontSize(9));
			cashierTbl.addCell(printDateVal);

			document.add(cashierTbl);

			document.close();

			return baos.toByteArray();

		} else {
			return null;
		}
	}

	@Override
	public byte[] createGLRCReceipt(ReceiptPrintDto receiptPrintDto) throws Exception {

		//System.out.println(receiptPrintDto.toString());

		{

			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			DecimalFormat formatter = new DecimalFormat("###,###.00");
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

			PageSize ps = new PageSize(580, 360);

			PdfWriter writer = new PdfWriter(baos);
			PdfDocument pdf = new PdfDocument(writer);
			Document document = new Document(pdf, ps);
			document.setMargins(17, 20, 20, 25);

			//System.out.println(receiptPrintDto.getRctStatus());

			document.add(new Paragraph(
					receiptPrintDto.getRctStatus() != null && receiptPrintDto.getRctStatus().equalsIgnoreCase("DUP")
							? "DUPPLICATE"
							: "").setFixedPosition(15, 320, 100));

			document.add(new Paragraph("ARPICO INSURANCE PLC.").setTextAlignment(TextAlignment.CENTER)
					.setCharacterSpacing(1).setFontSize(18));
			document.add(new Paragraph("MISCELLANEOUS RECEIPT " + test).setTextAlignment(TextAlignment.CENTER)
					.setCharacterSpacing(1).setFixedLeading(1).setFontSize(14));

			document.add(new Paragraph(""));

			float[] customerDetailsTableWidth = { 100, 230, 100, 100 };
			Table cusTable = new Table(customerDetailsTableWidth);

			Cell name = new Cell();
			name.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			name.add(new Paragraph("Name").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(name);
			Cell nameVal = new Cell();
			nameVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			nameVal.add(new Paragraph(
					receiptPrintDto.getCusName() != null ? receiptPrintDto.getCusName().toUpperCase() : "").setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f)
							.setFontSize(10));
			cusTable.addCell(nameVal);

			Cell rctNo = new Cell();
			rctNo.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			rctNo.add(new Paragraph("Receipt No").setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(rctNo);
			Cell rctNoVal = new Cell();
			rctNoVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			rctNoVal.add(
					new Paragraph(receiptPrintDto.getDocCode() + " / " + Integer.toString(receiptPrintDto.getDocNum()))
							.setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(rctNoVal);

			Cell glEmt1 = new Cell(0, 2);
			glEmt1.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			glEmt1.add(new Paragraph("").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(glEmt1);

			Cell glEmt2 = new Cell(0, 2);
			glEmt2.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			glEmt2.add(new Paragraph("").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(glEmt2);

			Cell pyMod = new Cell();
			pyMod.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			pyMod.add(new Paragraph("Payment Mode").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(pyMod);

			Cell pyModVal = new Cell();

			String payMethod;
			//System.out.println(receiptPrintDto.getPayMode());
			if (receiptPrintDto.getPayMode() != null) {

				switch (receiptPrintDto.getPayMode()) {
				case "CQ":
					payMethod = "Cheque";
					break;
				case "CR":
					payMethod = "Credit Card";
					break;
				case "CS":
					payMethod = "Cash";
					break;
				case "DD":
					payMethod = "Direct Deposit";
					break;
				default:
					payMethod = " ";
					break;
				}

				pyModVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
				pyModVal.add(new Paragraph(payMethod).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
				cusTable.addCell(pyModVal);

			}

			Cell rctDate = new Cell();
			rctDate.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			rctDate.add(new Paragraph("Receipt Date").setBorder(Border.NO_BORDER).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(rctDate);
			Cell rctDateVal = new Cell();
			rctDateVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			rctDateVal.add(new Paragraph(dateFormat.format(receiptPrintDto.getRctDate())).setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f)
					.setFontSize(10));
			cusTable.addCell(rctDateVal);

			Cell amount = new Cell();
			amount.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			amount.add(new Paragraph("Amount").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(amount);
			Cell amountVal = new Cell();
			amountVal.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			amountVal.add(new Paragraph(
					receiptPrintDto.getAmt() != null ? "Rs. " + " " + formatter.format(receiptPrintDto.getAmt()) : " ")
					.setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f).setFontSize(10));
			cusTable.addCell(amountVal);

			Cell glEmt3 = new Cell(0, 2);
			glEmt3.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			glEmt3.add(new Paragraph("").setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
			cusTable.addCell(glEmt3);

			Cell amtWrd = new Cell(0, 2);
			amtWrd.setBorder(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
			amtWrd.add(new Paragraph(receiptPrintDto.getAmtInWord() != null ? receiptPrintDto.getAmtInWord() : "").setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f)
					.setFontSize(10));
			cusTable.addCell(amtWrd);

			document.add(cusTable);

			document.add(new Paragraph(""));

			/*if (receiptPrintDto.getPayMode().equalsIgnoreCase("CQ")) {

				float[] bankDetailsColWidths = { 130, 130, 130 };
				Table bnkDetTable = new Table(bankDetailsColWidths);
				bnkDetTable.setBorder(new SolidBorder(1));

				Cell chqNo = new Cell();
				chqNo.setBorder(new SolidBorder(1));
				chqNo.add(new Paragraph("Cheque No").setFontSize(10));
				bnkDetTable.addCell(chqNo);

				Cell bnkName = new Cell();
				bnkName.setBorder(new SolidBorder(1));
				bnkName.add(new Paragraph("Bank Name").setFontSize(10));
				bnkDetTable.addCell(bnkName);

				Cell chqDate = new Cell();
				chqDate.setBorder(new SolidBorder(1));
				chqDate.add(new Paragraph("Cheque Date").setFontSize(10));
				bnkDetTable.addCell(chqDate);

				bnkDetTable.startNewRow();

				Cell chqNoVal = new Cell();
				chqNoVal.setBorder(new SolidBorder(1));
				chqNoVal.add(new Paragraph(
						receiptPrintDto.getChqNo() != null ? Integer.toString(receiptPrintDto.getChqNo()) : "")
								.setFontSize(10));
				bnkDetTable.addCell(chqNoVal);

				Cell bnkNameVal = new Cell();
				bnkNameVal.setBorder(new SolidBorder(1));
				bnkNameVal.add(new Paragraph(
						receiptPrintDto.getBankCode() != null ? Integer.toString(receiptPrintDto.getBankCode()) : "")
								.setFontSize(10));
				bnkDetTable.addCell(bnkNameVal);

				Cell chqDateVal = new Cell();
				chqDateVal.setBorder(new SolidBorder(1));
				chqDateVal.add(new Paragraph(
						receiptPrintDto.getChqDate() != null ? receiptPrintDto.getChqDate().substring(0, 10) : "")
								.setFontSize(10));
				bnkDetTable.addCell(chqDateVal);

				document.add(bnkDetTable);

				document.add(new Paragraph(""));
			} else {

			}*/
			document.add(new Paragraph(""));
			document.add(new Paragraph(""));
			

			float[] itemDtlColWidths = { 150, 260, 100 };
			Table itemDtlTable = new Table(itemDtlColWidths);

			String[] headers = { "Account", "Remark", "Amount" };

			for (String string : headers) {
				Cell cell = new Cell();
				cell.setBorderLeft(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
				cell.setBorderRight(Border.NO_BORDER).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f);
				if (string.equals("Amount")) {
					cell.add(new Paragraph(string).setFontSize(10).setTextAlignment(TextAlignment.RIGHT).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
				} else {
					cell.add(new Paragraph(string).setFontSize(10).setPadding(0f).setMargin(0f).setMarginBottom(0f).setMarginTop(0f));
				}
				itemDtlTable.addCell(cell);
			}

			receiptPrintDto.getAccounts().forEach(e -> {
				String[] row = { e.getDescription(), e.getRemark(), Double.toString(e.getAmount()) };

				for (String string : row) {
					Cell cell = new Cell();
					cell.setBorderLeft(Border.NO_BORDER);
					cell.setBorderRight(Border.NO_BORDER);

					try {
						Double.parseDouble(string);
						cell.add(new Paragraph(string).setFontSize(10).setTextAlignment(TextAlignment.RIGHT));
					} catch (Exception ex) {
						cell.add(new Paragraph(string).setFontSize(10));
					}

					itemDtlTable.addCell(cell);
				}
			});

			document.add(itemDtlTable);


			document.add(new Paragraph(""));
			document.add(new Paragraph(""));
			document.add(new Paragraph(""));
			document.add(new Paragraph(""));

			document.add(
					new Paragraph("This Receipt is valid subject to realization of cheque or acceptance of proposal.")
							.setFontSize(8).setFixedLeading(1));
			document.add(new Paragraph("This is a computer generated receipt and will not require a signature")
					.setFontSize(8).setFixedLeading(1));

			float[] cashierTblColsWidths = {60, 60, 210, 100, 100 };
			Table cashierTbl = new Table(cashierTblColsWidths);
			cashierTbl.setFixedPosition(1, 15, 460);

			Cell empty = new Cell();
			empty.setBorder(Border.NO_BORDER);
			empty.add(new Paragraph("").setFontSize(9));
			cashierTbl.addCell(empty);
			
			Cell usr = new Cell();
			usr.setBorder(Border.NO_BORDER);
			usr.add(new Paragraph("User Name").setFontSize(9));
			cashierTbl.addCell(usr);

			Cell usrName = new Cell();
			usrName.setBorder(Border.NO_BORDER);
			usrName.add(new Paragraph(receiptPrintDto.getUserName() != null ? receiptPrintDto.getUserName() : "")
					.setFontSize(9));
			cashierTbl.addCell(usrName);

			Cell printDate = new Cell();
			printDate.setBorder(Border.NO_BORDER);
			printDate.add(new Paragraph("Printed Date & Time").setFontSize(9));
			cashierTbl.addCell(printDate);

			Cell printDateVal = new Cell();
			printDateVal.setBorder(Border.NO_BORDER);

			Date rctPrintDate = new Date();

			printDateVal.add(new Paragraph(dateFormat.format(rctPrintDate)).setFontSize(9));
			cashierTbl.addCell(printDateVal);

			document.add(cashierTbl);

			document.close();

			return baos.toByteArray();

		}

	}

}
