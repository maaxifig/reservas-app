package com.consorcio.reservas.service;

import com.consorcio.reservas.model.Reserva;
import com.consorcio.reservas.repository.ReservaRepository;
import com.consorcio.reservas.response.Response;
import com.consorcio.reservas.response.ResponseData;
import com.consorcio.reservas.response.ResponseError;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.ArrayList;

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
            List<ResponseData> dataList = new ArrayList<>();
            dataList.add(responseData);
            response.setData(dataList);
        } catch (Exception e){
            responseError.setMessage(e.getMessage());
            List<ResponseError> errorList = new ArrayList<>();
            errorList.add(responseError);
            response.setErrors(errorList);
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
