package br.edu.utfpr.gerenciadorweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import br.edu.utfpr.gerenciadorweb.client.RestClientService;
import br.edu.utfpr.gerenciadorweb.enums.StatusEdicao;
import br.edu.utfpr.gerenciadorweb.model.Edicao;
import br.edu.utfpr.gerenciadorweb.service.EdicaoService;
import br.edu.utfpr.gerenciadorweb.util.UrlProperties;

@Service
public class EdicaoServiceImpl implements EdicaoService {

	@Autowired
	RestClientService client;
	@Autowired
	UrlProperties urls;

	@Override
	public Edicao salvar(Edicao edicao) {

		if (edicao.getStatus() == null) {
			edicao.setStatus(StatusEdicao.AGUARDANDO.getValue());
		}

		return (Edicao) client.consumeWithSingleObjectResult(edicao, urls.getEdicao_salvar(), HttpMethod.POST,
				new ParameterizedTypeReference<Edicao>() {
				});
	}

	@Override
	public boolean deletar(Edicao edicao) {
		return (boolean) client.consumeWithSingleObjectResult(edicao, urls.getEdicao_deletar(), HttpMethod.DELETE,
				new ParameterizedTypeReference<Boolean>() {
				});
	}

	@Override
	public Edicao publicar(Edicao edicao) {
		edicao.setStatus(StatusEdicao.PUBLICADO.getValue());

		return (Edicao) client.consumeWithSingleObjectResult(edicao, urls.getEdicao_salvar(), HttpMethod.POST,
				new ParameterizedTypeReference<Edicao>() {
				});
	}

	@Override
	public List<Edicao> listarTodas() {
		return client.consumeWithListResult(urls.getEdicao_listar(), HttpMethod.GET,
				new ParameterizedTypeReference<List<Edicao>>() {
				});
	}

	@Override
	public Edicao buscarPorId(Integer idEdicao) {
		return (Edicao) client.consumeWithSingleObjectResult(urls.getEdicao_buscar_por_id().replace("{id}", idEdicao.toString()),
				HttpMethod.GET, new ParameterizedTypeReference<Edicao>() {
				});
	}

}
