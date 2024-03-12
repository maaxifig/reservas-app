package com.consorcio.reservas.response;

import com.consorcio.reservas.model.Usuario;
import lombok.Data;

import java.util.List;
@Data
public class ResponseUser {
    private List<Usuario> data;
    private List<ResponseError> errors;
}
