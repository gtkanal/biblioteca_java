package br.biblioteca.livros.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.biblioteca.livros.beans.Livro;
import br.biblioteca.livros.repository.LivroRepository;

@Controller
public class Index {
	
	@Autowired
	private LivroRepository livroRepository;
	
	@RequestMapping("/")
	public ModelAndView home() {
		return new ModelAndView("index");
	}
	
	@GetMapping("/livros")
	public ModelAndView livros() {
		Iterable<Livro> livros = livroRepository.findAll();
		return new ModelAndView("livros", "livros", livros);
	}


}
