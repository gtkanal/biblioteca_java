package br.biblioteca.livros.repository;


import br.biblioteca.livros.beans.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository <Emprestimo, Long> { }
