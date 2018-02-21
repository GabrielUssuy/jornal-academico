package br.edu.utfpr.gerenciadorweb.service;

import java.util.List;

import br.edu.utfpr.gerenciadorweb.model.Noticia;

public interface NoticiaService {
	public Integer salvar(Noticia noticia);
	public List<Noticia> listarPorEdicao(Integer idEdicao);
	public boolean deletar(Noticia noticia);
}
