package br.com.felipe.microservico.fornecedor.dtos;

import lombok.ToString;

@ToString
public class ItemDoPedidoDTO {

    private long id;
    private int quantidade;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
