package com.consorcio.reservas.model;

import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@Document(collection = "Usuarios")
public class Usuario {

    @Id
    private String id;
    private String nombre;
    private String apellido;
    private String depto;
    private String mail;
    private String hashedPassword;


    public void setPassword(String contrasena) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.hashedPassword = passwordEncoder.encode(contrasena); // Generar hash de la contraseña
    }

    public boolean validatePassword(String contrasena) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(contrasena, this.hashedPassword); // Validar la contraseña ingresada
    }

}
