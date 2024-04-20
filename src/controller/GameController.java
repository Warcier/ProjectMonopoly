package controller;

import model.*;
import model.list.CircularLinkedList;
import view.GameView;

public class GameController {

    private GameModel gameModel;
    private GameView gameView;
    private CircularLinkedList circularLinkedList;

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void setcirclarLinkList(CircularLinkedList circularLinkedList){
        this.circularLinkedList = circularLinkedList;
    }

    public void rollDice() {
        // Implement roll dice logic here
        gameModel.rollDice();
        gameModel.moveCurrentPlayer();
    }

    public void buyProperty(Player player) {
        // Implement buy property logic here
        circularLinkedList.buyProperty(player);
        
    }

    public void checkWinCondition() {
        // Implement check win condition logic here
    }
}
