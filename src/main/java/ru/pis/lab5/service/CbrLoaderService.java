package ru.pis.lab5.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import ru.pis.lab5.dao.ValuteDao;
import ru.pis.lab5.model.ValCurs;
import ru.pis.lab5.model.Valute;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.ArrayList;
import java.util.List;

/**
 * Комментарий
 */
@Service
@RequiredArgsConstructor
public class CbrLoaderService {
    private final ValuteDao valuteDao;

    /**
     * Метод для загрузки курса валют с сайта Центробанка
     *
     * @throws Exception ошибка
     */
    public void downloadCourses() throws Exception {
        // Задаём URL-адрес, по которому хранятся курсы валют
        String url = "http://www.cbr.ru/scripts/XML_daily.asp";
        // Создаём фабрику, создающую строителей документов
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // Получаем из фабрики строителя документа
        DocumentBuilder builder = factory.newDocumentBuilder();
        // С помощью строителя читаем XML-документ, расположенный по URL-адресу
        Document doc = builder.parse(url);

        //Нормализуем документ
        doc.getDocumentElement().normalize();

        // Считываем полученный XML-документ в POJO-класс
        ValCurs valCurs = ValCurs.builder()
                .valute(getValutesFromDoc(doc))
                .build();

        // Сохраняем полученные курсы в базу данных
        valuteDao.addValutes(valCurs);
    }

    /**
     * Метод для трансформации XML-документа в спиок валютных POJO-классов
     *
     * @param doc XML-документ
     * @return спиок валютных POJO-классов
     */
    private List<Valute> getValutesFromDoc(Document doc) {
        // Создаём пустой список с валютами
        List<Valute> valutes = new ArrayList<>();
        // Получаем из XML-документа список нод
        NodeList childNodes = doc.getFirstChild().getChildNodes();
        //Создаём цикл для итерации по нодам XML-документа
        for (int i = 0; i < childNodes.getLength(); i++) {
            // Достаём ноду XML-документа по индексу
            Node node = childNodes.item(i);
            // Создаём экземпляр POJO-класса валюты
            Valute valute = Valute.builder()
                    .numCode(node.getChildNodes().item(0).getFirstChild().getNodeValue())
                    .charCode(node.getChildNodes().item(1).getFirstChild().getNodeValue())
                    .nominal(node.getChildNodes().item(2).getFirstChild().getNodeValue())
                    .name(node.getChildNodes().item(3).getFirstChild().getNodeValue())
                    .value(node.getChildNodes().item(4).getFirstChild().getNodeValue())
                    .build();

            //Кладём POJO-класс валюты в список с валютами
            valutes.add(valute);
        }

        // Возвращаем список валютных POJO-классов
        return valutes;
    }
}
