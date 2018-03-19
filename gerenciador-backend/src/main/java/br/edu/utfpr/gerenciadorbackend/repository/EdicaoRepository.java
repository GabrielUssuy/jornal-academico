package br.edu.utfpr.gerenciadorbackend.repository;

import java.util.List;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.edu.utfpr.gerenciadorbackend.model.Edicao;

@Repository
@Lazy
public interface EdicaoRepository extends JpaRepository<Edicao, Integer>{

	@Query(" select e from Edicao e where e.status <> 'EXCLUIDO' ")
	public List<Edicao> listarTodas();
}
