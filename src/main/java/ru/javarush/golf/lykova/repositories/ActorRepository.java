package ru.javarush.golf.lykova.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.golf.lykova.entities.ActorEntity;

import java.util.List;

public class ActorRepository {

    private final SessionFactory sessionFactory;

    public ActorRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<ActorEntity> findFirstActors() {
        try (Session session = sessionFactory.openSession()) {
            Query<ActorEntity> query = session.createQuery("from ActorEntity order by actorId limit 5", ActorEntity.class);
            return query.list();
        }
    }
}
