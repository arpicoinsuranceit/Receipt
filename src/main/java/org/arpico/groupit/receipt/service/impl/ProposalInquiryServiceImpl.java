package org.arpico.groupit.receipt.service.impl;

import java.util.List;

import org.arpico.groupit.receipt.client.InfosysClient;
import org.arpico.groupit.receipt.dao.InProposalCustomDao;
import org.arpico.groupit.receipt.dto.BenefictInquiryDto;
import org.arpico.groupit.receipt.dto.ChildInqDto;
import org.arpico.groupit.receipt.dto.DashboardParaDto;
import org.arpico.groupit.receipt.dto.DataTableResponseDto;
import org.arpico.groupit.receipt.dto.MedicalReqDto;
import org.arpico.groupit.receipt.dto.NomineeInquiryDto;
import org.arpico.groupit.receipt.dto.PaymentHistoryInqDto;
import org.arpico.groupit.receipt.dto.PolicyDispatchAcknowDto;
import org.arpico.groupit.receipt.dto.ProposalGeneralDto;
import org.arpico.groupit.receipt.dto.ProposalInquiryDataDto;
import org.arpico.groupit.receipt.dto.SettlementDto;
import org.arpico.groupit.receipt.dto.TransferHistoryDto;
import org.arpico.groupit.receipt.model.InProposalsModel;
import org.arpico.groupit.receipt.security.JwtDecoder;
import org.arpico.groupit.receipt.service.ProposalInquiryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProposalInquiryServiceImpl implements ProposalInquiryService {

	@Autowired
	private InfosysClient infosysClient;

	@Autowired
	private JwtDecoder decoder;

	@Autowired
	private InProposalCustomDao proposalCustomDao;

	@Override
	public Integer getCount(String token, String equality, String column, String data) {

		String advCode = decoder.generate(token);

		DashboardParaDto dashboardParaDto = infosysClient.getPara(advCode);

		System.out.println(advCode);
		System.out.println(dashboardParaDto.getUsertype());
		System.out.println(dashboardParaDto.getDashpara());
		System.out.println(advCode);

		System.out.println(data == null);

		if (data == null || data.equals("null")) {

			data = "";
		}

		Integer count = infosysClient.getCount(dashboardParaDto.getUsertype(), dashboardParaDto.getDashpara(), advCode,
				1, 1, equality, column, data);

		System.out.println(count);
		return count;
	}

	@Override
	public DataTableResponseDto propoInqloadData(String token, Integer page, Integer offset, String equality,
			String column, String data) {
		String advCode = decoder.generate(token);

		DashboardParaDto dashboardParaDto = infosysClient.getPara(advCode);

		if (data == null || data.equals("null")) {

			data = "";
		}

		DataTableResponseDto dataTableResponseDto = infosysClient.getDataPropInq(dashboardParaDto.getUsertype(),
				dashboardParaDto.getDashpara(), advCode, page, offset, equality, column, data);

		return dataTableResponseDto;
	}

	@Override
	public ProposalInquiryDataDto loadInfo(String token, String proposalNo) throws Exception {

		ProposalInquiryDataDto proposalInquiryDataDto = new ProposalInquiryDataDto();
		
		InProposalsModel inProposalsModel = proposalCustomDao.getProposalFromPprnum(Integer.parseInt(proposalNo));

		String data = proposalNo + "&" + inProposalsModel.getInProposalsModelPK().getLoccod() + "&"
				+ inProposalsModel.getInProposalsModelPK().getPrpseq();
		
		String data2 = inProposalsModel.getPolnum() + "&" + inProposalsModel.getInProposalsModelPK().getLoccod() + "&"
				+ inProposalsModel.getInProposalsModelPK().getPrpseq();

		ProposalGeneralDto generalDto = infosysClient.getPropInqGenData(proposalNo);
		List<ChildInqDto> childDtos = infosysClient.getChildren(data);
		List<NomineeInquiryDto> nomineeInquiryDaos = infosysClient.getNominee(data);
		List<BenefictInquiryDto> benefictInquiryDtos = infosysClient.getBeneficts(data);
		List<MedicalReqDto> medicalReqDtos = infosysClient.getMedDetails(data);
		List<TransferHistoryDto> transferHistoryDtos = infosysClient.getTransHist(proposalNo);
		List<SettlementDto> settlementDtos = infosysClient.getSettlement(proposalNo);
		
		System.out.println("data2: " +data2);
		
		List<PaymentHistoryInqDto> paymentHistoryInqDtos = infosysClient.getPayHist(data2);

		PolicyDispatchAcknowDto policyDispatchAcknowDto = null;
		if (inProposalsModel.getPolnum() != null && !inProposalsModel.getPolnum().equals("")) {
			policyDispatchAcknowDto = infosysClient.getPolDisAch(inProposalsModel.getPolnum());
		}
		
		proposalInquiryDataDto.setGeneralDto(generalDto);
		proposalInquiryDataDto.setBenefictInquiryDtos(benefictInquiryDtos);
		proposalInquiryDataDto.setChildDtos(childDtos);
		proposalInquiryDataDto.setMedicalReqDtos(medicalReqDtos);
		proposalInquiryDataDto.setNomineeInquiryDaos(nomineeInquiryDaos);
		proposalInquiryDataDto.setPaymentHistoryInqDtos(paymentHistoryInqDtos);
		proposalInquiryDataDto.setPolicyDispatchAcknowDto(policyDispatchAcknowDto);
		proposalInquiryDataDto.setSettlementDtos(settlementDtos);
		proposalInquiryDataDto.setTransferHistoryDtos(transferHistoryDtos);

		return proposalInquiryDataDto;
	}

}
