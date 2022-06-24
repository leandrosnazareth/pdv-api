package br.com.blsoft.pdvapi.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.blsoft.pdvapi.util.ProductDataTest;
import br.com.blsoft.pdvapi.util.ProductSoldDataTest;

public class ProductSoldTest {

    @Test
    public void calcularPrecoTotalProductSold(){
        var produto = ProductDataTest.newProductBuilderComplete().build();
        var productSold = new ProductSoldDataTest().newProductSoldSemID(produto);
        var expected = new BigDecimal("100");
        assertEquals(expected, productSold.getPrice());
    }
}
