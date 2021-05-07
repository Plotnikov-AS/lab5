package ru.pis.lab5.model;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ValCurs {
    private List<Valute> valute;
}
