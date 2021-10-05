package mad.example.teamdragons;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import mad.example.teamdragons.Database.DBHandlerFeedback;

public class UpdateFeedback extends AppCompatActivity {

    EditText customerName, email, description;
    Button updateFeedback, deleteFeedback, searchFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_feedback);

            customerName = findViewById(R.id.et_cusnamefeedback);
            email = findViewById(R.id.et_emailfeedback);
            description = findViewById(R.id.et_editdescrip);
            updateFeedback = findViewById(R.id.btn_editfeedback);
            deleteFeedback = findViewById(R.id.btn_deleteFeedback);
            searchFeedback = findViewById(R.id.btn_searchcusnamefeedback);


            searchFeedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    DBHandlerFeedback dbHandler = new DBHandlerFeedback(getApplicationContext());
                    List feedback = dbHandler.readAllData(customerName.getText().toString());

                    if(feedback.isEmpty()){

                        Toast.makeText(UpdateFeedback.this, "No Feedback Found", Toast.LENGTH_SHORT).show();
                        customerName.setText(null);
                    }
                    else {

                        Toast.makeText(UpdateFeedback.this, "Feedback details found! Customer Name: "+feedback.get(0).toString(), Toast.LENGTH_SHORT).show();

                        customerName.setText(feedback.get(0).toString());
                        email.setText(feedback.get(1).toString());
                        description.setText(feedback.get(2).toString());

                    }

                }
            });

        updateFeedback.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View view) {


                    if(!validatecustName() | !validateEm() | !validateDes()){
                        return;
                    }


                    DBHandlerFeedback dbHandler = new DBHandlerFeedback(getApplicationContext());

                    Boolean status = dbHandler.updateData(customerName.getText().toString(),email.getText().toString(), description.getText().toString());

                    if(status){
                        Toast.makeText(UpdateFeedback.this, "Feedback Updated", Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(UpdateFeedback.this, "Feedback Update Failed", Toast.LENGTH_SHORT).show();
                    }
                }

            });

        deleteFeedback.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    DBHandlerFeedback dbHandler = new DBHandlerFeedback(getApplicationContext());
                    dbHandler.deleteData(customerName.getText().toString());

                    Toast.makeText(UpdateFeedback.this, "Feedback Deleted", Toast.LENGTH_SHORT).show();

                    customerName.setText(null);
                    email.setText(null);
                    description.setText(null);
                }
            });

        }


    private Boolean validatecustName(){
        String val = customerName.getText().toString();
        String noWhiteSpace = "^[A-Za-z]+$";

        if(val.isEmpty()){
            customerName.setError("Field cannot be empty");
            Toast.makeText(UpdateFeedback.this,"Fields cannot be empty ! ",Toast.LENGTH_SHORT).show();
            return false;
        }else if(val.length()>=15){
            customerName.setError("Your username is too long");
            return false;
        }else if(!val.matches(noWhiteSpace)) {
            customerName.setError("White space are not allowed");
            Toast.makeText(UpdateFeedback.this,"White space are not allowed ! ",Toast.LENGTH_SHORT).show();
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
