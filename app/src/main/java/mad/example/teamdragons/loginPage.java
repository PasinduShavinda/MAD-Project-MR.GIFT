package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class loginPage extends AppCompatActivity {

    Button btncustomer, btnadmin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        btncustomer = (Button) findViewById(R.id.btn_loginCustomer);
        btnadmin = (Button) findViewById(R.id.btn_loginAdmin);


        btncustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCustomer();
            }
        });

        btnadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openAdmin();
            }
        });

    }

    // navigation


    public void openCustomer(){

        Intent intent = new Intent(this, ClientProducts.class);

        startActivity(intent);

    }



    public void openAdmin(){

        Intent intent = new Intent(this, AdminDashboardGift.class);

        startActivity(intent);

    }
}