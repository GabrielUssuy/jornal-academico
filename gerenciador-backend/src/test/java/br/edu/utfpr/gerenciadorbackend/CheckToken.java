package br.edu.utfpr.gerenciadorbackend;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class CheckToken {
	public static void main(String[] args) {

		String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE1MjEwOTU3NTAsInVzZXJfbmFtZSI6ImFkbWluIiwiYXV0aG9yaXRpZXMiOlsiUk9MRV9ST0xFIl0sImp0aSI6IjRkZGExZTE3LTAwNWMtNDQ5Yy1iNGNlLWFmNjg1M2FkZTAyNSIsImNsaWVudF9pZCI6ImNsaWVudCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.3pQ3qxBHyJeL5pxqAmqrn3dBUTVOcZd0dvR24PuMorY";

		try {
			RestTemplate rest = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://localhost:8090/auth/oauth/check_token")
					.queryParam("token", token);
			HttpEntity<String> entity = new HttpEntity<>(headers);
			HttpStatus ok = rest.exchange(builder.build().encode().toUri(), HttpMethod.GET, new HttpEntity<String>(createHeaders("client", "secret")), String.class).getStatusCode();
			System.out.println(ok);
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}

	private static HttpHeaders createHeaders(String username, String password) {
		return new HttpHeaders() {
			{
				String auth = username + ":" + password;
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
				set("Authorization", authHeader);
			}
		};
	}
}
