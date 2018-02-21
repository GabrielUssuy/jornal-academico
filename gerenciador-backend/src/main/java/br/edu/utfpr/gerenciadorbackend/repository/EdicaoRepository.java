package br.edu.utfpr.gerenciadorbackend.repository;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.utfpr.gerenciadorbackend.model.Edicao;

@Repository
@Lazy
public interface EdicaoRepository extends JpaRepository<Edicao, Integer>{

}
