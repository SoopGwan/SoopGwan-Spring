package com.example.soopgwan.domain.user.persistence.repository;

import com.example.soopgwan.domain.user.persistence.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {

    boolean existsByAccountId(String accountId);

    Optional<User> findByAccountId(String accountId);

    Optional<User> findByPhoneNumber(String phoneNumber);
}
