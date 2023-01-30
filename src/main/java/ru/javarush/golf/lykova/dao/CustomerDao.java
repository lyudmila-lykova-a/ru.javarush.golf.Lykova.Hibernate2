package ru.javarush.golf.lykova.dao;

import org.hibernate.SessionFactory;
import ru.javarush.golf.lykova.entities.AddressEntity;
import ru.javarush.golf.lykova.entities.CityEntity;
import ru.javarush.golf.lykova.entities.CustomerEntity;
import ru.javarush.golf.lykova.entities.StoreEntity;
import ru.javarush.golf.lykova.repositories.CustomerRepository;

import java.time.LocalDateTime;

public class CustomerDao {

    private final SessionFactory sessionFactory;

    public CustomerDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public CustomerEntity addHardcodeCustomer(StoreEntity storeEntity, CityEntity cityEntity) {

        CustomerRepository customerRepository = new CustomerRepository(sessionFactory);

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddress("Pushkin street, 12");
        addressEntity.setDistrict("central");
        addressEntity.setPostalCode("212230");
        addressEntity.setPhone("+74951010203");
        addressEntity.setLastUpdate(LocalDateTime.now());
        addressEntity.setCityEntity(cityEntity);

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setStoreEntity(storeEntity);
        customerEntity.setFirstName("Vasya");
        customerEntity.setLastName("Ivanov");
        customerEntity.setEmail("vasyaIvanov222@gmail.com");
        customerEntity.setActive(true);
        customerEntity.setCreateDate(LocalDateTime.now());
        customerEntity.setLastUpdate(LocalDateTime.now());

        return customerRepository.addCustomer(customerEntity, addressEntity);
    }

}
