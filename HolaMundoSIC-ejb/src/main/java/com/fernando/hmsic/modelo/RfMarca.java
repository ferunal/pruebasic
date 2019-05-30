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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Luis Fernando
 */
@Entity
@Table(name = "rf_marca")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RfMarca.findAll", query = "SELECT r FROM RfMarca r")
    , @NamedQuery(name = "RfMarca.findByMrcId", query = "SELECT r FROM RfMarca r WHERE r.mrcId = :mrcId")
    , @NamedQuery(name = "RfMarca.findByMrcNombre", query = "SELECT r FROM RfMarca r WHERE r.mrcNombre = :mrcNombre")
    , @NamedQuery(name = "RfMarca.findByMrcDesc", query = "SELECT r FROM RfMarca r WHERE r.mrcDesc = :mrcDesc")
    , @NamedQuery(name = "RfMarca.findByMrcEstado", query = "SELECT r FROM RfMarca r WHERE r.mrcEstado = :mrcEstado")
    , @NamedQuery(name = "RfMarca.findByIndversion", query = "SELECT r FROM RfMarca r WHERE r.indversion = :indversion")})
public class RfMarca implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "mrc_id")
    private Integer mrcId;
    @Size(max = 200)
    @Ordenar
    @Column(name = "mrc_nombre")
    private String mrcNombre;
    @Size(max = 2147483647)
    @Column(name = "mrc_desc")
    private String mrcDesc;
    @Column(name = "mrc_estado")
    private Boolean mrcEstado;
    @Column(name = "indversion")
    private Integer indversion;
    @OneToMany(mappedBy = "mrcId")
    private List<NegEncuesta> negEncuestaList;

    public RfMarca() {
    }

    public RfMarca(Integer mrcId) {
        this.mrcId = mrcId;
    }

    public Integer getMrcId() {
        return mrcId;
    }

    public void setMrcId(Integer mrcId) {
        this.mrcId = mrcId;
    }

    public String getMrcNombre() {
        return mrcNombre;
    }

    public void setMrcNombre(String mrcNombre) {
        this.mrcNombre = mrcNombre;
    }

    public String getMrcDesc() {
        return mrcDesc;
    }

    public void setMrcDesc(String mrcDesc) {
        this.mrcDesc = mrcDesc;
    }

    public Boolean getMrcEstado() {
        return mrcEstado;
    }

    public void setMrcEstado(Boolean mrcEstado) {
        this.mrcEstado = mrcEstado;
    }

    public Integer getIndversion() {
        return indversion;
    }

    public void setIndversion(Integer indversion) {
        this.indversion = indversion;
    }

    @XmlTransient
    @JsonIgnore
    public List<NegEncuesta> getNegEncuestaList() {
        return negEncuestaList;
    }

    public void setNegEncuestaList(List<NegEncuesta> negEncuestaList) {
        this.negEncuestaList = negEncuestaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mrcId != null ? mrcId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RfMarca)) {
            return false;
        }
        RfMarca other = (RfMarca) object;
        if ((this.mrcId == null && other.mrcId != null) || (this.mrcId != null && !this.mrcId.equals(other.mrcId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fernando.holamundosic.modelo.RfMarca[ mrcId=" + mrcId + " ]";
    }
    
}
