/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fernando.web.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fernando.ejb.AdmDocumentoSLBean;
import com.fernando.hmsic.modelo.NegEncuesta;
import com.fernando.hmsic.util.DynamicMixIn;
import com.fernando.hmsic.util.UsuarioDTO;
import java.util.List;
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

    @Path("/grabarencuesta")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response grabarEncuesta(String objEncuesta) {
        Response response;
        try {
            ObjectMapper mapper = new ObjectMapper();

            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

            SimpleFilterProvider sfp = new SimpleFilterProvider();
            sfp.addFilter("filtroDinamico", SimpleBeanPropertyFilter.filterOutAllExcept("encId"));

            response = Response.ok(mapper.
                    addMixIn(NegEncuesta.class, DynamicMixIn.class).
                    writer(sfp).
                    writeValueAsString(documentoSLBean.grabarEncuesta(objEncuesta))).build();

            return response;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(GestionEncuesta.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }

    @Path("/eliminarencuesta")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response eliminarEncuesta(List<Long> encIds) {
        Response response;
        try {
            ObjectMapper mapper = new ObjectMapper();

            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

            documentoSLBean.eliminarEncuesta(encIds);

            response = Response.ok(mapper.
                    writeValueAsString(encIds)).build();

            return response;
        } catch (JsonProcessingException ex) {
            Logger.getLogger(GestionEncuesta.class.getName()).log(Level.SEVERE, null, ex);
            return Response.serverError().build();
        }
    }

}
