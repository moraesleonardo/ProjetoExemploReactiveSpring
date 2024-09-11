package com.example.projetoexemploreactivespring;

import com.example.projetoexemploreactivespring.controller.ProdutoController;
import com.example.projetoexemploreactivespring.model.Produto;
import com.example.projetoexemploreactivespring.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.*;

@WebFluxTest(ProdutoController.class)
public class ProdutoControllerTest {

    @Autowired
    private WebTestClient webTestClient; // Mantida apenas uma instância de WebTestClient

    @MockBean
    private ProdutoRepository produtoRepository;

    private Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto(); // Usa o construtor padrão
        produto.setId(1L); // Configura o ID usando o setter
        produto.setNome("Coca");
        produto.setPreco(5.0);
    }

    @Test
    public void testFindAll() {
        doReturn(Flux.just(produto)).when(produtoRepository).findAll();

        webTestClient.get().uri("/api/produtos")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Produto.class)
                .hasSize(1)
                .contains(produto);

        verify(produtoRepository, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        doReturn(Mono.just(produto)).when(produtoRepository).findById(1L);

        webTestClient.get().uri("/produtos/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Produto.class)
                .isEqualTo(produto);

        verify(produtoRepository, times(1)).findById(1L);
    }

    @Test
    public void testSaveProduto() {
        Produto newProduto = new Produto();
        newProduto.setNome("Pepsi");
        newProduto.setPreco(4.0);

        Produto produtoSalvo = new Produto();
        produtoSalvo.setId(2L);
        produtoSalvo.setNome("Pepsi");
        produtoSalvo.setPreco(4.0);

        doReturn(Mono.just(produtoSalvo)).when(produtoRepository).save(newProduto);

        webTestClient.post().uri("/produtos")
                .bodyValue(newProduto)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Produto.class)
                .isEqualTo(produtoSalvo);

        verify(produtoRepository, times(1)).save(newProduto);
    }

    @Test
    public void testDeleteProduto() {
        doReturn(Mono.empty()).when(produtoRepository).deleteById(1L);

        webTestClient.delete().uri("/produtos/1")
                .exchange()
                .expectStatus().isOk();

        verify(produtoRepository, times(1)).deleteById(1L);
    }
}
