package com.example.galzilca.battleship;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    private Game mGame;
    private GridView gridPlayer1;
    private GridView gridPlayer2;

    private Bundle bundle;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mGame = new Game();
        mGame.setDifficulty(getIntent().getExtras().getString("difficulty"));

        gridPlayer1 = findViewById(R.id.gridPlayer1);
        gridPlayer2 = findViewById(R.id.gridPlayer2);

        gridPlayer1.setAdapter(new TileAdapter(getApplicationContext(), mGame.getBoardPlayer1(), false));
        gridPlayer2.setAdapter(new TileAdapter(getApplicationContext(), mGame.getBoardPlayer2(), true));

        Toast.makeText(getApplicationContext(), "Your turn", Toast.LENGTH_LONG).show();

        gridPlayer1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mGame.playTile(position);
                ((TileAdapter) gridPlayer1.getAdapter()).notifyDataSetChanged();

                bundle = new Bundle();
                intent = new Intent(GameActivity.this, EndActivity.class);

                if (mGame.isWon()) {
                    bundle.putString("winner", "player");
                    bundle.putString("difficulty", mGame.getBoardPlayer1().getDifficulty().toString());
                    intent.putExtras(bundle);
                    startActivity(intent);
                } else {
                    Thread thread = new Thread(new Runnable() {

                        @Override
                        public void run() {
                            mGame.playComputer();
                            if (mGame.isWon()) {
                                bundle.putString("winner", "computer");
                                bundle.putString("difficulty", mGame.getBoardPlayer2().getDifficulty().toString());
                                intent.putExtras(bundle);
                                startActivity(intent);
                            }

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast turn = Toast.makeText(getApplicationContext(), "Your turn",
                                            Toast.LENGTH_LONG);
                                    turn.show();
                                    ((TileAdapter) gridPlayer2.getAdapter()).notifyDataSetChanged();
                                }
                            });
                        }
                    });
                    thread.start();
                }
            }
        });
    }
}
