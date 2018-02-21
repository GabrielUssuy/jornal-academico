package br.edu.utfpr.gerenciadorweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import br.edu.utfpr.gerenciadorweb.client.RestClientService;
import br.edu.utfpr.gerenciadorweb.enums.TipoNoticia;
import br.edu.utfpr.gerenciadorweb.model.Noticia;
import br.edu.utfpr.gerenciadorweb.service.NoticiaService;
import br.edu.utfpr.gerenciadorweb.util.UrlProperties;

@Service
public class NoticiaServiceImpl implements NoticiaService {

	@Autowired
	private RestClientService restClienteService;
	@Autowired
	private UrlProperties urls;

	@Override
	public Integer salvar(Noticia noticia) {
		return (Integer) restClienteService.consumeWithSingleObjectResult(noticia, urls.getNoticia_salvar(),
				HttpMethod.POST, new ParameterizedTypeReference<Integer>() {
				});
	}

	@Override
	public List<Noticia> listarPorEdicao(Integer idEdicao) {
		return restClienteService.consumeWithListResult(
				urls.getNoticia_listar().replace("{id}", idEdicao.toString()), HttpMethod.GET,
				new ParameterizedTypeReference<List<Noticia>>() {
				});
	}

	@Override
	public boolean deletar(Noticia noticia) {
		return (boolean) restClienteService.consumeWithSingleObjectResult(noticia, urls.getNoticia_deletar(), 
				HttpMethod.DELETE, new ParameterizedTypeReference<Boolean>() {});
	}

}
