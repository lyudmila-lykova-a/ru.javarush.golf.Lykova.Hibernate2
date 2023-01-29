package ru.javarush.golf.lykova;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.query.Query;
import ru.javarush.golf.lykova.entities.*;

import java.util.List;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        SessionFactory sessionFactory = initDatabase();
        TaskSix taskSix = new TaskSix(sessionFactory);
        taskSix.perform();

        TaskSeven taskSeven = new TaskSeven(sessionFactory);
        taskSeven.perform();
//        List<?> allFilms = findAllFilms(sessionFactory);
//        System.out.println(allFilms);

    }

    private static SessionFactory initDatabase() {
        Properties properties = new Properties();
        properties.put(Environment.DIALECT, "org.hibernate.dialect.MySQL8Dialect");
        properties.put(Environment.USER, "root");
        properties.put(Environment.PASS, "my-secret-pw");
        properties.put(Environment.DRIVER, "com.p6spy.engine.spy.P6SpyDriver");
        properties.put(Environment.URL, "jdbc:p6spy:mysql://localhost:3306/movie");
        Configuration configuration = new Configuration();
        SessionFactory sessionFactory = configuration
                .setProperties(properties)
                .addAnnotatedClass(ActorEntity.class)
                .addAnnotatedClass(AddressEntity.class)
                .addAnnotatedClass(CategoryEntity.class)
                .addAnnotatedClass(CityEntity.class)
                .addAnnotatedClass(CountryEntity.class)
                .addAnnotatedClass(CustomerEntity.class)
                .addAnnotatedClass(FilmEntity.class)
                .addAnnotatedClass(FilmTextEntity.class)
                .addAnnotatedClass(InventoryEntity.class)
                .addAnnotatedClass(LanguageEntity.class)
                .addAnnotatedClass(PaymentEntity.class)
                .addAnnotatedClass(RentalEntity.class)
                .addAnnotatedClass(StaffEntity.class)
                .addAnnotatedClass(StoreEntity.class)
                .buildSessionFactory();
        return sessionFactory;
    }

    public static List<?> findAllFilms(SessionFactory sessionFactory) {
        try (Session session = sessionFactory.openSession()) {
            Query<?> query = session.createQuery("from FilmEntity");
            return query.list();
        }
    }
}