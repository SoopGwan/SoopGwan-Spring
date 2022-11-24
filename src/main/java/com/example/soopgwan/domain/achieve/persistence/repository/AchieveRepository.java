package com.example.soopgwan.domain.achieve.persistence.repository;

import com.example.soopgwan.domain.achieve.enums.AchieveType;
import com.example.soopgwan.domain.achieve.persistence.Achieve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AchieveRepository extends JpaRepository<Achieve, Long>, CustomAchieveRepository {
    Optional<Achieve> findByCode(AchieveType code);
}
