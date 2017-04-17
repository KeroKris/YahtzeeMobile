package com.example.kristoffer.yahtzeemobile;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Kristoffer on 2017-04-04.
 */

public class Player implements Serializable {
    private String name = "";
    private ArrayList<Score> scores;
    private int totalScore = 0;

    public Player(String name) {
        this.name = name;

        scores = new ArrayList<>();
        populateScoreList();
    }

    private void populateScoreList() {
        scores.add(new Score(YahtzeeSet.ACES));
        scores.add(new Score(YahtzeeSet.TWOS));
        scores.add(new Score(YahtzeeSet.THREES));
        scores.add(new Score(YahtzeeSet.FOURS));
        scores.add(new Score(YahtzeeSet.FIVES));
        scores.add(new Score(YahtzeeSet.SIXES));
        scores.add(new Score(YahtzeeSet.THREE_OF_A_KIND));
        scores.add(new Score(YahtzeeSet.FOUR_OF_A_KIND));
        scores.add(new Score(YahtzeeSet.FULL_HOUSE));
        scores.add(new Score(YahtzeeSet.SMALL_STRAIGHT));
        scores.add(new Score(YahtzeeSet.LARGE_STRAIGHT));
        scores.add(new Score(YahtzeeSet.YAHTZEE));
        scores.add(new Score(YahtzeeSet.CHANCE));

        scores.add(new Score(YahtzeeSet.BONUS));
    }

    public String getName() {
        return name;
    }

    public Score getScoreByID(int id){

        if (id >= 0 && id < 14) {
            return scores.get(id);
        }

        return scores.get(0);
    }

    public void increaseTotalScore(int score) {
        this.totalScore += score;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setName(String name) {
        this.name = name;
    }
}
