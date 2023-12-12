package com.example.demo.controller;

import com.example.demo.model.Produto;
import com.example.demo.persistencia.ProdutoRepository;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/produto")
@AllArgsConstructor
public class ProdutoController {

    private final ProdutoRepository repository;
    @GetMapping()
    public ResponseEntity<List<Produto>> list(){
        return ResponseEntity.ok(repository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Produto> findByID(@Valid @PathVariable long id)
    {
        return repository
                .findById(id)
                .map(ResponseEntity::ok)

        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "produto não encontrado"));
    }

    @PostMapping()
    public ResponseEntity<Produto> insert(@RequestBody Produto produto){
        final var newProduto = repository.save(produto);
        final var location = URI.create("/produto/" + newProduto.getId());
        return ResponseEntity.created(location)
                .body(newProduto);
    }
    //não precisa retornar o produto, só de exemplo
    @PutMapping("{id}")
    public ResponseEntity<Produto> update(@Valid @PathVariable long id, @RequestBody Produto produto){
        var msg = "O ID informado não coincide com o ID do objeto passado";
        if(id != produto.getId()) throw new ResponseStatusException(HttpStatus.CONFLICT, msg);
        return ResponseEntity.ok(repository.save(produto));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@Valid @PathVariable long id) {
        repository.findAndDelete(id);
        return ResponseEntity.noContent().build();
    }
}
