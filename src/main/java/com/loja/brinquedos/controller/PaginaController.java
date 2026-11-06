package com.loja.brinquedos.controller;

import com.loja.brinquedos.model.Brinquedo;
import com.loja.brinquedos.model.TipoBrinquedo;
import com.loja.brinquedos.service.BrinquedoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class PaginaController {

    @Autowired
    private BrinquedoService service;

    @GetMapping("/")
    public String landing() {
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/painel")
    public String painel(Model model) {
        model.addAttribute("brinquedos", service.listarTodos());
        model.addAttribute("brinquedo", new Brinquedo());
        model.addAttribute("tipos", TipoBrinquedo.values());
        return "painel";
    }

    @PostMapping("/painel/admin/brinquedos")
    public String cadastrarBrinquedo(@ModelAttribute Brinquedo brinquedo, RedirectAttributes redirect) {
        brinquedo.setId(null);
        service.salvar(brinquedo);
        redirect.addFlashAttribute("mensagem", "Brinquedo cadastrado com sucesso!");
        return "redirect:/painel";
    }

    @PostMapping("/painel/admin/brinquedos/{id}/excluir")
    public String excluirBrinquedo(@PathVariable Long id, RedirectAttributes redirect) {
        String resposta = service.deletar(id);
        redirect.addFlashAttribute("mensagem", resposta);
        return "redirect:/painel";
    }
}
