/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fernando.ejb;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author ferunal
 */
public abstract class BaseEJB {

    @PersistenceContext
    protected EntityManager em;

}
