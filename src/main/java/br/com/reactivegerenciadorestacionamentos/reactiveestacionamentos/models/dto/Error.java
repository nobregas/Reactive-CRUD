package br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.models.dto;

import java.util.Date;

public record Error(
        String error,
        String message,
        Date timestamp,
        Integer status
) {
}
