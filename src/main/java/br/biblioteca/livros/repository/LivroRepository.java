package br.biblioteca.livros.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.biblioteca.livros.beans.Livro;

public interface LivroRepository  extends JpaRepository <Livro, Long> {
    Livro findByNome(String nome);

}
