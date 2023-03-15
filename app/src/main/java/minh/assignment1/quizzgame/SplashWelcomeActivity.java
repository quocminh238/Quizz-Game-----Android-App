package minh.assignment1.quizzgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class SplashWelcomeActivity extends AppCompatActivity {

    private static int freezeTime = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_welcome);

        // Make a welcome splash for the game
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent moveToIntroductionActivity = new Intent(SplashWelcomeActivity.this, IntroductionActivity.class);
                startActivity(moveToIntroductionActivity);
                finish();
            }
        }, freezeTime);



    }
}
