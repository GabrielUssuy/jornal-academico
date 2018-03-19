package br.edu.utfpr.gerenciadorweb.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.utfpr.gerenciadorweb.model.Token;
import br.edu.utfpr.gerenciadorweb.service.TokenService;
import br.edu.utfpr.gerenciadorweb.util.UrlProperties;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired private UrlProperties urls;
	
	@Override
	public Token getToken() {
		
		LinkedMultiValueMap<String, Object> mvm = new LinkedMultiValueMap<String, Object>();
		mvm.add("client", "client");
		mvm.add("username", "admin");
		mvm.add("password", "admin");
		mvm.add("grant_type", "password");
		
		ResponseEntity<Token> result = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			headers.add("Authorization", "Basic Y2xpZW50OnNlY3JldA==");
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(urls.getToken_solicitar());
			HttpEntity<Object> entity = new HttpEntity<>(mvm,headers);
			result = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.POST, entity, Token.class);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
		return result.getBody();
	}

}
