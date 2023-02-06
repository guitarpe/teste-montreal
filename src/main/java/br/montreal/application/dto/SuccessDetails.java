package br.montreal.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuccessDetails {
    private long code;
    private boolean sucesso;
	private LocalDateTime timestamp;
    private String message;
    private Object data;
}
