package br.com.blsoft.pdvapi.util;

import java.math.BigDecimal;
import java.util.List;

import br.com.blsoft.pdvapi.domain.entity.Product;
import br.com.blsoft.pdvapi.domain.entity.Sale;
import br.com.blsoft.pdvapi.domain.model.Payment;

public class SaleDataTest {

    public Sale newSaleBuilderSemID(List<Product> products) {
        var sale = Sale.builder()
                .payment(Payment.DINHEIRO)
                .productSolds(new ProductSoldDataTest().newProductsSoldsSemID(products))
                .amount(new BigDecimal("0"))
                .amountPaid(new BigDecimal("0"))
                .difference(new BigDecimal("0")).build();
        sale.calcularValorTotal();
        return sale;
    }
}
