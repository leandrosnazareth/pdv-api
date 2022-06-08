package br.com.blsoft.pdvapi.domain.model;

public enum FormaPagamento {

    DINHEIRO("DINHEIRO"),
    CARTAO("CARTÃO"),
    VALEALIMENTACAO("VALE ALIMENTAÇÃO"),
    CHEQUE("CHEQUE"),
    FIADO("FIADO");

    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    private FormaPagamento(String descricao) {
        this.descricao = descricao;
    }
}
