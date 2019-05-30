/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fernando.holamundosicrest;

import com.fernando.hmsic.modelo.AdmColaborador;
import com.fernando.hmsic.util.UsuarioDTO;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author ferunal
 */
@SessionScoped
@Named
public class PrincipalJSFBean extends BaseJSFBean implements Serializable {

    private AdmColaborador admColaboradorSession = new AdmColaborador();

    private UsuarioDTO usuarioDTO = new UsuarioDTO();

    @PostConstruct
    public void init() {
        error = "";
    }

    public String validarColaborador_A() {
        admColaboradorSession = admEncuestaCLN.validarColaborador(usuarioDTO).readEntity(AdmColaborador.class);
        if (admColaboradorSession != null && admColaboradorSession.getColId() != null) {
            try {
                FacesContext fc = FacesContext.getCurrentInstance();
                ELContext elc = fc.getELContext();
                String beanEncuesta = "encuestaJSFBean";
                Object objDestJSFBean = elc.getELResolver().getValue(elc, null, beanEncuesta);
                Class cls = objDestJSFBean.getClass();//

                Method mthdInit = cls.getDeclaredMethod("cargarMarcas", new Class<?>[0]);
                mthdInit.invoke(objDestJSFBean, new Object[0]);
                reglaNav = "ingresar";
            } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException ex) {
                error = "Error al ingresar";

                Logger.getLogger(PrincipalJSFBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            error = "Usuario o clave incorrecto";

            reglaNav = "";

        }
        return reglaNav;
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
