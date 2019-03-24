package com.codecool.ee.overcomplicated.avatar.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AvatarService {
    private static final String API_URL = "https://robohash.org/";


    public String getAvatar(String username) {
        return API_URL + username;
    }
}
