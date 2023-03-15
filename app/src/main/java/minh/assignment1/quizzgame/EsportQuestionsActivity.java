package minh.assignment1.quizzgame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.*;
import android.view.*;


import java.util.ArrayList;


public class EsportQuestionsActivity extends AppCompatActivity {
    int currentQuestionIndex;
    int totalCorrectAnswers;
    int totalQuestions;

    ArrayList<Questions> esportQuestions;

    // Declare member variables for View
    ImageView questionEsportImageView;
    TextView questionEsportTextView;
    TextView questionsRemainingEsportTextView;
    TextView questionsTotalTextView;
    Button answer0EsportButton;
    Button answer1EsportButton;
    Button answer2EsportButton;
    Button answer3EsportButton;


    private static int freezeTime = 500;
    public static int countQuestion = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display app icon in ActionBar
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setLogo(R.mipmap.ic_quizz_game);
//        getSupportActionBar().setDisplayUseLogoEnabled(true);
//        getSupportActionBar().setElevation(1);

        //Hide the action bar and the time on the screen
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_esport_questions);

        // Assign View member variables
        questionEsportImageView = findViewById(R.id.iv_esport_question_image);
        questionEsportTextView = findViewById(R.id.tv_esport_question_title);
        questionsRemainingEsportTextView = findViewById(R.id.tv_esport_questions_remaining_count);
        questionsTotalTextView = findViewById(R.id.tv_esport_total_questions);
        answer0EsportButton = findViewById(R.id.btn_esport_answer_0);
        answer1EsportButton = findViewById(R.id.btn_esport_answer_1);
        answer2EsportButton = findViewById(R.id.btn_esport_answer_2);
        answer3EsportButton = findViewById(R.id.btn_esport_answer_3);


        // Add onClickListener for each answer Button
        answer0EsportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCorrectOrIncorrectAnswer(0);
            }
        });

        answer1EsportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCorrectOrIncorrectAnswer(1);
            }
        });

        answer2EsportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCorrectOrIncorrectAnswer(2);
            }
        });

        answer3EsportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCorrectOrIncorrectAnswer(3);
            }
        });

        // Start the game
        startGame();
    }


    // --------------------------- Methods -------------------------------//
    // Display question when game starts
    void displayQuestion(Questions question) {
        questionEsportImageView.setImageResource(question.image);
        questionEsportTextView.setText(question.questionContent);
        answer0EsportButton.setText(question.answer0);
        answer1EsportButton.setText(question.answer1);
        answer2EsportButton.setText(question.answer2);
        answer3EsportButton.setText(question.answer3);

        //Reset the color buttons when display next question
        answer0EsportButton.setBackgroundColor(Color.WHITE);
        answer0EsportButton.setTextColor(Color.BLACK);

        answer1EsportButton.setBackgroundColor(Color.WHITE);
        answer1EsportButton.setTextColor(Color.BLACK);

        answer2EsportButton.setBackgroundColor(Color.WHITE);
        answer2EsportButton.setTextColor(Color.BLACK);

        answer3EsportButton.setBackgroundColor(Color.WHITE);
        answer3EsportButton.setTextColor(Color.BLACK);

    }

    // Display the current question number on the TextView
    void displayEsportQuestionNumber(int questionNumber) {
        questionsRemainingEsportTextView.setText(String.valueOf(questionNumber));
    }


    // Check Correct or Incorrect Answer when user click the button
    void checkCorrectOrIncorrectAnswer(int answerSelected) {
        Questions currentQuestion = takeCurrentQuestion();
        currentQuestion.answerPlayerChoose = answerSelected;


        if (currentQuestion.checkCorrect()) {
            switch(answerSelected) {
                case 0:
                    answer0EsportButton.setBackgroundColor(Color.GREEN);
                    answer0EsportButton.setTextColor(Color.WHITE);
                    answer0EsportButton.setText(currentQuestion.answer0);
                    break;
                case 1:
                    answer1EsportButton.setBackgroundColor(Color.GREEN);
                    answer1EsportButton.setTextColor(Color.WHITE);
                    answer1EsportButton.setText(currentQuestion.answer1);
                    break;
                case 2:
                    answer2EsportButton.setBackgroundColor(Color.GREEN);
                    answer2EsportButton.setTextColor(Color.WHITE);
                    answer2EsportButton.setText(currentQuestion.answer2);
                    break;
                case 3:
                    answer3EsportButton.setBackgroundColor(Color.GREEN);
                    answer3EsportButton.setTextColor(Color.WHITE);
                    answer3EsportButton.setText(currentQuestion.answer3);
                    break;
            }
            totalCorrectAnswers = totalCorrectAnswers + 1;

        } else if (!currentQuestion.checkCorrect()) {
            switch(answerSelected) {
                case 0:
                    answer0EsportButton.setBackgroundColor(Color.RED);
                    answer0EsportButton.setTextColor(Color.WHITE);
                    answer0EsportButton.setText(currentQuestion.answer0);
                    break;
                case 1:
                    answer1EsportButton.setBackgroundColor(Color.RED);
                    answer1EsportButton.setTextColor(Color.WHITE);
                    answer1EsportButton.setText(currentQuestion.answer1);
                    break;
                case 2:
                    answer2EsportButton.setBackgroundColor(Color.RED);
                    answer2EsportButton.setTextColor(Color.WHITE);
                    answer2EsportButton.setText(currentQuestion.answer2);
                    break;
                case 3:
                    answer3EsportButton.setBackgroundColor(Color.RED);
                    answer3EsportButton.setTextColor(Color.WHITE);
                    answer3EsportButton.setText(currentQuestion.answer3);
                    break;
            }
        }
        esportQuestions.remove(currentQuestion);
        countQuestion++;

        // Set delay time between each question after players chose their answer
        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                displayEsportQuestionNumber(countQuestion);
            }
        }, freezeTime);



        if (esportQuestions.size() == 0) {

            // Move to the next Activity and pass the value to that Activity
            countQuestion -= 1;

            Intent moveToGameOver = new Intent(EsportQuestionsActivity.this,GameOverActivity.class);
            moveToGameOver.putExtra("numberQuestions", totalQuestions);
            moveToGameOver.putExtra("numberCorrectQuestions", totalCorrectAnswers);
            startActivity(moveToGameOver);
            finish();


        } else {
            chooseNewEsportQuestion();
            //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    displayQuestion(takeCurrentQuestion());
                }
            }, freezeTime);

        }
    }
    // -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --

    // -- -- -- -- -- -- Functions to play game -- -- -- -- -- -- -- //
    // Function to Start the game
    void startGame() {
        countQuestion = 1;
        esportQuestions = new ArrayList<>();

        // Put the data into each question
        Questions question0 = new Questions(R.drawable.img_esport_0, "An iconic of the League of Legends with 3-times World Championship, who is he?", "Sneaky", "Rekkles", "Faker", "S1mple", 2);
        Questions question1 = new Questions(R.drawable.img_esport_1, "What is the name of the team that won The International 10 in Dota 2?", "Team Spirit", "PSG.LGD", "OG", "T1", 0);
        Questions question2 = new Questions(R.drawable.img_esport_2, "The only team has won 4 Majors in CS:GO, which is that team?", "Natus Vincere", "Astralis", "G2 Esport", "Team Liquid", 1);
        Questions question3 = new Questions(R.drawable.img_esport_3, "A guy who is called \"Human Headshot Machine\" in CS:GO, achieved headshot accuracy of 65.8% during his entire professional career before retirement. Who is this guy?", "Oleksandr \"s1mple\" Kostyliev", "Nicolai \"device\" Reedtz", "Mathieu \"ZywOo\" Herbaut", "Adil \"ScreaM\" Benrlitom", 3);
        Questions question4 = new Questions(R.drawable.img_esport_4, "A famous Streamer in League of Legends in Vietnam. Who is this guy?", "Optimus", "Thầy giáo ba", "Hiếu Nidalee", "MisThy", 1);

        esportQuestions.add(question0);
        esportQuestions.add(question1);
        esportQuestions.add(question2);
        esportQuestions.add(question3);
        esportQuestions.add(question4);


        totalCorrectAnswers = 0;
        totalQuestions = esportQuestions.size();
        questionsTotalTextView.setText(String.valueOf(totalQuestions));

        Questions firstQuestion = chooseNewEsportQuestion();


        // Display the current number for the current question
        displayEsportQuestionNumber(countQuestion);

        // Display the question on the TextView
        displayQuestion(firstQuestion);
    }

    // Function to choose a new Question when game is started or after the previous question is finished
    Questions chooseNewEsportQuestion() {
        int newQuestionIndex = pickRandomQuestion(esportQuestions.size());
        currentQuestionIndex = newQuestionIndex;
        return esportQuestions.get(currentQuestionIndex);
    }


    // Function generate the random question during the game
    int pickRandomQuestion(int max) {
        double randomNumber = Math.random();
        double result = max * randomNumber;
        return (int) result;
    }

    // Function to take the question
    Questions takeCurrentQuestion() {
        Questions currentQuestion = esportQuestions.get(currentQuestionIndex);
        return currentQuestion;
    }











}
