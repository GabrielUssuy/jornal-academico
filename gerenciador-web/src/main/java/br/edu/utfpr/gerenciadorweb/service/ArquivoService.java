package br.edu.utfpr.gerenciadorweb.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import br.edu.utfpr.gerenciadorweb.model.Arquivo;

public interface ArquivoService {
	
	public Arquivo salvar(MultipartFile mfile, String tipo, Integer idNoticia) throws IllegalStateException, IOException;

}
