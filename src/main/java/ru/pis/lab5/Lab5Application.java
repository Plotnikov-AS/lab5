package ru.pis.lab5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.pis.lab5.service.CbrLoaderService;

@SpringBootApplication
public class Lab5Application {

    /**
     * Метод для запуска приложения
     *
     * @param args массив агрументов старта приложения
     * @throws Exception выбрасываемое исключение
     */
    public static void main(String[] args) throws Exception {
        //Создаём контекст приложения и запускаем его
        ConfigurableApplicationContext context = SpringApplication.run(Lab5Application.class, args);
        //Достаём из контекста сервисный класс для загрузки курсов валют
        context.getBean(CbrLoaderService.class).downloadCourses();
    }

}
