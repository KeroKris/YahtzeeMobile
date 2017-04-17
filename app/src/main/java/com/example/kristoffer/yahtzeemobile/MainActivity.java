package com.example.kristoffer.yahtzeemobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.NumberPicker;

public class MainActivity extends AppCompatActivity {

    NumberPicker numberPicker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberPicker = (NumberPicker) findViewById(R.id.number_of_players_picker);
        numberPicker.setMaxValue(4);
        numberPicker.setMinValue(1);
        numberPicker.setWrapSelectorWheel(false);
        numberPicker.setValue(2);

    }

    public void onPlayGameClick(View view) {

        final int result = 1;

        int numberOfPlayers = numberPicker.getValue();
        Intent sendNumberOfPlayers = new Intent(this, GameScreen.class);
        sendNumberOfPlayers.putExtra("numberOfPlayers", numberOfPlayers);

        startActivityForResult(sendNumberOfPlayers, result);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
