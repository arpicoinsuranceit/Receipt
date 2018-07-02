package org.arpico.groupit.receipt.validation.impl;

import org.arpico.groupit.receipt.client.QuotationClient;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.service.AgentService;
import org.arpico.groupit.receipt.service.BankService;
import org.arpico.groupit.receipt.validation.CommonValidations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:errormessages.properties")
public class CommonValidationImpl implements CommonValidations {

	@Value("${quotationnotavailable}")
	private String quotationNotAvailable;

	@Value("${agentNotAvailable}")
	private String agentNotAvailable;

	@Value("${bankNotAvailable}")
	private String bankNotAvailable;

	@Value("${lowAmount}")
	private String lowAmount;

	@Value("${inCompleteChequeDetails}")
	private String inCompleteChequeDetails;

	@Value("${inCompleteCC}")
	private String inCompleteCC;

	private String ok = "ok";

	@Autowired
	private QuotationClient quotationClient;

	@Autowired
	private AgentService agentService;

	@Autowired
	private BankService bankService;

	@Override
	public String validateQuotationReceiptInputs(SaveReceiptDto saveReceiptDto) throws Exception {

		if (quotationClient.isAvailableQuotation(saveReceiptDto.getQuotationDetailId(),
				saveReceiptDto.getQuotationId())) {
			if (agentService.availableAgent(saveReceiptDto.getAgentCode())) {
				if (bankService.findBankById(saveReceiptDto.getBankCode())) {
					if (saveReceiptDto.getAmount() != null && saveReceiptDto.getAmount() >= 1000) {
						if (saveReceiptDto.getPayMode().equals("CQ")) {
							if (saveReceiptDto.getChequebank() != null && saveReceiptDto.getChequebank().length() > 0
									&& saveReceiptDto.getChequeno() != null && saveReceiptDto.getChequeno().length() > 0
									&& saveReceiptDto.getChequedate() != null
									&& saveReceiptDto.getChequedate().length() > 0) {
								return ok;
							} else {
								return inCompleteChequeDetails;
							}
						} else if (saveReceiptDto.getPayMode().equals("CR")) {
							if (saveReceiptDto.getTransferno() != null && saveReceiptDto.getTransferno().length() > 0) {
								return ok;
							} else {
								return inCompleteCC;
							}

						} else {
							return ok;
						}
					} else {
						return lowAmount;
					}
				} else {
					return bankNotAvailable;
				}
			} else {
				return agentNotAvailable;
			}
		} else {
			return quotationNotAvailable;
		}

	}

}
