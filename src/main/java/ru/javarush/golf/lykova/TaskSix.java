package ru.javarush.golf.lykova;

import org.hibernate.SessionFactory;
import ru.javarush.golf.lykova.dao.CustomerDao;
import ru.javarush.golf.lykova.entities.CityEntity;
import ru.javarush.golf.lykova.entities.CountryEntity;
import ru.javarush.golf.lykova.entities.CustomerEntity;
import ru.javarush.golf.lykova.entities.StoreEntity;
import ru.javarush.golf.lykova.repositories.CityRepository;
import ru.javarush.golf.lykova.repositories.CountryRepository;
import ru.javarush.golf.lykova.repositories.CustomerRepository;
import ru.javarush.golf.lykova.repositories.StoreRepository;

import java.util.Optional;

/**
 * 6. Добавить метод, который умеет создавать нового покупателя (таблица customer) со всеми зависимыми полями.
 * Не забудь сделать чтоб метод был транзакционным (чтоб не попасть в ситуацию что адрес покупателя записали в БД,
 * а самого покупателя – нет).
 */
public class TaskSix {

    private final SessionFactory sessionFactory;

    public TaskSix(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void perform() {
        CustomerDao customerDao = new CustomerDao(sessionFactory);
        CountryRepository countryRepository = new CountryRepository(sessionFactory);
        CityRepository cityRepository = new CityRepository(sessionFactory);
        StoreRepository storeRepository = new StoreRepository(sessionFactory);

        Optional<StoreEntity> storeEntityOptional = storeRepository.findFirstStore();
        if (storeEntityOptional.isEmpty()) {
            return;
        }

        Optional<CountryEntity> countryEntityOptional = countryRepository.findByName("Russian Federation");
        if (countryEntityOptional.isEmpty()) {
            return;
        }

        Optional<CityEntity> cityEntityOptional = cityRepository.findByCountryAndName(countryEntityOptional.get(), "Moscow");
        if (cityEntityOptional.isEmpty()) {
            return;
        }

        CustomerEntity customerEntity = customerDao.addHardcodeCustomer(storeEntityOptional.get(), cityEntityOptional.get());
        System.out.println(customerEntity);
    }
}