package br.montreal.application.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ServiceResponse {
    private boolean status;
    private String mensagem;
    private Object data;
}
