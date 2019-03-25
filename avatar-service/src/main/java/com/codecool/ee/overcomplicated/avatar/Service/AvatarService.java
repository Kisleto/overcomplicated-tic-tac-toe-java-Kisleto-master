package com.codecool.ee.overcomplicated.avatar.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;

@Service
@Slf4j
public class AvatarService {
    private static final String API_URL = "https://robohash.org/";


    public URL getAvatar(String username) throws MalformedURLException {
        String avatar = API_URL + username;
        return new URL(avatar);
    }
}
