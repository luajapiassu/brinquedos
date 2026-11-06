package com.loja.brinquedos.controller;

import com.loja.brinquedos.model.Brinquedo;
import com.loja.brinquedos.model.ErrorResponse;
import com.loja.brinquedos.model.SuccessResponse;
import com.loja.brinquedos.service.BrinquedoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brinquedos")
public class BrinquedoController {

    @Autowired
    private BrinquedoService service;

    @GetMapping("/geral")
    public List<Brinquedo> listarBrinquedos(){
        return service.listarTodos();
    }

    @GetMapping("/individual/{id}")
    public Brinquedo buscarBrinquedo(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PostMapping("/criar")
    public Brinquedo criarBrinquedo(@RequestBody Brinquedo brinquedo) {
        return service.salvar(brinquedo);
    }

    @PutMapping("/atualizacao/{id}")
    public Brinquedo atualizarBrinquedo(@PathVariable Long id, @RequestBody Brinquedo brinquedo) {
        return service.atualizar(id, brinquedo);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Object> deletarBrinquedo(@PathVariable Long id) {
        String resposta = service.deletar(id);

        if (resposta.equals("Brinquedo deletado com sucesso!")) {
            // Retorna código 200 (OK)
            return ResponseEntity.ok(new SuccessResponse(resposta));
        } else {
            // Retorna código 404 (Not Found)
            return ResponseEntity.status(404).body(new ErrorResponse(resposta));
        }
    }
}