package br.edu.utfpr.gerenciadorbackend.service;

import java.util.List;

import br.edu.utfpr.gerenciadorbackend.model.Edicao;

public interface EdicaoService {
	public Edicao salvar(Edicao edicao);
	public boolean deletar(Edicao edicao);
	public List<Edicao> listarTodas();
	public Edicao buscarPorId(Integer id);
}
