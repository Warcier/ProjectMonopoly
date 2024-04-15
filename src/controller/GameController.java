package controller;

import model.*;
import model.list.CircularLinkedList;
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
    public void checkWinCondition() {
        // Implement check win condition logic here
        gameModel.checkWinCondition();

    }

    public Player nextPlayer() {
        // Implement next player logic here
        gameModel.endTurn();

        //get next player;
        return gameModel.getCurrPlayer();
    }
    public List<Player> getPlayers() {
        // get player list from model
        // Returns an unmodifiable list to prevent modification from the view
        return Collections.unmodifiableList(gameModel.getBoard().getPlayers());
    }

    public void updateViewPlayers(){
        // get slot details
        List<Player> players = getPlayers();
        gameView.updatePlayers(players);
    }
    //TODO: get current round player information
}
