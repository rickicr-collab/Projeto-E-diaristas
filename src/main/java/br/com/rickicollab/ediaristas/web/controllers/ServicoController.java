package br.com.rickicollab.ediaristas.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.rickicollab.ediaristas.core.enums.Icone;
import br.com.rickicollab.ediaristas.core.models.Servico;
import br.com.rickicollab.ediaristas.core.repositories.ServicoRepository;

@Controller
@RequestMapping("/admin/servicos")
public class ServicoController {

   
    private ServicoRepository repository;

    public ServicoController(ServicoRepository repository){
        this.repository = repository;
    }

    @GetMapping
    public ModelAndView buscarTodos(){
        var mav = new ModelAndView("admin/servico/lista");
        mav.addObject("servicos", repository.findAll());
        return mav;
    }

    @GetMapping("cadastrar")
    public ModelAndView cadastrar(){
        var modelAndView = new ModelAndView("admin/servico/form");
        modelAndView.addObject("servico", new Servico());
        return modelAndView;

    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable long id){
        var mav = new ModelAndView("admin/servico/form");
        mav.addObject("servico", repository.getReferenceById(id));
        return mav;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(Servico servico){
        repository.save(servico);
        return "redirect:/admin/servicos";
    }
    
    @GetMapping("{id}/excluir")
    public String excluir(@PathVariable long id){
        repository.deleteById(id);
        return "redirect:/admin/servicos";
    }

    @PostMapping("/{id}/editar")
    public String editar(@PathVariable long id, Servico servico){
        repository.save(servico);
        return "redirect:/admin/servicos";
    }


    @ModelAttribute("icones")
    public Icone[] getIcone(){
        return Icone.values();
    }

}
