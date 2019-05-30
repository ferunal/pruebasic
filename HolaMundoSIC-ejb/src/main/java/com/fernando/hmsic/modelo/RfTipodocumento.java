/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fernando.hmsic.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fernando.ejb.comportamiento.Ordenar;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis Fernando
 */
@Entity
@Table(name = "rf_tipodocumento")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RfTipodocumento.findAll", query = "SELECT r FROM RfTipodocumento r")
    , @NamedQuery(name = "RfTipodocumento.findByTdcId", query = "SELECT r FROM RfTipodocumento r WHERE r.tdcId = :tdcId")
    , @NamedQuery(name = "RfTipodocumento.findByTdcNombre", query = "SELECT r FROM RfTipodocumento r WHERE r.tdcNombre = :tdcNombre")
    , @NamedQuery(name = "RfTipodocumento.findByTdcEstado", query = "SELECT r FROM RfTipodocumento r WHERE r.tdcEstado = :tdcEstado")})
public class RfTipodocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "tdc_id")
    private String tdcId;
    @Size(max = 500)
    @Ordenar
    @Column(name = "tdc_nombre")
    private String tdcNombre;
    @Column(name = "tdc_estado")
    private Boolean tdcEstado;
    @OneToMany(mappedBy = "tdcId")
    private List<NegEncuesta> negEncuestaList;
    @OneToMany(mappedBy = "tdcId")
    private List<AdmColaborador> admColaboradorList;

    public RfTipodocumento() {
    }

    public RfTipodocumento(String tdcId) {
        this.tdcId = tdcId;
    }

    public String getTdcId() {
        return tdcId;
    }

    public void setTdcId(String tdcId) {
        this.tdcId = tdcId;
    }

    public String getTdcNombre() {
        return tdcNombre;
    }

    public void setTdcNombre(String tdcNombre) {
        this.tdcNombre = tdcNombre;
    }

    public Boolean getTdcEstado() {
        return tdcEstado;
    }

    public void setTdcEstado(Boolean tdcEstado) {
        this.tdcEstado = tdcEstado;
    }

    @XmlTransient
    @JsonIgnore
    public List<NegEncuesta> getNegEncuestaList() {
        return negEncuestaList;
    }

    public void setNegEncuestaList(List<NegEncuesta> negEncuestaList) {
        this.negEncuestaList = negEncuestaList;
    }

    @XmlTransient
    @JsonIgnore
    public List<AdmColaborador> getAdmColaboradorList() {
        return admColaboradorList;
    }

    public void setAdmColaboradorList(List<AdmColaborador> admColaboradorList) {
        this.admColaboradorList = admColaboradorList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tdcId != null ? tdcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RfTipodocumento)) {
            return false;
        }
        RfTipodocumento other = (RfTipodocumento) object;
        if ((this.tdcId == null && other.tdcId != null) || (this.tdcId != null && !this.tdcId.equals(other.tdcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fernando.holamundosic.modelo.RfTipodocumento[ tdcId=" + tdcId + " ]";
    }
    
}
