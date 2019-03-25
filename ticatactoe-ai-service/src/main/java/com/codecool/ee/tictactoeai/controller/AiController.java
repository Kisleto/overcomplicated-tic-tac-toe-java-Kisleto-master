package com.codecool.ee.tictactoeai.controller;

import com.codecool.ee.tictactoeai.service.AiService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AiController {

    @Autowired
    private AiService service;

    @GetMapping("/{gameState}")
    public Object getRecommendation(@PathVariable String gameState) throws JSONException {
        return service.getRecommendation(gameState);
    }
}