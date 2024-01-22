package com.example.players.services;


import com.example.players.config.advise.ResourceNotFoundException;
import com.example.players.modal.Player;
import com.example.players.repositories.IPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayersService implements IPlayersService {

    private final IPlayerRepository playersRepository;

    @Autowired
    public PlayersService(IPlayerRepository playersRepository) {
        this.playersRepository = playersRepository;
    }

    //the sneaky throw concept allows us to throw any checked exception without defining it explicitly in the method signature
    @Override
    public Player getPlayerByID(String id) {
        Player playerByID = playersRepository.getPlayerByID(id);
        if (playerByID == null)
            throw new ResourceNotFoundException("player with id" + id + "doesnt exist");

        return playerByID;
    }

    @Override
    public List<Player> getPlayers(Integer pageNumber, Integer pageSize) {
        return playersRepository.getPlayers(pageNumber, pageSize);
    }
}
