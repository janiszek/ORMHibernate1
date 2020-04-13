package com.kjaniszewski;

import com.kjaniszewski.dao.CarDAO;
import com.kjaniszewski.dao.CustomerDAO;
import com.kjaniszewski.dao.RentalDAO;
import com.kjaniszewski.entity.*;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import static com.kjaniszewski.entity.Address.createAddress;
import static com.kjaniszewski.entity.TechnicalReview.createTechnicalReview;

public class HibernateApplication {

    private static EntityManager em = null;




    public static void CarTableExample(){
        System.out.println("Adding some records to CAR table");
        CarDAO carDao = new CarDAO(em);

        //start the transaction
        em.getTransaction().begin();
            /*Car carEntity = new Car();
            carEntity.setName("Ford Mondeo");
            carEntity.setPlates("DWR 3123A");
            carEntity.setAvailable(true);*/
        Car carEntity = new Car("Ford Mondeo", "DWR 3123A", true, 5L, Car.CarColorEnum.black, Car.CarType.Sedan, new Date());

        //add collection of 1 field stored in 2nd table with FK
        Collection<Date> rentDates = new ArrayList<Date>();
        rentDates.add(java.sql.Date.valueOf("2020-01-01"));
        rentDates.add(java.sql.Date.valueOf("2020-04-04"));
        carEntity.setRentalDates(rentDates);

        //add list of
        List<Address> rentAddresses = new ArrayList<Address>();
        rentAddresses.add(createAddress("Mirkow", "PL", "Swierkowa", "1"));
        carEntity.setRentalAddress(rentAddresses);

        carEntity.getTechnicalReview().add(
                createTechnicalReview(TechnicalReview.TechnicalReviewResultEnum.PASSED,  java.sql.Date.valueOf("2016-01-18"), java.sql.Date.valueOf("2017-01-18")));
        carEntity.getTechnicalReview().add(
                createTechnicalReview(TechnicalReview.TechnicalReviewResultEnum.FAILED, java.sql.Date.valueOf("2017-01-18"), null));

        carDao.save(carEntity);

        carEntity = new Car("Ford Kuga", "DWR 3355A", true, 5L, Car.CarColorEnum.white, Car.CarType.SUV, new Date());
        carDao.save(carEntity);

        //as creationDate nullable=false passing null as last parameter will create an exception
        //carEntity = new Car("Ford Cmax", "DWR 3600", false, null);
        carEntity = new Car("Ford Cmax", "DWR 3600", false, 4L, Car.CarColorEnum.yellow, Car.CarType.Kombi, new Date());
        carDao.save(carEntity);

        //end the transaction
        em.getTransaction().commit();

        //list all objects/records in database
        System.out.println("Show all records in CAR table");
        //Car - is the name of object not table in database
        //List<Car> result = em.createQuery("from Car").getResultList();
        List<Car> result = carDao.findAll();
        for (Car carEnt : result) {
            System.out.println(carEnt);
        }
        System.out.println("Find CAR by plates");
        System.out.println(carDao.findByPlates("DWR 3123A"));
        System.out.println(carDao.findByPlates("DWR 6300"));
        System.out.println("Check available CARS");
        result = carDao.findAvailable();
        for (Car carEnt : result) {
            System.out.println(carEnt);
        }

        //check EntityManager.find method - find the record with the primaryKey as second param
        Car carDb = em.find(Car.class, 2L);
        if (carDb!=null)
            System.out.println("Found Car type class record! " + carDb);
    }

    public static void CustomerTableExample() {
        System.out.println("Adding some records to CUSTOMER table");
        CustomerDAO customerDao = new CustomerDAO(em);

        //start the transaction
        em.getTransaction().begin();
        Customer customerEntity = new Customer("Krzysztof", "Janiszewski", LocalDateTime.now());

        //add embedded entity Address
        Address address = createAddress("Mirkow", "PL", "Swierkowa", "1");
        customerEntity.setAddress(address);
        customerDao.save(customerEntity);

        customerEntity = new Customer("Natalia", "Janiszewska", LocalDateTime.now());
        //embedded entity Address will be null
        customerDao.save(customerEntity);

        //end the transaction
        em.getTransaction().commit();
        //list all objects/records in database

        System.out.println("Show all records in CUSTOMER table");
        List<Customer> result = customerDao.findAll();
        for (Customer customerEnt : result) {
            System.out.println(customerEnt);
        }
        System.out.println("Find CUSTOMER by first and last name");
        System.out.println(customerDao.findCustomer("Krzysztof","Janiszewski"));
        System.out.println(customerDao.findCustomer("Ewelina","Janiszewska"));
    }

    public static void RentalInvoiceExample() {
        System.out.println("Adding some records to RENTAL and INVOICE tables");
        RentalDAO rentalDao = new RentalDAO(em);
        //InvoiceDAO invoiceDao = new InvoiceDAO(em);
        //start the transaction
        em.getTransaction().begin();

        Invoice invoice = new Invoice();
        invoice.setInvoiceNumber("F-VAT-01/2018");
        invoice.setIssueDate(new Date());
        //invoiceDao.save(invoice);

        Rental rental = new Rental();
        rental.setDateFrom(java.sql.Date.valueOf("2020-04-14"));
        rental.setDateTo(java.sql.Date.valueOf("2020-04-15"));

        // add reference from source (rental) to target (invoice)
        rental.setInvoice(invoice);
        // add reversed reference from target to source
        invoice.setRental(rental);
        rentalDao.save(rental);

        //end the transaction
        em.getTransaction().commit();

        System.out.println("Show all records in RENTAL and INVOICE tables");
        List<Rental> resultRental = rentalDao.findAll();
        //List<Invoice> resultInvoice = invoiceDao.findAll();
        for (Rental rentalEnt : resultRental) {
            System.out.println(rentalEnt);
            /*for (Invoice invoiceEnt : resultInvoice){
                if (invoiceEnt.getRentalId()==rentalEnt.getId())
                    System.out.println(invoiceEnt);
            }*/
        }

    }

    public static void main(String[] args) {
        try {
            em = HibernateUtil.getEntityManager();
            CarTableExample();
            CustomerTableExample();
            RentalInvoiceExample();
        }
        finally {
            HibernateUtil.close();
        }
    }
}
