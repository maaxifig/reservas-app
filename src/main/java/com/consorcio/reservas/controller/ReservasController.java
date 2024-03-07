package com.consorcio.reservas.controller;

import com.consorcio.reservas.model.Reserva;
import com.consorcio.reservas.model.Usuario;
import com.consorcio.reservas.service.ReservasService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/reserva")
public class ReservasController {


    ReservasService reservasService;
    @PostMapping("/create")
    public void createReserva(){
        reservasService.createReserva();
    }
    @PutMapping("/update")
    public void updateReserva(String reserva){
        //Castear reserva json a reservaActual
        Reserva reservaActual = new Reserva(); //ESTO VA A SER REEMPLAZADO POR LA RESERVA CASTEADA
        reservasService.updateReserva(reservaActual);

    }
    @DeleteMapping("/delete")
    public void deleteReserva(){
        reservasService.deleteReserva();
    }
}
