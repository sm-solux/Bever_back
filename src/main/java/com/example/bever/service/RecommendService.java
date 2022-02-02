package com.example.bever.service;

import com.example.bever.domain.Drinks;
import com.example.bever.dto.DrinksDto;
import com.example.bever.repository.DrinksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Transactional
public class RecommendService {

    private final DrinksRepository drinksRepository;

    public List<DrinksDto> recommend(Long userId){

        List<DrinksDto> result = new LinkedList<>();

        Random rand = new Random();

        for(int i=0; i<10; i++){
            Long randID = Long.valueOf(rand.nextInt(Math.toIntExact(drinksRepository.maxdrinkID())));
            List<Drinks> drinks = drinksRepository.findByDrinkID(randID);

            if(drinks.size()>0) {
                Drinks drink = drinks.get(0);
                DrinksDto drinksDto = DrinksDto.builder()
                        .drinkId(drink.getDrinkID())
                        .drinkOwners(drink.getDrinkOwners().getTitle())
                        .drinkImageLink(drink.getDrinkImageLink())
                        .drinkName(drink.getDrinkName())
                        .build();
                result.add(drinksDto);
            }
        }
        return result;
    }
}
