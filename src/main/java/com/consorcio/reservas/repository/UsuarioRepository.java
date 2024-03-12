package com.consorcio.reservas.repository;

import com.consorcio.reservas.model.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UsuarioRepository extends MongoRepository<Usuario, String> {
    Optional<Usuario> findByMail(String mail);
}
