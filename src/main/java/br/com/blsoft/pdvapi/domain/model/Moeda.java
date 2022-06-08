package br.com.blsoft.pdvapi.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

import org.springframework.format.annotation.NumberFormat;

@Embeddable
public class Moeda {

    @NumberFormat(pattern = "#,###,###,###.##")
    private BigDecimal valor;
    @Transient
    private static final String UNIDADE_MONETARIA = "R$";
    @Transient
    private static final DecimalFormat FORMATO = new DecimalFormat(UNIDADE_MONETARIA + " #,###,##0.00");
    @Transient
    public static int CASAS_DECIMAIS = 2;
    @Transient
    public static RoundingMode ARREDONDAMENTO = RoundingMode.HALF_EVEN;

    public Moeda() {
    }

    public Moeda(double valor) {
        this.valor = new BigDecimal(String.valueOf(valor));
    }

    public Moeda(BigDecimal valor) {
        this.valor = valor;
    }

    public Moeda(String valor) {
        this.valor = new BigDecimal(valor);
    }

    public void somarCom(BigDecimal valor) {
        this.valor = this.valor.add(valor);
    }

    public void subtrair(BigDecimal valor) {
        this.valor = this.valor.subtract(valor);
    }

    public void multiplicarPor(BigDecimal valor) {
        this.valor = this.valor.multiply(valor);
    }

    public static Moeda zero() {
        return new Moeda("0");
    }

    public void dividirPor(BigDecimal valor) throws Exception {
        if (valor.compareTo(BigDecimal.ZERO) == 0) {
            throw new Exception("Ops! Erro ao dividir " + this.valor + " por " + valor);
        } else {
            this.valor = this.valor.divide(valor, CASAS_DECIMAIS, ARREDONDAMENTO);
        }
    }

    public String getValorFormatado() {
        this.valor = this.valor != null ? this.valor : BigDecimal.ZERO;
        return FORMATO.format(this.valor);
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
