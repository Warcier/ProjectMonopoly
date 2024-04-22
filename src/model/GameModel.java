package model;

import controller.GameController;
import model.list.CircularLinkedList;
import model.list.Node;

import java.util.List;

public class GameModel {
    private Dice dice;
    private GameController gameController;
    private TurnController turnController;
    private final CircularLinkedList board;
    private Editor editor;



    public GameModel(List<Player> players, List<Property> properties) {
        this.board = new CircularLinkedList(players, properties);
        this.turnController = new TurnController();
        this.dice = new Dice();
        this.editor = new Editor(board, this);
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public CircularLinkedList getBoard() {
        return board;
    }

    public void createBoard(){
        board.createBoard();
    }

    public void moveCurrentPlayer(Player player,int diceNum){
        board.movePlayerToNextNode(player, diceNum);
    }

    public void rollDice() {
        dice.roll();
    }

    // get dice num for View
    public int getDiceNum() {
        return dice.getDiceNumber();
    }


    // TURN LOGIC
    public void endTurn() {
        turnController.nextTurn();
    }

    public Player checkWinCondition() {

        return board.checkWinCondition();
    }

    public Player getCurrPlayer(){
        return turnController.getCurrentPlayer();
    }

    public void setCurrPlayer(Player player){
        turnController.setCurrentPlayer(player);
    }

    public void findPropertyOwner(Player player){
        board.propertyOwner(player);
    }

    public void payRent(Player currentPlayer, Node propertyNode) {
        board.payRent(currentPlayer, propertyNode);
    }

    public void buyProperty(Player currentPlayer) {
        board.buyProperty(currentPlayer);
    }

    public Editor getEditor(){
        //get editor
        return editor;
    }

    public void addGameMessage(String message){
        gameController.addGameMessageToLog(message);
    }
}
