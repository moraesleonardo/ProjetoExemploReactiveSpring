package com.example.projetoexemploreactivespring.repository;

import com.example.projetoexemploreactivespring.model.Produto;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

//public interface ProdutoRepository extends ReactiveCrudRepository<Produto, Long> {
//    Flux<Produto> findByNomeContaining(String nome);
//}


public interface ProdutoRepository extends R2dbcRepository<Produto, Long> {
    Flux<Produto> findByNomeContaining(String nome);
}
