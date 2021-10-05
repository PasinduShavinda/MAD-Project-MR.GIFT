package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import mad.example.teamdragons.Database.DBHandler;

public class orderList extends AppCompatActivity {

    ListView orderList;
    ArrayList dataList;
    ArrayAdapter adapter;

    DBHandler db;

    Button btnPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);

        orderList = findViewById(R.id.lv_orderList);
        btnPayment = (Button) findViewById(R.id.btn_paymentOL);

        db = new DBHandler(getApplicationContext());

        dataList = db.readData();

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,dataList);

        orderList.setAdapter(adapter);

        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String text = orderList.getItemAtPosition(i).toString();

                Toast.makeText(orderList.this, "Order :" +text, Toast.LENGTH_SHORT).show();
            }
        });

        btnPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPayment();
            }
        });
    }

    // navigation


    public void openPayment(){

        Intent intent = new Intent(this, PaymentInsert.class);

        startActivity(intent);

    }

}