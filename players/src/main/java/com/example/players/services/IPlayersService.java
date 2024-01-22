package com.example.players.services;

import com.example.players.config.advise.ResourceNotFoundException;
import com.example.players.modal.Player;

import java.util.List;

public interface IPlayersService {
    Player getPlayerByID(String id);
    List<Player> getPlayers(Integer pageNumber, Integer pageSize);


}
