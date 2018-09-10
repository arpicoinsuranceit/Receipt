package org.arpico.groupit.receipt.security;

import java.io.IOException;
import java.util.HashMap;

import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JwtDecoder {
	public String generate(String token) {
		String usercode = null;

		Jwt jwtToken = JwtHelper.decode(token);
		String claims = jwtToken.getClaims();
		try {
			HashMap claimsMap = new ObjectMapper().readValue(claims, HashMap.class);
			usercode = claimsMap.get("userCode").toString();
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return usercode;
	}
}
