package com.mohrtw.blackjack;

import java.util.ArrayList;

public class Hand {

    ArrayList<Card> cards;
    int score;

    Hand() {
        cards = new ArrayList<Card>();
    }

    public void add(Card c) {
        cards.add(c);
    }

    public int getScore() {
        boolean ace = false;
        score = 0;
        for (Card c : cards) {
            score += c.getScore();
            if (c.getScore() == 1) ace = true;
        }

        if (ace && score <= 11)
            return score + 10;
        else
            return score;
    }

    public int getDealerScore() {
        int s = cards.get(0).getScore();
        if (s==1) return 11;
        else return s;
    }

    public String getString() {
        StringBuilder builder = new StringBuilder();

        for (Card c : cards) {
            builder.append(c.getString());
            builder.append(" ");
        }

        return builder.toString();
    }

    public String getDealerString() {
        StringBuilder builder = new StringBuilder();

        // only show first card
        builder.append(cards.get(0).getString());
        builder.append(" * ");

        return builder.toString();
    }
}
