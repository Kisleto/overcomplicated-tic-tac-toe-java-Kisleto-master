package com.codecool.enterprise.overcomplicated.model;

import com.codecool.enterprise.overcomplicated.service.ServiceHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TictactoeGame {
    private String gameState = "---------";
    private boolean isGameWon = false;

    public void handleMove(String player, int index) {
        String[] gameStateSplit = gameState.split("");

        if (player.equals("X") || player.equals("O")) {
            if (gameStateSplit[index].equals("-"))
                gameStateSplit[index] = player;
        }

        gameState = arrayToString(gameStateSplit);
    }

    private String arrayToString(String[] splitStrings) {
        StringBuilder result = new StringBuilder();
        for (String str : splitStrings) {
            result.append(str);
        }
        return result.toString();
    }

    public boolean isMoveValid(String player, int index) {
        String[] gameStateSplit = gameState.split("");
        return gameStateSplit[index].equals("-");
    }

    public boolean isGameWon(String player) {
        String[] gameStateSplit = gameState.split("");

        //check horizontal rows
        for (int i = 0; i <= 6; i += 3) {
            if (gameStateSplit[i].equals(player) &&
                    gameStateSplit[i + 1].equals(player) &&
                    gameStateSplit[i + 2].equals(player))
                return true;
        }

        //check vertical rows
        for (int i = 0; i <= 2; i++) {
            if (gameStateSplit[i].equals(player) &&
                    gameStateSplit[i + 3].equals(player) &&
                    gameStateSplit[i + 6].equals(player))
                return true;
        }

        //check transverse patterns
        if (gameStateSplit[0].equals(player) &&
                gameStateSplit[4].equals(player) &&
                gameStateSplit[8].equals(player)) {
            return true;
        }
        if (gameStateSplit[2].equals(player) &&
                gameStateSplit[4].equals(player) &&
                gameStateSplit[6].equals(player)) {
            return true;
        }

        return false;
    }


    public String getGameState() {
        return gameState;
    }


    public String evaluateMove(TictactoeGame game, int move, ServiceHandler serviceHandler) {
        if (game.isMoveValid("X", move) && !game.isGameWon("O")) {
            game.handleMove("X", move);
        } else {
            return "redirect:/game";
        }
        if (!game.isGameWon("X"))
            game.handleMove("O", serviceHandler.getRecommendation(game.getGameState()));
        log.info("== Game state ==");
        log.info(game.getGameState());
        log.info("============");
        log.info("== Winner is... ==");
        log.info("X - " + game.isGameWon("X") + ", O - " + game.isGameWon("O"));
        log.info("============");
        return "redirect:/game";
    }
}
