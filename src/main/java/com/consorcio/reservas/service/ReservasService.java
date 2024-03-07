package com.consorcio.reservas.service;

import com.consorcio.reservas.model.Reserva;

public class ReservasService {

    public Reserva createReserva(){
        Reserva reserva = new Reserva();

        return reserva;
    }

    public Reserva updateReserva(Reserva reserva){
        Reserva reservaActual = new Reserva();

        return reservaActual;
    }

    public void deleteReserva(){//Aca tengo que recibir una reserva, json seguramente y castearla.
        //reserva.delete
    }
}
