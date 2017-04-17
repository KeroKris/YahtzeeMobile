package com.example.kristoffer.yahtzeemobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Kristoffer on 2017-04-04.
 */

public class GameScreen extends Activity{

    ArrayList<Player> players = new ArrayList<>();

    Player currentPlayer = null;
    private Spinner combinationTypeSpinner;
    String itemSelectedInSpinner;
    int pointsScored = -1, setToBeChosenRepresenetedAsNumber, rollCount = 0;

    Die[] dice = {new Die(), new Die(), new Die(), new Die(), new Die()};

    TextView[] diceTextViews = new TextView[5];
    TextView[] scoringTextViews = new TextView[14];
    TextView callingActivityMessage;
    TextView leaderBoardTextView;
    CheckBox[] checkBoxes = new CheckBox[5];
    private boolean gameOver = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.game_layout);

        Intent activityThatCalled = getIntent();

        int numberOfPlayers = activityThatCalled.getExtras().getInt("numberOfPlayers");

        for (int i = 0; i < numberOfPlayers; i++){
            players.add(new Player("Player " + (i+1)));
        }
        currentPlayer = players.get(0);

        leaderBoardTextView = (TextView) findViewById(R.id.leader_board_text_view);

        initializeDiceAndTextViews();
        addItemsToCombinationTypeSpinner();
        addListenerToCombinationTypeSpinner();

        callingActivityMessage = (TextView) findViewById(R.id.calling_activity_info_text_view);

        callingActivityMessage.append(" " + (3 - rollCount));
    }

    private void addItemsToCombinationTypeSpinner() {
        combinationTypeSpinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> combinationsSpinnerAdapter = ArrayAdapter.createFromResource(this, R.array.combination_types, android.R.layout.simple_spinner_item);
        combinationsSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        combinationTypeSpinner.setAdapter(combinationsSpinnerAdapter);
    }

    private void addListenerToCombinationTypeSpinner() {
        combinationTypeSpinner = (Spinner) findViewById(R.id.spinner);

        combinationTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemSelectedInSpinner = parent.getItemAtPosition(position).toString();

                setToBeChosenRepresenetedAsNumber = position +1;
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void initializeDiceAndTextViews() {

        TextView currentPlayerTextView = (TextView) findViewById(R.id.current_player_text_view);

        TextView
                dieOneTextView = (TextView) findViewById(R.id.die_one_textView),
                dieTwoTextView = (TextView) findViewById(R.id.die_two_textView),
                dieThreeTextView = (TextView) findViewById(R.id.die_three_textView),
                dieFourTextView = (TextView) findViewById(R.id.die_four_textView),
                dieFiveTextView = (TextView) findViewById(R.id.die_five_textView);

        TextView
                scoreOneTextView = (TextView) findViewById(R.id.score_one_textView),
                scoreTwoTextView = (TextView) findViewById(R.id.score_two_textView),
                scoreThreeTextView = (TextView) findViewById(R.id.score_three_textView),
                scoreFourTextView = (TextView) findViewById(R.id.score_four_textView),
                scoreFiveTextView = (TextView) findViewById(R.id.score_five_textView),
                scoreSixTextView = (TextView) findViewById(R.id.score_six_textView),
                scoreThreeXTextView = (TextView) findViewById(R.id.score_threex_textView),
                scoreFourXTextView = (TextView) findViewById(R.id.score_fourx_textView),
                scoreFullHouseTextView = (TextView) findViewById(R.id.score_fullhouse_textView),
                scoreSStraightTextView = (TextView) findViewById(R.id.score_smstraight_textView),
                scoreLStraightTextView = (TextView) findViewById(R.id.score_lstraight_textView),
                scoreYahtzeeTextView = (TextView) findViewById(R.id.score_yahtzee_textView),
                scoreChanceTextView = (TextView) findViewById(R.id.score_chance_textView),
                scoreBonusTextView = (TextView) findViewById(R.id.score_bonus_textView);

        scoringTextViews[0] = scoreOneTextView;
        scoringTextViews[1] = scoreTwoTextView;
        scoringTextViews[2] = scoreThreeTextView;
        scoringTextViews[3] = scoreFourTextView;
        scoringTextViews[4] = scoreFiveTextView;
        scoringTextViews[5] = scoreSixTextView;
        scoringTextViews[6] = scoreThreeXTextView;
        scoringTextViews[7] = scoreFourXTextView;
        scoringTextViews[8] = scoreFullHouseTextView;
        scoringTextViews[9] = scoreSStraightTextView;
        scoringTextViews[10] = scoreLStraightTextView;
        scoringTextViews[11] = scoreYahtzeeTextView;
        scoringTextViews[12] = scoreChanceTextView;
        scoringTextViews[13] = scoreBonusTextView;


        //Refreshing score sheet
        for (int i = 0; i < 14; i++){
            Score currentScore = currentPlayer.getScoreByID(i);
            if (currentScore.isNotScored()){
                scoringTextViews[i].setText(currentScore.getYahtzeeSet().name() + ": ");
            }else {
                scoringTextViews[i].setText(currentScore.getYahtzeeSet().name() + ": " + currentScore.getPoints());
            }
        }
        if (!currentPlayer.getScoreByID(13).isNotScored()){
            scoringTextViews[13].setText("BONUS: 35");
        }

        String leaderBoardText = "Leaderboard";
        for (Player p :
                players) {
            leaderBoardText += "\n" + p.getName() + ": " + p.getTotalScore() + " points";
        }

        leaderBoardTextView.setText(leaderBoardText);

        dieOneTextView.setText("Please");
        dieTwoTextView.setText("Press");
        dieThreeTextView.setText("Roll");
        dieFourTextView.setText("To");
        dieFiveTextView.setText("Play");

        diceTextViews[0] = dieOneTextView;
        diceTextViews[1] = dieTwoTextView;
        diceTextViews[2] = dieThreeTextView;
        diceTextViews[3] = dieFourTextView;
        diceTextViews[4] = dieFiveTextView;

        checkBoxes[0] = (CheckBox) findViewById(R.id.die_one_checkBox);
        checkBoxes[1] = (CheckBox) findViewById(R.id.die_two_checkBox);
        checkBoxes[2] = (CheckBox) findViewById(R.id.die_three_checkBox);
        checkBoxes[3] = (CheckBox) findViewById(R.id.die_four_checkBox);
        checkBoxes[4] = (CheckBox) findViewById(R.id.die_five_checkBox);

        for (CheckBox c :
                checkBoxes) {
            c.setChecked(false);
        }
        currentPlayerTextView.setText("Current Player: " + currentPlayer.getName() + ", " + currentPlayer.getTotalScore() + " points");
    }

    public void onRollButtonClick(View view) {

        if (rollCount < 3){
            roll(dice);
        }
    }

    public void roll(Die[] dice) {

        for (int i = 0; i < dice.length; i++){

            if (!checkBoxes[i].isChecked()){
                dice[i].randomizeResult();
                diceTextViews[i].setText("" + dice[i].getDieResult().getValue());
            }
        }
        rollCount++;
        callingActivityMessage.setText("Rolls remaining: " + (3 - rollCount));
    }

    public void onScoreCombinationClick(View view) {

        Score currentScore = currentPlayer.getScoreByID(setToBeChosenRepresenetedAsNumber -1);
        boolean notPickedBefore = currentScore.isNotScored();

        gameStateCondition:
        if (gameOver){
            Toast.makeText(view.getContext(), "Game is over, start a new one!", Toast.LENGTH_LONG).show();
        } else if (rollCount == 0){
            Toast.makeText(view.getContext(), "Roll before scoring!", Toast.LENGTH_SHORT).show();
        } else if (notPickedBefore){
            pointsScored = determinePoints(dice);
            currentScore.saveScore(pointsScored);
            currentPlayer.increaseTotalScore(pointsScored);
            Toast.makeText(view.getContext(), currentPlayer.getName() + " scored " + currentScore.getYahtzeeSet().name() + ": " + pointsScored + " points", Toast.LENGTH_LONG).show();

            //Bonus calculation if points > 63 add 35 points
            if (currentPlayer.getScoreByID(13).isNotScored()){
                int pointsTowardsBonus = 0;
                for (int i = 0; i < 6; i++){
                    if (currentPlayer.getScoreByID(i).getPoints() != -1){
                        pointsTowardsBonus += currentPlayer.getScoreByID(i).getPoints();
                    }
                }
                if (pointsTowardsBonus >= 63){
                    currentPlayer.getScoreByID(13).saveScore(35);
                    currentPlayer.increaseTotalScore(35);
                    Toast.makeText(view.getContext(), "Bonus scored: 35 points", Toast.LENGTH_SHORT).show();
                }
            }

            //End-game check
            endGameCheck:
            if (currentPlayer == players.get(players.size()-1)){
                for (int i=0; i<13; i++){
                    if (currentPlayer.getScoreByID(i).isNotScored()){
                        break endGameCheck;
                    }
                }
                gameOver = true;
                Toast.makeText(view.getContext(), "Game Over!", Toast.LENGTH_LONG).show();
                break gameStateCondition;
            }

            nextCurrentPlayer();
            rollCount = 0;
            initializeDiceAndTextViews();
        } else {
            Toast.makeText(view.getContext(), "Set already chosen, pick another!", Toast.LENGTH_SHORT).show();
        }
    }

    private void nextCurrentPlayer() {
        int currentPlayerID = players.indexOf(currentPlayer);
        currentPlayerID++;
        if (currentPlayerID > players.size()-1){
            currentPlayerID = 0;
        }
        currentPlayer = players.get(currentPlayerID);
    }

    private int determinePoints(Die[] dice) {

        //Sorting Dice for easier comparison
        boolean swapped = true;
        int i, j = dice.length;
        Die tmp;
        while(swapped){
            swapped = false;
            i = 0;
            while(i < j - 1){
                if(dice[i].getDieResult().getValue() > dice[i+1].getDieResult().getValue()){
                    tmp = dice[i+1];
                    dice[i+1] = dice[i];
                    dice[i] = tmp;
                    swapped = true;
                }
                i++;
            }
            j--;
        }
        return YahtzeeSet.getSetWithIdentifier(setToBeChosenRepresenetedAsNumber).evaluateSet(dice);
    }
}
