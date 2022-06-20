package br.com.blsoft.pdvapi.integration;

import static br.com.blsoft.pdvapi.util.ConstantTest.DEFAULT_PRODUTO_NOME;
import static br.com.blsoft.pdvapi.util.ConstantTest.DEFAULT_PRODUTO_VALOR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.blsoft.pdvapi.domain.entity.Product;
import br.com.blsoft.pdvapi.domain.repository.ProductRepository;

@ActiveProfiles(profiles = "integration-test")
@SpringBootTest(classes = br.com.blsoft.pdvapi.PdvApiApplication.class)
public class ProdutoIntegrationTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void testFindallProdutos() {
        assertNotNull(productRepository.findAll());
    }

    @Test
    public void testCreteProduto() {
        var product = this.criarProdutoPadrao();

        var productSalvo = productRepository.save(product);
        assertEquals(product.getName(), productSalvo.getName());
        assertEquals(product.getPrice().getValor(), productSalvo.getPrice().getValor());

        productRepository.delete(productSalvo);
        assertFalse(productRepository.findById(productSalvo.getId()).isPresent());
    }

    @Test
    public void testDeactivateProduct() {
        var product = this.criarProdutoPadrao();

        var productSalvo = productRepository.save(product);
        assertEquals(product.getName(), productSalvo.getName());
        assertEquals(product.getPrice().getValor(), productSalvo.getPrice().getValor());

        productRepository.deactivate(productSalvo.getId());

        assertFalse(productRepository.findByIdAndActive(productSalvo.getId(), true).isPresent());
        assertTrue(productRepository.findByIdAndActive(productSalvo.getId(), false).isPresent());

        productRepository.delete(productSalvo);
        assertFalse(productRepository.findById(productSalvo.getId()).isPresent());
    }

    @Test
    public void testFindProdutoByID() {
        var product = this.criarProdutoPadrao();

        var productSalvo = productRepository.save(product);
        assertEquals(product.getName(), productSalvo.getName());
        assertEquals(product.getPrice().getValor(), productSalvo.getPrice().getValor());

        var productfound = productRepository.findById(productSalvo.getId()).get();

        assertEquals(productSalvo.getId(), productfound.getId());
        assertEquals(productSalvo.getName(), productfound.getName());
        assertEquals(productSalvo.getPrice().getValor(), productfound.getPrice().getValor());

        productRepository.delete(productfound);
        assertFalse(productRepository.findById(productSalvo.getId()).isPresent());
    }

    @Test
    public void testFindProdutoByIDActive() {
        var product = this.criarProdutoPadrao();

        var productSalvo = productRepository.save(product);
        assertEquals(product.getName(), productSalvo.getName());
        assertEquals(product.getPrice().getValor(), productSalvo.getPrice().getValor());

        var productfound = productRepository.findByIdAndActive(productSalvo.getId(), true).get();

        assertEquals(productSalvo.getId(), productfound.getId());
        assertEquals(productSalvo.getName(), productfound.getName());
        assertEquals(productSalvo.getPrice().getValor(), productfound.getPrice().getValor());

        productRepository.delete(productfound);
        assertFalse(productRepository.findById(productSalvo.getId()).isPresent());
    }

    @Test
    public void testFindProdutoByNameActive() {
        var product = this.criarProdutoPadrao();

        var productSalvo = productRepository.save(product);
        assertEquals(product.getName(), productSalvo.getName());
        assertEquals(product.getPrice().getValor(), productSalvo.getPrice().getValor());

        var productfound = productRepository.findByNameAndActive(productSalvo.getName(), true).get();

        assertEquals(productSalvo.getId(), productfound.getId());
        assertEquals(productSalvo.getName(), productfound.getName());
        assertEquals(productSalvo.getPrice().getValor(), productfound.getPrice().getValor());

        productRepository.delete(productfound);
        assertFalse(productRepository.findById(productSalvo.getId()).isPresent());
    }

    @Test
    public void testFindProdutoByIDNotActive() {
        var product = this.criarProdutoNotActive();

        var productSalvo = productRepository.save(product);
        assertEquals(product.getName(), productSalvo.getName());
        assertEquals(product.getPrice().getValor(), productSalvo.getPrice().getValor());
        assertEquals(product.getActive(), productSalvo.getActive());

        assertFalse(productRepository.findByIdAndActive(productSalvo.getId(), true).isPresent());

        productRepository.delete(productSalvo);
        assertFalse(productRepository.findById(productSalvo.getId()).isPresent());
    }

    @Test
    public void testFindProdutoByName() {
        var product = this.criarProdutoPadrao();

        var productSalvo = productRepository.save(product);
        assertEquals(product.getName(), productSalvo.getName());
        assertEquals(product.getPrice().getValor(), productSalvo.getPrice().getValor());

        var productfound = productRepository.findByName(product.getName()).get();

        assertEquals(productSalvo.getId(), productfound.getId());
        assertEquals(productSalvo.getName(), productfound.getName());
        assertEquals(productSalvo.getPrice().getValor(), productfound.getPrice().getValor());

        productRepository.delete(productfound);
        assertFalse(productRepository.findById(productSalvo.getId()).isPresent());
    }

    public Product criarProdutoPadrao() {
        return Product.builder()
                .name(DEFAULT_PRODUTO_NOME)
                .price(DEFAULT_PRODUTO_VALOR).build();
    }

    public Product criarProdutoNotActive() {
        return Product.builder()
                .name(DEFAULT_PRODUTO_NOME)
                .price(DEFAULT_PRODUTO_VALOR)
                .active(false).build();
    }
}
