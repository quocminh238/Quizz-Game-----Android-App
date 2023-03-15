package minh.assignment1.quizzgame;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Button;
import android.view.*;

public class IntroductionActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        // Hide the action bar
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Add onClickListener for each button Play
        Button btnEsport = (Button) findViewById(R.id.btn_introduction_play);
        btnEsport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToEsportQuestion = new Intent(IntroductionActivity.this,EsportQuestionsActivity.class);
                startActivity(moveToEsportQuestion);
                finish();
            }
        });

    }
}
