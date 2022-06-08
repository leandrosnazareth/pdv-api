package br.com.blsoft.pdvapi.domain.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
    private String nome;
    private BigInteger preco;    


    public Produto() {
    }

    public Produto(Long id, String nome, BigInteger preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigInteger getPreco() {
        return this.preco;
    }

    public void setPreco(BigInteger preco) {
        this.preco = preco;
    }

    public Produto id(Long id) {
        setId(id);
        return this;
    }

    public Produto nome(String nome) {
        setNome(nome);
        return this;
    }

    public Produto preco(BigInteger preco) {
        setPreco(preco);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Produto)) {
            return false;
        }
        Produto produto = (Produto) o;
        return Objects.equals(id, produto.id) && Objects.equals(nome, produto.nome) && Objects.equals(preco, produto.preco);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, preco);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", nome='" + getNome() + "'" +
            ", preco='" + getPreco() + "'" +
            "}";
    }

}
