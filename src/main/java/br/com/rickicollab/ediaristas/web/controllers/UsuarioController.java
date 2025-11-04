package br.com.rickicollab.ediaristas.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.rickicollab.ediaristas.web.dto.UsuarioCadastroForm;
import br.com.rickicollab.ediaristas.web.services.WebUsuarioService;

@Controller
@RequestMapping("/admin/usuarios")
public class UsuarioController {

    @Autowired
    private WebUsuarioService webUsuarioService;

    @GetMapping
    public ModelAndView buscarTodos(){
        var mav = new ModelAndView("admin/usuario/lista");
        mav.addObject("usuarios", webUsuarioService.buscarTodos());
        return mav;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(){
        var mav = new ModelAndView("admin/usuario/cadastro-form");
        mav.addObject("cadastroForm", new UsuarioCadastroForm());
        return mav;
    }

}
