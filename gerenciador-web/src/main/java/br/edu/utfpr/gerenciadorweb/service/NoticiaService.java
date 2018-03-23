package br.edu.utfpr.gerenciadorweb.service;

import java.util.List;

import br.edu.utfpr.gerenciadorweb.model.Noticia;

public interface NoticiaService {
	Integer salvar(Noticia noticia);
	List<Noticia> listarPorEdicao(Integer idEdicao);
	boolean deletar(Noticia noticia);
}
