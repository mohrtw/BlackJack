package com.mohrtw.blackjack;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {

    ArrayList<Card> cards;

    public Deck() {
        this.reshuffle();
    }


    public Card pop() {
        return cards.remove(0);
    }
    public void shuffle() {
        Collections.shuffle(cards);
    }

    public void reshuffle() {
        cards = new ArrayList<Card>();
        for (int suit = 0; suit < 4; suit++) {
            for (int num = 1; num < 14; num++) {
                Card card = new Card(suit, num);
                cards.add(card);
            }
        }
        this.shuffle();
    }
}
