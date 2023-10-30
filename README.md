# Guess the Word Game - HTTP API Instructions and Design

## Instructions

In this game, you'll play a word guessing game using HTTP methods. The goal is to guess the secret word one letter at a time within a limited number of attempts. Here's how to play:

1. **Create a new player:**
   - HTTP POST `/api/player?name=<player_name>`
   - Example: `POST /api/player?name=John`
   - Response: Player created: John

2. **Start a new game:**
   - HTTP POST `/api/game?secretWord=<secret_word>&maxAttempts=<max_attempts>&playerName=<player_name>`
   - Example: `POST /api/game?secretWord=apple&maxAttempts=6&playerName=John`
   - Response: New game started. Guessed word: _____ (underscore for each letter in the secret word)

3. **Make a move (guess a letter):**
   - HTTP PUT `/api/game?guess=<letter>`
   - Example: `PUT /api/game?guess=a`
   - Response: Guessed word: a__l_ (the guessed letters will replace the underscores)
   - If you guess incorrectly, the response will also show the attempts left: "Guessed word: a__l_, Attempts left: 5"

4. Continue making moves until you either guess the word or run out of attempts. The game provides feedback on the guessed word and remaining attempts.

5. If you correctly guess the word, you win the game:
   - Response: Congratulations! You've guessed the word: apple in X moves. (X is the number of moves)

6. If you run out of attempts without guessing the word, the game is over:
   - Response: Game over. The secret word was: apple

7. **To view your player statistics:**
   - HTTP GET `/api/player?name=<player_name>`
   - Example: `GET /api/player?name=John`
   - Response: Player information, including games played and total moves

8. To start a new game, return to step 2. You can play multiple games, and your player statistics will be updated accordingly.

**P.S.:** Player information can be found in the `player_data` directory in the root directory.

## Design

- **Player Class:** Stores player information and includes methods to update and retrieve player data.

- **Game Class:** Represents the game logic for the "Guess the Word" game, including methods for starting new games, making moves, and checking the game's status.

- **PlayerController Class:** Handles HTTP requests for player information, such as creating players and retrieving player data.

- **GameController Class:** Handles HTTP requests related to the game, including starting new games, making moves, and updating player information. Includes the `updatePlayerInfo` method to update player data.

- **Endpoints:** Implement endpoints for creating players, starting games, making moves, and retrieving player information.

- **Error Handling:** Error handling is included for various scenarios, such as invalid input or non-existent players.

Have fun playing the "Guess the Word" game! Compete with others to see who can guess the word with the fewest moves.

