package br.com.blsoft.pdvapi.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.leandrosnazareth.pdvapi.domain.entity.Product;
import br.com.leandrosnazareth.pdvapi.domain.entity.ProductSold;

public class ProductSoldTest {

    @Test
    public void calcPriceTotalProductSoldQuantity5() {
        // product
        var product = Product.builder()
                .price(new BigDecimal("20")).build();
        // productSolds
        var productSold = ProductSold.builder()
                .price(product.getPrice())
                .quantity(5).build();
        productSold.calcularPrecoTotal();
        var expected = new BigDecimal("100");
        assertEquals(expected, productSold.getPrice());
    }

    @Test
    public void calcPriceTotalProductSoldQuantity1000() {
        // product
        var product = Product.builder()
                .price(new BigDecimal("20")).build();
        // productSolds
        var productSold = ProductSold.builder()
                .price(product.getPrice())
                .quantity(100).build();
        productSold.calcularPrecoTotal();
        var expected = new BigDecimal("2000");
        assertEquals(expected, productSold.getPrice());
    }

    @Test
    public void calcPriceTotalProductSoldQuantity5Real() {
        // product
        var product = Product.builder()
                .price(new BigDecimal("3.25")).build();
        // productSolds
        var productSold = ProductSold.builder()
                .price(product.getPrice())
                .quantity(5).build();
        productSold.calcularPrecoTotal();
        var expected = new BigDecimal("16.25");
        assertEquals(expected, productSold.getPrice());
    }

    @Test
    public void calcPriceTotalProductSoldQuantityZero() {
        // product
        var product = Product.builder()
                .price(new BigDecimal("3.25")).build();
        // productSolds
        var productSold = ProductSold.builder()
                .price(product.getPrice())
                .quantity(0).build();
        productSold.calcularPrecoTotal();
        var expected = new BigDecimal("0.00");
        assertEquals(expected, productSold.getPrice());
    }

    @Test
    public void calcPriceTotalProductSoldPriceZero() {
        // product
        var product = Product.builder()
                .price(new BigDecimal("0")).build();
        // productSolds
        var productSold = ProductSold.builder()
                .price(product.getPrice())
                .quantity(5).build();
        productSold.calcularPrecoTotal();
        var expected = new BigDecimal("0");
        assertEquals(expected, productSold.getPrice());
    }
    
}
