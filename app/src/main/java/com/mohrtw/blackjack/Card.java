package com.mohrtw.blackjack;


public class Card {

    public Card(int suit, int number) {
        setSuit(suit);
        setNumber(number);
    }

    public int getSuit() {
        return suit;
    }

    public void setSuit(int suit) {
        /*
        0 = Hearts
        1 = Diamonds
        2 = Clubs
        3 = Spades
         */
        if (suit < 0  || suit > 3) {
            //throw new Exception("suit must be 0, 1, 2, or 3");
        }
        this.suit = suit;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getScore() {
        if (number >= 10) return 10;
        else return number;
    }

    public String getString() {
        StringBuilder builder = new StringBuilder();

        switch (number) {

            case 1 :
                builder.append("A");
                break;
            case 11 :
                builder.append("J");
                break;
            case 12 :
                builder.append("Q");
                break;
            case 13 :
                builder.append("K");
                break;
            default :
                builder.append(Integer.toString(number));
                break;
        }

        switch (suit) {
            case 0 :
                builder.append("H");
                break;
            case 1 :
                builder.append("D");
                break;
            case 2 :
                builder.append("C");
                break;
            case 3 :
                builder.append("S");
                break;
            default :
                break;
        }


        return builder.toString();
    }

    private int suit;
    private int number;
}
