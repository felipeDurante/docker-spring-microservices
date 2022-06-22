package br.com.felipe.microservico.fornecedor.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseApiFornecedor {

    private String msg;
    private int status;
    private List<?> objetos;

    public ResponseApiFornecedor(String msg, Integer status) {
        this.msg = msg;
        this.status = status;
    }


}
