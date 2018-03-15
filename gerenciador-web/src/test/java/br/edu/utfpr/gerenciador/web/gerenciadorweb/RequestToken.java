package br.edu.utfpr.gerenciador.web.gerenciadorweb;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class RequestToken {

	public static void main(String[] args) {
		
		LinkedMultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();
		mvm.add("client", "client");
		mvm.add("username", "admin");
		mvm.add("password", "admin");
		mvm.add("grant_type", "password");
		
		try {
			ResponseEntity<Token> result = null;
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.add("Authorization", "Basic Y2xpZW50OnNlY3JldA==");
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/oauth/token");
			HttpEntity<Object> entity = new HttpEntity<>(mvm,headers);
			result = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, entity, Token.class);
			System.out.println(result.getBody().getAccess_token());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
