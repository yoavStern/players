package com.example.players.controller;


import com.example.players.modal.Player;
import com.example.players.services.IPlayersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private static final Logger log = LoggerFactory.getLogger(PlayerController.class);
    private final IPlayersService playersService;

    @Autowired
    public PlayerController(IPlayersService playersService) {
        this.playersService = playersService;
    }


    @GetMapping()
//    public List<Player> getPlayers(@RequestParam(defaultValue = "0") Integer pageNumber, @RequestParam(defaultValue = "10") Integer pageSize) {
    public List<Player> getPlayers(@RequestParam (required = false)Integer pageNumber, @RequestParam(required = false) Integer pageSize) {
        log.info("getPlayers");
        return this.playersService.getPlayers(pageNumber,pageSize);
    }

    @GetMapping("/{playerId}")
    public Player getPlayer(@PathVariable(name = "playerId") String playerId) {
        log.info("get concrete Player ");
        return this.playersService.getPlayerByID(playerId);
    }


}
