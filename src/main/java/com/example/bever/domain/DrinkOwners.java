package com.example.bever.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@RequiredArgsConstructor
public enum DrinkOwners {

    STARBUCKS("STARBUCKS", "스타벅스"),
    TWOSOME("TWOSOME","투썸플레이스");

    private final String key;
    private final String title;
}
