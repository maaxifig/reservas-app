package com.consorcio.reservas.controller;

import com.consorcio.reservas.response.Response;
import com.consorcio.reservas.response.ResponseData;
import com.consorcio.reservas.model.ResponseError;
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
        ResponseUser res = new ResponseUser();
        Usuario user = new Usuario(); //userService.getUser(email);
        user.setDepto("asd");
        user.setNombre("vasa");
        user.setApellido("afdg");
        List<Usuario> responseUserList = new ArrayList<>();
        responseUserList.add(user);
        res.setResponseUserData(responseUserList);
//        String json = objectMapper.writeValueAsString(user);
//        responseData.setMessage(json);
//        res.setData(responseData);


        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @PostMapping("/create")
    public ResponseEntity<Response> createUser (@RequestBody Usuario user){
        logger.info(String.valueOf(user));
        Response res = userService.createUser(user);
        return new ResponseEntity<>(res, getStatusCode(res));

    }

    @PutMapping("/update")
    public void updateUser(@RequestBody Usuario user){

        userService.updateUser(user);
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody Usuario user){
        userService.deleteUser(user);
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


}
