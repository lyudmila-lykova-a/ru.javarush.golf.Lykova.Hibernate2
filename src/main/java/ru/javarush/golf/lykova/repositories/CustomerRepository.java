package ru.javarush.golf.lykova.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.javarush.golf.lykova.entities.AddressEntity;
import ru.javarush.golf.lykova.entities.CustomerEntity;

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
}
