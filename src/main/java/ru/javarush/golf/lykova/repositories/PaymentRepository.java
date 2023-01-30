package ru.javarush.golf.lykova.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.javarush.golf.lykova.entities.PaymentEntity;
import ru.javarush.golf.lykova.entities.RentalEntity;

public class PaymentRepository {

    private final SessionFactory sessionFactory;

    public PaymentRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public PaymentEntity save(Session session, PaymentEntity paymentEntity) {
        return session.merge(paymentEntity);
    }
}
