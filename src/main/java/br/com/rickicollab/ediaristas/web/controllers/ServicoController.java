package br.com.rickicollab.ediaristas.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.rickicollab.ediaristas.core.enums.Icone;
import br.com.rickicollab.ediaristas.web.dto.FlashMessage;
import br.com.rickicollab.ediaristas.web.dto.ServicoForm;
import br.com.rickicollab.ediaristas.web.services.WebServicoService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/servicos")
public class ServicoController {

    @Autowired
    private WebServicoService webServicoService;

    @GetMapping
    public ModelAndView buscarTodos() {
        var mav = new ModelAndView("admin/servico/lista");
        mav.addObject("servicos", webServicoService.buscarTodos());
        return mav;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        var modelAndView = new ModelAndView("admin/servico/form");
        modelAndView.addObject("form", new ServicoForm());
        return modelAndView;

    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable long id) {
        var mav = new ModelAndView("admin/servico/form");
        mav.addObject("form", webServicoService.buscarPorId(id));
        return mav;
    }

    @PostMapping("/cadastrar")
    public String cadastrar(@Valid @ModelAttribute("form") ServicoForm form, BindingResult result, Model model, RedirectAttributes attrs) {
        if (result.hasErrors()) {
            model.addAttribute("icone", Icone.values());
            return "admin/servico/form";
        }
        webServicoService.cadastrar(form);
        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Serviço cadastrado com sucesso!"));
        return "redirect:/admin/servicos";
    }

    @GetMapping("{id}/excluir")
    public String excluir(@PathVariable long id, RedirectAttributes attrs) {
        webServicoService.excluirPorId(id);
        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Serviço excluido com sucesso!"));
        return "redirect:/admin/servicos";
    }

    @PostMapping("/{id}/editar")
    public String editar(@PathVariable long id, @Valid @ModelAttribute("form") ServicoForm form, BindingResult result,
            Model model, RedirectAttributes attrs) {
        if (result.hasErrors()) {
            model.addAttribute("form", form);
            model.addAttribute("icone", Icone.values());
            return "admin/servico/form";
        }
        webServicoService.editar(form, id);
        attrs.addFlashAttribute("alert", new FlashMessage("alert-success", "Serviço Atualizado com sucesso!"));
        return "redirect:/admin/servicos";
    }

    @ModelAttribute("icones")
    public Icone[] getIcone() {
        return Icone.values();
    }

}
