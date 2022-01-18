package com.example.bever.repository;

import com.example.bever.domain.Community;
import com.example.bever.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommunityRepository extends JpaRepository<Community, Long> {
    List<Community> findAllByUser(User user);
    List<Community> findAll();
}
