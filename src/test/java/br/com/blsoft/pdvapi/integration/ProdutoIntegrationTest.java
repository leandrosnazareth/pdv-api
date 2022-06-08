package br.com.blsoft.pdvapi.integration;

import static br.com.blsoft.pdvapi.util.ProdutoConstantTest.DEFAULT_PRODUTO_NOME;
import static br.com.blsoft.pdvapi.util.ProdutoConstantTest.DEFAULT_PRODUTO_VALOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.blsoft.pdvapi.domain.entity.Produto;
import br.com.blsoft.pdvapi.domain.repository.ProdutoRepository;

@ActiveProfiles(profiles = "integration-test")
@SpringBootTest(classes = br.com.blsoft.pdvapi.PdvApiApplication.class)
public class ProdutoIntegrationTest {

    @Autowired
    ProdutoRepository produtoRepository;

    @Test
    public void testFindallProdutos() {
        assertNotNull(produtoRepository.findAll());
    }

    @Test
    public void testCreteProduto() {
        var produto = this.criarProdutoPadrao();

        var produtoSalvo = produtoRepository.save(produto);
        assertEquals(produto.getNome(), produtoSalvo.getNome());
        assertEquals(produto.getPreco(), produtoSalvo.getPreco());

        produtoRepository.delete(produtoSalvo);
        assertFalse(produtoRepository.findById(produtoSalvo.getId()).isPresent());
    }

    @Test
    public void testFindProdutoByID() {
        var produto = this.criarProdutoPadrao();

        var produtoSalvo = produtoRepository.save(produto);
        assertEquals(produto.getNome(), produtoSalvo.getNome());
        assertEquals(produto.getPreco(), produtoSalvo.getPreco());

        var produtofound = produtoRepository.findById(produtoSalvo.getId()).get();

        assertEquals(produtoSalvo.getId(), produtofound.getId());
        assertEquals(produtoSalvo.getNome(), produtofound.getNome());
        assertEquals(produtoSalvo.getPreco(), produtofound.getPreco());

        produtoRepository.delete(produtofound);
        assertFalse(produtoRepository.findById(produtoSalvo.getId()).isPresent());
    }

    @Test
    public void testFindProdutoByName() {
        var produto = this.criarProdutoPadrao();

        var produtoSalvo = produtoRepository.save(produto);
        assertEquals(produto.getNome(), produtoSalvo.getNome());
        assertEquals(produto.getPreco(), produtoSalvo.getPreco());

        var produtofound = produtoRepository.findByNome(produto.getNome());

        assertEquals(produtoSalvo.getId(), produtofound.getId());
        assertEquals(produtoSalvo.getNome(), produtofound.getNome());
        assertEquals(produtoSalvo.getPreco(), produtofound.getPreco());

        produtoRepository.delete(produtofound);
        assertFalse(produtoRepository.findById(produtoSalvo.getId()).isPresent());
    }

    public Produto criarProdutoPadrao() {
        return Produto.builder()
                .nome(DEFAULT_PRODUTO_NOME)
                .preco(DEFAULT_PRODUTO_VALOR).build();
    }
}
