package com.consorcio.reservas.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Response {

    private List<ResponseData> data = new ArrayList<>();
    private List<ResponseError> errors = new ArrayList<>();
}
