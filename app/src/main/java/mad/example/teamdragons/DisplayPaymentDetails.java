package mad.example.teamdragons;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import mad.example.teamdragons.Database.DBHandlerpayment;

public class DisplayPaymentDetails extends AppCompatActivity {


    ListView paymentlist;
    ArrayList paydataList;
    ArrayAdapter payadapter;
    DBHandlerpayment paydb;
    Button success, edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_payment_details);

        success = findViewById(R.id.paysuccesPI);
        edit = findViewById(R.id.btneditPD);

        paymentlist = findViewById(R.id.paymentlist);

        paydb = new DBHandlerpayment(getApplicationContext());
        paydataList = paydb.readAllPayment();

        payadapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,paydataList);
        paymentlist.setAdapter(payadapter);

        paymentlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String paytext = paymentlist.getItemAtPosition(i).toString();
                Toast.makeText(DisplayPaymentDetails.this, "Payment"+paytext, Toast.LENGTH_SHORT).show();
            }
        });

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),UpdatePayment.class);
                startActivity(i);
            }
        });

        success.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),PaymentSuccess.class);
                startActivity(i);
            }
        });

 }


    }
