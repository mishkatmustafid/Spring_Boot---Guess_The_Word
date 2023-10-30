package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {
    private Game currentGame;
    private Player currentPlayer;

    @PostMapping
    public String startNewGame(@RequestParam String secretWord, @RequestParam int maxAttempts, @RequestParam String playerName) {
        currentGame = new Game(secretWord, maxAttempts);
        currentPlayer = new Player(playerName);
        return "New game started. Guessed word: " + currentGame.getGuessedWord();
    }

    @PutMapping
    public String makeMove(@RequestParam char guess) {
        if (currentGame == null) {
            return "No game in progress. Start a new game first.";
        }

        String updatedGuessedWord = currentGame.play(guess);
        if (currentGame.isGameWon()) {
            currentPlayer.incrementGamesPlayed();
            currentPlayer.incrementTotalMoves(currentGame.getAttempts());
            currentPlayer.updatePlayerInfo();
            // Save the updated player information to a file (optional)
            // You can use currentPlayer.saveToFile(fileName) here.

            return "Congratulations! You've guessed the word: " + currentGame.getSecretWord() +
                    " in " + currentGame.getAttempts() + " moves.";
        } else if (currentGame.isGameFinished() || currentGame.getAttemptsLeft() <= 0) {
            return "Game over. The secret word was: " + currentGame.getSecretWord();
        } else {
            return "Guessed word: " + updatedGuessedWord + ", Attempts left: " + currentGame.getAttemptsLeft();
        }
    }
}

