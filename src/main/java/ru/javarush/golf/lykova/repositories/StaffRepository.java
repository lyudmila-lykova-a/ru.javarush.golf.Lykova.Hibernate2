package ru.javarush.golf.lykova.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.golf.lykova.entities.StaffEntity;

import java.util.Optional;

public class StaffRepository {

    private final SessionFactory sessionFactory;

    public StaffRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<StaffEntity> findById(int staffId) {
        try (Session session = sessionFactory.openSession()) {
            Query<StaffEntity> query = session.createQuery("from StaffEntity where staffId = ?1", StaffEntity.class);
            query.setParameter(1, staffId);
            return query.uniqueResultOptional();
        }
    }
}
