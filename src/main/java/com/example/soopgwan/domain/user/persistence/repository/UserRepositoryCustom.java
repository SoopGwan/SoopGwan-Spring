package com.example.soopgwan.domain.user.persistence.repository;

import com.example.soopgwan.domain.user.persistence.User;

import java.util.List;

public interface UserRepositoryCustom {

    List<User> findAllUserNotIn();
}
