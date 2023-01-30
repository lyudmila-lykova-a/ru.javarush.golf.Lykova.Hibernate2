package ru.javarush.golf.lykova;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.javarush.golf.lykova.entities.*;
import ru.javarush.golf.lykova.repositories.*;

import java.time.LocalDateTime;
import java.util.Optional;

/**
 * 8. Добавить транзакционный метод, который описывает событие «покупатель сходил в магазин (store)
 * и арендовал (rental) там инвентарь (inventory).
 * При этом он сделал оплату (payment) у продавца (staff)». Фильм (через инвентарь) выбери на свое усмотрение.
 * Единственное ограничение – фильм должен быть доступен для аренды.
 * То есть либо в rental не должно быть вообще записей по инвентарю,
 * либо должна быть заполнена колонка return_date таблицы rental для последней аренды этого инвентаря.
 */
public class TaskEight {

    private final SessionFactory sessionFactory;

    public TaskEight(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void perform() {
        CustomerRepository customerRepository = new CustomerRepository(sessionFactory);
        Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(593);
        if (customerEntityOptional.isEmpty()) {
            return;
        }

        InventoryRepository inventoryRepository = new InventoryRepository(sessionFactory);
        Optional<InventoryEntity> inventoryEntityOptional = inventoryRepository.findFirstNotRental(customerEntityOptional.get().getStoreEntity());
        if (inventoryEntityOptional.isEmpty()) {
            return;
        }

        StaffRepository staffRepository = new StaffRepository(sessionFactory);
        Optional<StaffEntity> staffEntityOptional = staffRepository.findById(2);
        if (staffEntityOptional.isEmpty()) {
            return;
        }

        RentalEntity rentalEntity = new RentalEntity();
        rentalEntity.setRentalDate(LocalDateTime.now());
        rentalEntity.setInventoryEntity(inventoryEntityOptional.get());
        rentalEntity.setCustomerEntity(customerEntityOptional.get());
        rentalEntity.setStaffEntity(staffEntityOptional.get());
        rentalEntity.setLastUpdate(LocalDateTime.now());

        PaymentEntity paymentEntity = new PaymentEntity();
        paymentEntity.setCustomerEntity(customerEntityOptional.get());
        paymentEntity.setStaffEntity(staffEntityOptional.get());
        paymentEntity.setAmount(25.0);
        paymentEntity.setPaymentDate(LocalDateTime.now());
        rentalEntity.setLastUpdate(LocalDateTime.now());

        RentalEntity savedRentalEntity = addRental(rentalEntity, paymentEntity);
        System.out.println(savedRentalEntity);
    }

    public RentalEntity addRental(RentalEntity rentalEntity, PaymentEntity paymentEntity) {
        RentalRepository rentalRepository = new RentalRepository(sessionFactory);
        PaymentRepository paymentRepository = new PaymentRepository(sessionFactory);
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            rentalEntity = rentalRepository.save(session, rentalEntity);
            paymentEntity.setRentalEntity(rentalEntity);
            paymentRepository.save(session, paymentEntity);
            transaction.commit();
            return rentalEntity;
        }
    }
}