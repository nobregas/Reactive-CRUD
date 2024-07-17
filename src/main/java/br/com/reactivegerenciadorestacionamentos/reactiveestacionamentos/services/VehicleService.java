package br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.services;

import br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.models.dto.VehicleDTO;
import br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.models.entities.Vehicle;
import br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.repositories.VehicleRepository;
import br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.services.exceptions.LicencePlateAlreadyExists;
import br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.services.exceptions.VehicleNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class VehicleService {

    private final VehicleRepository vehicleRepository;

    public Flux<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    public Mono<Vehicle> findById(String id) {
        return vehicleRepository.findById(id)
                .switchIfEmpty(Mono.error(new VehicleNotFoundException(id)));
    }

    public Mono<Vehicle> save(VehicleDTO vehicleDTO) {
        return vehicleRepository.existsByLicencePlate(vehicleDTO.licencePlate())
                .flatMap(
                        exists -> exists
                                ? Mono.error(new LicencePlateAlreadyExists())
                                : vehicleRepository.save(vehicleDTO.toEntity())
                );
    }

    public Mono<Void> delete(String id) {
        return vehicleRepository.existsById(id)
                .flatMap(
                        exists -> exists
                                ? vehicleRepository.deleteById(id)
                                : Mono.error(new VehicleNotFoundException(id))
                );
    }

    public Mono<Vehicle> update(String id, VehicleDTO vehicleDTO) {
        return vehicleRepository.findById(id)
                .switchIfEmpty(Mono.error(new VehicleNotFoundException(id)))
                .flatMap(vehicle -> updateAttributes(vehicleDTO, vehicle)
                        .flatMap(vehicleRepository::save)
                );
    }

    public Mono<Vehicle> updateAttributes(VehicleDTO vehicleDTO, Vehicle vehicle) {
        if (!vehicleDTO.brand().isBlank() && !vehicleDTO.brand().equals(vehicle.getBrand())) {
            vehicle.setBrand(vehicleDTO.brand());
        }
        if (!vehicleDTO.model().isBlank() && !vehicleDTO.model().equals(vehicle.getModel())) {
            vehicle.setModel(vehicleDTO.model());
        }
        if (!vehicleDTO.color().isBlank() && !vehicleDTO.color().equals(vehicle.getColor())) {
            vehicle.setColor(vehicleDTO.color());
        }
        if (!vehicleDTO.licencePlate().isBlank()
                && !vehicleDTO.licencePlate().equals(vehicle.getLicencePlate()))
        {
            vehicleRepository.existsByLicencePlate(vehicleDTO.licencePlate())
                    .flatMap(exists -> {
                        Mono<?> mono;
                        if (exists) {
                            mono = Mono.error(new LicencePlateAlreadyExists());
                        }
                        else {
                            vehicle.setLicencePlate(vehicleDTO.licencePlate());
                            mono = Mono.just(vehicle);
                        }
                        return mono;
                    }


            );
        }
        if (vehicleDTO.type() != null && !vehicleDTO.type().equals(vehicle.getType())) {
            vehicle.setType(vehicleDTO.type());
        }
        return Mono.just(vehicle);
    }
}
