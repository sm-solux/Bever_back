package com.example.bever.repository;

import com.example.bever.domain.Calendar;
import com.example.bever.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {
    List<Calendar> findAllByUserAndDateBetween(User user, LocalDateTime start, LocalDateTime end, Sort sort);
}
