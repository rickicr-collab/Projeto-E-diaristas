package br.com.rickicollab.ediaristas.web.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.rickicollab.ediaristas.core.enums.Icone;
import br.com.rickicollab.ediaristas.core.repositories.ServicoRepository;
import br.com.rickicollab.ediaristas.web.dto.ServicoForm;
import br.com.rickicollab.ediaristas.web.mappers.WebServicoMapper;
import jakarta.validation.Valid;

@Controller 
@RequestMapping("/admin/servicos")
public class ServicoController {

   
    private ServicoRepository repository;

    private WebServicoMapper mapper;

    public ServicoController(ServicoRepository repository, WebServicoMapper mapper){
        this.repository = repository;
        this.mapper = mapper;
    }

    @GetMapping
    public ModelAndView buscarTodos(){
        var mav = new ModelAndView("admin/servico/lista");
        mav.addObject("servicos", repository.findAll());
        return mav;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar(){
        var modelAndView = new ModelAndView("admin/servico/form");
        modelAndView.addObject("form", new ServicoForm());
        return modelAndView;

    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable long id){
        var mav = new ModelAndView("admin/servico/form");
        var servico = repository.getReferenceById(id);
        var form = mapper.toForm(servico);
        mav.addObject("form", form);
        return mav;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid @ModelAttribute("form") ServicoForm form, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("icone", Icone.values());
            return "admin/servico/form";
        }
        var servico = mapper.toModel(form);
        repository.save(servico);
        return "redirect:/admin/servicos";
    }
    
    @GetMapping("{id}/excluir")
    public String excluir(@PathVariable long id){
        repository.deleteById(id);
        return "redirect:/admin/servicos";
    }

    @PostMapping("/{id}/editar")
    public String editar(@PathVariable long id, @Valid @ModelAttribute("form") ServicoForm form, BindingResult result, Model model){
        if(result.hasErrors()){
            model.addAttribute("form", form);
            model.addAttribute("icone", Icone.values());
            return "admin/servico/form";
        }
        var servico = mapper.toModel(form);
        servico.setId(id);
        repository.save(servico);
        return "redirect:/admin/servicos";
    }


    @ModelAttribute("icones")
    public Icone[] getIcone(){
        return Icone.values();
    }

}
