package mad.example.teamdragons;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import mad.example.teamdragons.Database.DBHandlerFeedback;

public class Feedback extends AppCompatActivity {

    EditText customerName, email, description;
    Button addFeedback, updateFeedback, view, rateUs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        customerName = findViewById(R.id.et_nameFeed);
        email = findViewById(R.id.et_entercusfeedemail);
        description = findViewById(R.id.et_enterfeeddescrip);
        addFeedback = findViewById(R.id.btn_addFeedback);
        updateFeedback = findViewById(R.id.btn_editFeedback);
        view = findViewById(R.id.btn_view_feedback);
        rateUs = findViewById(R.id.btn_rate);


        view.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), feedbackList.class);
                startActivity(i);
            }
        });

        updateFeedback.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), UpdateFeedback.class);
                startActivity(i);
            }
        });

        rateUs.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), RatingProducts.class);
                startActivity(i);
            }
        });



        addFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if(!validatecustName() | !validateEm() | !validateDes()){
                    return;
                }


                DBHandlerFeedback dbHandler = new DBHandlerFeedback(getApplicationContext());
                long newID = dbHandler.addData(

                        customerName.getText().toString(),
                        email.getText().toString(),
                        description.getText().toString()
                );

                Toast.makeText(Feedback.this,"Feedback submitted, Feedback ID: "+newID, Toast.LENGTH_SHORT).show();

                Intent i = new Intent(getApplicationContext(), feedbackSuccessSplashScreen.class);
                startActivity(i);

                customerName.setText(null);
                email.setText(null);
                description.setText(null);

            }
        });

    }
    ///////////////////////////////////////////////////////////////////////////////////////

    private Boolean validatecustName(){
        String val = customerName.getText().toString();
        String noWhiteSpace = "^[A-Za-z]+$";

        if(val.isEmpty()){
            customerName.setError("Field cannot be empty");
            Toast.makeText(Feedback.this,"Fields cannot be empty ! ",Toast.LENGTH_SHORT).show();
            return false;
        }else if(val.length()>=15){
            customerName.setError("Your username is too long");
            return false;
        }else if(!val.matches(noWhiteSpace)) {
            customerName.setError("White space are not allowed");
            Toast.makeText(Feedback.this,"White space are not allowed ! ",Toast.LENGTH_SHORT).show();
            return false;
        }else {
            customerName.setError(null);
            return true;
        }
    }

    private Boolean validateEm(){
        String val = email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()){
            email.setError("Field cannot be empty");
            return false;
        }else if (!val.matches(emailPattern)){
            email.setError("Invalid email address");
            return false;
        }else{
            email.setError(null);
            return true;
        }
    }

    private Boolean validateDes(){
        String val = description.getText().toString();

        if(val.isEmpty()){
            description.setError("Field cannot be empty");
            return false;
        }else if(val.length()>=50){
            description.setError("Your description is too long");
            return false;
        }else{
            description.setError(null);
            return true;
        }
    }
}