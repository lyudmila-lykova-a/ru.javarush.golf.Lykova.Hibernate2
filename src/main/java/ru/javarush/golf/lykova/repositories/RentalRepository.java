package ru.javarush.golf.lykova.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.javarush.golf.lykova.entities.CountryEntity;
import ru.javarush.golf.lykova.entities.RentalEntity;

import java.util.Optional;

public class RentalRepository {

    private final SessionFactory sessionFactory;

    public RentalRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public RentalEntity save(RentalEntity rentalEntity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            rentalEntity = session.merge(rentalEntity);
            transaction.commit();
            return rentalEntity;
        }
    }

    public Optional<RentalEntity> findById(int rentalId) {
        try (Session session = sessionFactory.openSession()) {
            Query<RentalEntity> query = session.createQuery("from RentalEntity where rentalId = ?1", RentalEntity.class);
            query.setParameter(1, rentalId);
            return query.uniqueResultOptional();
        }
    }
}
