package org.arpico.groupit.receipt.client;

import org.arpico.groupit.receipt.dto.EmailDto;
import org.arpico.groupit.receipt.dto.EmailResponseDto;
import org.arpico.groupit.receipt.dto.SMSDto;
import org.arpico.groupit.receipt.dto.SMSResponseDto;
import org.arpico.groupit.receipt.util.AppConstant;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InfosysWSClient {

	public EmailResponseDto sendEmail(EmailDto emailDto) {
		
		//System.out.println(emailDto.toString());

		try {
			
			//System.out.println("try");
			
			RestTemplate restTemplate = new RestTemplate();
			EmailResponseDto result = restTemplate.postForObject(AppConstant.URI_SEND_EMAIL, emailDto,
					EmailResponseDto.class);
			
			//System.out.println(result.toString());
			return result;
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	public SMSResponseDto sendSMS(SMSDto smsDto) {
		
		//System.out.println(smsDto.toString());

		try {
			
			//System.out.println("try");
			
			RestTemplate restTemplate = new RestTemplate();
			SMSResponseDto result = restTemplate.postForObject(AppConstant.URI_SEND_SMS, smsDto,
					SMSResponseDto.class);
			
			//System.out.println(result.toString());
			return result;
		} catch (Exception e) {
			//System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
}
