package br.edu.utfpr.gerenciadorbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.edu.utfpr.gerenciadorbackend.model.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, Integer> {

	@Query("select a from Arquivo a where a.noticia.id = ?1 and a.status = 'ATIVO'")
	List<Arquivo> listarPorNoticia(Integer idNoticia);
	
}
