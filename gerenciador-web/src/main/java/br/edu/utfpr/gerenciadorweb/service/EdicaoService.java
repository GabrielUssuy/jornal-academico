package br.edu.utfpr.gerenciadorweb.service;

import java.util.List;

import br.edu.utfpr.gerenciadorweb.model.Edicao;

public interface EdicaoService {
	Edicao salvar(Edicao edicao);
	boolean deletar(Edicao edicao);
	Edicao publicar(Edicao edicao);
	List<Edicao> listarTodas();
	Edicao buscarPorId(Integer idEdicao);
}
