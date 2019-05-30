/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fernando.holamundosicrest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernando.hmsic.modelo.AdmColaborador;
import com.fernando.holamundosicrest.rest.client.AdmEncuestaCLN;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.ws.rs.core.Response;

/**
 *
 * @author ferunal
 */
@SessionScoped
@Named
public class PrincipalJSFBean implements Serializable {

    AdmEncuestaCLN admDocumentoCLN = new AdmEncuestaCLN();

    private List<AdmColaborador> colaboradores = new ArrayList<>();

    private void cargarColaboradores() {

        Response resp = admDocumentoCLN.getLstEntidad("{}", "AdmColaborador");
        colaboradores = Arrays.asList(admDocumentoCLN.getLstEntidad(new AdmColaborador(), "AdmColaborador").readEntity(AdmColaborador[].class));

    }

    @PostConstruct
    public void init() {
        cargarColaboradores();
    }

    public List<AdmColaborador> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<AdmColaborador> colaboradores) {
        this.colaboradores = colaboradores;
    }

}
