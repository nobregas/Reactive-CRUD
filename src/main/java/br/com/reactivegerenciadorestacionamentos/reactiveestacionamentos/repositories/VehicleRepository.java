package br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.repositories;

import br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.models.entities.Vehicle;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface VehicleRepository extends ReactiveCrudRepository<Vehicle, String> {
    Mono<Boolean> existsByLicencePlate(String licencePlate);
}
