package com3101_project;

/**
 *
 * @author rexhe
 */
public class Player {
    private String name;
    private int position;
    private int balance;

    public Player(String name) {
        this.name = name;
        position = 0; // Start position
        balance = 2000; // Initial balance
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public void increaseBalance(int amount) {
        balance += amount;
    }

    public void decreaseBalance(int amount) {
        balance -= amount;
    }
    
}


