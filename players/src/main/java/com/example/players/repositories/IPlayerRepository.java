package com.example.players.repositories;

import com.example.players.modal.Player;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface IPlayerRepository {

    Player getPlayerByID(String id);

    List<Player> getPlayers(Integer pageNumber, Integer pageSize);

}
