package com.example.projetoexemploreactivespring.controller;

import com.example.projetoexemploreactivespring.model.Produto;
import com.example.projetoexemploreactivespring.repository.ProdutoRepository;
import com.example.projetoexemploreactivespring.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    private final ProdutoRepository produtoRepository;

    public ProdutoController(ProdutoRepository produtoRepository){
        this.produtoRepository = produtoRepository;
    }

    @GetMapping
    public Flux<Produto> getAll() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Mono<Produto> getById(@PathVariable Long id) {
        return produtoRepository.findById(id);
    }

    @PostMapping
    public Mono<Produto> save(@RequestBody Produto produto){
        return produtoRepository.save(produto);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable Long id){
        return produtoRepository.deleteById(id);
    }
}

