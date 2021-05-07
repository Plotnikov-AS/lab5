package ru.pis.lab5.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "table_valutes", schema = "lab5_schema")
public class ValuteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "num_code")
    private String numCode;

    @Column(name = "char_code")
    private String charCode;

    private String nominal;
    private String name;
    private String value;
}
