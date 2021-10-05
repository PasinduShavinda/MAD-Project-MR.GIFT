package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btnAdmin, btnUser, btnFeedbackClient, btnPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAdmin = (Button) findViewById(R.id.btnaddash);
        btnUser = (Button) findViewById(R.id.btn_userProfile);
        btnFeedbackClient = (Button) findViewById(R.id.btnfeedbackClient);
        btnPayment = (Button) findViewById(R.id.btnClientPayment);


        btnAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdminDashboard();
            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openUserProfile();
            }
        });


        btnFeedbackClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFeedbackClient();
            }

        });

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPaymentClient();
            }

        });



    }

    // navigation


    public void openAdminDashboard(){

        Intent intent = new Intent(this, AdminDashboardGift.class);

        startActivity(intent);

    }



    public void openUserProfile(){

        Intent intent = new Intent(this, createUserProfile.class);

        startActivity(intent);

    }



    public void openFeedbackClient(){

        Intent intent = new Intent(this, feedbackSplashScreen.class);

        startActivity(intent);

    }

    public void openPaymentClient(){

        Intent intent = new Intent(this, PaymentInsert.class);

        startActivity(intent);

    }




}