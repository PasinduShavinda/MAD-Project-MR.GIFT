package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminDashboardGift extends AppCompatActivity {

    Button order, feedback, product, payment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard_gift);

        order = (Button) findViewById(R.id.btnadmindash_order);
        feedback = (Button) findViewById(R.id.btnadmindash_feed);
        product = (Button) findViewById(R.id.btn_adminProduct);
        payment = (Button) findViewById(R.id.btnadmindash_payment);



        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOrderAdminDash();
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFeedAdmin();
            }
        });

        product.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProdAdmin();
            }
        });

        payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPaymentAdmin();
            }

        });
    }

    // navigation

    public void openOrderAdminDash(){

        Intent intent = new Intent(this, AdminOrderHome.class);

        startActivity(intent);

    }

    public void openFeedAdmin(){

        Intent intent = new Intent(this, FeedbackHome.class);

        startActivity(intent);

    }

    public void openProdAdmin(){

        Intent intent = new Intent(this, ProductManagementHome.class);

        startActivity(intent);

    }

    public void openPaymentAdmin(){

        Intent intent = new Intent(this, CalPay.class);

        startActivity(intent);

    }
}