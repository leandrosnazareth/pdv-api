package br.com.blsoft.pdvapi.util;

import static br.com.blsoft.pdvapi.util.ConstantTest.DEFAULT_DATE_CREATE;
import static br.com.blsoft.pdvapi.util.ConstantTest.DEFAULT_DATE_UPDATE;
import static br.com.blsoft.pdvapi.util.ConstantTest.DEFAULT_PRODUTO_ACTIVE;
import static br.com.blsoft.pdvapi.util.ConstantTest.DEFAULT_PRODUTO_ID;
import static br.com.blsoft.pdvapi.util.ConstantTest.DEFAULT_PRODUTO_NOME;
import static br.com.blsoft.pdvapi.util.ConstantTest.DEFAULT_PRODUTO_VALOR;

import br.com.leandrosnazareth.pdvapi.domain.entity.Product;

public final class ProductDataTest {

    public static Product newProductComplete() {
        var product = new Product(
                DEFAULT_PRODUTO_ID,
                DEFAULT_PRODUTO_NOME,
                DEFAULT_PRODUTO_VALOR,
                DEFAULT_PRODUTO_ACTIVE,
                DEFAULT_DATE_CREATE,
                DEFAULT_DATE_UPDATE);
        return product;
    }

    public static Product newProductSemId() {
        var product = new Product(
                null,
                DEFAULT_PRODUTO_NOME,
                DEFAULT_PRODUTO_VALOR,
                DEFAULT_PRODUTO_ACTIVE,
                DEFAULT_DATE_CREATE,
                DEFAULT_DATE_UPDATE);
        return product;
    }
}
