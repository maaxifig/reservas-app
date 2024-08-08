package com.consorcio.reservas.response;

import com.consorcio.reservas.model.Usuario;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ResponseUser {
    private List<Usuario> data = new ArrayList<>();
    private List<ResponseError> errors = new ArrayList<>();
}
