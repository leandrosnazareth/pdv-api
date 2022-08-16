package br.com.blsoft.pdvapi.util;

import java.util.ArrayList;
import java.util.List;

import br.com.leandrosnazareth.pdvapi.domain.entity.Product;
import br.com.leandrosnazareth.pdvapi.domain.entity.ProductSold;

public class ProductSoldDataTest {

    public List<ProductSold> newProductsSoldsSemID(List<Product> products) {
        List<ProductSold> productSolds = new ArrayList<>();
        ProductSold productSold;
        for (Product product : products) {
            productSold = new ProductSold(
                    null,
                    product,
                    product.getPrice(),
                    product.getPrice(),
                    5);
            productSold.calcularPrecoTotal();
            productSolds.add(productSold);
        }
        return productSolds;
    }

    public ProductSold newProductSoldSemID(Product product) {
        var productSold = new ProductSold(
                null,
                product,
                product.getPrice(),
                product.getPrice(),
                5);
        productSold.calcularPrecoTotal();
        return productSold;
    }
}
