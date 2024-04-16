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


    public void rollDice() {
        // Implement roll dice logic here
        gameModel.rollDice();
    }

    public void moveCurrentPlayer() {
        // Implement move player logic here
        gameModel.moveCurrentPlayer();
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

    public Player nextPlayer() {
        // Implement next player logic here
        gameModel.endTurn();
        //get next player;
        return gameModel.getCurrPlayer();
    }

    public CircularLinkedList getBoard() {
        // get board from model
        return gameModel.getBoard();
    }

    public void updateViewBoard(){
        // get slot details
        CircularLinkedList board = getBoard();
        gameView.updateBoard(board);
    }

    public void createBoard(){
        // create board
        gameModel.getBoard().createBoard();
    }

    public List<Node> getPlayersNode(){
        // return a list of player current Node
        List<Node> playersNode = new ArrayList<>();
        CircularLinkedList board = getBoard();
        List<Player> players = board.getPlayers();
        for (int i=0; i<players.size();i++){
            if(players.get(i).isBankrupt()){
                playersNode.add(i, null);
                continue;
            }
            playersNode.add(i, board.findPlayerNode(players.get(i)));           
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
}
