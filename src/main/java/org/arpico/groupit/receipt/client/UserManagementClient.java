package org.arpico.groupit.receipt.client;

import org.arpico.groupit.receipt.dto.ResponseDto;
import org.arpico.groupit.receipt.util.AppConstant;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class UserManagementClient {

	public String getBranch(String userCode) {

		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("userName", userCode);
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseDto result = restTemplate.postForObject(AppConstant.URI_BRANCH_FIND, map,
					ResponseDto.class);
			return result.getMessage();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
