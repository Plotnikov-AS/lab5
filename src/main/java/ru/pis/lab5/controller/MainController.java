package ru.pis.lab5.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.pis.lab5.entity.ValuteEntity;
import ru.pis.lab5.service.MainService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final MainService mainService;

    @GetMapping("/")
    public String index() {
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String mainPage(Model model) {
        List<ValuteEntity> valuteEntities = mainService.getAllEntities();

        model.addAttribute("valutes", valuteEntities);

        return "mainPage";
    }

    @PostMapping("/getVal")
    public String getVal(Long valute, Model model) {
        ValuteEntity valuteEntity = mainService.getValute(valute);

        model.addAttribute("valute", valuteEntity);
        return "valute";
    }
}
