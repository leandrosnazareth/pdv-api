package br.com.blsoft.pdvapi.integration;

import static br.com.blsoft.pdvapi.util.ConstantTest.DEFAULT_PRODUTO_ID;
import static br.com.blsoft.pdvapi.util.ConstantTest.DEFAULT_PRODUTO_NOME;
import static br.com.blsoft.pdvapi.util.ConstantTest.DEFAULT_PRODUTO_VALOR;
import static br.com.blsoft.pdvapi.util.ConstantTest.DEFAULT_PRODUTO_ACTIVE;
import static br.com.blsoft.pdvapi.util.ConstantTest.DEFAULT_DATE_CREATE;
import static br.com.blsoft.pdvapi.util.ConstantTest.DEFAULT_DATE_UPDATE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.leandrosnazareth.pdvapi.domain.entity.Product;
import br.com.leandrosnazareth.pdvapi.domain.repository.ProductRepository;

@ActiveProfiles(profiles = "integration-test")
@SpringBootTest(classes = br.com.leandrosnazareth.pdvapi.PdvApiApplication.class)
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
        assertEquals(product.getPrice(), productSalvo.getPrice());

        productRepository.delete(productSalvo);
        assertFalse(productRepository.findById(productSalvo.getId()).isPresent());
    }

    @Test
    public void testDeactivateProduct() {
        var product = this.criarProdutoPadrao();

        var productSalvo = productRepository.save(product);
        assertEquals(product.getName(), productSalvo.getName());
        assertEquals(product.getPrice(), productSalvo.getPrice());

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
        assertEquals(product.getPrice(), productSalvo.getPrice());

        var productfound = productRepository.findById(productSalvo.getId()).get();

        assertEquals(productSalvo.getId(), productfound.getId());
        assertEquals(productSalvo.getName(), productfound.getName());
        assertEquals(productSalvo.getPrice(), productfound.getPrice());

        productRepository.delete(productfound);
        assertFalse(productRepository.findById(productSalvo.getId()).isPresent());
    }

    @Test
    public void testFindProdutoByIDActive() {
        var product = this.criarProdutoPadrao();

        var productSalvo = productRepository.save(product);
        assertEquals(product.getName(), productSalvo.getName());
        assertEquals(product.getPrice(), productSalvo.getPrice());

        var productfound = productRepository.findByIdAndActive(productSalvo.getId(), true).get();

        assertEquals(productSalvo.getId(), productfound.getId());
        assertEquals(productSalvo.getName(), productfound.getName());
        assertEquals(productSalvo.getPrice(), productfound.getPrice());

        productRepository.delete(productfound);
        assertFalse(productRepository.findById(productSalvo.getId()).isPresent());
    }

    @Test
    public void testFindProdutoByNameActive() {
        var product = this.criarProdutoPadrao();

        var productSalvo = productRepository.save(product);
        assertEquals(product.getName(), productSalvo.getName());
        assertEquals(product.getPrice(), productSalvo.getPrice());

        var productfound = productRepository.findByNameAndActive(productSalvo.getName(), true).get();

        assertEquals(productSalvo.getId(), productfound.getId());
        assertEquals(productSalvo.getName(), productfound.getName());
        assertEquals(productSalvo.getPrice(), productfound.getPrice());

        productRepository.delete(productfound);
        assertFalse(productRepository.findById(productSalvo.getId()).isPresent());
    }

    @Test
    public void testFindProdutoByIDNotActive() {
        var product = this.criarProdutoNotActive();

        var productSalvo = productRepository.save(product);
        assertEquals(product.getName(), productSalvo.getName());
        assertEquals(product.getPrice(), productSalvo.getPrice());
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
        assertEquals(product.getPrice(), productSalvo.getPrice());

        var productfound = productRepository.findByName(product.getName()).get();

        assertEquals(productSalvo.getId(), productfound.getId());
        assertEquals(productSalvo.getName(), productfound.getName());
        assertEquals(productSalvo.getPrice(), productfound.getPrice());

        productRepository.delete(productfound);
        assertFalse(productRepository.findById(productSalvo.getId()).isPresent());
    }

    public Product criarProdutoPadrao() {
        var product = new Product(
                DEFAULT_PRODUTO_ID,
                DEFAULT_PRODUTO_NOME,
                DEFAULT_PRODUTO_VALOR,
                DEFAULT_PRODUTO_ACTIVE,
                DEFAULT_DATE_CREATE,
                DEFAULT_DATE_UPDATE);
        return product;
    }

    public Product criarProdutoNotActive() {
        var product = new Product(
                DEFAULT_PRODUTO_ID,
                DEFAULT_PRODUTO_NOME,
                DEFAULT_PRODUTO_VALOR,
                false,
                DEFAULT_DATE_CREATE,
                DEFAULT_DATE_UPDATE);
        return product;
    }
}
