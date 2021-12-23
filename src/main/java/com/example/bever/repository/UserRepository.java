package com.example.bever.repository;

import com.example.bever.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


//    User findUsersByUserEmailAndUserPw(String email, String pw);
    List<User> findAllByUserEmailAndUserPw(String email, String pw);
}
