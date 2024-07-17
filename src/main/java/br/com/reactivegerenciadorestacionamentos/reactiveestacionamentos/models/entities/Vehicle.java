package br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.models.entities;

import br.com.reactivegerenciadorestacionamentos.reactiveestacionamentos.models.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "vehicles")
public class Vehicle {

    @Id
    private String id;

    private String brand;

    private String model;

    private String color;

    private Integer year;

    @Indexed(unique = true)
    private String licencePlate;

    private Type type;

}
