package br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.services.exceptions;

public class LicencePlateAlreadyExists extends RuntimeException {
    public LicencePlateAlreadyExists() {
        super("This licence plate already exists");
    }
}
