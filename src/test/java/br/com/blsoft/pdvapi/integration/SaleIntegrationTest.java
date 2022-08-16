package br.com.blsoft.pdvapi.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import br.com.blsoft.pdvapi.util.ProductDataTest;
import br.com.blsoft.pdvapi.util.SaleDataTest;
import br.com.leandrosnazareth.pdvapi.domain.entity.Sale;
import br.com.leandrosnazareth.pdvapi.domain.repository.ProductRepository;
import br.com.leandrosnazareth.pdvapi.domain.repository.SaleRepository;

@ActiveProfiles(profiles = "integration-test")
// @ActiveProfiles(profiles = "aplication")
@SpringBootTest(classes = br.com.leandrosnazareth.pdvapi.PdvApiApplication.class)
public class SaleIntegrationTest {

    @Autowired
    SaleRepository saleRepository;
    @Autowired
    ProductRepository productRepository;


    @Test
    public void testFindallSales() {
        assertNotNull(saleRepository.findAll());
    }

    @Test
    public void testCreateSale() throws JsonProcessingException {
        var product = ProductDataTest.newProductSemId();
        productRepository.save(product);

        var valorPago = new BigDecimal("66.65");

        var products = productRepository.findAll();

        var sale = new SaleDataTest().newSaleBuilderSemID(products);

        Sale saleSave = saleRepository.save(sale);

        //imprimir json
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new JavaTimeModule());
        var json = objectMapper.writeValueAsString(saleSave);
        System.out.println(json.toString()+"AQUI");
        
        assertEquals(sale.getAmountPaid(), saleSave.getAmountPaid());
        assertEquals(valorPago, saleSave.getAmountPaid());
        
        saleRepository.delete(sale);
        assertFalse(saleRepository.findById(sale.getId()).isPresent());

        productRepository.delete(product);
        assertFalse(productRepository.findById(product.getId()).isPresent());
    }
}
