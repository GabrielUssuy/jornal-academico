package br.edu.utfpr.gerenciadorbackend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.utfpr.gerenciadorbackend.model.Arquivo;
import br.edu.utfpr.gerenciadorbackend.repository.ArquivoRepository;
import br.edu.utfpr.gerenciadorbackend.service.ArquivoService;
import br.edu.utfpr.gerenciadorbackend.util.Constantes;

@Service
public class ArquivoServiceImpl implements ArquivoService {

	@Autowired ArquivoRepository repository;
	
	@Override
	public Arquivo salvar(Arquivo arquivo) {
		if(arquivo.getStatus()==null) {
			arquivo.setStatus(Constantes.STATUS_ATIVO);
		}
		return repository.save(arquivo);
	}

	@Override
	public List<Arquivo> listarPorNoticia(Integer idNoticia) {
		return repository.listarPorNoticia(idNoticia);
	}

	@Override
	public boolean deletar(Arquivo arquivo) {
		arquivo = repository.findOne(arquivo.getId());
		arquivo.setStatus(Constantes.STATUS_EXCLUIDO);
		repository.save(arquivo);
		return true;
	}

}
