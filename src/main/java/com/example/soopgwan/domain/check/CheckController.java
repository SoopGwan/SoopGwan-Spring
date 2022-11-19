package com.example.soopgwan.domain.check;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CheckController {

    @GetMapping
    public String healthCheck() {
        return "Health Check Ok!";
    }
}
