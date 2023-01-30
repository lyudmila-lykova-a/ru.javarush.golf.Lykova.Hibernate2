package ru.javarush.golf.lykova.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.golf.lykova.entities.CountryEntity;
import ru.javarush.golf.lykova.entities.StoreEntity;

import java.util.Optional;

public class StoreRepository {

    private final SessionFactory sessionFactory;

    public StoreRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<StoreEntity> findFirstStore() {
        try (Session session = sessionFactory.openSession()) {
            Query<StoreEntity> query = session.createQuery("from StoreEntity order by storeId limit 1", StoreEntity.class);
            return query.uniqueResultOptional();
        }
    }
}
