package controller;

import model.*;
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
    }

    public void checkWinCondition() {
        // Implement check win condition logic here
    }
}
