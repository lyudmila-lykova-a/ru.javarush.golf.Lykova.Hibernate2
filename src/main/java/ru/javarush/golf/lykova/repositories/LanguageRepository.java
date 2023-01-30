package ru.javarush.golf.lykova.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.golf.lykova.entities.CategoryEntity;
import ru.javarush.golf.lykova.entities.LanguageEntity;

import java.util.List;
import java.util.Optional;

public class LanguageRepository {

    private final SessionFactory sessionFactory;

    public LanguageRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<LanguageEntity> findFirstLanguage() {
        try (Session session = sessionFactory.openSession()) {
            Query<LanguageEntity> query = session.createQuery("from LanguageEntity order by languageId limit 1", LanguageEntity.class);
            return query.uniqueResultOptional();
        }
    }
}
