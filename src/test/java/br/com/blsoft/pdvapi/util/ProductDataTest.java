package br.com.blsoft.pdvapi.util;

import java.math.BigDecimal;

import br.com.blsoft.pdvapi.domain.entity.Product;
import br.com.blsoft.pdvapi.domain.entity.Product.ProductBuilder;

public final class ProductDataTest {

    public static ProductBuilder newProductBuilderComplete() {
        return Product.builder()
                .id(ConstantTest.DEFAULT_PRODUTO_ID)
                .name(ConstantTest.DEFAULT_PRODUTO_NOME)
                .price(new BigDecimal("20"))
                .active(true);
    }

    public static ProductBuilder newProductBuilderSemId() {
        return Product.builder()
                .name(ConstantTest.DEFAULT_PRODUTO_NOME)
                .price(new BigDecimal("20"))
                .active(true);
    }
}
