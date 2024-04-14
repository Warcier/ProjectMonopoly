package model;

import controller.GameController;
import model.list.CircularLinkedList;

public class GameModel {
    private Dice dice;
    private GameController gameController;
    private TurnController turnController;
    private final CircularLinkedList board;



    public GameModel() {
        this.board = new CircularLinkedList(test.initPlayer(), test.initProperty());
        this.turnController = new TurnController(test.initPlayer());
        this.dice = new Dice();
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public CircularLinkedList getBoard() {
        return board;
    }

    public void moveCurrentPlayer() {
        board.movePlayerToNextNode(turnController.getCurrentPlayer(), dice.getDiceNumber());
    }

    public void rollDice() {
        dice.roll();
    }

    // get dice num for View
    public int getDiceNum() {
        return dice.getDiceNumber();
    }

    public void buyProperty() {
        board.checkIfBuyOrPayRent(turnController.getCurrentPlayer(), board.findPlayerNode(turnController.getCurrentPlayer()));
    }



    // TURN LOGIC
    public void endTurn() {
        turnController.nextTurn();
        // Other end turn logic...
    }

    public boolean checkWinCondition() {
        // Implement check win condition logic here
        return board.checkWinCondition();
    }

    public Player getCurrPlayer(){
        //get current Player
        return turnController.getCurrentPlayer();
    }
}
