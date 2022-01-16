package com.example.bever.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "DrinkOwners")
public class DrinkOwners {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drinkOwnerID")
    private Long drinkOwnerID;

    @Column
    private String drinkOwnerName;
}
