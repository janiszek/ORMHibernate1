package com.kjaniszewski.dao;

import com.kjaniszewski.entity.Rental;

import javax.persistence.EntityManager;

public class RentalDAO extends AbstractDAO<Rental> {

    //private EntityManager em;

    public RentalDAO(EntityManager em) {
        //this.em = em;
        super(em, Rental.class);
    }

}
