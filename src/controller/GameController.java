package controller;

import model.*;
import model.list.CircularLinkedList;
import model.list.Node;
import view.GameView;
import view.GameEditor;

import java.util.*; 

public class GameController {

    private GameModel gameModel;
    private GameView gameView;
    private GameEditor editor;
    private boolean dice1Clicked = false;
    private boolean dice2Clicked = false; 

    public void setGameModel(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }

    public void setGameEditor(GameEditor editor) {
        this.editor = new GameEditor(this);
        editor.setVisible(false); 
    }

    public void rollDice() {
        // Implement roll dice logic here
        gameModel.rollDice();
    }

    public void  moveCurrentPlayer(Player player, int diceNum){
        // Implement move player logic here
        gameModel.moveCurrentPlayer(player, diceNum);
    }

    public int getDiceNum(){
        // Get dice number to show in view
        return gameModel.getDiceNum();
    }

    // Call every turn to check if a player has won
    public Player checkWinCondition() {
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

    public void createBoard(){
        // create board
        gameModel.createBoard();
    }

    public Node findPlayerNode(Player player){
        // find player node
        return gameModel.getBoard().findPlayerNode(player);
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

    public void ShowAllPlayerNode(){
        gameModel.getBoard().ShowAllPlayerPostion();

    }

    public void findPropertyOwner(Player currentPlayer) {
        // Implement find property owner logic here
        gameModel.findPropertyOwner(currentPlayer);
    }

    public void payRent(Player currentPlayer, Node propertyNode) {
        // Implement pay rent logic here
        gameModel.payRent(currentPlayer, propertyNode);
    }

    public void tradeProperty(Player currentPlayer, Player otherPlayer, Node propertyNode) {
        // Implement trade property logic here
        gameModel.getBoard().tradeProperty(currentPlayer, otherPlayer, propertyNode);
    }

    public void buyProperty(Player currentPlayer) {
        // Implement buy property logic here
        gameModel.buyProperty(currentPlayer);
    }

    public void showAllPlayerStats(){
        gameModel.getBoard().ShowAllPlayerStats();
    }

    // Method to toggle the visibility of the editor
    public void toggleEditorVisibility() {
        boolean isVisible = editor.isVisible();
        editor.setVisible(!isVisible);
    }

    private void checkAndToggleVisibility() {
        if (dice1Clicked && dice2Clicked) {
            toggleEditorVisibility();
            resetFlags();
        }}

    public void dice1Clicked() {
        dice1Clicked = true;
        checkAndToggleVisibility();
    }

    public void dice2Clicked() {
        dice2Clicked = true;
        checkAndToggleVisibility();
    }

    private void resetFlags() {
        dice1Clicked = false;
        dice2Clicked = false;
    }

    public void changeOwnerBut(int slot, Player newOwner) {
        gameModel.getEditor().modifyLandOwnership(slot, newOwner);
        gameView.updateViewPlayerInfo(newOwner);
        
    }

    public void changeBalanceBut(Player player, int newBalance) {
        gameModel.getEditor().modifyPlayerBalance(player, newBalance);
        gameView.updateViewPlayerInfo(player);
    }

    public void changeLocationBut(Player player, int newLocation) {
        gameModel.getEditor().modifyPlayerLocation(player, newLocation);
        gameView.movePlayerLoca(player);
        gameView.updateViewPlayerInfo(player);
    }

    public void changeStatusBut(Player player, boolean isBankrupt) {
        gameModel.getEditor().modifyPlayerStatus(player, isBankrupt);
        gameView.changeStatus(player);

    }

    public void changeCurrentPlayerBut(Player newCurrentPlayer) {
        gameModel.getEditor().modifyCurrentPlayer(newCurrentPlayer);
        gameView.changeCurrentPlayer(newCurrentPlayer);
    }

}
