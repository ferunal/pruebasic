/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fernando.holamundosicrest;

import com.fernando.holamundosicrest.rest.client.AdmEncuestaCLN;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import org.icefaces.util.JavaScriptRunner;

/**
 *
 * @author Luis Fernando
 */
public class BaseJSFBean {

    protected AdmEncuestaCLN admEncuestaCLN = new AdmEncuestaCLN();
    protected String reglaNav;
    protected Integer numPanel;
    protected String error;

   
    public Integer getNumPanel() {
        return numPanel;
    }

    public void setNumPanel(Integer numPanel) {
        this.numPanel = numPanel;
    }

    public String getReglaNav() {
        return reglaNav;
    }

    protected boolean esNumero(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    protected boolean validarEmail(String email) {
        boolean emailValido = true;

        if(email.contains("@")){
         emailValido=true;
        }else{
        emailValido=false;
        }
        return emailValido;
    }

    public void setReglaNav(String reglaNav) {
        this.reglaNav = reglaNav;
    }



    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
