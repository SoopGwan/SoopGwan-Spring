package com.example.soopgwan.domain.achieve.persistence.repository;

import com.example.soopgwan.domain.achieve.persistence.AchieveSuccess;
import com.example.soopgwan.domain.achieve.persistence.AchieveSuccessId;
import com.example.soopgwan.domain.user.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface AchieveSuccessRepository extends JpaRepository<AchieveSuccess, Long> {
    List<AchieveSuccess> findAllByUserId(Long id);

    List<AchieveSuccess> findAllByUserNotIn(Collection<User> user);
}
