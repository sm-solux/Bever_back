package com.example.bever.controller;

import com.example.bever.domain.Calendar;
import com.example.bever.domain.Drinks;
import com.example.bever.domain.User;
import com.example.bever.dto.CalendarPostRequestDto;
import com.example.bever.repository.CalendarRepository;
import com.example.bever.repository.DrinksRepository;
import com.example.bever.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDate.parse;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")

public class CalendarController {
    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;
    private final DrinksRepository drinksRepository;

    @PostMapping("v1/calendar/post")
    public String post(@RequestBody CalendarPostRequestDto calendarPostRequestDto) {
        Long userID = calendarPostRequestDto.getUserID();
        Long drinkID = calendarPostRequestDto.getDrinkID();
        String date = calendarPostRequestDto.getDate();

        List<User> user = userRepository.findAllByUserID(userID);
        if(user.size() == 0) {
            return "error";
        }
        List<Drinks> drinks = drinksRepository.findByDrinkID(drinkID);
        if(drinks.size() == 0){
            return "error";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime calendarDate = LocalDateTime.parse(date, formatter);

        Calendar calendar = Calendar.builder().user(user.get(0)).drinks(drinks.get(0))
                        .date(calendarDate).build();

        calendarRepository.save(calendar);
        return "success";
    }

    @GetMapping("v1/calendar")
    public List<Calendar> getParameters(@RequestParam Long userID, @RequestParam Integer month) {
        List<User> user = userRepository.findAllByUserID(userID);

        if(user.size()!=0) {
            LocalDateTime startDate = LocalDateTime.of(2022, month, 1 ,0,0,0);
            LocalDateTime endDate = startDate.plusMonths(1).minusDays(1);
            List<Calendar> calendarList = calendarRepository.findAllByUserAndDateBetween(user.get(0),startDate, endDate);
            return calendarList;
        }
        return new ArrayList<>();
    }

    }
