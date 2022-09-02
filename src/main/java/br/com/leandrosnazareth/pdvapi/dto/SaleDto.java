package br.com.leandrosnazareth.pdvapi.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import br.com.leandrosnazareth.pdvapi.domain.model.Payment;
import lombok.Data;

@Data
public class SaleDTO {

    private Long id;
    private BigDecimal amount;
    private BigDecimal amountPaid;
    private BigDecimal difference;
    private Payment payment;
    private List<ProductSoldDTO> productSolds;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}