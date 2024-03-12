package com.consorcio.reservas.response;

import lombok.Data;

@Data
public class Response {

    private ResponseData data;
    private ResponseError errors;
}
