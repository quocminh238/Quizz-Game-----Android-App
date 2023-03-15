package minh.assignment1.quizzgame;


import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.*;
import android.view.*;


import org.w3c.dom.Text;

import java.util.ArrayList;

public class GameOverActivity extends EsportQuestionsActivity {

    private int freezeTime = 500;

    //Declare variables for View member
    TextView numberOfQuestions;
    TextView numberOfCorrectAnswers;
    Button playAgain;
    Button backToMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Assign View member variables
        numberOfQuestions = findViewById(R.id.tv_game_over_total_questions);
        numberOfCorrectAnswers = findViewById(R.id.tv_game_over_correct_questions);
        playAgain = findViewById(R.id.btn_game_over_play_again);
        backToMainMenu = findViewById(R.id.btn_game_over_back_to_menu);

        //Send value from EsportQuestionActivity to this one to display
        Intent intent = getIntent();
        int valueOfCorrectQuestions = intent.getIntExtra("numberCorrectQuestions",0);
        int valueOfTotalQuestions = intent.getIntExtra("numberQuestions",0);

        numberOfCorrectAnswers.setText(String.valueOf(valueOfCorrectQuestions));
        numberOfQuestions.setText(String.valueOf(valueOfTotalQuestions));

        //Add onClickListener for "Play Again" button and "Quit" button
        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent playGameAgain = new Intent(GameOverActivity.this,EsportQuestionsActivity.class);
                        startActivity(playGameAgain);
                        finish();
                    }
                }, freezeTime);
            }
        });


        backToMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
                finish();
                System.exit(0);
            }
        });

    }
}
