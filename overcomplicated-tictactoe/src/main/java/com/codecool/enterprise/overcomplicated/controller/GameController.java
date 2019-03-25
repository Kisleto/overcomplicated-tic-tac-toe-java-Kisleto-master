package com.codecool.enterprise.overcomplicated.controller;

import com.codecool.enterprise.overcomplicated.model.Player;
import com.codecool.enterprise.overcomplicated.model.TictactoeGame;
import com.codecool.enterprise.overcomplicated.service.ServiceHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import java.net.URL;

@Controller
@SessionAttributes({"player", "game"})
public class GameController {

    @Autowired
    ServiceHandler serviceHandler;

    private HttpSession session;

    public GameController(HttpSession session) {
        this.session = session;
    }

    @ModelAttribute("player")
    public Player getPlayer() {
        Player player = new Player();
        session.setAttribute("player", new Player());
        return player;
    }

    @ModelAttribute("game")
    public TictactoeGame getGame() {
        TictactoeGame game = new TictactoeGame();
        session.setAttribute("game", game);
        return game;
    }

    @ModelAttribute("avatar_uri")
    public URL getAvatarUri() {
        Player player = (Player) session.getAttribute("player");
        URL avatar;
        if (player == null) {
            avatar = serviceHandler.getAvatar("Anonymous");
        } else {
            avatar = serviceHandler.getAvatar(player.getUserName());
        }
        return avatar;
    }

    @GetMapping(value = "/")
    public String welcomeView(@ModelAttribute Player player) {
        return "welcome";
    }

    @PostMapping(value="/changeplayerusername")
    public String changPlayerUserName(@ModelAttribute Player player) {
        session.setAttribute("player", player);
        return "redirect:/game";
    }

    @GetMapping(value = "/game")
    public String gameView(@ModelAttribute("player") Player player, Model model) {
        model.addAttribute("funfact", serviceHandler.getFunfact());
        model.addAttribute("comic_uri", serviceHandler.getComic());
        return "game";
    }

    @GetMapping(value = "/game-move")
    public String gameMove(@ModelAttribute("player") Player player, @ModelAttribute("move") int move) {
        TictactoeGame game = (TictactoeGame) session.getAttribute("game");
        String redirect = game.evaluateMove(game, move, serviceHandler);
        System.out.println("Player moved " + move);
        return redirect;

    }
}
