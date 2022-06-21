package br.com.blsoft.pdvapi.util;

import java.util.List;

import br.com.blsoft.pdvapi.domain.entity.Product;
import br.com.blsoft.pdvapi.domain.entity.Sale;
import br.com.blsoft.pdvapi.domain.model.Moeda;
import br.com.blsoft.pdvapi.domain.model.Payment;

public class SaleDataTest {

    public Sale newSaleBuilderSemID(List<Product> products) {
        var sale = Sale.builder()
                .payment(Payment.DINHEIRO)
                .productSolds(new ProductSoldDataTest().newProductsSoldsSemID(products))
                .amount(new Moeda("0"))
                .amountPaid(new Moeda("0"))
                .difference(new Moeda("0")).build();
        sale.calcularValorTotal();
        return sale;
    }
}
