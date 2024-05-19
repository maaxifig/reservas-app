package com.consorcio.reservas.controller;

import com.consorcio.reservas.model.Reserva;
import com.consorcio.reservas.model.Usuario;
import com.consorcio.reservas.repository.ReservaRepository;
import com.consorcio.reservas.response.Response;
import com.consorcio.reservas.response.ResponseError;
import com.consorcio.reservas.response.ResponseUser;
import com.consorcio.reservas.service.ReservasService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/reserva")
public class ReservasController {

    ReservaRepository reservaRepository;
    ReservasService reservasService;
    @PostMapping("/create")
    public ResponseEntity<Response> createReserva(@RequestBody Reserva reserva){

        Response response = reservasService.createReserva(reserva);
        return new ResponseEntity<>(response, getStatusCode(response));
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


    @PostMapping("/{reservaId}/cargar-comprobante")
    public ResponseEntity<Response> uploadComprobantePago(@PathVariable String reservaId,
                                                          @RequestParam("file") MultipartFile file) throws IOException {

        Response response = new Response();
        Reserva reserva = reservaRepository.findById(reservaId).orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        reserva.setComprobanteDePago(file.getBytes());
        reservaRepository.save(reserva);

        return new ResponseEntity<>(response,getStatusCode(response));
    }

//    Arreglar esto para que reciba un objeto tipo Response
    private HttpStatus getStatusCode(Response response){
//        List<ResponseError> error = response.getErrors();
//        List<Usuario> data = response.getData();
//        if(error == null && data == null){
//            return HttpStatus.NO_CONTENT;
//        }
//        if(error != null){
//            if(data != null){
//                return HttpStatus.CREATED;
//            }
//            return HttpStatus.INTERNAL_SERVER_ERROR;
//        }
        return HttpStatus.OK;
    }

}
