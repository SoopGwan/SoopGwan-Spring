package com.example.soopgwan.domain.user.persistence.repository;

import com.example.soopgwan.domain.user.persistence.VerifyCode;
import org.springframework.data.repository.CrudRepository;

public interface VerifyCodeRepository extends CrudRepository<VerifyCode, String> {
}
