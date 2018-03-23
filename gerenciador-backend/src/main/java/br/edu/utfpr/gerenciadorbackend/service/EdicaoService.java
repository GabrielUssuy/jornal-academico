package br.edu.utfpr.gerenciadorbackend.service;

import java.util.List;

import br.edu.utfpr.gerenciadorbackend.model.Edicao;

public interface EdicaoService {
	Edicao salvar(Edicao edicao);
	boolean deletar(Edicao edicao);
	List<Edicao> listarTodas();
	Edicao buscarPorId(Integer id);
}
