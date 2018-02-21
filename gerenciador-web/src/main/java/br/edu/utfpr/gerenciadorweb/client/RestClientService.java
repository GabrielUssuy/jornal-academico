package br.edu.utfpr.gerenciadorweb.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

public interface RestClientService {
	
	public <T> List<T> consumeWithListResult(String url, HttpMethod httpMethod, @SuppressWarnings("rawtypes") ParameterizedTypeReference typeReference);
	public Object consumeWithSingleObjectResult(String url, HttpMethod httpMethod, @SuppressWarnings("rawtypes") ParameterizedTypeReference typeReference);
	public Object consumeWithSingleObjectResult(Object object, String url, HttpMethod httpMethod, @SuppressWarnings("rawtypes") ParameterizedTypeReference typeReference);
	

}
