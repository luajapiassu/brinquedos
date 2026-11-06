package com.loja.brinquedos.service;

import com.loja.brinquedos.model.Brinquedo;
import com.loja.brinquedos.repository.BrinquedoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrinquedoService {

    @Autowired
    private BrinquedoRepository repository;

    public List<Brinquedo> listarTodos() {
        return repository.findAll();
    }

    public Brinquedo buscarPorId(Long id){
        return repository.findById(id).orElse(null);
    }

    public Brinquedo salvar(Brinquedo brinquedo){
        return repository.save(brinquedo);
    }

    public Brinquedo atualizar(Long id, Brinquedo brinquedo){
        if (repository.existsById(id)){
            brinquedo.setId(id);
            return repository.save(brinquedo);
        }
        return null;
    }

    public String deletar(Long id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return "Brinquedo deletado com sucesso!";
        }
        return "Brinquedo não encontrado.";
    }
}