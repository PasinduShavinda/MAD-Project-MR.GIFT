package mad.example.teamdragons;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class feedbackSuccess extends AppCompatActivity {

    Button feedbackEdit, feedbackView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_success);

        feedbackEdit = findViewById(R.id.btn_successedit);
        feedbackView = findViewById(R.id.btn_successview);

        feedbackEdit.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), UpdateFeedback.class);
                startActivity(i);
            }
        });

        feedbackView.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), feedbackList.class);
                startActivity(i);
            }
        });

    }
}