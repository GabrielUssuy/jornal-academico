package br.edu.utfpr.gerenciadorbackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.gerenciadorbackend.model.Noticia;
import br.edu.utfpr.gerenciadorbackend.repository.NoticiaRepository;
import br.edu.utfpr.gerenciadorbackend.service.EdicaoService;
import br.edu.utfpr.gerenciadorbackend.service.NoticiaService;
import br.edu.utfpr.gerenciadorbackend.util.Constantes;

@Service
public class NoticiaServiceImpl implements NoticiaService{
	
	@Autowired NoticiaRepository repository;
	@Autowired EdicaoService edicaoService;

	@Override
	public Noticia salvar(Noticia noticia) {
		noticia.setEdicao(edicaoService.buscarPorId(noticia.getEdicao().getId()));
		if(noticia.getStatus() == null) {
			noticia.setStatus(Constantes.STATUS_ATIVO);
		}
		return repository.save(noticia);
	}

	@Override
	public List<Noticia> listarPorEdicao(Integer idEdicao) {
		return repository.listarPorEdicao(idEdicao);
	}

	@Override
	public boolean deletar(Noticia noticia) {
		noticia = repository.findOne(noticia.getId());
		noticia.setStatus(Constantes.STATUS_EXCLUIDO);
		repository.save(noticia);
		return true;
	}
	
}
