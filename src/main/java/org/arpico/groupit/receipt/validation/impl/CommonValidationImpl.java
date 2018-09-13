package org.arpico.groupit.receipt.validation.impl;

import org.arpico.groupit.receipt.client.QuotationClient;
import org.arpico.groupit.receipt.dto.ExpenseDto;
import org.arpico.groupit.receipt.dto.MiscellaneousReceiptInvDto;
import org.arpico.groupit.receipt.dto.SaveReceiptDto;
import org.arpico.groupit.receipt.service.AgentService;
import org.arpico.groupit.receipt.service.BankService;
import org.arpico.groupit.receipt.service.ExpenseService;
import org.arpico.groupit.receipt.service.ProposalServce;
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

	@Value("${expenceNotAvailable}")
	private String expenceNotAvailable;
	
	@Value("${proposalnotavailable}")
	private String proposalnotavailable;

	private String ok = "ok";

	@Autowired
	private QuotationClient quotationClient;

	@Autowired
	private AgentService agentService;

	@Autowired
	private BankService bankService;

	@Autowired
	private ExpenseService expenseService;
	
	@Autowired
	private ProposalServce proposalServce;

	@Override
	public String validateQuotationReceiptInputs(SaveReceiptDto saveReceiptDto) throws Exception {

		if (quotationClient.isAvailableQuotation(saveReceiptDto.getSeqNo(), saveReceiptDto.getQuotationId())) {
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

	@Override
	public String validateMiscellaneousReceiptInvInputs(MiscellaneousReceiptInvDto dto, String token) throws Exception {
		if (bankService.findBankById(dto.getBank())) {
			if (agentService.availableAgent(dto.getAgent())) {
				Boolean exp = true;
				for (ExpenseDto e : dto.getExpencess()) {
					if (expenseService.findByCode(e.getExpenseId()) == null) {
						exp = false;
					}
				}
				if (exp) {
					return ok;
				} else {
					return expenceNotAvailable;
				}
			} else {
				return agentNotAvailable;
			}
		}else {
			return bankNotAvailable;
		}
	}

	@Override
	public String validateProposalReceiptInputs(SaveReceiptDto saveReceiptDto) throws Exception {
		if (bankService.findBankById(saveReceiptDto.getBankCode())) {
			if(proposalServce.getProposalNoSeqNoDto(Integer.toString(saveReceiptDto.getPropId()))!= null ) {
				return ok;
			}else {
				return proposalnotavailable;
			}
		}else {
			return bankNotAvailable;
		}
	}

}
