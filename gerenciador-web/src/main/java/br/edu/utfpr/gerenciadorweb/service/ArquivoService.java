package br.edu.utfpr.gerenciadorweb.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.edu.utfpr.gerenciadorweb.model.Arquivo;

public interface ArquivoService {
	
	Arquivo salvar(MultipartFile mfile, String tipo, Integer idNoticia) throws IllegalStateException, IOException;
	List<Arquivo> listarPorNoticia(Integer idNoticia) throws Exception;
	boolean deletar(Arquivo arquivo) throws Exception;
}
