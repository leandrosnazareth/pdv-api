package br.com.blsoft.pdvapi.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.blsoft.pdvapi.domain.entity.Product;
import br.com.blsoft.pdvapi.domain.entity.ProductSold;
import br.com.blsoft.pdvapi.domain.entity.Sale;
import br.com.blsoft.pdvapi.domain.entity.Sale.SaleBuilder;
import br.com.blsoft.pdvapi.domain.model.Payment;

public class SaleDataTest {

    public SaleBuilder newSaleBuilderSemID(List<Product> products) {
        return Sale.builder()
                .formaPagamento(Payment.DINHEIRO)
                .productSolds(newProductSoldSemID(products))
                .valorPago(new BigDecimal("100"))
                .valorTotal(new BigDecimal("100"))
                .troco(new BigDecimal("100"));
    }

    private List<ProductSold> newProductSoldSemID(List<Product> products) {
        List<ProductSold> productSolds = new ArrayList<>();
        for (Product product : products) {
            productSolds.add(ProductSold.builder()
                    .preco(new BigDecimal("20"))
                    .quantidae(5)
                    .product(product).build());
        }
        return productSolds;
    }
}
