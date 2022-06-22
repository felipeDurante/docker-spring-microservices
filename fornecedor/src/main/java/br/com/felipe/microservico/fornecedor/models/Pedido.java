package br.com.felipe.microservico.fornecedor.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer tempoDePreparo;

    @Enumerated(EnumType.STRING)
    private PedidoStatus status;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pedidoId")
    private List<PedidoItem> itens;

    public Pedido(List<PedidoItem> itens) {
        this.itens = itens;
        this.status = PedidoStatus.RECEBIDO;
    }

    public Pedido() {

    }
}
