package ru.pis.lab5.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.pis.lab5.entity.ValuteEntity;
import ru.pis.lab5.model.ValCurs;
import ru.pis.lab5.repo.ValuteRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
@RequiredArgsConstructor
public class ValuteDao {
    private final ValuteRepo valuteRepo;
    @PersistenceContext
    private final EntityManager entityManager;

    /**
     * Метод для добавления валюты в базу данных
     *
     * @param valCurs
     */
    public void addValutes(ValCurs valCurs) {
        valCurs.getValute().stream().filter(Objects::nonNull).forEach(valute -> {
            ValuteEntity valuteEntity = new ValuteEntity();
            valuteEntity.setNumCode(valute.getNumCode());
            valuteEntity.setCharCode(valute.getCharCode());
            valuteEntity.setNominal(valute.getNominal());
            valuteEntity.setName(valute.getName());
            valuteEntity.setValue(valute.getValue());

            entityManager.persist(valuteEntity);
        });
    }

    /**
     * Метод для получения списка всех курсов валют
     *
     * @return
     *
     */
    public List<ValuteEntity> getAllValutes() {
        return valuteRepo.getAll();
    }


    /**
     * Метод для получения валюты по Id
     *
     * @param id - Id валюты
     * @return валюту
     */
    public ValuteEntity getValuteById(Long id) {
        return valuteRepo.getById(id);
    }
}
