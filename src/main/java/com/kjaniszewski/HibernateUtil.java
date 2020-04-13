package com.kjaniszewski;

import com.kjaniszewski.entity.Car;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {

    private static final String JDBC_DB_URL= "persistenceUnit-h2";
    // Configuration from file resources/META-INF/persistence.xml
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory(JDBC_DB_URL);
    private static EntityManager em;

    private HibernateUtil() {
    }

    //singleton model - only one exists at the time
    public static EntityManager getEntityManager(){
        if (em==null){
            em = emf.createEntityManager();
        }
        return em;
    }

    public static void close(){
        if (em!=null)
            em.close();
        if (emf!=null)
            emf.close();
    }


    //this is for native Hibernate - without JPA standard EntityManager
    public static SessionFactory getSessionFactoryFromXml() {
        Configuration configuration = new Configuration();

        configuration.addAnnotatedClass(Car.class);

        // load config from hibernate.cfg.xml
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory(
                new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .build());
        return sessionFactory;
    }
}
