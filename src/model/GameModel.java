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
    }

    public CircularLinkedList getBoard() {
        return board;
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public void moveCurrentPlayer() {
        Player currentPlayer = turnController.getCurrentPlayer();
        board.movePlayerToNextNode(currentPlayer, dice.getDiceNumber());
    }

    public void rollDice() {
        dice.roll();
    }


    // TURN LOGIC
    public void endTurn() {
        turnController.nextTurn();
        // Other end turn logic...
    }

}
