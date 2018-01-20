package br.biblioteca.livros.services;


import br.biblioteca.livros.beans.Role;
import br.biblioteca.livros.beans.User;
import br.biblioteca.livros.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public void save(User user) {
        user.setPasssword(bCryptPasswordEncoder.encode(user.getPasssword()));
        user.getRoles().add(new Role("ROLE_BASIC"));
        userRepository.save(user);
    }


    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username);

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}