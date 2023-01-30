package ru.javarush.golf.lykova;

import org.hibernate.SessionFactory;
import ru.javarush.golf.lykova.dao.CustomerDao;
import ru.javarush.golf.lykova.entities.*;
import ru.javarush.golf.lykova.repositories.CityRepository;
import ru.javarush.golf.lykova.repositories.CountryRepository;
import ru.javarush.golf.lykova.repositories.RentalRepository;
import ru.javarush.golf.lykova.repositories.StoreRepository;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 7. Добавить транзакционный метод, который описывает событие «покупатель пошел и вернул ранее арендованный фильм».
 * Покупателя и событие аренды выбери любое на свое усмотрение. Рейтинг фильма пересчитывать не нужно.
 */
public class TaskSeven {

    private final SessionFactory sessionFactory;

    public TaskSeven(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void perform() {
        RentalRepository rentalRepository = new RentalRepository(sessionFactory);
        Optional<RentalEntity> rentalEntityOptional = rentalRepository.findById(11496);
        if (rentalEntityOptional.isEmpty()) {
            return;
        }
        RentalEntity rentalEntity = rentalEntityOptional.get();
        rentalEntity.setReturnDate(LocalDateTime.now());
        rentalEntity.setLastUpdate(LocalDateTime.now());
        RentalEntity savedRentalEntity = rentalRepository.save(rentalEntity);
        System.out.println(savedRentalEntity);
    }
}