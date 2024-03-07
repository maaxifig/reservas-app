package com.consorcio.reservas.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Reservas")
public class Reserva {

    @Id
    private String id;
    private String nombre;
    private String date;
    private String depto;
    private String turno;
}
