package com.example.huascar.tic_tac_toe.gameTypes;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.huascar.tic_tac_toe.Board;
import com.example.huascar.tic_tac_toe.ComputerPlayer;
import com.example.huascar.tic_tac_toe.GameState;
import com.example.huascar.tic_tac_toe.GameType;
import com.example.huascar.tic_tac_toe.HandleTurns;
import com.example.huascar.tic_tac_toe.HumanPlayer;
import com.example.huascar.tic_tac_toe.Player;
import com.example.huascar.tic_tac_toe.R;

/**
 * Created by huascar on 11/04/2018.
 */

public class HumanVsComputer extends AppCompatActivity {

    private Board board;
    private GameState gameState;
    private HumanPlayer humanPlayer;
    private ComputerPlayer computerPlayer;
    private HandleTurns handleTurns;

    private TextView gridOne;
    private TextView gridTwo;
    private TextView gridThree;
    private TextView gridFour;
    private TextView gridFive;
    private TextView gridSix;
    private TextView gridSeven;
    private TextView gridEigth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        board = new Board();
        gameState = new GameState();
        computerPlayer = new ComputerPlayer();
        humanPlayer = new HumanPlayer();
        handleTurns = new HandleTurns();

        Intent intent = getIntent();

        Bundle extra = intent.getExtras();
        String firstPlayer = extra.getString("firstPlayer");
        String firstToken = extra.getString("firstToken");

        this.setUp(firstPlayer, firstToken);
    }

    public Board getBoard() {
        return board;
    }

    public ComputerPlayer getComputerPlayer() {
        return computerPlayer;
    }

    public HumanPlayer getHumanPlayer() {
        return humanPlayer;
    }

    public GameState getGameState() {
        return gameState;
    }

    public HandleTurns getHandleTurns() { return handleTurns; }

    public void setUp( String firstPlayer, String firstToken) {
        handleTurns.setCurrentPlayerToken(firstToken);
        if( firstPlayer.equals("Human") ) {
            humanPlayer.setToken(firstToken);
            computerPlayer.autoToken(firstToken);
            handleTurns.setCurrentPlayerToken(firstToken);
        } else {
            computerPlayer.setToken(firstToken);
            humanPlayer.autoToken(firstToken);
            handleTurns.setCurrentPlayerToken(firstToken);
        }
    }

    public void onClick(View textView) {
        TextView grid = (TextView) textView;
        if(!gameState.finished(board)) {
            int spot = Integer.parseInt((String) grid.getContentDescription());
            humanPlayer.play(board, spot);
            grid.setText(humanPlayer.getToken());
        }
    }
}
