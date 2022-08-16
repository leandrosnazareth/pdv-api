package br.com.blsoft.pdvapi.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import br.com.leandrosnazareth.pdvapi.domain.entity.ProductSold;

public class ProductSoldTest {

        @Test
        public void calcPriceTotalProductSoldQuantity5() {
                var productSold = new ProductSold(
                                null,
                                null,
                                new BigDecimal("20"),
                                new BigDecimal("0"),
                                5);
                productSold.calcularPrecoTotal();
                var expected = new BigDecimal("100");
                assertEquals(expected, productSold.getPrice());
        }

        @Test
        public void calcPriceTotalProductSoldQuantity1000() {
                var productSold = new ProductSold(
                                null,
                                null,
                                new BigDecimal("20"),
                                new BigDecimal("0"),
                                100);
                productSold.calcularPrecoTotal();
                var expected = new BigDecimal("2000");
                assertEquals(expected, productSold.getPrice());
        }

        @Test
        public void calcPriceTotalProductSoldQuantity5Real() {
                // productSolds
                var productSold = new ProductSold(
                                null,
                                null,
                                new BigDecimal("3.25"),
                                new BigDecimal("0"),
                                5);
                productSold.calcularPrecoTotal();
                var expected = new BigDecimal("16.25");
                assertEquals(expected, productSold.getPrice());
        }

        @Test
        public void calcPriceTotalProductSoldQuantityZero() {
                // productSolds
                var productSold = new ProductSold(
                                null,
                                null,
                                new BigDecimal("3.25"),
                                new BigDecimal("0"),
                                0);
                productSold.calcularPrecoTotal();
                var expected = new BigDecimal("0.00");
                assertEquals(expected, productSold.getPrice());
        }

        @Test
        public void calcPriceTotalProductSoldPriceZero() {
                // productSolds
                var productSold = new ProductSold(
                                null,
                                null,
                                new BigDecimal("0"),
                                new BigDecimal("0"),
                                5);
                productSold.calcularPrecoTotal();
                var expected = new BigDecimal("0");
                assertEquals(expected, productSold.getPrice());
        }
}
