package mad.example.teamdragons;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class feedbackSuccessSplashScreen extends AppCompatActivity {

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_success_splash_screen);

        handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent intent = new Intent(feedbackSuccessSplashScreen.this,feedbackSuccess.class);
                startActivity(intent);
                finish();

            }

        },5000);


    }
}
