# Tic-Tac-Toe

**"Tic-Tac-Toe"** is a game written in Java.
The game is for 2 players.
The game is customizable to your liking.
All game settings and statistics are saved inside a JSON file.

## Game frame
- Game grid is in the middle of the frame
- The player name and symbol whoose turn it is is on top of the frame
- A reset button is on bottom of the frame for resetting tha game
- An exit button is on the bottom for exiting the game

## Game settings
- Game difficulty (game board size and win line length)
- X symbol color
- O symbol color
- Player 1 name
- Player 2 name
- Resetting statistics
- Exporting statistics in a .txt
- Default settings

## Statistics
- Total games played count
- Draws count
- Player 1 wins count
- Player 2 wins count

## How to run the game
For running the game, you have to have Java installed.
1. Download the latest release asset
2. Extract the zip file with the game jar
3. Open a command prompt and change the current folder to location of the jar file
4. Run the game using this command:
```bash
java -jar Tic-Tac-Toe.jar
```
If you run the jar outside the folder, your saved data wont load into the game.
In case you experience a bug with the color pickers, add the following java option to the command when you run the game:
```bash
-Dsun.java2d.d3d=false
```
## Author
- Author: Matej Kesl
- Program language: Java 23
- UI Framwork: Java Swing
