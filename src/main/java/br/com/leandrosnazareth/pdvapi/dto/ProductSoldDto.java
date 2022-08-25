package br.com.leandrosnazareth.pdvapi.dto;

import java.math.BigDecimal;

import br.com.leandrosnazareth.pdvapi.domain.entity.Product;
import lombok.Data;

@Data
public class ProductSoldDto {

    private Long id;
    private Product product;
    private BigDecimal price;
    private BigDecimal priceTotal;
    private int quantity;
}