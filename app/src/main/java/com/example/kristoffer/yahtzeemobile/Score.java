package com.example.kristoffer.yahtzeemobile;

/**
 * Created by Kristoffer on 2017-04-11.
 */

class Score {

    private YahtzeeSet yahtzeeSet;
    private boolean scored = false;
    private int points = -1;

    public Score(YahtzeeSet yahtzeeSet) {
        this.yahtzeeSet = yahtzeeSet;
    }

    public boolean isNotScored() {
        return !scored;
    }

    public YahtzeeSet getYahtzeeSet() {
        return yahtzeeSet;
    }

    public int getPoints() {
        return points;
    }

    public void saveScore(int points) {
        this.scored = true;
        this.points = points;
    }
}
