package controller;

import model.*;
import model.list.CircularLinkedList;
import view.GameView;

public class GameController {

    private GameModel gameModel;
    private GameView gameView;

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }


    public void rollDice() {
        // Implement roll dice logic here
        gameModel.rollDice();
        gameModel.moveCurrentPlayer();
    }

    public void buyProperty() {
        // Implement buy property logic here
        gameModel.buyProperty();
    }

    // Call every turn to check if a player has won
    public void checkWinCondition() {
        // Implement check win condition logic here
        gameModel.checkWinCondition();

    }

    public void nextPlayer() {
        // Implement next player logic here
        gameModel.endTurn();
    }
}
