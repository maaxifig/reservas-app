package com.consorcio.reservas.response;

import com.consorcio.reservas.model.ResponseError;
import lombok.Data;

@Data
public class Response {

    private ResponseData data;
    private ResponseError errors;
}
