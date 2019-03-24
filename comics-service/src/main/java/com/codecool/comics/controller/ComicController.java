package com.codecool.comics.controller;

import com.codecool.comics.service.ComicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ComicController {
    @Autowired
    private ComicService service;

    @GetMapping("/getcomic")
    public String getRandomComics() throws IOException {
        return service.getComic();
    }
}
