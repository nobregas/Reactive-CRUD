package br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.controllers;

import br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.models.dto.VehicleDTO;
import br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.models.entities.Vehicle;
import br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.services.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService vehicleService;

    @GetMapping
    public ResponseEntity<Flux<Vehicle>> getAll() {
        return ResponseEntity.ok(vehicleService.findAll());
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Vehicle>> getVehicleById(@PathVariable String id) {
        return vehicleService.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Vehicle>> create(@RequestBody VehicleDTO dto) {
        return vehicleService.save(dto)
                .map(createdVehicle -> new ResponseEntity<>(createdVehicle, HttpStatus.CREATED)
        );
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<Vehicle>> update(@PathVariable String id, @RequestBody VehicleDTO dto) {
        return vehicleService.update(id, dto)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deleteVehicle(@PathVariable String id) {
        return vehicleService.delete(id)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}
