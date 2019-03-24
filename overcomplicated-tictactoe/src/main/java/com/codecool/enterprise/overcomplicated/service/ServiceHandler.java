package com.codecool.enterprise.overcomplicated.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

@Service
public class ServiceHandler {
    private static final String FUNFACTAPI_URL = "http://localhost:60001/funfact";
    private static final String COMICAPI_URL = "http://localhost:60002/getcomic";
    private static final String AVATARAPI_URL = "http://localhost:60003/getavatar";
    private static final String AIAPI_URL = "http://localhost:60004/aimove";

    public String getAvatar() {
        return getResource(AVATARAPI_URL);
    }

    public String getFunfact() {
        return getResource(FUNFACTAPI_URL);
    }

    public String getComic() {
        return getResource(COMICAPI_URL);
    }

    public int getAIMove(char[] gameState) {
        if (new String(gameState).contains("-")) {
            String aimove = getResource(AIAPI_URL + "?gamestate=" + new String(gameState));
            if (aimove != null) {
                return Integer.valueOf(aimove);
            }
        }

        return -1;
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
}
