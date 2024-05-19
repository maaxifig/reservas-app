package com.consorcio.reservas.repository;

import com.consorcio.reservas.model.Reserva;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReservaRepository extends MongoRepository<Reserva, String> {
    Optional<Reserva> findById(String id);
}
