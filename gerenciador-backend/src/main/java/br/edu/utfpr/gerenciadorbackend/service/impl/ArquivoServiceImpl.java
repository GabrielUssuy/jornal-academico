package br.edu.utfpr.gerenciadorbackend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.gerenciadorbackend.model.Arquivo;
import br.edu.utfpr.gerenciadorbackend.repository.ArquivoRepository;
import br.edu.utfpr.gerenciadorbackend.service.ArquivoService;

@Service
public class ArquivoServiceImpl implements ArquivoService {

	@Autowired ArquivoRepository repository;
	
	@Override
	public Arquivo salvar(Arquivo arquivo) {
		return repository.save(arquivo);
	}

}
