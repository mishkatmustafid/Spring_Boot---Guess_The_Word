package com.example.demo;

import java.io.*;

public class Player implements Serializable{
    private String name;
    private int gamesPlayed;
    private int totalMoves;

    public Player(String name) {
        this.name = name;
        this.gamesPlayed = 0;
        this.totalMoves = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void incrementGamesPlayed() {
        gamesPlayed++;
    }

    public int getTotalMoves() {
        return totalMoves;
    }

    public void incrementTotalMoves(int moves) {
        totalMoves += moves;
    }

    public void saveToFile(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load player information from a file
    public static Player loadFromFile(String fileName) throws FileNotFoundException {
        try {
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
                return (Player) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updatePlayerInfo() {
        String fileName = "player_data/" + this.name + ".dat";
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(this);
        } catch (IOException e) {
            // Handle the exception, e.g., log an error or provide a response to the client
            e.printStackTrace();
        }
    }
}
