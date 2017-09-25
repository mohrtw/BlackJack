package com.mohrtw.blackjack;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    //region views
    @BindView(R.id.dealerScore)
    TextView dealerScore;

    @BindView(R.id.playerScore)
    TextView playerScore;

    @BindView(R.id.dealerHand)
    TextView dealerHand;

    @BindView(R.id.playerHand)
    TextView playerHand;

    @BindView(R.id.buttonHIT)
    Button buttonHIT;

    @BindView(R.id.buttonSTAY)
    Button buttonSTAY;

    @BindView(R.id.buttonDEAL)
    Button buttonDEAL;

    @BindView(R.id.buttonCLEAR)
    Button buttonCLEAR;

    @BindView(R.id.buttonDouble)
    Button buttonDouble;

    @BindView(R.id.buttonSplit)
    Button buttonSplit;

    @BindView(R.id.message)
    TextView message;

    @BindView(R.id.buttonOne) Button buttonOne;
    @BindView(R.id.buttonFive) Button buttonFive;
    @BindView(R.id.buttonTen) Button buttonTen;
    @BindView(R.id.buttonTwentyFive) Button buttonTwentyFive;

    @BindView(R.id.bank) TextView viewBank;
    @BindView(R.id.bet) TextView viewBet;

    //endregion

    Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        swapButtons(true);

        game = new Game();
        render();
    }

    private void render() {

        playerScore.setText(Integer.toString(game.player.getScore()));
        playerHand.setText(game.player.getString());

        viewBet.setText(Integer.toString(game.getBet()));
        viewBank.setText("$" + Integer.toString(game.getCash()));

        if (game.dealerTurn) {
            dealerScore.setText(Integer.toString(game.dealer.getScore()));
            dealerHand.setText(game.dealer.getString());
        } else {
            dealerScore.setText(Integer.toString(game.dealer.getDealerScore()));
            dealerHand.setText(game.dealer.getDealerString());
        }

        if (game.dealerTurn) {
            buttonHIT.setEnabled(false);
            buttonSTAY.setEnabled(false);
        }

        if (game.over) {
            swapButtons(true);
            if (game.busted) {
                message.setText("BUSTED!");
            }
            else if (game.win) {
                message.setText("WIN!");
            }

            else if (game.push) {
                message.setText("PUSH!");
            }
            else {
                message.setText("LOSE!");
        }

        }
    }

    @OnClick(R.id.buttonHIT)
    void hit() {
        //Toast.makeText(this, "hit", Toast.LENGTH_SHORT).show();
        game.hit();
        render();
    }

    @OnClick(R.id.buttonSTAY)
    void stay() {
        //Toast.makeText(this, "stay", Toast.LENGTH_SHORT).show();
        game.stay();
        render();
    }

    @OnClick(R.id.buttonDEAL)
    void deal(){
        message.setText("");

        swapButtons(false);

        game.deal();
        render();
    }

    @OnClick(R.id.buttonCLEAR)
    void clearBet() {
        game.clearBet();
        render();
    }

    //region Betting buttons
    @OnClick(R.id.buttonOne)
    void betOne() {
        game.bet(1);
        render();
    }
    @OnClick(R.id.buttonFive)
    void betFive() {
        game.bet(5);
        render();
    }
    @OnClick(R.id.buttonTen)
    void betTen() {
        game.bet(10);
        render();
    }
    @OnClick(R.id.buttonTwentyFive)
    void betTwentyFive() {
        game.bet(25);
        render();
    }
    //endregion

    // false dealing phase
    void swapButtons(boolean bettingPhase) {
        if (bettingPhase) {
            buttonDEAL.setEnabled(true);
            buttonCLEAR.setEnabled(true);
            buttonOne.setEnabled(true);
            buttonFive.setEnabled(true);
            buttonTen.setEnabled(true);
            buttonTwentyFive.setEnabled(true);
            viewBet.setEnabled(true);

            buttonHIT.setEnabled(false);
            buttonSTAY.setEnabled(false);
            buttonDouble.setEnabled(false);
            buttonSplit.setEnabled(false);
        }
        else {
            buttonDEAL.setEnabled(false);
            buttonCLEAR.setEnabled(false);
            buttonOne.setEnabled(false);
            buttonFive.setEnabled(false);
            buttonTen.setEnabled(false);
            buttonTwentyFive.setEnabled(false);
            viewBet.setEnabled(false);

            buttonHIT.setEnabled(true);
            buttonSTAY.setEnabled(true);
            buttonDouble.setEnabled(true);
            buttonSplit.setEnabled(true);
        }
    }
}


// features
// TODO : double down
// TODO : split
// TODO : save cash between sessions
