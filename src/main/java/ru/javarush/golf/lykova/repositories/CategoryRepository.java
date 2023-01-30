package ru.javarush.golf.lykova.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.golf.lykova.entities.CategoryEntity;

import java.util.List;

public class CategoryRepository {

    private final SessionFactory sessionFactory;

    public CategoryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<CategoryEntity> findFirstCategory() {
        try (Session session = sessionFactory.openSession()) {
            Query<CategoryEntity> query = session.createQuery("from CategoryEntity order by categoryId limit 1", CategoryEntity.class);
            return query.list();
        }
    }
}
