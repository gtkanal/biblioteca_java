package br.biblioteca.livros.controladores;

import br.biblioteca.livros.beans.Autor;
import br.biblioteca.livros.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/autores")

public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @GetMapping("/list")
    public ModelAndView autores() {

        Iterable<Autor> autores = autorRepository.findAll();
        return new ModelAndView("autores/list", "autores", autores);

    }

    @GetMapping("/novo")
    public String createForm(@ModelAttribute Autor livro) {
        return "autores/form";
    }

    @PostMapping(params = "form")
    public ModelAndView create(@ModelAttribute("autor") @Valid Autor autor, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("autores/form");
        }

        autor = autorRepository.save(autor);
        return new ModelAndView("redirect:/autores/list");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Long id) {
        Autor autor = this.autorRepository.findOne(id);
        return new ModelAndView("autores/form", "autor", autor);
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Autor autor = this.autorRepository.findOne(id);
        this.autorRepository.delete(autor);
        return new ModelAndView("redirect:/autores/list");
    }

}
