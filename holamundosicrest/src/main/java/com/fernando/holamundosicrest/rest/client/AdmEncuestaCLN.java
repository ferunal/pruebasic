/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fernando.holamundosicrest.rest.client;

import com.fernando.hmsic.modelo.NegEncuesta;
import com.fernando.hmsic.util.UsuarioDTO;
import java.util.List;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Jersey REST client generated for REST resource:GestionDocumento
 * [/documento]<br>
 * USAGE:
 * <pre>
        AdmEncuestaCLN client = new AdmEncuestaCLN();
        Object response = client.XXX(...);
        // do whatever with response
        client.close();
 </pre>
 *
 * @author ferunal
 */
public class AdmEncuestaCLN {

    private final WebTarget webTarget;
    private final Client client;
    private static final String BASE_URI = "http://localhost:28080/holamundosicweb/webresources";

    public AdmEncuestaCLN() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("encuesta");
    }

    public Response getLstEntidad(Object requestEntity, String tabla) throws ClientErrorException {
        return webTarget.path("/consultartabla").queryParam("entidad", tabla). request(javax.ws.rs.core.MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON).
                post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }
    
    public Response validarColaborador(UsuarioDTO usuarioDTO) throws ClientErrorException {
        return webTarget.path("/validarusuario"). request(javax.ws.rs.core.MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON).
                post(javax.ws.rs.client.Entity.entity(usuarioDTO, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }
    
      public Response grabarEncuesta(NegEncuesta negEncuesta) throws ClientErrorException {
        return webTarget.path("/grabarencuesta"). request(javax.ws.rs.core.MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON).
                post(javax.ws.rs.client.Entity.entity(negEncuesta, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }
      
       public Response eliminarEncuesta(List<Long> negEncuestaIds) throws ClientErrorException {
        return webTarget.path("/eliminarencuesta"). request(javax.ws.rs.core.MediaType.APPLICATION_JSON).
                accept(MediaType.APPLICATION_JSON).
                post(javax.ws.rs.client.Entity.entity(negEncuestaIds, javax.ws.rs.core.MediaType.APPLICATION_JSON), Response.class);
    }

    
    public void close() {
        client.close();
    }

}
