package br.com.leandrosnazareth.pdvapi.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.leandrosnazareth.pdvapi.domain.model.Payment;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Sale implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotNull(message = "{campo.valortotal.obrigatorio}")
	private BigDecimal amount;
	@Column(nullable = false)
	@NotNull(message = "{campo.valorpago.obrigatorio}")
	private BigDecimal amountPaid;
	@Column(nullable = false)
	@NotNull(message = "{campo.troco.obrigatorio}")
	private BigDecimal difference;
	@Column(nullable = false)
	@NotNull(message = "{campo.formapagamento.obrigatorio}")
	private Payment payment;

	@OneToMany(cascade = CascadeType.PERSIST, orphanRemoval = true)
	private List<ProductSold> productSolds;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") // formatar data no json de retorno
	private LocalDateTime createdAt;

	@LastModifiedBy
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") // formatar data no json de retorno
	private LocalDateTime updatedAt;

	@PrePersist
	protected void prePersist() {
		if (this.createdAt == null)
			createdAt = LocalDateTime.now();
		if (this.updatedAt == null)
			updatedAt = LocalDateTime.now();
	}

	@PreUpdate
	protected void preUpdate() {
		this.updatedAt = LocalDateTime.now();
	}

	public void calcularValorTotal() {
		for (ProductSold productSold : this.productSolds) {
			this.amountPaid = this.amountPaid.add(productSold.getPrice());
		}
	}
}
