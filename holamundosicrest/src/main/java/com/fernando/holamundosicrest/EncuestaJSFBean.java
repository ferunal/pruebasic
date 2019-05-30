/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fernando.holamundosicrest;

import com.fernando.hmsic.modelo.NegEncuesta;
import com.fernando.hmsic.modelo.RfMarca;
import com.fernando.holamundosicrest.rest.client.AdmEncuestaCLN;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Luis Fernando
 */
@SessionScoped
@Named
public class EncuestaJSFBean extends BaseJSFBean implements Serializable {

    @Inject
    PrincipalJSFBean principalJSFBean;
    private List<RfMarca> lstMarcas = new ArrayList<>();
    private Integer marcaSel;

    public void navegacionLateral_ActionEvent(ActionEvent ae) {
        numPanel = Integer.valueOf((String) ae.getComponent().getAttributes().get("numPanel"));
        switch (numPanel) {
            case 2:
                cargarEncuestas();
                break;
        }
    }

    private List<EncuestaDTO> lstEncuestaDTOs = new ArrayList<>();

    private List<SelectItem> lstItemsMarcas = new ArrayList<>();

    public List<SelectItem> getLstItemsMarcas() {
        return lstItemsMarcas;
    }

    public void setLstItemsMarcas(List<SelectItem> lstItemsMarcas) {
        this.lstItemsMarcas = lstItemsMarcas;
    }

    private NegEncuesta encuesta = new NegEncuesta();

    public NegEncuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(NegEncuesta encuesta) {
        this.encuesta = encuesta;
    }

    public void deiligenciarrEncuesta() {
        numPanel = 1;
    }

    public void listarEncuesta() {
        numPanel = 2;
    }

    @PostConstruct
    public void init() {
        numPanel = 1;
    }

    public void cargarMarcas() {
lstItemsMarcas.clear();
        lstMarcas = Arrays.asList(admEncuestaCLN.getLstEntidad(new RfMarca(), "RfMarca").readEntity(RfMarca[].class));
        lstItemsMarcas.add(new SelectItem(-1, "Seleccione>>"));
        lstItemsMarcas.addAll(lstMarcas.stream().map(e -> new SelectItem(e.getMrcId(), e.getMrcNombre())).collect(Collectors.toList()));

    }

    public void cargarEncuestas() {

        List<NegEncuesta> lstEncuestas = Arrays.asList(admEncuestaCLN.getLstEntidad(new NegEncuesta(), "NegEncuesta").readEntity(NegEncuesta[].class));
        lstEncuestaDTOs = lstEncuestas.stream().map(e -> new EncuestaDTO(e)).collect(Collectors.toList());
    }

    private boolean validarEncuesta() {
        boolean validarForm = true;
        StringBuilder strBMensaje = new StringBuilder();
        if (!esNumero(encuesta.getEncNumdoc())) {

            strBMensaje.append("El documento solo puede contener números\n");
            validarForm = false;
        }
        if (!validarEmail(encuesta.getEncEmail())) {

            strBMensaje.append("El email es incorrecto\n");
            validarForm = false;
        }
        if (marcaSel.equals(-1)) {

            strBMensaje.append("Debe seleccionar la marca\n");
            validarForm = false;
        }
        error = strBMensaje.toString();

        return validarForm;
    }

    public void guardarEncuesta() {
        if (validarEncuesta()) {
            encuesta.setEncFecharesp(new Date());
            encuesta.setMrcId(new RfMarca(marcaSel));
            encuesta = admEncuestaCLN.grabarEncuesta(encuesta).readEntity(NegEncuesta.class);
            if (encuesta.getEncId() == null) {
                System.out.println("Error al grabar encuesta..");
                 error = "Error al grabar encuesta..";
            } else {
                encuesta = new NegEncuesta();
            }
            error = "Grabado exitoso";
        }
    }

    public void eliminarEncuestas_A() {

        List<Long> lstEncuestasEliminar = Arrays.asList(admEncuestaCLN.eliminarEncuesta(lstEncuestaDTOs.stream().
                filter(e -> e.isSeleccionado()).
                map(enc -> enc.getEncuesta().getEncId()).
                collect(Collectors.toList())).readEntity(Long[].class));
        cargarEncuestas();

    }

    public List<RfMarca> getLstMarcas() {
        return lstMarcas;
    }

    public void setLstMarcas(List<RfMarca> lstMarcas) {
        this.lstMarcas = lstMarcas;
    }

    public Integer getMarcaSel() {
        return marcaSel;
    }

    public void setMarcaSel(Integer marcaSel) {
        this.marcaSel = marcaSel;
    }

    public List<EncuestaDTO> getLstEncuestaDTOs() {
        return lstEncuestaDTOs;
    }

    public void setLstEncuestaDTOs(List<EncuestaDTO> lstEncuestaDTOs) {
        this.lstEncuestaDTOs = lstEncuestaDTOs;
    }
}
