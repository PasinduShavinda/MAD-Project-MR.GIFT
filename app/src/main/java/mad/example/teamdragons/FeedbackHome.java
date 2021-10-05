package mad.example.teamdragons;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FeedbackHome extends AppCompatActivity {

    Button viewFeedbacks, feedbackCalculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_home);
        viewFeedbacks = findViewById(R.id.btn_adminview);
        feedbackCalculator = findViewById(R.id.btn_adminCal);

        viewFeedbacks.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), feedbackList.class);
                startActivity(i);
            }
        });

        feedbackCalculator.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), feedbackCalculation.class);
                startActivity(i);
            }
        });

    }
}