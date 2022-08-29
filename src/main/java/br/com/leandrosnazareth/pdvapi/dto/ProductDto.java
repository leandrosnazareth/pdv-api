package br.com.leandrosnazareth.pdvapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String name;
    private BigDecimal price;
    private Boolean active;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}