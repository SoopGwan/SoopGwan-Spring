package com.example.soopgwan.domain.user.persistence.repository;

import com.example.soopgwan.domain.user.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
