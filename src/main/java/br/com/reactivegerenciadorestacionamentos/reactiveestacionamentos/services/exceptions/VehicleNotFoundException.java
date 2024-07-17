package br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.services.exceptions;

public class VehicleNotFoundException extends RuntimeException {
    public VehicleNotFoundException(String id) {
        super("Vehicle not found id: " + id);
    }
}
