package br.com.felipe.microservico.loja.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CompraDTO {

    private List<ItemDaCompraDTO> itens;

    private EnderecoDTO endereco;
}
