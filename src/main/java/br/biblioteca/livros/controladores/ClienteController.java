package br.biblioteca.livros.controladores;

import br.biblioteca.livros.beans.Cliente;
import br.biblioteca.livros.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/clientes")

public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping("/list")
    public ModelAndView clientes() {

        Iterable<Cliente> clientes = clienteRepository.findAll();
        return new ModelAndView("clientes/list", "clientes", clientes);

    }

    @GetMapping("/novo")
    public String createForm(@ModelAttribute Cliente cliente) {
        return "clientes/form";
    }

    @PostMapping(params = "form")
    public ModelAndView create(@ModelAttribute("Cliente") @Valid Cliente cliente, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return new ModelAndView("clientes/form");
        }

        cliente = clienteRepository.save(cliente);

        return new ModelAndView("redirect:/clientes/list");
    }

    @GetMapping("/alterar/{id}")
    public ModelAndView alterar(@PathVariable("id") Long id) {
        Cliente cliente = this.clienteRepository.findOne(id);
        return new ModelAndView("clientes/form", "cliente", cliente);
    }

    @GetMapping("/excluir/{id}")
    public ModelAndView excluir(@PathVariable("id") Long id) {
        Cliente cliente = this.clienteRepository.findOne(id);
        this.clienteRepository.delete(cliente);
        return new ModelAndView("redirect:/clientes/list");
    }

}
