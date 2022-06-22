package br.com.felipe.microservico.loja.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class InfoFornecedorDTO {

    @Getter
    @Setter
    private String endereco;
}
