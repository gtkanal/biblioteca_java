package br.biblioteca.livros.controladores;

import br.biblioteca.livros.beans.Usuario;
import br.biblioteca.livros.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/usuarios")

public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/list")
    public ModelAndView usuarios() {

        Iterable<Usuario> usuarios = usuarioRepository.findAll();
        return new ModelAndView("usuarios/list", "usuarios", usuarios);

    }

    @GetMapping("/novo")
    public String createForm(@ModelAttribute Usuario usuario) {
        return "usuarios/form";
    }

    @PostMapping(params = "form")
    public ModelAndView create(@ModelAttribute("usuario") @Valid Usuario usuario, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("usuarios/form");
        }

        usuario = usuarioRepository.save(usuario);

        return new ModelAndView("redirect:/usuarios/list");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Long id) {
        Usuario usuario = this.usuarioRepository.findOne(id);
        return new ModelAndView("usuarios/form", "usuario", usuario);
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Usuario usuario = this.usuarioRepository.findOne(id);
        this.usuarioRepository.delete(usuario);
        return new ModelAndView("redirect:/usuarios/list");
    }

}
