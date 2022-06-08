package br.com.blsoft.pdvapi.domain.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import com.fasterxml.jackson.annotation.JsonFormat;

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
public class Venda implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigInteger valorTotal;
    private BigInteger valorPago;
    private BigInteger troco;

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
