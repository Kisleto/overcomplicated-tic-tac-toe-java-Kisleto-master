package com.codecool.ee.funfact.service;

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
public class FunFactService {

    @Autowired
    RestTemplate restTemplate;

    @Value("${funfact.url}")
    private String baseUrl;

    public String getFunFact() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Mozilla/5.0");
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String result = restTemplate.exchange(baseUrl, HttpMethod.GET, entity, String.class).getBody();
        JSONObject jsonObject = new JSONObject(result);
        String funfact = String.valueOf(jsonObject.get("value"));
        log.info("Fact: " + funfact);
        return funfact;
    }
}
