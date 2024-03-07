package com.consorcio.reservas.repository;

import com.consorcio.reservas.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
}
