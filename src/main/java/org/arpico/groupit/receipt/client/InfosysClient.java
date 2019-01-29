package org.arpico.groupit.receipt.client;

import java.util.Arrays;
import java.util.List;

import org.arpico.groupit.receipt.dto.BenefictInquiryDto;
import org.arpico.groupit.receipt.dto.ChildDto;
import org.arpico.groupit.receipt.dto.ChildInqDto;
import org.arpico.groupit.receipt.dto.DashboardParaDto;
import org.arpico.groupit.receipt.dto.DataTableResponseDto;
import org.arpico.groupit.receipt.dto.MedicalReqDto;
import org.arpico.groupit.receipt.dto.NomineeInquiryDto;
import org.arpico.groupit.receipt.dto.PaymentHistoryInqDto;
import org.arpico.groupit.receipt.dto.PolicyDispatchAcknowDto;
import org.arpico.groupit.receipt.dto.ProposalGeneralDto;
import org.arpico.groupit.receipt.dto.SettlementDto;
import org.arpico.groupit.receipt.dto.TransferHistoryDto;
import org.arpico.groupit.receipt.util.AppConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Component
public class InfosysClient {

	public DashboardParaDto getPara(String userId) {

		try {

			RestTemplate restTemplate = new RestTemplate();
			DashboardParaDto result = restTemplate.postForObject(AppConstant.URI_DASH_PARA, userId,
					DashboardParaDto.class);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer getCount(String userType, String dashpara,
			String advcod, Integer offset, Integer limit,
			String equality, String column, String data) {

		try {

			RestTemplate restTemplate = new RestTemplate();
			Integer result = restTemplate.getForObject(AppConstant.URI_INQ_COUNT + userType + "/" + dashpara + "/" + advcod + "/" + offset + "/" + limit + "/" + equality + "/" + column + "/" + data, Integer.class);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public DataTableResponseDto getDataPropInq(String usertype, String dashpara, String advCode, Integer page,
			Integer offset, String equality, String column, String data) {
		try {

			RestTemplate restTemplate = new RestTemplate();
			DataTableResponseDto result = restTemplate.getForObject(AppConstant.URI_INQ_LOAD_DATA + usertype + "/" + dashpara + "/" + advCode + "/" + page + "/" + offset + "/" + equality + "/" + column + "/" + data, DataTableResponseDto.class);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ProposalGeneralDto getPropInqGenData(String proposalNo) {
		try {

			RestTemplate restTemplate = new RestTemplate();
			ProposalGeneralDto result = restTemplate.postForObject(AppConstant.URI_PROPOSAL_GEN_DATA, proposalNo,
					ProposalGeneralDto.class);
			

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<ChildInqDto> getChildren(String data) {
		try {

			RestTemplate restTemplate = new RestTemplate();
			ChildInqDto [] result = restTemplate.postForObject(AppConstant.URI_PROPOSAL_CHILD, data,
					ChildInqDto[].class);

			List<ChildInqDto> childDtos = Arrays.asList(result);

			return childDtos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<NomineeInquiryDto> getNominee(String data) {
		try {

			RestTemplate restTemplate = new RestTemplate();
			NomineeInquiryDto [] result = restTemplate.postForObject(AppConstant.URI_PROPOSAL_NOMINEE, data,
					NomineeInquiryDto[].class);

			List<NomineeInquiryDto> nomineeInquiryDtos = Arrays.asList(result);

			return nomineeInquiryDtos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<BenefictInquiryDto> getBeneficts(String data) {
		try {

			RestTemplate restTemplate = new RestTemplate();
			BenefictInquiryDto [] result = restTemplate.postForObject(AppConstant.URI_PROPOSAL_BENEFICT, data,
					BenefictInquiryDto[].class);

			List<BenefictInquiryDto> benefictInquiryDtos = Arrays.asList(result);

			return benefictInquiryDtos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<MedicalReqDto> getMedDetails(String data) {
		try {

			RestTemplate restTemplate = new RestTemplate();
			MedicalReqDto [] result = restTemplate.postForObject(AppConstant.URI_PROPOSAL_MED_REQ, data,
					MedicalReqDto[].class);

			List<MedicalReqDto> medicalReqDtos = Arrays.asList(result);

			return medicalReqDtos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<TransferHistoryDto> getTransHist(String proposalNo) {
		try {

			RestTemplate restTemplate = new RestTemplate();
			TransferHistoryDto [] result = restTemplate.postForObject(AppConstant.URI_PROPOSAL_TRANS, proposalNo,
					TransferHistoryDto[].class);

			List<TransferHistoryDto> transferHistoryDtos = Arrays.asList(result);

			return transferHistoryDtos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<SettlementDto> getSettlement(String proposalNo) {
		try {

			RestTemplate restTemplate = new RestTemplate();
			SettlementDto [] result = restTemplate.postForObject(AppConstant.URI_PROPOSAL_SETTLEMENTS, proposalNo,
					SettlementDto[].class);

			List<SettlementDto> settlementDtos = Arrays.asList(result);

			return settlementDtos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<PaymentHistoryInqDto> getPayHist(String data) {
		try {

			RestTemplate restTemplate = new RestTemplate();
			PaymentHistoryInqDto [] result = restTemplate.postForObject(AppConstant.URI_PROPOSAL_PAYMENT_HISTORY, data,
					PaymentHistoryInqDto[].class);

			List<PaymentHistoryInqDto> paymentHistoryInqDtos = Arrays.asList(result);

			return paymentHistoryInqDtos;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public PolicyDispatchAcknowDto getPolDisAch(String polnum) {
		try {

			RestTemplate restTemplate = new RestTemplate();
			PolicyDispatchAcknowDto result = restTemplate.postForObject(AppConstant.URI_PROPOSAL_POL_DIS_ACH, polnum,
					PolicyDispatchAcknowDto.class);
			

			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
