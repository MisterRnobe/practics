package ru.nikitamedvedev.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Integer age;
    private Boolean sex;
    private String fullName;
    private Integer spentMoney;
    private Integer joinedYearsAgo;
    private Integer purchase;

}
