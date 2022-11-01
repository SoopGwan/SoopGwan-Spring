package com.example.soopgwan.global.util;

import com.example.soopgwan.domain.user.persistence.User;
import com.example.soopgwan.domain.user.persistence.repository.UserRepository;
import com.example.soopgwan.global.exception.UserNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserUtil {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        return userRepository.findByAccountId(id)
                .orElseThrow(() -> UserNotFound.EXCEPTION);
    }
}
