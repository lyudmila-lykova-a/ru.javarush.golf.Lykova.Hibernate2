package ru.javarush.golf.lykova.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.golf.lykova.entities.CityEntity;
import ru.javarush.golf.lykova.entities.CountryEntity;

import java.util.Optional;

public class CityRepository {

    private final SessionFactory sessionFactory;

    public CityRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<CityEntity> findByCountryAndName(CountryEntity countryEntity, String cityName) {
        try (Session session = sessionFactory.openSession()) {
            Query<CityEntity> query = session.createQuery("from CityEntity where city = ?1 and countryEntity = ?2", CityEntity.class);
            query.setParameter(1, cityName);
            query.setParameter(2, countryEntity);
            return query.uniqueResultOptional();
        }
    }
}
