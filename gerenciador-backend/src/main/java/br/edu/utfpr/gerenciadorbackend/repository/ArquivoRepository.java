package br.edu.utfpr.gerenciadorbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.utfpr.gerenciadorbackend.model.Arquivo;

public interface ArquivoRepository extends JpaRepository<Arquivo, Integer> {

}
