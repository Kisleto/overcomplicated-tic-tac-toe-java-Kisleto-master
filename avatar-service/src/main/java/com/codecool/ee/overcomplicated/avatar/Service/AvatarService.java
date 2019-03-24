package com.codecool.ee.overcomplicated.avatar.Service;

import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
@RestController
@Slf4j
public class AvatarService {
    private static final String API_URL = "https://robohash.org/%s.png";


    @GetMapping("/getavatar")
    public String getAvatar(String username){
        if(username == null){
            username = "default";
        }
        return String.format(API_URL, username);
    }
}
