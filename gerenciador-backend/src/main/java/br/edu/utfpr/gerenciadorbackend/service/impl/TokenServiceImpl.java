package br.edu.utfpr.gerenciadorbackend.service.impl;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.utfpr.gerenciadorbackend.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService{

	@Override
	public boolean validarToken(String token) {
		try {
			RestTemplate rest = new RestTemplate();
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8080/oauth/check_token")
					.queryParam("token", token);
			HttpEntity<String> entity = new HttpEntity<String>(createHeaders("client", "secret"));
			HttpStatus statusCode = rest.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class).getStatusCode();
			
			if(HttpStatus.OK.equals(statusCode)) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return false;
	}
	
	private static HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			private static final long serialVersionUID = 1L;
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}

}
