package br.edu.utfpr.gerenciadorweb.service.impl;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationHome;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.edu.utfpr.gerenciadorweb.client.RestClientService;
import br.edu.utfpr.gerenciadorweb.model.Arquivo;
import br.edu.utfpr.gerenciadorweb.model.Noticia;
import br.edu.utfpr.gerenciadorweb.service.ArquivoService;
import br.edu.utfpr.gerenciadorweb.util.UrlProperties;

@Service
public class ArquivoServiceImpl implements ArquivoService {

	@Autowired
	RestClientService restClient;
	@Autowired
	UrlProperties urls;

	@Override
	public Arquivo salvar(MultipartFile mfile, String tipo, Integer idNoticia) throws IllegalStateException, IOException {
		
		String dirRaiz = new ApplicationHome(this.getClass()).getDir().toString();
		String novoNome = LocalDateTime.now().toString().replaceAll("[^0-9]", "") + getExtensao(mfile.getOriginalFilename());
		String pathDestino = dirRaiz + "\\arquivos\\" + novoNome ;

		File file = new File(pathDestino);
		mfile.transferTo(file);

		Arquivo arquivo = new Arquivo();
		arquivo.setCaminho(pathDestino);
		arquivo.setNome(novoNome);
		arquivo.setNomeOriginal(mfile.getOriginalFilename());
		arquivo.setTipo(tipo);
		arquivo.setExtensao(getExtensao(file.getName()));
		arquivo.setNoticia(new Noticia());
		arquivo.getNoticia().setId(idNoticia);
		
		return (Arquivo) restClient.consumeWithSingleObjectResult(arquivo, urls.getArquivo_salvar(), HttpMethod.POST,
				new ParameterizedTypeReference<Arquivo>() {
				});

	}

	private String getExtensao(String nomeArquivo) {
		return nomeArquivo.substring(nomeArquivo.lastIndexOf("."), nomeArquivo.length());
	}

}
