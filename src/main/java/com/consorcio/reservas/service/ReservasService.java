package com.consorcio.reservas.service;

import com.consorcio.reservas.model.Reserva;
import com.consorcio.reservas.repository.ReservaRepository;
import com.consorcio.reservas.response.Response;
import com.consorcio.reservas.response.ResponseData;
import com.consorcio.reservas.response.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;

public class ReservasService {

    @Autowired
    ReservaRepository reservaRepository;


    public Response createReserva(Reserva reserva){

        Response response = new Response();
        ResponseError responseError = new ResponseError();

        try{
            reservaRepository.save(reserva);
            ResponseData responseData = new ResponseData();
            responseData.setMessage("Reserva creada correctamente");
            response.setData(responseData);
        } catch (Exception e){
            responseError.setMessage(e.getMessage());
            response.setErrors(responseError);
        }

        return response;
    }

    public Reserva updateReserva(Reserva reserva){
        Reserva reservaActual = new Reserva();

        return reservaActual;
    }

    public void deleteReserva(){//Aca tengo que recibir una reserva, json seguramente y castearla.
        //reserva.delete
    }
}
