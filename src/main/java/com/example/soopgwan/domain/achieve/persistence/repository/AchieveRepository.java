package com.example.soopgwan.domain.achieve.persistence.repository;

import com.example.soopgwan.domain.achieve.persistence.Achieve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchieveRepository extends JpaRepository<Achieve, Long> {
}
