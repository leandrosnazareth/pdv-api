package br.com.blsoft.pdvapi.util;

import java.math.BigDecimal;
import java.util.List;

import br.com.blsoft.pdvapi.domain.entity.Product;
import br.com.blsoft.pdvapi.domain.entity.Sale;
import br.com.blsoft.pdvapi.domain.model.Payment;

public class SaleDataTest {

    public Sale newSaleBuilderSemID(List<Product> products) {
        var sale = Sale.builder()
                .formaPagamento(Payment.DINHEIRO)
                .productSolds(new ProductSoldDataTest().newProductsSoldsSemID(products))
                .valorTotal(new BigDecimal("0"))
                .valorPago(new BigDecimal("0"))
                .troco(new BigDecimal("0")).build();
        sale.calcularValorTotal();
        return sale;
    }
}
