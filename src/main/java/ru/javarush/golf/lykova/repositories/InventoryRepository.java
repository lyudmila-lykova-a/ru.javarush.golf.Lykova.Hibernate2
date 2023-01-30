package ru.javarush.golf.lykova.repositories;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import ru.javarush.golf.lykova.entities.InventoryEntity;
import ru.javarush.golf.lykova.entities.StoreEntity;

import java.util.Optional;

public class InventoryRepository {

    private final SessionFactory sessionFactory;

    public InventoryRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Optional<InventoryEntity> findFirstNotRental(StoreEntity storeEntity) {
        try (Session session = sessionFactory.openSession()) {
            Query<InventoryEntity> query = session.createQuery(
                    "from InventoryEntity inventory join RentalEntity rental where inventory.storeEntity = ?1  and rental.returnDate is not null order by inventory.inventoryId limit 1",
                    InventoryEntity.class);
            query.setParameter(1, storeEntity);
            return query.uniqueResultOptional();
        }
    }
}
