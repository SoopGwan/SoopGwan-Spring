package com.example.soopgwan.domain.achieve.persistence.repository;

import com.example.soopgwan.domain.achieve.persistence.AchieveSuccess;
import com.example.soopgwan.domain.achieve.persistence.AchieveSuccessId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AchieveSuccessRepository extends JpaRepository<AchieveSuccess, AchieveSuccessId> {
}
