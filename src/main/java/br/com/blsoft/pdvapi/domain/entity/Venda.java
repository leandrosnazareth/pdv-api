package br.com.blsoft.pdvapi.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.blsoft.pdvapi.domain.model.FormaPagamento;
import br.com.blsoft.pdvapi.domain.model.Moeda;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Venda implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	@NotNull(message = "{campo.valortotal.obrigatorio}")
	private BigDecimal valorTotal;
	@Column(nullable = false)
	@NotNull(message = "{campo.valorpago.obrigatorio}")
	private BigDecimal valorPago;
	@Column(nullable = false)
	@NotNull(message = "{campo.troco.obrigatorio}")
	private BigDecimal troco;
	@Column(nullable = false)
	@NotNull(message = "{campo.formapagamento.obrigatorio}")
	private FormaPagamento formaPagamento;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") // formatar data no json de retorno
	private LocalDateTime dataCadastro;

	@LastModifiedBy
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss") // formatar data no json de retorno
	private LocalDateTime dataAtualizacao;

	@PrePersist
	protected void prePersist() {
		if (this.dataCadastro == null)
			dataCadastro = LocalDateTime.now();
		if (this.dataAtualizacao == null)
			dataAtualizacao = LocalDateTime.now();
	}

	@PreUpdate
	protected void preUpdate() {
		this.dataAtualizacao = LocalDateTime.now();
	}
}
