package br.biblioteca.livros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.biblioteca.livros.beans.User;

public interface UserRepository  extends JpaRepository <User, Long> {
    User findByUsername(String username);
}



