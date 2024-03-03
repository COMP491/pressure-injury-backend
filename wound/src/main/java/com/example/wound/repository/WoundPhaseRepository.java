package com.example.wound.repository;

import com.example.wound.entity.WoundPhaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WoundPhaseRepository extends JpaRepository<WoundPhaseEntity, Long> {

    WoundPhaseEntity findAllById(Long id);

}
