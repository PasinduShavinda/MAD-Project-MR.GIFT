package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class OrderCalculation extends AppCompatActivity {

    EditText edtID, edtName, edtTotP, edtDelivery, edtOrderTot;
    Button btnCal, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_calculation);


        edtID = findViewById(R.id.et_id_OC);
        edtName = findViewById(R.id.et_name_OC);
        edtTotP = findViewById(R.id.et_totPrice);
        edtDelivery = findViewById(R.id.et_delivaryOC);
        edtOrderTot = findViewById(R.id.et_totOrderOC);

        btnCal = findViewById(R.id.btn_calculateOC);
        btnClear = findViewById(R.id.btn_clearOC);


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                clear();

            }
        });

        btnCal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                double tot = orderCal(Double.parseDouble(edtTotP.getText().toString()), Double.parseDouble(edtDelivery.getText().toString()));

                edtOrderTot.setText(String.valueOf(tot));

            }
        });
    }

    public void clear(){

        edtID.setText("");
        edtName.setText("");
        edtTotP.setText("");
        edtDelivery.setText("");
        edtOrderTot.setText("");

    }

    protected double orderCal(double amOFProduct, double delCharg){

        return amOFProduct + delCharg;
    }

}