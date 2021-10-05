package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminOrderHome extends AppCompatActivity {

    private Button orderAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_order_home);

        orderAd = (Button) findViewById(R.id.btn_orderAdmin);

        orderAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOrderCal();
            }
        });

    }

    public void openOrderCal(){

        Intent intent = new Intent(this, OrderCalculation.class);

        startActivity(intent);

    }
}