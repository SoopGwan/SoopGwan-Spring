package com.example.soopgwan.infrastructure.utils;

import com.example.soopgwan.domain.user.persistence.VerifyCode;
import com.example.soopgwan.domain.user.persistence.repository.VerifyCodeRepository;
import com.example.soopgwan.infrastructure.CoolsmsProperties;
import lombok.RequiredArgsConstructor;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class MessageUtil {

    private final CoolsmsProperties coolsmsProperties;
    private final VerifyCodeRepository verifyCodeRepository;

    public void send(String phoneNumber, Integer count) throws CoolsmsException {
        net.nurigo.java_sdk.api.Message coolsms = new net.nurigo.java_sdk.api.Message(coolsmsProperties.getApiKey(), coolsmsProperties.getApiSecret());

        String code = "";
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < 4; i++) {
            code += random.nextInt(10);
        }

        HashMap<String, String> params = new HashMap<>();
        params.put("to", phoneNumber);
        params.put("from", coolsmsProperties.getSender());
        params.put("type", "SMS");
        params.put("text", "[SOOPGWAN] 숲관 인증번호는 [" + code + "] 입니다.");

        coolsms.send(params);

        VerifyCode verifyCode = VerifyCode.builder()
                .phoneNumber(phoneNumber)
                .code(code)
                .count(count + 1)
                .ttl(300)
                .build();

        verifyCodeRepository.save(verifyCode);
    }
}