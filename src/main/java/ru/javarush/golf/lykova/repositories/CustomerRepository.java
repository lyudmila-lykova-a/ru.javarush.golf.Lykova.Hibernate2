package ru.javarush.golf.lykova.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.javarush.golf.lykova.entities.AddressEntity;
import ru.javarush.golf.lykova.entities.CustomerEntity;
import ru.javarush.golf.lykova.entities.RentalEntity;

import java.util.Optional;

public class CustomerRepository {

    private final SessionFactory sessionFactory;

    public CustomerRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public CustomerEntity addCustomer(CustomerEntity customerEntity, AddressEntity addressEntity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            addressEntity = session.merge(addressEntity);
            customerEntity.setAddressEntity(addressEntity);
            CustomerEntity result = session.merge(customerEntity);
            transaction.commit();
            return result;
        }
    }

    public Optional<CustomerEntity> findById(int customerId) {
        try (Session session = sessionFactory.openSession()) {
            Query<CustomerEntity> query = session.createQuery("from CustomerEntity where customerId = ?1", CustomerEntity.class);
            query.setParameter(1, customerId);
            return query.uniqueResultOptional();
        }
    }
}
