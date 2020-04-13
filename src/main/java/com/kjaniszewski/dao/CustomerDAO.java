package com.kjaniszewski.dao;

import com.kjaniszewski.entity.Customer;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;


public class CustomerDAO extends AbstractDAO<Customer>{
    //private EntityManager em;

    public CustomerDAO(EntityManager em) {
        //this.em = em;
        super(em, Customer.class);
    }

    /*public void save(Customer customer){
        em.getTransaction().begin();
        em.persist(customer);
        em.getTransaction().commit();
    }

    public List<Customer> findAll() {
        return em.createQuery("from Customer").getResultList();
    }

    public Customer findById(Long id){
        return em.find(Customer.class, id);
    }

    public void delete(Customer customer) {
        em.remove(customer);
    }*/

    public Customer findCustomer(String first_Name, String last_Name) {
        try {
            Object result = em.createQuery("FROM Customer WHERE firstName=:firstName AND lastName=:lastName")
                    .setParameter("firstName", first_Name)
                    .setParameter("lastName", last_Name)
                    .getSingleResult();
            return (Customer) result;
        }
        catch (NoResultException e){
            return null;
        }

    }

}
