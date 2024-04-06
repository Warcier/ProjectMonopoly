package com3101_project;

/**
 *
 * @author rexhe
 */
import java.util.Scanner;

public class GameStatus {
    private Player[] players; 
    private int currentPlayerIndex; 
    private Dice dice; 
    private boolean gameOver;

    public GameStatus(int numPlayers) {
        players = new Player[numPlayers];
        currentPlayerIndex = 0;
        dice = new Dice();
        gameOver = false;
    }

    public void initializePlayers() {
        // input the name using scanner(it can delete if it is not useful)
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < players.length; i++) {
            System.out.print("Enter name for Player " + (i + 1) + ": ");
            String name = scanner.nextLine();
            players[i] = new Player(name);
        }
    }

    public void playGame() {
        while (!gameOver) {

            Player currentPlayer = players[currentPlayerIndex];

            System.out.println("\n--- " + currentPlayer.getName() + "'s turn ---");
            int diceNumber = dice.getDiceNumber();
            System.out.println("Dice Number: " + diceNumber);
            
            // if person who pass the Go which is the 0, +2000 balance and set the position which need to -23
            if(currentPlayer.getPosition() + diceNumber > 23){
                currentPlayer.increaseBalance(2000);
                currentPlayer.setPosition(currentPlayer.getPosition() + diceNumber - 23);
            }else{
                //person who are not passing the Go, set the position
                currentPlayer.setPosition(currentPlayer.getPosition() + diceNumber);
            }
            
            //need to link to linkedlist to get the slot owner is null or not
            if(slot.getSlotOwner(currentPlayer.getPosition() != Null)){
                //check the slot price
                //cost the person slot price*10%
                currentPlayer.decreaseBalance(//Slot_price*10%);
                players[//owner].increaseBalance(//slot_price*10%);
            }else{
                //buy or not to buy the slot
                Scanner scanner = new Scanner(System.in);
                System.out.print("Buy the slot or not(Yes/No)");
                String BuyOrNot = scanner.nextLine();
                if(BuyOrNot == "Yes"){
                    if(currentPlayer.getBalance() > //slot price){
                        //set the slot owner = currentPlayerIndex
                        currentPlayer.decreaseBalance(//slot price);
                    }   
                }
            }
            
            //check the players is bankrupt or not
            if(currentPlayer.getBalance() <= 0){
                System.out.println(currentPlayer.getName() + " is bankrupt!");
                //remove the bankrupt player from the players array
                players[currentPlayerIndex] = null;
                //how to deleted the slot owner who players are the owners ******************************************
            }
            
            currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
            
            int activePlayers = 0;
            for(Player player: players){
                if(player != null){
                    activePlayers++;
                }
            }
            if(activePlayers == 1){
                gameOver = true;
            }
        }

        // Display game over message and final results****************************************
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numPlayers = 4;

        GameStatus game = new GameStatus(numPlayers);
        game.initializePlayers();
        game.playGame();
    }
}

