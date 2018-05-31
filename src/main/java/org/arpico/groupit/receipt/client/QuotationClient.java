package org.arpico.groupit.receipt.client;

import org.arpico.groupit.receipt.dto.ViewQuotationDto;
import org.arpico.groupit.receipt.util.AppConstant;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class QuotationClient {

	public ViewQuotationDto getQuotation(Integer quId, Integer qId) {
		
		MultiValueMap<String, Integer> map = new LinkedMultiValueMap<String, Integer>();
		map.add("qdId", quId);
		map.add("qId", qId);
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			ViewQuotationDto result = restTemplate.postForObject(AppConstant.URI, map, ViewQuotationDto.class);
			return result;
		}catch (Exception e) {

		}
		return null;
	}
	
}
