package br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.services.exceptions;

import br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.models.dto.Error;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestControllerAdvice
public class VehicleExceptionHandler {

    private final String ERROR = "VEHICLE EXCEPTION";

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Error>> handleException(Exception ex) {
        return Mono.just(
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new Error(
                            ERROR,
                            "Internal Server Error",
                            new Date(),
                            HttpStatus.INTERNAL_SERVER_ERROR.value()
                    )
                )
        );
    }

    @ExceptionHandler(VehicleNotFoundException.class)
    public Mono<ResponseEntity<Error>> handleException(VehicleNotFoundException ex) {
        return Mono.just(
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new Error(
                                ERROR,
                                ex.getMessage(),
                                new Date(),
                                HttpStatus.NOT_FOUND.value()
                        )
                )
        );
    }

    @ExceptionHandler(LicencePlateAlreadyExists.class)
    public Mono<ResponseEntity<Error>> handleException(LicencePlateAlreadyExists ex) {
        return Mono.just(
                ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                        new Error(
                                ERROR,
                                ex.getMessage(),
                                new Date(),
                                HttpStatus.BAD_REQUEST.value()
                        )
                )
        );
    }

}
