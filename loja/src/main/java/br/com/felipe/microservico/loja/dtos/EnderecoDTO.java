package br.com.felipe.microservico.loja.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EnderecoDTO {

    private String rua;
    private Integer numero;
    private String estado;

}
