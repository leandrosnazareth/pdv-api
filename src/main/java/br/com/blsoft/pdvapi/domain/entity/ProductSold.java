package br.com.blsoft.pdvapi.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class ProductSold implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Product product;
    @Column(nullable = false)
    @NotNull(message = "{campo.preco.obrigatorio}")
    private BigDecimal price;
    @Column(nullable = false)
    @NotNull(message = "{campo.preco.obrigatorio}")
    private BigDecimal priceTotal;
    @Column(nullable = false)
    @NotNull(message = "{campo.quantidade.obrigatorio}")
    private int quantity;

    public void calcularPrecoTotal() {
        this.price = this.price.multiply(new BigDecimal(this.quantity));
        // this.price.multiply(new BigDecimal(this.quantity));
    }
}
