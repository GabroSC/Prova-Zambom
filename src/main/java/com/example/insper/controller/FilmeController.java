package com.example.insper.controller;

import com.example.insper.entity.Filme;
import com.example.insper.service.FilmeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filmes")
public class FilmeController {

    private final FilmeService service;

    public FilmeController(FilmeService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Filme> cadastrar(@RequestBody Filme filme) {
        return ResponseEntity.ok(service.salvar(filme));
    }

    @GetMapping
    public ResponseEntity<List<Filme>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
