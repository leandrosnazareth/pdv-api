package br.com.blsoft.pdvapi.util;

import java.math.BigDecimal;
import java.util.List;

import br.com.leandrosnazareth.pdvapi.domain.entity.Product;
import br.com.leandrosnazareth.pdvapi.domain.entity.Sale;
import br.com.leandrosnazareth.pdvapi.domain.model.Payment;

public class SaleDataTest {

    public Sale newSaleBuilderSemID(List<Product> products) {
        var sale = new Sale(
                null,
                new BigDecimal("0"),
                new BigDecimal("0"),
                new BigDecimal("0"),
                Payment.DINHEIRO,
                new ProductSoldDataTest().newProductsSoldsSemID(products),
                null,
                null);
        sale.calcularValorTotal();
        return sale;
    }
}