package com.example.bever.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "Drinks")
public class Drinks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drinkID")
    private Long DrinkID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drinkOwnerID")
    private DrinkOwners drinkOwners;

    @Column
    private String drinkName;

    @Column
    private String drinkImageLink;

    @Column
    private Boolean drinkNew;
}