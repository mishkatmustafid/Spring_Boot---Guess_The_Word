package com.example.demo;

import org.springframework.web.bind.annotation.*;
import java.io.FileNotFoundException;
import java.io.File;

@RestController
@RequestMapping(path= "/api/player")
public class PlayerController {

    private static final String PLAYER_DATA_DIR = "player_data/";

    @PostMapping
    public String createPlayer(@RequestParam String name) {
        // Create a new player
        Player player = new Player(name);

        // Create the directory if it doesn't exist
        File directory = new File(PLAYER_DATA_DIR);
        if (!directory.exists()) directory.mkdir();

        // Save player data to a file
        String fileName = PLAYER_DATA_DIR + name + ".dat";
        player.saveToFile(fileName);

        return "Player created: " + name;
    }

    @GetMapping
    public Player getPlayerInfo(@RequestParam String name) {
        // Load player data from a file
        String fileName = PLAYER_DATA_DIR + name + ".dat";
        Player player = null;
        try {
            player = Player.loadFromFile(fileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        if (player == null) {
            // Handle the case where the player does not exist
            // You can return an appropriate response, e.g., an error message
            throw new PlayerNotFoundException("Player not found: " + name);
        }

        return player;
    }

}
