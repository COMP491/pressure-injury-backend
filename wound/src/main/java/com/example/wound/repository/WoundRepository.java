package com.example.wound.repository;

import com.example.wound.entity.WoundEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WoundRepository extends JpaRepository<WoundEntity, Long> {

    WoundEntity findAllById(Long id);
}
