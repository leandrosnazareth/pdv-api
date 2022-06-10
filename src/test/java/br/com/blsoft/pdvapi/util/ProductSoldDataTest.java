package br.com.blsoft.pdvapi.util;

import java.util.ArrayList;
import java.util.List;

import br.com.blsoft.pdvapi.domain.entity.Product;
import br.com.blsoft.pdvapi.domain.entity.ProductSold;

public class ProductSoldDataTest {

    public List<ProductSold> newProductsSoldsSemID(List<Product> products) {
        List<ProductSold> productSolds = new ArrayList<>();
        ProductSold productSold;
        for (Product product : products) {
            productSold = ProductSold.builder()
                    .preco(product.getPrice())
                    .quantidade(5)
                    .product(product).build();
            productSold.calcularPrecoTotal();
            productSolds.add(productSold);
        }
        return productSolds;
    }

    public ProductSold newProductSoldSemID(Product product) {
        var productSold = ProductSold.builder()
                .preco(product.getPrice())
                .quantidade(5)
                .product(product).build();
        productSold.calcularPrecoTotal();
        return productSold;
    }
}
