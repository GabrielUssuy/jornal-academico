package br.edu.utfpr.gerenciadorbackend.service;

import java.util.List;

import br.edu.utfpr.gerenciadorbackend.model.Arquivo;

public interface ArquivoService {
	Arquivo salvar(Arquivo arquivo);
	List<Arquivo> listarPorNoticia(Integer idNoticia);
}
