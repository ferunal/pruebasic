/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fernando.hmsic.modelo;

import com.fernando.ejb.comportamiento.Ordenar;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Luis Fernando
 */
@Entity
@Table(name = "neg_encuesta")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NegEncuesta.findAll", query = "SELECT n FROM NegEncuesta n")
    , @NamedQuery(name = "NegEncuesta.findByEncId", query = "SELECT n FROM NegEncuesta n WHERE n.encId = :encId")
    , @NamedQuery(name = "NegEncuesta.findByEncNumdoc", query = "SELECT n FROM NegEncuesta n WHERE n.encNumdoc = :encNumdoc")
    , @NamedQuery(name = "NegEncuesta.findByEncEmail", query = "SELECT n FROM NegEncuesta n WHERE n.encEmail = :encEmail")
    , @NamedQuery(name = "NegEncuesta.findByEncFecharesp", query = "SELECT n FROM NegEncuesta n WHERE n.encFecharesp = :encFecharesp")
    , @NamedQuery(name = "NegEncuesta.findByIndversion", query = "SELECT n FROM NegEncuesta n WHERE n.indversion = :indversion")})
public class NegEncuesta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "enc_id")
    private Long encId;
    @Size(max = 50)
    @Column(name = "enc_numdoc")
    private String encNumdoc;
    @Column(name = "enc_comentatarios")
    private String encComentatarios;
    @Size(max = 100)
    @Column(name = "enc_email")
    private String encEmail;
    @Column(name = "enc_fecharesp")
    @Ordenar
    @Temporal(TemporalType.TIMESTAMP)
    private Date encFecharesp;
    @Column(name = "indversion")
    private Integer indversion;
    @JoinColumn(name = "mrc_id", referencedColumnName = "mrc_id")
    @ManyToOne
    private RfMarca mrcId;
    @JoinColumn(name = "tdc_id", referencedColumnName = "tdc_id")
    @ManyToOne
    private RfTipodocumento tdcId;

    public NegEncuesta() {
    }

    public NegEncuesta(Long encId) {
        this.encId = encId;
    }

    public Long getEncId() {
        return encId;
    }

    public void setEncId(Long encId) {
        this.encId = encId;
    }

    public String getEncNumdoc() {
        return encNumdoc;
    }

    public void setEncNumdoc(String encNumdoc) {
        this.encNumdoc = encNumdoc;
    }

    public String getEncEmail() {
        return encEmail;
    }

    public void setEncEmail(String encEmail) {
        this.encEmail = encEmail;
    }

    public Date getEncFecharesp() {
        return encFecharesp;
    }

    public void setEncFecharesp(Date encFecharesp) {
        this.encFecharesp = encFecharesp;
    }

    public Integer getIndversion() {
        return indversion;
    }

    public void setIndversion(Integer indversion) {
        this.indversion = indversion;
    }

    public RfMarca getMrcId() {
        return mrcId;
    }

    public void setMrcId(RfMarca mrcId) {
        this.mrcId = mrcId;
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
        hash += (encId != null ? encId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NegEncuesta)) {
            return false;
        }
        NegEncuesta other = (NegEncuesta) object;
        if ((this.encId == null && other.encId != null) || (this.encId != null && !this.encId.equals(other.encId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.fernando.holamundosic.modelo.NegEncuesta[ encId=" + encId + " ]";
    }

    public String getEncComentatarios() {
        return encComentatarios;
    }

    public void setEncComentatarios(String encComentatarios) {
        this.encComentatarios = encComentatarios;
    }

}
