package br.biblioteca.livros.repository;


import br.biblioteca.livros.beans.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository <Cliente, Long> { }
