package br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.models.dto;

import br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.models.entities.Vehicle;
import br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.models.enums.Type;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record VehicleDTO(

        @NotNull(message = "brand is mandatory")
        String brand,

        @NotNull(message = "model is mandatory")
        String model,

        @NotNull(message = "color is mandatory")
        String color,

        @NotNull(message = "licence plate is mandatory")
        String licencePlate,

        @NotNull(message = "year is mandatory")
        @Min(value = 1950, message = "year must be at least 1950")
        Integer year,

        @NotNull(message = "type is mandatory")
        Type type
) {
    public Vehicle toEntity() {
        var vehicle = new Vehicle();

        vehicle.setBrand(this.brand);
        vehicle.setModel(this.model);
        vehicle.setColor(this.color);
        vehicle.setLicencePlate(this.licencePlate);
        vehicle.setYear(this.year);
        vehicle.setType(this.type);

        return vehicle;
    }
}
