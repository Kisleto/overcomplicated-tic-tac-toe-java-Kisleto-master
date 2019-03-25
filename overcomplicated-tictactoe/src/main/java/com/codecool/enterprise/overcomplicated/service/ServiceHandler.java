package com.codecool.enterprise.overcomplicated.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

@Service
@Slf4j
public class ServiceHandler {
    private static final String FUNFACTAPI_URL = "http://localhost:60001/funfact";
    private static final String COMICAPI_URL = "http://localhost:60002/getcomic";
    private static final String AVATARAPI_URL = "http://localhost:60003/getavatar";

    @Autowired
    RestTemplate restTemplate;

    @Value("${aiservice.url}")
    private String aiUrl;

    @RequestMapping
    public URL getAvatar(String username) {
        log.info("== Avatar creation ==");
        URL avatar = restTemplate.getForEntity(AVATARAPI_URL + username, URL.class).getBody();
        log.info("== Avatar: " + avatar + " ==");
        log.info("============");
        return avatar;
    }

    public String getFunfact() {
        return getResource(FUNFACTAPI_URL);
    }

    public String getComic() {
        return getResource(COMICAPI_URL);
    }


    private String getResource(String URI) {

        URL url;
        HttpURLConnection con;
        BufferedReader in;
        String responseString;
        try {
            url = new URL(URI);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            responseString = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        System.out.println(responseString);
        return responseString;
    }

    public int getRecommendation(String gameState) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return Integer.parseInt(Objects.requireNonNull(restTemplate.exchange(aiUrl + gameState, HttpMethod.GET, entity, String.class).getBody()));
    }
}
