package com.kjaniszewski.dao;

import com.kjaniszewski.entity.Invoice;

import javax.persistence.EntityManager;

public class InvoiceDAO extends AbstractDAO<Invoice> {

    //private EntityManager em;

    public InvoiceDAO(EntityManager em) {
        //this.em = em;
        super(em, Invoice.class);
    }

}
