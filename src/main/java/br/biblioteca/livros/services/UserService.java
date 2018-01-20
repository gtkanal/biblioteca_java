package br.biblioteca.livros.services;

import br.biblioteca.livros.beans.User;

import java.util.List;

public interface UserService {
	
	void save(User user);

	User findByUsername(String username);

	List<User> findAll();
	
}