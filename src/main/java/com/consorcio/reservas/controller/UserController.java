package com.consorcio.reservas.controller;

import com.consorcio.reservas.response.Response;
import com.consorcio.reservas.response.ResponseData;
import com.consorcio.reservas.response.ResponseError;
import com.consorcio.reservas.model.Usuario;
import com.consorcio.reservas.response.ResponseUser;
import com.consorcio.reservas.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);


    @GetMapping("/{email}")
    public ResponseEntity<ResponseUser> getUser(@PathVariable String email) throws JsonProcessingException {
        ResponseUser res = userService.getUser(email);
//        String json = objectMapper.writeValueAsString(user);

        return new ResponseEntity<>(res, getStatusCode(res));
    }


    @PostMapping("/create")
    public ResponseEntity<Response> createUser (@RequestBody Usuario user){
        logger.info(String.valueOf(user));
        Response res = userService.createUser(user);
        return new ResponseEntity<>(res, getStatusCode(res));

    }

    @PutMapping("")
    public ResponseEntity<Response> updateUser(@RequestBody Usuario user){

       Response res = userService.updateUser(user);
        return new ResponseEntity<>(res, getStatusCode(res));
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody Usuario user){
        userService.deleteUser(user);
    }



    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody String json){
        Response res = userService.login(json);

        return new ResponseEntity<>(res, getStatusCode(res));
    }
    private HttpStatus getStatusCode(Response response){
        ResponseError error = response.getErrors();
        ResponseData data = response.getData();
        if(error == null && data == null){
            return HttpStatus.NO_CONTENT;
        }
        if(error != null){
            if(data != null){
                return HttpStatus.CREATED;
            }
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }

    private HttpStatus getStatusCode(ResponseUser response){
        List<ResponseError> error = response.getErrors();
        List<Usuario> data = response.getData();
        if(error == null && data == null){
            return HttpStatus.NO_CONTENT;
        }
        if(error != null){
            if(data != null){
                return HttpStatus.CREATED;
            }
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.OK;
    }


}
