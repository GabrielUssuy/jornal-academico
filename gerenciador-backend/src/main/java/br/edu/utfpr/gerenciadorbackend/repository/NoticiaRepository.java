package br.edu.utfpr.gerenciadorbackend.repository;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.utfpr.gerenciadorbackend.model.Noticia;

@Repository
@Lazy
public interface NoticiaRepository extends JpaRepository<Noticia, Integer> {
	
	@Query(" select n from Noticia n where n.edicao.id = ?1 and n.status <> 'EXCLUIDO' ")
	public List<Noticia> listarPorEdicao(Integer idEdicao);

}
