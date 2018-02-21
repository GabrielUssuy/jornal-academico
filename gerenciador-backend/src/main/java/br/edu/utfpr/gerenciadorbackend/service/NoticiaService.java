package br.edu.utfpr.gerenciadorbackend.service;

import java.util.List;

import br.edu.utfpr.gerenciadorbackend.model.Noticia;

public interface NoticiaService {
	public Noticia salvar(Noticia noticia);
	public List<Noticia> listarPorEdicao(Integer idEdicao);
	public boolean deletar(Noticia noticia);
}
