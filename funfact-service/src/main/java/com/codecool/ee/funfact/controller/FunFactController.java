package com.codecool.ee.funfact.controller;

import com.codecool.ee.funfact.service.FunFactService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FunFactController {


    @Autowired
    private FunFactService funFactService;

    @GetMapping("/funfact")
    public String randomFunFact() throws JSONException {
        log.info("=== Getting random fun fact: " + funFactService.getFunFact() + " ===");
        return funFactService.getFunFact();
    }
}
