package br.com.blsoft.pdvapi.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.AttributeOverride;
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

import br.com.blsoft.pdvapi.domain.model.Moeda;
import br.com.blsoft.pdvapi.domain.model.Payment;
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
public class Sale implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotNull(message = "{campo.valortotal.obrigatorio}")
	@AttributeOverride(name = "valor", column = @Column(name = "amount"))
	private Moeda amount;
	@Column(nullable = false)
	@NotNull(message = "{campo.valorpago.obrigatorio}")
	@AttributeOverride(name = "valor", column = @Column(name = "amount_paid"))
	private Moeda amountPaid;
	@Column(nullable = false)
	@NotNull(message = "{campo.troco.obrigatorio}")
	@AttributeOverride(name = "valor", column = @Column(name = "difference"))
	private Moeda difference;
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
			this.amountPaid.somarCom(productSold.getPrice().getValor());
			// this.valorTotal =
			// this.valorTotal.somarCom(productSold.getPreco().getValor());
		}
	}
}
