package br.biblioteca.livros.repository;


import br.biblioteca.livros.beans.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository <Usuario, Long> { }
