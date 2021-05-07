package ru.pis.lab5.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.pis.lab5.dao.ValuteDao;
import ru.pis.lab5.entity.ValuteEntity;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {
    private final ValuteDao valuteDao;

    /**
     * Получает все курсы валют из базы данных
     *
     * @return список курсов валют
     */
    public List<ValuteEntity> getAllEntities() {
        //Вызываем класс, отвечающий за общение с базой данных и получаем список всех валют
        return valuteDao.getAllValutes();
    }

    /**
     * Метод для получения курса валюты по её Id
     *
     * @param valuteId id поля с курсом валюты в базе данных
     * @return валюта
     */
    public ValuteEntity getValute(Long valuteId) {
        //Вызываем класс, отвечающий за общение с базой данных и получаем валюту по Id
        return valuteDao.getValuteById(valuteId);
    }
}
