/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fernando.holamundosicrest;

import com.fernando.hmsic.modelo.NegEncuesta;

/**
 *
 * @author Luis Fernando
 */
public class EncuestaDTO {
    private NegEncuesta encuesta = new NegEncuesta();
    private boolean seleccionado;

    public EncuestaDTO() {
    }
    
     public EncuestaDTO(NegEncuesta ne) {
         this.encuesta = ne;
    }
    


    public NegEncuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(NegEncuesta encuesta) {
        this.encuesta = encuesta;
    }

    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }
}
