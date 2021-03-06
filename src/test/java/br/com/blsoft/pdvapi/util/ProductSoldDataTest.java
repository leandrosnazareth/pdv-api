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
            productSold = ProductSold.builder()
                    .price(product.getPrice())
                    .priceTotal(product.getPrice())
                    .quantity(5)
                    .product(product).build();
            productSold.calcularPrecoTotal();
            productSolds.add(productSold);
        }
        return productSolds;
    }

    public ProductSold newProductSoldSemID(Product product) {
        var productSold = ProductSold.builder()
                .price(product.getPrice())
                .quantity(5)
                .product(product).build();
        productSold.calcularPrecoTotal();
        return productSold;
    }
}
