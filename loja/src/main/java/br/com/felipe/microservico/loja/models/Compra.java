package br.com.felipe.microservico.loja.models;


import javax.persistence.*;
import java.time.LocalDate;


@Entity
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;

    private Integer tempoDePreparo;

    private String enderecoDestino;

    private LocalDate dataParaEntrega;

    private Long voucher;

    @Enumerated(EnumType.STRING)
    private CompraState state;

    public Compra() {
    }

    public Compra(Long pedidoId, Integer tempoDePreparo, String enderecoDestino) {
        this.pedidoId = pedidoId;
        this.tempoDePreparo = tempoDePreparo;
        this.enderecoDestino = enderecoDestino;
    }

    public Compra(Long pedidoId, Integer tempoDePreparo, String enderecoDestino, LocalDate dataParaEntrega, Long voucher) {
        this.pedidoId = pedidoId;
        this.tempoDePreparo = tempoDePreparo;
        this.enderecoDestino = enderecoDestino;
        this.dataParaEntrega = dataParaEntrega;
        this.voucher = voucher;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Integer getTempoDePreparo() {
        return tempoDePreparo;
    }

    public void setTempoDePreparo(Integer tempoDePreparo) {
        this.tempoDePreparo = tempoDePreparo;
    }

    public String getEnderecoDestino() {
        return enderecoDestino;
    }

    public void setEnderecoDestino(String enderecoDestino) {
        this.enderecoDestino = enderecoDestino;
    }

    public LocalDate getDataParaEntrega() {
        return dataParaEntrega;
    }

    public void setDataParaEntrega(LocalDate dataParaEntrega) {
        this.dataParaEntrega = dataParaEntrega;
    }

    public Long getVoucher() {
        return voucher;
    }

    public void setVoucher(Long voucher) {
        this.voucher = voucher;
    }

    public CompraState getState() {
        return state;
    }

    public void setState(CompraState state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", pedidoId=" + pedidoId +
                ", tempoDePreparo=" + tempoDePreparo +
                ", enderecoDestino='" + enderecoDestino + '\'' +
                ", dataParaEntrega=" + dataParaEntrega +
                ", voucher=" + voucher +
                ", state=" + state +
                '}';
    }
}
