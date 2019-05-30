/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fernando.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernando.ejb.AdmDocumentoSLBean;
import com.fernando.hmsic.util.UsuarioDTO;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author ferunal
 */
@Path("/encuesta")
public class GestionEncuesta {

    @EJB
    AdmDocumentoSLBean documentoSLBean;

    @Path("/validarusuario")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getLstEntidad(UsuarioDTO pUsuarioDTO) {
        Response response;
        try {
            ObjectMapper mapper = new ObjectMapper();

            response = Response.ok(mapper.writeValueAsString(documentoSLBean.validarColaborador(pUsuarioDTO))).build();

            return response;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(GestionEncuesta.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }

    @Path("/consultartabla")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getLstEntidad(String objEntidad, @QueryParam("entidad") String pEntidad, @QueryParam("activa") @DefaultValue("-1") Integer pActiva,
            @QueryParam("start") @DefaultValue("0") int start, @QueryParam("size") @DefaultValue("0") int size) {
        Response response;
        try {
            ObjectMapper mapper = new ObjectMapper();

            response = Response.ok(mapper.writeValueAsString(documentoSLBean.consultarEntidad(objEntidad, pEntidad, pActiva, start, size))).build();

            return response;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(GestionEncuesta.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }

}
