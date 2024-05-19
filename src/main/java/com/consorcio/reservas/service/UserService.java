package com.consorcio.reservas.service;

import com.consorcio.reservas.response.Response;
import com.consorcio.reservas.response.ResponseData;
import com.consorcio.reservas.response.ResponseError;
import com.consorcio.reservas.model.Usuario;
import com.consorcio.reservas.repository.UsuarioRepository;
import com.consorcio.reservas.response.ResponseUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.MongoSocketException;
import com.mongodb.MongoWriteException;
import jakarta.ws.rs.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public ResponseUser getUser(String email) {
        ResponseUser response = new ResponseUser();
        List<Usuario> responseUserList = new ArrayList<>();
        ResponseError responseError = new ResponseError();
        try{
            Optional<Usuario> user = usuarioRepository.findByMail(email);
            if (user.isPresent()){
                Usuario userFound = user.get();
                responseUserList.add(userFound);
                response.setData(responseUserList);
            }
        } catch (Exception e){
            responseError.setMessage(e.getMessage());
            List<ResponseError> responseErrorsList = new ArrayList<>();
            responseErrorsList.add(responseError);
            response.setErrors(responseErrorsList);
        }
        return response;
    }

    public Response createUser(Usuario user) {

        Response response = new Response();
        ResponseError responseError = new ResponseError();
        try{

            usuarioRepository.save(user);
            ResponseData responseData = new ResponseData();
            responseData.setMessage("Usuario creado correctamente");
            response.setData(responseData);

        }catch (MongoWriteException e){
            logger.error(e.getMessage());
            responseError.setMessage(e.getMessage());
            response.setErrors(responseError);
        }catch (MongoSocketException e){
            logger.error(e.getMessage());
            responseError.setMessage(e.getMessage());
            response.setErrors(responseError);
        } catch (Exception e){
            responseError.setMessage(e.getMessage());
            response.setErrors(responseError);
        }

        return response;
    }

    public Response updateUser(Usuario user){

        Response response = new Response();
        ResponseError responseError = new ResponseError();
        ResponseData  responseData = new ResponseData();
        Usuario usuarioActual = getUser(user.getMail()).getData().get(0);
        if(usuarioActual != null){
            usuarioActual.setNombre(user.getNombre());
            usuarioActual.setApellido(user.getApellido());
            usuarioActual.setDepto(user.getDepto());
            usuarioActual.setMail(user.getMail());
            usuarioRepository.save(usuarioActual);
            responseData.setMessage("Usuario actualizado correctamente");
            response.setData(responseData);
        }else{

            responseError.setMessage("El usuario no existe");
            response.setErrors(responseError);
        }
        return response;
    }

    public void deleteUser (Usuario usuario) {
        //usuario.delete()
    }

    public Response login (String json){
        Response resp = new Response();
        ResponseData data = new ResponseData();
        ResponseError error = new ResponseError();

        try {
            JsonNode jsonParsed = objectMapper.readTree(json);
            String email = jsonParsed.path("email").asText();
            Optional<Usuario> user = usuarioRepository.findByMail(email);
            if (user.isPresent()){
                Usuario userFound = user.get();
                if(userFound.validatePassword(jsonParsed.path(("password")).asText())){
                    data.setMessage("Logueado correctamente");
                    resp.setData(data);
                } else {
                    error.setMessage("Usuario o contraseña incorrecto");
                    resp.setErrors(error);
                }

            }

        } catch (IOException e){
            error.setMessage("Hubo un error desconocido");
            resp.setErrors(error);
        }


        return resp;
    }
}
