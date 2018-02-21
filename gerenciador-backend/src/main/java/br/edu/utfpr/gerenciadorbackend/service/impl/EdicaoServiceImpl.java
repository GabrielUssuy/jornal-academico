package br.edu.utfpr.gerenciadorbackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.gerenciadorbackend.model.Edicao;
import br.edu.utfpr.gerenciadorbackend.repository.EdicaoRepository;
import br.edu.utfpr.gerenciadorbackend.service.EdicaoService;

@Service
public class EdicaoServiceImpl implements EdicaoService{
	
	@Autowired EdicaoRepository repository;

	@Override
	public Edicao salvar(Edicao edicao) {
		return repository.save(edicao);
	}

	@Override
	public List<Edicao> listarTodas() {
		return repository.findAll();
	}

	@Override
	public boolean deletar(Edicao edicao) {
		repository.delete(edicao);
		return true;
	}

	@Override
	public Edicao buscarPorId(Integer id) {
		return repository.getOne(id);
	}


}
