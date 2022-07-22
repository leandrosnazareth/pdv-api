package br.com.leandrosnazareth.pdvapi.domain.model;

public enum Payment {

    DINHEIRO("DINHEIRO"),
    CARTAO("CARTÃO"),
    VALEALIMENTACAO("VALE ALIMENTAÇÃO"),
    CHEQUE("CHEQUE"),
    FIADO("FIADO");

    private String name;

    public String getDescricao() {
        return name;
    }

    private Payment(String name) {
        this.name = name;
    }
}
