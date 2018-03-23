package br.edu.utfpr.gerenciadorbackend.service;

import java.util.List;

import br.edu.utfpr.gerenciadorbackend.model.Noticia;

public interface NoticiaService {
	Noticia salvar(Noticia noticia);
	List<Noticia> listarPorEdicao(Integer idEdicao);
	boolean deletar(Noticia noticia);
}
