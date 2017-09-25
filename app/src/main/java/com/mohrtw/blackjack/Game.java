package com.mohrtw.blackjack;

public class Game {

    Deck deck;
    Hand player;
    Hand dealer;

    private int cash = 50;
    private int bet = 0;

    public int getCash() {
        return cash;
    }

    public int getBet() {
        return bet;
    }

    // game over
    boolean over;

    // busted
    boolean busted;

    // dealer's turn
    boolean dealerTurn;

    // did the player win
    boolean win;
    boolean push;

    public Game() {
        deal();
    }

    public void deal() {
        cash -= bet;

        newDeck();
        player =  new Hand();
        dealer =  new Hand();

        // Deal two cards to each player
        player.add(deck.pop());
        dealer.add(deck.pop());
        player.add(deck.pop());
        dealer.add(deck.pop());

        dealerTurn = false;
        over = false;
        busted = false;
        this.evaluate();
    }
    public void hit() {
        player.add(deck.pop());
        this.evaluate();
    }
    public void stay() {
        if (player.getScore() < 21)
            this.dealerPlay();
        else
            evaluate();
    }
    public void evaluate() {
        int score = player.getScore();
        int dscore = dealer.getScore();

        // Player bust
        if (score > 21) {
            over = true;
            win = false;
            busted = true;
            push = false;
            settle();
            return;
        }
        // dealer bust
        else if (dscore > 21) {
            over = true;
            win = true;
            busted = false;
            push = false;
            settle();
            return;
        }

        // Player blackjack
        if (score == 21 && dscore != 21) {
            over = true;
            win = true;
            busted = false;
            push = false;
            settle();
            return;
        }

        // determine winner if game is over
        if (over) {
            // dealer high score
            if (score < dscore) {
                win = false;
                busted = false;
                push = false;
            }
            // push
            else if (score == dscore) {
                win = false;
                busted = false;
                push = true;
            }
            // player high score
            else {
                win = true;
                busted = false;
                push = false;
            }
            settle();
        }
    }

    public void dealerPlay() {
        dealerTurn = true;
        if (dealer.getScore() == 21) {
            // Dealer win
            over = true;
            win = false;
            evaluate();
        }
        while (dealer.getScore() < 17) {
            // Dealer hit
            dealer.add(deck.pop());
        }

        over = true;
        evaluate();

    }

    public boolean bet(int amount) {
        if (bet + amount <= cash) {
            bet += amount;
            return true;
        }
        return false;
    }

    void settle() {
        if (win) {
            cash += bet*2;
        }
        else if (push) {
            cash += bet;
        }
        else if (cash <= 0) {
            gameOver();
        }

        // clear bet if larger than cash
        if (bet > cash) {
            bet = 0;
        }
    }

    private void newDeck() {
        deck = new Deck();
    }

    public void clearBet() {
        bet = 0;
    }

    void gameOver() {
        cash = 50;
        bet = 0;
        newDeck();
    }
}
