package br.edu.utfpr.gerenciadorweb.service;

import java.util.List;

import br.edu.utfpr.gerenciadorweb.model.Edicao;

public interface EdicaoService {
	public Edicao salvar(Edicao edicao);
	public boolean deletar(Edicao edicao);
	public Edicao publicar(Edicao edicao);
	public List<Edicao> listarTodas();
}
