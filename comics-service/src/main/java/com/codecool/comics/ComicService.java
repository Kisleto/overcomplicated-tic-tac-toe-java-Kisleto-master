package com.codecool.comics;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Random;

@Service
@RestController
@Data
public class ComicService {

    private static final String API_URL = "https://xkcd.com/";
    Random random = new Random();

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/getcomic")
    public String getComic() throws IOException {
        ResponseEntity<String > responseEntity = restTemplate.getForEntity(API_URL + random.nextInt(1930) + "/info.0.json", String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode root = objectMapper.readTree(responseEntity.getBody());
        System.out.println(root.path("img").asText());
        return root.path("img").asText();
    }
}
