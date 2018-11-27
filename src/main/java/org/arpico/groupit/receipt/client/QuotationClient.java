package org.arpico.groupit.receipt.client;

import java.util.ArrayList;
import java.util.List;
import org.arpico.groupit.receipt.dto.MedicalRequirementsDto;
import org.arpico.groupit.receipt.dto.NomineeDto;
import org.arpico.groupit.receipt.dto.PensionSheduleDto;
import org.arpico.groupit.receipt.dto.SheduleDto;
import org.arpico.groupit.receipt.dto.SurrenderValsDto;
import org.arpico.groupit.receipt.dto.ViewQuotationDto;
import org.arpico.groupit.receipt.util.AppConstant;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class QuotationClient {

	public ViewQuotationDto getQuotation(Integer seqNo, Integer qId) {

		MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
		map.add("seqNo", seqNo);
		map.add("qId", qId);
		
		System.out.println("seqNo" +  seqNo);
		System.out.println("qId" +  qId);

		try {
			RestTemplate restTemplate = new RestTemplate();
			ViewQuotationDto result = restTemplate.postForObject(AppConstant.URI_GET_QUO_DETAILS, map,
					ViewQuotationDto.class);
			//System.out.println(result.toString());
			System.out.println(result.get_mainlife().toString());
			System.out.println(result.get_spouse().toString());
			System.out.println(result.get_plan().toString());
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer getQuotationSeqnum(Integer qdId) {

		MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
		map.add("qdId", qdId);
		
		System.out.println("qdId" +  qdId);

		try {
			RestTemplate restTemplate = new RestTemplate();
			Integer result = restTemplate.postForObject(AppConstant.URI_GET_QUO_SEQNUM, map,
					Integer.class);
			System.out.println(result);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ViewQuotationDto getHealthBenef(Integer seqNo, Integer qId) {

		MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
		map.add("seqNo", seqNo);
		map.add("qId", qId);

		try {
			RestTemplate restTemplate = new RestTemplate();
			ViewQuotationDto result = restTemplate.postForObject(AppConstant.URI_GET_QUO_DETAILS, map,
					ViewQuotationDto.class);
			return result;
		} catch (Exception e) {

		}
		return null;
	}

	public List<SheduleDto> getShedule(Integer seqNo, Integer qId) {

		MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
		map.add("seqNo", seqNo);
		map.add("qId", qId);

		try {
			RestTemplate restTemplate = new RestTemplate();
			SheduleDto[] result = restTemplate.postForObject(AppConstant.URI_GET_QUO_SHEDULES, map, SheduleDto[].class);

			List<SheduleDto> sheduleDtos = new ArrayList<>();
			for (SheduleDto sheduleDto : result) {
				sheduleDtos.add(sheduleDto);
			}

			return sheduleDtos;
		} catch (Exception e) {

		}
		return null;
	}

	public List<MedicalRequirementsDto> getMediReq(Integer seqNo, Integer qId) {

		MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
		map.add("seqNo", seqNo);
		map.add("qId", qId);

		try {
			RestTemplate restTemplate = new RestTemplate();
			MedicalRequirementsDto[] result = restTemplate.postForObject(AppConstant.URI_GET_QUO_MEDILEIS, map,
					MedicalRequirementsDto[].class);

			List<MedicalRequirementsDto> requirementsDtos = new ArrayList<>();
			for (MedicalRequirementsDto medicalRequirementsDto : result) {
				requirementsDtos.add(medicalRequirementsDto);
			}

			return requirementsDtos;
		} catch (Exception e) {

		}
		return null;
	}
	
	public List<SurrenderValsDto> getSurrenderVals(Integer seqNo, Integer qId) {

		MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
		map.add("seqNo", seqNo);
		map.add("qId", qId);

		try {
			RestTemplate restTemplate = new RestTemplate();
			SurrenderValsDto[] result = restTemplate.postForObject(AppConstant.URI_QUOTATION_SURRENDER_VALS, map,
					SurrenderValsDto[].class);

			List<SurrenderValsDto> surrenderValsDtos = new ArrayList<>();
			for (SurrenderValsDto surrenderValsDto : result) {
				surrenderValsDtos.add(surrenderValsDto);
			}

			return surrenderValsDtos;
		} catch (Exception e) {

		}
		return null;
	}

	public boolean isAvailableQuotation(Integer seqNo, Integer qId) throws Exception{

		MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
		map.add("seqNo", seqNo);
		map.add("qId", qId);

		RestTemplate restTemplate = new RestTemplate();
		String resp =  restTemplate.postForObject(AppConstant.URI_QUOTATION_AVAILABLE, map,
				String.class);
		
		if(resp.equals("true")) {
			return true;	
		}
		return false;

	}
	
	public String updateStatus(Integer seqNo, Integer qId) {

		MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
		map.add("seqNo", seqNo);
		map.add("qId", qId);

		try {
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.postForObject(AppConstant.URI_UPDATE_STATUS, map, String.class);

			return result;
		} catch (Exception e) {

		}
		return null;
	}
	
	public List<PensionSheduleDto> getPensionShedule(Integer seqNo, Integer qId) {

		MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
		map.add("seqNo", seqNo);
		map.add("qId", qId);

		try {
			RestTemplate restTemplate = new RestTemplate();
			PensionSheduleDto[] result = restTemplate.postForObject(AppConstant.URI_QUOTATION_PENSION_SHEDULE, map,
					PensionSheduleDto[].class);

			List<PensionSheduleDto> pensionValsDtos = new ArrayList<>();
			for (PensionSheduleDto pensionValsDto : result) {
				pensionValsDtos.add(pensionValsDto);
			}

			return pensionValsDtos;
		} catch (Exception e) {

		}
		return null;
	}
	
	public List<NomineeDto> getNominee(Integer seqNo, Integer qId) {

		MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
		map.add("seqNo", seqNo);
		map.add("qId", qId);

		try {
			RestTemplate restTemplate = new RestTemplate();
			NomineeDto[] result = restTemplate.postForObject(AppConstant.URI_QUOTATION_NOMINEE_DETAILS, map,
					NomineeDto[].class);

			List<NomineeDto> nomineeDtos = new ArrayList<>();
			for (NomineeDto nomineeValsDto : result) {
				nomineeDtos.add(nomineeValsDto);
			}

			return nomineeDtos;
		} catch (Exception e) {

		}
		return null;
	}
	
	public List<MedicalRequirementsDto> getMedicals(Integer seqNo, Integer qId) {

		MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
		map.add("seqNo", seqNo);
		map.add("qId", qId);

		try {
			RestTemplate restTemplate = new RestTemplate();
			MedicalRequirementsDto[] result = restTemplate.postForObject(AppConstant.URI_QUOTATION_MEDICAL_REQUIREMENTS, map,
					MedicalRequirementsDto[].class);

			List<MedicalRequirementsDto> medicalDtos = new ArrayList<>();
			for (MedicalRequirementsDto medicalValsDto : result) {
				medicalDtos.add(medicalValsDto);
			}

			return medicalDtos;
		} catch (Exception e) {

		}
		return null;
	}
	
	public Integer getQuotationDetailFromSeqNo(Integer seqNo, Integer qId) {

		MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
		map.add("seqNo", seqNo);
		map.add("qId", qId);

		try {
			RestTemplate restTemplate = new RestTemplate();
			Integer result = restTemplate.postForObject(AppConstant.URI_QUOTATION_DETAILS_FROM_SEQNO, map,
					Integer.class);
			System.out.println(result);
			return result;
		} catch (Exception e) {

		}
		return null;
	}
	
	public String checkNicValidation(String nic,String gender,String age,String seqNo, String qId) {
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("nic", nic);
		map.add("gender", gender);
		map.add("age", age);
		map.add("seqNo", seqNo);
		map.add("qId", qId);
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			String result = restTemplate.postForObject(AppConstant.URI_CHECK_NIC_VALIDATION, map,
					String.class);
			System.out.println(result);
			return result;
		} catch (Exception e) {

		}
		return null;
	}

}
