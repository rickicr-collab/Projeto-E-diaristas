package br.com.rickicollab.ediaristas.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.rickicollab.ediaristas.core.exceptions.ValidacaoException;
import br.com.rickicollab.ediaristas.web.dto.FlashMessage;
import br.com.rickicollab.ediaristas.web.dto.UsuarioCadastroForm;
import br.com.rickicollab.ediaristas.web.dto.UsuarioEdicaoForm;
import br.com.rickicollab.ediaristas.web.services.WebUsuarioService;
import jakarta.validation.Valid;


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
    
    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable long id){
        var mav = new ModelAndView("admin/usuario/edicao-form");
        mav.addObject("edicaoForm", webUsuarioService.buscarFormPorId(id));
        return  mav;
       

    }


    @PostMapping("/cadastrar")
    public String cadastrar(@Valid @ModelAttribute("cadastroForm") UsuarioCadastroForm cadastroForm, BindingResult result, RedirectAttributes attrs){
        if(result.hasErrors()){
            return "admin/usuario/cadastro-form";
        }
        try {
            webUsuarioService.cadastrar(cadastroForm);
            attrs.addFlashAttribute("alert", new FlashMessage("alert-sucess", "Usuario cadastrado com sucesso!"));
        } catch (ValidacaoException e) {
            result.addError(e.getFieldError());
            return "admin/usuario/cadastro-form";
        }
        return "redirect:/admin/usuarios";
    }



    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable long id, RedirectAttributes attrs){
        webUsuarioService.excluirPorId(id);
        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Usuario excluido com sucesso ! "));
        return "redirect:/admin/usuarios";
    }


    @PostMapping("/{id}/editar")
    public String editar(@PathVariable long id, @Valid @ModelAttribute("edicaoForm") UsuarioEdicaoForm edicaoForm, BindingResult result, RedirectAttributes attrs){
        if(result.hasErrors()){
            return"admin/usuario/edicao-form";
        }
        try {
            webUsuarioService.editar(edicaoForm, id);
            attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Usuario editado com sucesso !"));
        } catch (ValidacaoException e) {
           result.addError(e.getFieldError());
           return "admin/usuario/edicao-form";
        }
        return "redirect:/admin/usuarios";
    }
    

}
