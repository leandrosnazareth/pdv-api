package br.com.blsoft.pdvapi.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import br.com.blsoft.pdvapi.domain.entity.ProductSold;
import br.com.blsoft.pdvapi.domain.repository.ProductRepository;
import br.com.blsoft.pdvapi.domain.repository.SaleRepository;
import br.com.blsoft.pdvapi.util.ProductDataTest;
import br.com.blsoft.pdvapi.util.SaleDataTest;

@ActiveProfiles(profiles = "integration-test")
@SpringBootTest(classes = br.com.blsoft.pdvapi.PdvApiApplication.class)
public class SaleIntegrationTest {

    @Autowired
    SaleRepository saleRepository;
    @Autowired
    ProductRepository productRepository;
    ProductSold productSold;

    @Test
    public void testFindallSales() {
        assertNotNull(saleRepository.findAll());
    }

    @Test
    public void testCreateSale() {
        var product = ProductDataTest.newProductBuilderSemId().build();
        productRepository.save(product);

        var products = productRepository.findAll();

        var sale = new SaleDataTest().newSaleBuilderSemID(products).build();

        var saleSave = saleRepository.save(sale);
        assertEquals(sale.getFormaPagamento(), saleSave.getFormaPagamento());
    }
}
