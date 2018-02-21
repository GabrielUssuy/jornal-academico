package br.edu.utfpr.gerenciadorweb.client.impl;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import br.edu.utfpr.gerenciadorweb.client.RestClientService;

@Service
public class RestClientServiceImpl implements RestClientService{

	@Override
	@SuppressWarnings("unchecked")
	public <T> List<T> consumeWithListResult(String url, HttpMethod httpMethod, @SuppressWarnings("rawtypes") ParameterizedTypeReference typeReference) {
		ResponseEntity<List<T>> result = null;
		try {
			RestTemplate rest = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
			HttpEntity<String> entity = new HttpEntity<>(headers);
			result = rest.exchange(builder.build().encode().toUri(), httpMethod, entity, typeReference);
			return result.getBody();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public Object consumeWithSingleObjectResult(Object object, String url, HttpMethod httpMethod, @SuppressWarnings("rawtypes") ParameterizedTypeReference typeReference) {
		ResponseEntity<Object> result = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
			HttpEntity<Object> entity = new HttpEntity<>(object, headers);
			result = restTemplate.exchange(builder.build().encode().toUri(), httpMethod, entity, typeReference);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result.getBody();
	}


	@Override
	@SuppressWarnings("unchecked")
	public Object consumeWithSingleObjectResult(String url, HttpMethod httpMethod, @SuppressWarnings("rawtypes") ParameterizedTypeReference typeReference) {
		ResponseEntity<Object> result = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
			HttpEntity<String> entity = new HttpEntity<>(headers);
			result = restTemplate.exchange(builder.build().encode().toUri(), httpMethod, entity, typeReference);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return result.getBody();
	}

}
