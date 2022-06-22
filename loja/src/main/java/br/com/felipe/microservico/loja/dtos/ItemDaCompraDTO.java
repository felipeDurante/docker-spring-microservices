package br.com.felipe.microservico.loja.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemDaCompraDTO {

    private Long id;
    private Integer quantidade;
}
