package com.example.demo;

public class Game {
    final private String secretWord;
    private String guessedWord;
    private final int maxAttempts;
    private int attempts;

    public Game(String secretWord, int maxAttempts) {
        this.secretWord = secretWord;
        this.guessedWord = "_".repeat(secretWord.length());
        this.maxAttempts = maxAttempts;
        this.attempts = 0;
    }


    public String getGuessedWord() {
        return guessedWord;
    }

    public int getAttempts() {
        return attempts;
    }

    public String play(char guess) {
        // Check if the game is already won or lost
        if (isGameFinished()) {
            return "Game is already finished. Secret word: " + secretWord;
        }

        if (isGameWon()) {
            return "Game is already won. Secret word: " + secretWord;
        }

        // Update the guessedWord based on the guess
        for (int i = 0; i < secretWord.length(); i++) {
            if (secretWord.charAt(i) == guess) {
                guessedWord = guessedWord.substring(0, i) + guess + guessedWord.substring(i + 1);
            }
        }
        attempts++;
        return guessedWord;
    }


    public String getSecretWord() {
        return secretWord;
    }

    public boolean isGameFinished() {
        return guessedWord.equals(secretWord);
    }

    public boolean isGameWon() {
        return guessedWord.equals(secretWord);
    }

    public int getAttemptsLeft() {
        return maxAttempts - attempts;
    }
}
