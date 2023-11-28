package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.persistencia.ProdutoRepository;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@RestController
@RequestMapping("/produto")
@AllArgsConstructor
public class ProdutoController {

    private final ProdutoRepository repository;
    @GetMapping()
    public List<Produto> list(){
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Produto findByID(@PathVariable long id)
    {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PostMapping()
    public Produto insert(@RequestBody Produto produto){
        return repository.save(produto);
    }
}
