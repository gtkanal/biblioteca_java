package br.biblioteca.livros.controladores;


import br.biblioteca.livros.beans.Autor;
import br.biblioteca.livros.beans.Emprestimo;
import br.biblioteca.livros.beans.Livro;
import br.biblioteca.livros.beans.User;
import br.biblioteca.livros.repository.EmprestimoRepository;
import br.biblioteca.livros.repository.LivroRepository;
import br.biblioteca.livros.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

@Controller
@RequestMapping("/emprestimo")
public class EmprestimoController {


    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ModelAndView livros() {

        ArrayList<Livro> livros = new ArrayList<Livro>();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User usuario = userService.findByUsername(currentPrincipalName);

        Iterable<Emprestimo> emprestimos = (Iterable<Emprestimo>) usuario.getEmprestimos();

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getDataDevolucao() == null){
                livros.add(emprestimo.getLivro());
            }
        }


        return new ModelAndView("emprestimos/list", "livros", livros);

    }

    @GetMapping("/novo")
    public ModelAndView createForm(@ModelAttribute Livro livro) {

        ArrayList<Livro> livros = new ArrayList<Livro>();

        Iterable<Livro> todosLivros = (Iterable<Livro>) livroRepository.findAll();


        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User usuario = userService.findByUsername(currentPrincipalName);

        Iterable<Emprestimo> emprestimos = (Iterable<Emprestimo>) usuario.getEmprestimos();

        for (Livro book : todosLivros){
            boolean showBook = true;
            for (Emprestimo emprestimo : emprestimos) {
                if (emprestimo.getDataDevolucao() == null){
                    if(book.getId() == emprestimo.getLivro().getId()){
                        showBook = false;
                    }
                }
            }
            if(showBook){
                livros.add(book);
            }

        }

        return new ModelAndView("emprestimos/novo", "livros", livros);

    }

    @GetMapping("/emprestar/{id}")
    public ModelAndView emprestar(@PathVariable("id") Long id) {

        Livro livro = this.livroRepository.findOne(id);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User usuario = userService.findByUsername(currentPrincipalName);

        Emprestimo emprestimo = new Emprestimo();

        long time = System.currentTimeMillis();
        java.sql.Timestamp timestamp = new java.sql.Timestamp(time);

        emprestimo.setLivro(livro);
        emprestimo.setUser(usuario);
        emprestimo.setDataEmprestimo(timestamp);
        emprestimo.setDataDevolucao(null);

        emprestimo = emprestimoRepository.save(emprestimo);

        return new ModelAndView("redirect:/emprestimo/list");
    }

    @GetMapping("/devolver/{id}")
    public ModelAndView devolver(@PathVariable("id") Long id) {

        Livro livro = this.livroRepository.findOne(id);

        Iterable<Emprestimo> emprestimos = livro.getEmprestimos();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        User usuario = userService.findByUsername(currentPrincipalName);

        for (Emprestimo emprestimo : emprestimos) {

            if (emprestimo.getUser().getId() == usuario.getId() && emprestimo.getDataDevolucao() == null){

                long time = System.currentTimeMillis();
                java.sql.Timestamp timestamp = new java.sql.Timestamp(time);

                emprestimo.setDataDevolucao(timestamp);

                emprestimo = emprestimoRepository.save(emprestimo);
            }

        }

        return new ModelAndView("redirect:/emprestimo/list");
    }

}
