package com.consorcio.reservas.service;

import com.consorcio.reservas.response.Response;
import com.consorcio.reservas.response.ResponseData;
import com.consorcio.reservas.model.ResponseError;
import com.consorcio.reservas.model.Usuario;
import com.consorcio.reservas.repository.UsuarioRepository;
import com.mongodb.MongoSocketException;
import com.mongodb.MongoWriteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario getUser(String email){
        Usuario user = new Usuario();


        return user;
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

    public Usuario updateUser(Usuario user){
        Usuario usuarioActual;


        Usuario usuarioModificado = new Usuario();

        return usuarioModificado;
    }

    public void deleteUser (Usuario usuario) {
        //usuario.delete()
    }
}
