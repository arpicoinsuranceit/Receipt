package org.arpico.groupit.receipt.client;

import org.arpico.groupit.receipt.dto.EditQuotation;
import org.arpico.groupit.receipt.util.AppConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class QuotationClient {

	public EditQuotation getQuotation(Integer quId) {
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			EditQuotation result = restTemplate.postForObject(AppConstant.URI, quId, EditQuotation.class);
			return result;
		}catch (Exception e) {

		}
		return null;
	}
	
}
