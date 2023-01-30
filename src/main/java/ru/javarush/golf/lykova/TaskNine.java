package ru.javarush.golf.lykova;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import ru.javarush.golf.lykova.entities.*;
import ru.javarush.golf.lykova.repositories.ActorRepository;
import ru.javarush.golf.lykova.repositories.CategoryRepository;
import ru.javarush.golf.lykova.repositories.LanguageRepository;
import ru.javarush.golf.lykova.repositories.StoreRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * 9. Добавить транзакционный метод, который описывает событие «сняли новый фильм, и он стал доступен для аренды».
 * Фильм, язык, актеров, категории и т д выбери на свое усмотрение.
 */
public class TaskNine {

    private final SessionFactory sessionFactory;

    public TaskNine(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void perform() {
        ActorRepository actorRepository = new ActorRepository(sessionFactory);
        List<ActorEntity> actorEntityList = actorRepository.findFirstActors();

        CategoryRepository categoryRepository = new CategoryRepository(sessionFactory);
        List<CategoryEntity> categoryEntityList = categoryRepository.findFirstCategory();

        LanguageRepository languageRepository = new LanguageRepository(sessionFactory);
        Optional<LanguageEntity> languageEntityOptional = languageRepository.findFirstLanguage();
        if (languageEntityOptional.isEmpty()) {
            return;
        }

        StoreRepository storeRepository = new StoreRepository(sessionFactory);
        Optional<StoreEntity> storeEntityOptional = storeRepository.findFirstStore();
        if (storeEntityOptional.isEmpty()) {
            return;
        }

        FilmTextEntity filmTextEntity = new FilmTextEntity();
        filmTextEntity.setTitle("Great Catsby title");
        filmTextEntity.setDescription("Good family film. Nice for cats");

        FilmEntity filmEntity = new FilmEntity();
        filmEntity.setTitle("Great Catsby");
        filmEntity.setRentalDuration(12);
        filmEntity.setRentalRate(25.0);
        filmEntity.setReplacementCost(30.0);
        filmEntity.setLastUpdate(LocalDateTime.now());

        filmEntity = addFilm(new HashSet<>(actorEntityList), new HashSet<>(categoryEntityList), languageEntityOptional.get(), storeEntityOptional.get(), filmTextEntity, filmEntity);
        System.out.println(filmEntity);

    }

    public FilmEntity addFilm(Set<ActorEntity> actorEntitySet,
                              Set<CategoryEntity> categoryEntitySet,
                              LanguageEntity languageEntity,
                              StoreEntity storeEntity,
                              FilmTextEntity filmTextEntity,
                              FilmEntity filmEntity) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            filmEntity.setLanguageEntity(languageEntity);
            filmEntity.setActorEntities(actorEntitySet);
            filmEntity.setCategoryEntities(categoryEntitySet);
            filmEntity.setLanguageEntity(languageEntity);
            filmEntity = session.merge(filmEntity);

            InventoryEntity inventoryEntity = new InventoryEntity();
            inventoryEntity.setFilmEntity(filmEntity);
            inventoryEntity.setStoreEntity(storeEntity);
            inventoryEntity.setLastUpdate(LocalDateTime.now());
            session.merge(inventoryEntity);

            filmTextEntity.setFilmId(filmEntity.getFilmId());
            session.merge(filmTextEntity);

            transaction.commit();
            return filmEntity;
        }
    }
}