package com.example.players.repositories;


import com.example.players.controller.PlayerController;
import com.example.players.modal.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class InMemoryPlayersRepository implements InitializingBean, IPlayerRepository {

    private static final Logger log = LoggerFactory.getLogger(PlayerController.class);
    private final ArrayList<Player> playerArrayList = new ArrayList<>();
    private final Map<String, Player> playertoIdMap = new HashMap<>();


    @Value("classpath:player.csv")
    private Resource resourceFile;


    public void afterPropertiesSet() throws Exception {
        this.readData();
        log.info("finished spin up ");
    }


    public Player getPlayerByID(String id) {
        return playertoIdMap.get(id);
    }

    public List<Player> getPlayers(Integer pageNumber, Integer pageSize) {
        if (pageSize == null || pageNumber == null) {
            return playerArrayList;
        }
        return IntStream.range(pageNumber * pageSize, Math.min(playerArrayList.size(), pageNumber * pageSize + pageSize))
                .mapToObj(playerArrayList::get)
                .collect(Collectors.toList());
    }


    private void readData() throws Exception {
        InputStream inputStream = resourceFile.getInputStream();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            // Skip the first line which is used for headers
            String line = br.readLine();

            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(",");
                Player player = createPlayer(attributes);
                playerArrayList.add(player);
                playertoIdMap.put(player.getPlayerID(), player);
            }

        } catch (IOException ioe) {
            log.error("failed due to error",ioe);
        }
    }

    private Player createPlayer(String[] metadata) {

        String playerID = metadata[0];
        Integer birthYear = this.extractIntValue(1, metadata);
        Integer birthMonth = this.extractIntValue(2, metadata);
        Integer birthDay = this.extractIntValue(3, metadata);
        String birthCountry = this.extractStringValue(4, metadata);
        String birthState = this.extractStringValue(5, metadata);
        String birthCity = this.extractStringValue(6, metadata);
        Integer deathYear = this.extractIntValue(7, metadata);
        Integer deathMonth = this.extractIntValue(8, metadata);
        Integer deathDay = this.extractIntValue(9, metadata);
        String deathCountry = this.extractStringValue(10, metadata);
        String deathState = this.extractStringValue(11, metadata);
        String deathCity = this.extractStringValue(12, metadata);
        String nameFirst = this.extractStringValue(13, metadata);
        String nameLast = this.extractStringValue(14, metadata);
        String nameGiven = this.extractStringValue(15, metadata);
        Integer weight = this.extractIntValue(16, metadata);
        Integer height = this.extractIntValue(17, metadata);
        String bats = this.extractStringValue(18, metadata);
        String throwsHand = this.extractStringValue(19, metadata);
        String debut = this.extractStringValue(20, metadata);
        String finalGame = this.extractStringValue(21, metadata);
        String retroID = this.extractStringValue(22, metadata);
        String bbrefID = this.extractStringValue(23, metadata);

        // Return a new Player object
        return new Player(playerID, birthYear, birthMonth, birthDay, birthCountry, birthState, birthCity, deathYear, deathMonth, deathDay, deathCountry, deathState, deathCity, nameFirst, nameLast, nameGiven, weight, height, bats, throwsHand, debut, finalGame, retroID, bbrefID);
    }

    private String extractStringValue(int index, String[] metadata) {
        return metadata.length > index ? metadata[index] : null;
    }

    private Integer extractIntValue(int index, String[] metadata) {
        return metadata.length > index && !metadata[index].isEmpty() ? Integer.parseInt(metadata[index].trim()) : null;
    }


}

