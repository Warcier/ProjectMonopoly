package controller;

import model.*;
import model.list.CircularLinkedList;
import model.list.Node;
import view.GameView;

import java.util.*; 

public class GameController {

    private GameModel gameModel;
    private GameView gameView;

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public List<Player> getPlayers(){
        return gameModel.getBoard().getPlayers();
    }


    public void rollDice() {
        // Implement roll dice logic here
        gameModel.rollDice();
    }

    public Player getCurrentPlayer() {
        // Get current player to show in view
        return gameModel.getCurrPlayer();
    }
    public void  moveCurrentPlayer(Player player, int diceNum){
        // Implement move player logic here
        gameModel.moveCurrentPlayer(player, diceNum);
    }

    public int getDiceNum(){
        // Get dice number to show in view
        return gameModel.getDiceNum();
    }

    public void buyProperty() {
        // Implement buy property logic here
        gameModel.buyProperty();
    }

    // Call every turn to check if a player has won
    public boolean checkWinCondition() {
        // Implement check win condition logic here
        return gameModel.checkWinCondition();

    }

    public void nextPlayer() {
        // Implement next player logic here
        gameModel.endTurn();
        //get next player;

    }

    public CircularLinkedList getBoard() {
        // get board from model
        return gameModel.getBoard();
    }

    // Fix later
    public void updateViewBoard(){
        // get slot details
        gameView.updateBoard();
    }

    public void createBoard(){
        // create board
        gameModel.createBoard();
    }

    public Node findPlayerNode(Player player){
        // find player node
        return gameModel.getBoard().findPlayerNode(player);
    }

    public List<Node> getPlayersNode(CircularLinkedList board){
        // return a list of player current Node
        List<Node> playersNode = new ArrayList<>();
        for (int i=0; i < board.getPlayers().size();i++){
            Player player = board.getPlayers().get(i);
            if(player == null || !player.isBankrupt()){
                playersNode.add(i, null);
                continue;
            }
            playersNode.add(i, board.findPlayerNode(player));
        }
        return playersNode;
    }

    public Player getCurrentPlayerToView(){
        return gameModel.getCurrPlayer();
    }
    
    public void addGameMessageToLog(String message){
        // add game log to View( TextArea logText )
        gameView.showGameMessage(message);
    }

    public void addPlayerActionTakenToView(Player player, String action){
        // add action message that taken by program auto
        gameView.addPlayerTakenAction(player, action);
    }

    public List<Property> getProperties() {
        // get properties from model
        return gameModel.getBoard().getProperties();
    }

    public void ShowAllPlayerNode(){
        gameModel.getBoard().ShowAllPlayerPostion();

    }
}
