/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fernando.holamundosicrest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernando.hmsic.modelo.AdmColaborador;
import com.fernando.hmsic.modelo.RfMarca;
import com.fernando.hmsic.util.UsuarioDTO;
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
public class PrincipalJSFBean extends BaseJSFBean implements Serializable {

    AdmEncuestaCLN admEncuestaCLN = new AdmEncuestaCLN();

    private AdmColaborador admColaboradorSession = new AdmColaborador();

    private List<RfMarca> lstMarcas = new ArrayList<>();

    private UsuarioDTO usuarioDTO = new UsuarioDTO();

    private void cargarMarcas() {

        lstMarcas = Arrays.asList(admEncuestaCLN.getLstEntidad(new RfMarca(), "RfMarca").readEntity(RfMarca[].class));

    }

    @PostConstruct
    public void init() {
        cargarMarcas();

    }

    public String validarColaborador_A() {
        admColaboradorSession = admEncuestaCLN.validarColaborador(usuarioDTO).readEntity(AdmColaborador.class);
        if (admColaboradorSession != null && admColaboradorSession.getColId() != null) {
            reglaNav = "ingresar";
        } else {
            reglaNav = "";

        }
        return reglaNav;
    }

    public List<RfMarca> getLstMarcas() {
        return lstMarcas;
    }

    public void setLstMarcas(List<RfMarca> lstMarcas) {
        this.lstMarcas = lstMarcas;
    }

    public AdmColaborador getAdmColaboradorSession() {
        return admColaboradorSession;
    }

    public void setAdmColaboradorSession(AdmColaborador admColaboradorSession) {
        this.admColaboradorSession = admColaboradorSession;
    }

    public UsuarioDTO getUsuarioDTO() {
        return usuarioDTO;
    }

    public void setUsuarioDTO(UsuarioDTO usuarioDTO) {
        this.usuarioDTO = usuarioDTO;
    }

}
