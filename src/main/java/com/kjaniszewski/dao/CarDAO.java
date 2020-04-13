package com.kjaniszewski.dao;

import com.kjaniszewski.entity.Car;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;


public class CarDAO extends AbstractDAO<Car>{
    //private EntityManager em;

    public CarDAO(EntityManager em) {
        //this.em = em;
        super(em, Car.class);
    }

    /*public void save(Car car){
        em.getTransaction().begin();
        em.persist(car);
        em.getTransaction().commit();
    }

    public List<Car> findAll() {
        return em.createQuery("from Car").getResultList();
    }

    public Car findById(Long id){
        return em.find(Car.class, id);
    }

    public void delete(Car car) {
        em.remove(car);
    }*/

    /* will work only if single record returned, otherwise exception */
    public Car findByPlates(String platesNo) {
        try {
            Object result = em.createQuery("FROM Car WHERE plates=:platesNumber")
                    .setParameter("platesNumber", platesNo)
                    .getSingleResult();
            return (Car) result;
        }
        catch (NoResultException e){
            return null;
        }
    }

    public List<Car> findAvailable(){
        List<Car> result = em.createQuery("FROM Car WHERE available=:available")
                .setParameter("available", true)
                .getResultList();
        return result;
    }
}
