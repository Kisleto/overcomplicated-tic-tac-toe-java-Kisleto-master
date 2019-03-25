package com.codecool.ee.tictactoeai.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
@Slf4j

public class AiService {
    @Autowired
    RestTemplate restTemplate;

    @Value("${ai.url}")
    private String baseUrl;

    public Object getRecommendation(String gameState) throws JSONException {
        log.info("== Calling AI recommendation ==");

        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Mozilla/5.0");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        String result = restTemplate.exchange(baseUrl + gameState + "/O", HttpMethod.GET, entity, String.class).getBody();
        JSONObject jsonObject = new JSONObject(result);

        Object recommendation = jsonObject.get("recommendation");
        log.info("== Recommendation: ==" + recommendation);
        log.info("============");
        return recommendation;
    }
}
