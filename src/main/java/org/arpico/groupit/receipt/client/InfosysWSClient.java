package org.arpico.groupit.receipt.client;

import org.arpico.groupit.receipt.dto.EmailDto;
import org.arpico.groupit.receipt.dto.EmailResponseDto;
import org.arpico.groupit.receipt.util.AppConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InfosysWSClient {

	public EmailResponseDto sendEmail(EmailDto emailDto) {

		try {
			RestTemplate restTemplate = new RestTemplate();
			EmailResponseDto result = restTemplate.postForObject(AppConstant.URI_SEND_EMAIL, emailDto,
					EmailResponseDto.class);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
