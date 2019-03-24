package com.codecool.ee.overcomplicated.avatar.controller;

import com.codecool.ee.overcomplicated.avatar.Service.AvatarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class AvatarController {
    @Autowired
    private AvatarService avatarService;

    @GetMapping("/{username}")
    public String getComic(@PathVariable("username") String username) {
        if (username.equals("Anonymous")) {
            return avatarService.getAvatar("Anonymous");
        }
        return avatarService.getAvatar(username);
    }
}
