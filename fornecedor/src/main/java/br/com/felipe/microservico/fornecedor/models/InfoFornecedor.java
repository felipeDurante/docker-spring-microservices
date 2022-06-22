package br.com.felipe.microservico.fornecedor.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class InfoFornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estado;

    private String endereco;

    private String nome;

    public InfoFornecedor() {
    }

    public InfoFornecedor(String estado, String endereco, String nome) {
        this.estado = estado;
        this.endereco = endereco;
        this.nome = nome;
    }
}
