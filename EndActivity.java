package com.example.galzilca.battleship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndActivity extends AppCompatActivity {

    private Button main;
    private Button playAgain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        final String difficulty = getIntent().getExtras().getString("difficulty");
        String winner = getIntent().getExtras().getString("winner");

        switch (winner) {
            case "player":
                ((TextView) findViewById(R.id.winnerTextView)).setText("You won!");
                break;
            case "computer":
                ((TextView) findViewById(R.id.winnerTextView)).setText("Computer won");
                break;
        }

        main = (Button)findViewById(R.id.mainButton);
        playAgain = (Button)findViewById(R.id.playAgainButton);

        main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndActivity.this, MainActivity.class);
                startActivity(intent);
            }});

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndActivity.this, GameActivity.class);
                Bundle b = new Bundle();
                b.putString("difficulty", difficulty);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}
