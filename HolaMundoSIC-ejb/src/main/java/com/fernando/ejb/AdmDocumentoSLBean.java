/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fernando.ejb;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fernando.ejb.comportamiento.EntidadActiva;
import com.fernando.ejb.comportamiento.Ordenar;
import com.fernando.hmsic.modelo.AdmColaborador;
import com.fernando.hmsic.util.UsuarioDTO;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Id;
import javax.persistence.NamedEntityGraph;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

/**
 *
 * @author ferunal
 */
@Stateless
@LocalBean
public class AdmDocumentoSLBean extends BaseEJB {

    public AdmColaborador validarColaborador(UsuarioDTO pUsuarioDTO) {
        AdmColaborador admColaborador;
        Query q = em.createNamedQuery("AdmColaborador.validarCol");
        q.setParameter("colUsuario", pUsuarioDTO.getUsuario());
        q.setParameter("colClave", pUsuarioDTO.getClave());
        try {
            admColaborador = (AdmColaborador) q.getSingleResult();
        } catch (NoResultException e) {
            Logger.getLogger(AdmDocumentoSLBean.class.getName()).log(Level.WARNING,
                    "Usuario o clave incorrecto para " + pUsuarioDTO.getUsuario(), e);
            admColaborador = null;
        }

        return admColaborador;

    }

    public List consultarEntidad(String objEntidad, String nombreEntidad, Integer pActiva, int start, int size) {

        try {
            Class<?> claseEntidad = Class.forName("com.fernando.hmsic.modelo." + nombreEntidad);

            CriteriaBuilder cbEntidades = em.getCriteriaBuilder();
            CriteriaQuery<?> cqConsulta = cbEntidades.createQuery(claseEntidad);

            Root<?> rootEntidades = cqConsulta.from(claseEntidad);

            List<Order> lstCriteriosOrdenar = new ArrayList<>();
            List<Predicate> lstPredicados = new ArrayList<>();

            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsnActualObj = mapper.readTree(objEntidad);
            if (objEntidad != null && !objEntidad.isEmpty()) {

                Object objEntidadMapeada = mapper.readValue(objEntidad, claseEntidad);
                Class<?> claseObjeto = objEntidadMapeada.getClass();

                if (claseObjeto.equals(claseEntidad) && !claseObjeto.isArray()) {
                    Metamodel mm = em.getMetamodel();
                    EntityType<?> et = mm.entity(claseObjeto);

                    for (Attribute<?, ?> attr : et.getAttributes()) {
                        if (attr.getPersistentAttributeType().equals(Attribute.PersistentAttributeType.BASIC)) {
                            for (Field campo : claseObjeto.getDeclaredFields()) {
                                if (attr.getName().equals(campo.getName())) {
                                    campo.setAccessible(true);

                                    if (campo.get(objEntidadMapeada) != null && jsnActualObj.get(attr.getName()) != null) {
                                        ParameterExpression<?> p
                                                = cbEntidades.parameter(attr.getJavaType(), attr.getName());
                                        lstPredicados.add(cbEntidades.equal(rootEntidades.get(attr.getName()), p));
                                    }

                                }
                            }
                        }
                        if (attr.getPersistentAttributeType().equals(Attribute.PersistentAttributeType.MANY_TO_ONE)) {
                            for (Field campo : claseObjeto.getDeclaredFields()) {
                                if (attr.getName().equals(campo.getName())) {
                                    campo.setAccessible(true);

                                    if (campo.get(objEntidadMapeada) != null && jsnActualObj.get(attr.getName()) != null) {

                                        Join<?, ?> ve = rootEntidades.join(attr.getName());
                                        for (Field f : campo.get(objEntidadMapeada).getClass().getDeclaredFields()) {
                                            f.setAccessible(true);
                                            if (f.isAnnotationPresent(Id.class)) {
                                                f.get(campo.get(objEntidadMapeada));
                                                ParameterExpression<?> p
                                                        = cbEntidades.parameter(attr.getJavaType(), attr.getName());
                                                lstPredicados.add(cbEntidades.equal(ve.get(attr.getName()), p));
                                            }
                                        }

                                    }

                                }
                            }
                        }
                    }
                }
            }
            for (Field declaredField : claseEntidad.getDeclaredFields()) {
                declaredField.setAccessible(true);
                if (!pActiva.equals(-1)) {
                    if (declaredField.isAnnotationPresent(EntidadActiva.class)) {
                        ParameterExpression<Boolean> p
                                = cbEntidades.parameter(Boolean.class, declaredField.getName());
                        lstPredicados.add(cbEntidades.equal(rootEntidades.get(declaredField.getName()), p));
                    }
                }
                if (declaredField.isAnnotationPresent(Ordenar.class)) {
                    lstCriteriosOrdenar.add(cbEntidades.asc(rootEntidades.get(declaredField.getName())));

                }

            }
            if (!lstCriteriosOrdenar.isEmpty()) {
                cqConsulta.orderBy(lstCriteriosOrdenar);
            }
            if (!lstPredicados.isEmpty()) {
                if (lstPredicados.size() == 1) {
                    cqConsulta.where(lstPredicados.get(0));
                } else {
                    cqConsulta.where(cbEntidades.and(lstPredicados.toArray(new Predicate[0])));
                }
            }

            TypedQuery<?> tq = em.createQuery(cqConsulta);
            if (claseEntidad.isAnnotationPresent(NamedEntityGraph.class)) {
                tq.setHint("javax.persistence.fetchgraph", em.getEntityGraph(claseEntidad.getAnnotation(NamedEntityGraph.class).name()));

            }
            for (Field declaredField : claseEntidad.getDeclaredFields()) {
                declaredField.setAccessible(true);
                if (declaredField.isAnnotationPresent(EntidadActiva.class)) {
                    if (!pActiva.equals(-1)) {
                        tq.setParameter(declaredField.getName(), pActiva.equals(1));
                    }
                }

            }
            if (objEntidad != null && !objEntidad.isEmpty()) {

                Object objEntidadMapeada = mapper.readValue(objEntidad, claseEntidad);
                Class<?> claseObjeto = objEntidadMapeada.getClass();

                if (claseObjeto.equals(claseEntidad) && !claseObjeto.isArray()) {
                    Metamodel mm = em.getMetamodel();
                    EntityType<?> et = mm.entity(claseObjeto);
                    for (Attribute<?, ?> attr : et.getAttributes()) {
                        if (attr.getPersistentAttributeType().equals(Attribute.PersistentAttributeType.BASIC)) {
                            for (Field campo : claseObjeto.getDeclaredFields()) {
                                if (attr.getName().equals(campo.getName())) {
                                    campo.setAccessible(true);
                                    if (campo.get(objEntidadMapeada) != null && jsnActualObj.get(attr.getName()) != null) {
                                        tq.setParameter(attr.getName(), campo.get(objEntidadMapeada));
                                    }

                                }
                            }
                        }
                    }
                }
            }

            if (start == 0 && size == 0) {
                return tq.getResultList();
            } else if (start >= 0 && size > 0) {
                return tq.setFirstResult(start * size).setMaxResults(size).getResultList();
            }

        } catch (ClassNotFoundException | IOException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(AdmDocumentoSLBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

}
