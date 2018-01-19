package br.biblioteca.livros.controladores;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;
import br.biblioteca.livros.beans.Livro;
@Controller
public class UserController {

    @GetMapping("/login")
    public ModelAndView login(@ModelAttribute Livro livro) {

        return new ModelAndView("login");

    }



    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {

        HttpSession session= request.getSession(false);
        SecurityContextHolder.clearContext();
        if(session != null) {
            session.invalidate();
        }
        return "redirect:login";
    }


}
