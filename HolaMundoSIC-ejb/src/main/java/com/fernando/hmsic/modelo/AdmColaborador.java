/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fernando.hmsic.modelo;

import com.fernando.ejb.comportamiento.EntidadActiva;
import com.fernando.ejb.comportamiento.Ordenar;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Fernando
 */
@Entity
@Table(name = "adm_colaborador")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdmColaborador.findAll", query = "SELECT a FROM AdmColaborador a")
    , @NamedQuery(name = "AdmColaborador.findByColId", query = "SELECT a FROM AdmColaborador a WHERE a.colId = :colId")
    , @NamedQuery(name = "AdmColaborador.findByColDocumento", query = "SELECT a FROM AdmColaborador a WHERE a.colDocumento = :colDocumento")
    , @NamedQuery(name = "AdmColaborador.findByColNombre1", query = "SELECT a FROM AdmColaborador a WHERE a.colNombre1 = :colNombre1")
    , @NamedQuery(name = "AdmColaborador.findByColNombre2", query = "SELECT a FROM AdmColaborador a WHERE a.colNombre2 = :colNombre2")
    , @NamedQuery(name = "AdmColaborador.findByColApellido1", query = "SELECT a FROM AdmColaborador a WHERE a.colApellido1 = :colApellido1")
    , @NamedQuery(name = "AdmColaborador.findByColApellido2", query = "SELECT a FROM AdmColaborador a WHERE a.colApellido2 = :colApellido2")
    , @NamedQuery(name = "AdmColaborador.findByColEstado", query = "SELECT a FROM AdmColaborador a WHERE a.colEstado = :colEstado")
    , @NamedQuery(name = "AdmColaborador.findByIndversion", query = "SELECT a FROM AdmColaborador a WHERE a.indversion = :indversion")
    , @NamedQuery(name = "AdmColaborador.findByColUsuario", query = "SELECT a FROM AdmColaborador a WHERE a.colUsuario = :colUsuario")
    , @NamedQuery(name = "AdmColaborador.findByColClave", query = "SELECT a FROM AdmColaborador a WHERE a.colClave = :colClave")
    , @NamedQuery(name = "AdmColaborador.validarCol",query = "SELECT a FROM AdmColaborador a WHERE  a.colUsuario = :colUsuario AND  a.colClave = :colClave ")})
public class AdmColaborador implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "col_id")
    private Long colId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "col_documento")
    private String colDocumento;
    @Size(max = 150)
    @Column(name = "col_nombre1")
    @Ordenar
    private String colNombre1;
    @Size(max = 150)
    @Column(name = "col_nombre2")
    private String colNombre2;
    @Size(max = 150)
    @Ordenar
    @Column(name = "col_apellido1")
    private String colApellido1;
    @Size(max = 150)
    @Column(name = "col_apellido2")
    private String colApellido2;
    @Column(name = "col_estado")
    @EntidadActiva
    private Boolean colEstado;
    @Column(name = "indversion")
    private Integer indversion;
    @Size(max = 200)
    @Column(name = "col_usuario")
    private String colUsuario;
    @Size(max = 5000)
    @Column(name = "col_clave")
    private String colClave;
    @JoinColumn(name = "tdc_id", referencedColumnName = "tdc_id")
    @ManyToOne
    private RfTipodocumento tdcId;

    public AdmColaborador() {
    }

    public AdmColaborador(Long colId) {
        this.colId = colId;
    }

    public AdmColaborador(Long colId, String colDocumento) {
        this.colId = colId;
        this.colDocumento = colDocumento;
    }

    public Long getColId() {
        return colId;
    }

    public void setColId(Long colId) {
        this.colId = colId;
    }

    public String getColDocumento() {
        return colDocumento;
    }

    public void setColDocumento(String colDocumento) {
        this.colDocumento = colDocumento;
    }

    public String getColNombre1() {
        return colNombre1;
    }

    public void setColNombre1(String colNombre1) {
        this.colNombre1 = colNombre1;
    }

    public String getColNombre2() {
        return colNombre2;
    }

    public void setColNombre2(String colNombre2) {
        this.colNombre2 = colNombre2;
    }

    public String getColApellido1() {
        return colApellido1;
    }

    public void setColApellido1(String colApellido1) {
        this.colApellido1 = colApellido1;
    }

    public String getColApellido2() {
        return colApellido2;
    }

    public void setColApellido2(String colApellido2) {
        this.colApellido2 = colApellido2;
    }

    public Boolean getColEstado() {
        return colEstado;
    }

    public void setColEstado(Boolean colEstado) {
        this.colEstado = colEstado;
    }

    public Integer getIndversion() {
        return indversion;
    }

    public void setIndversion(Integer indversion) {
        this.indversion = indversion;
    }

    public String getColUsuario() {
        return colUsuario;
    }

    public void setColUsuario(String colUsuario) {
        this.colUsuario = colUsuario;
    }

    public String getColClave() {
        return colClave;
    }

    public void setColClave(String colClave) {
        this.colClave = colClave;
    }

    public RfTipodocumento getTdcId() {
        return tdcId;
    }

    public void setTdcId(RfTipodocumento tdcId) {
        this.tdcId = tdcId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (colId != null ? colId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdmColaborador)) {
            return false;
        }
        AdmColaborador other = (AdmColaborador) object;
        if ((this.colId == null && other.colId != null) || (this.colId != null && !this.colId.equals(other.colId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fernando.holamundosic.modelo.AdmColaborador[ colId=" + colId + " ]";
    }

}
