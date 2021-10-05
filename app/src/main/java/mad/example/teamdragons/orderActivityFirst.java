package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import mad.example.teamdragons.Database.DBHandler;

public class orderActivityFirst extends AppCompatActivity {

    EditText customer, contact, address, postal, date;
    Button add;
    RadioButton wrapYes, wrapNo, cash, card;
    String wrap, payment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_first);

        customer = findViewById(R.id.et_nameOF);
        contact = findViewById(R.id.et_phoneOF);
        address = findViewById(R.id.et_addressOF);
        postal = findViewById(R.id.et_postalOF);
        date = findViewById(R.id.et_dateOF);
        wrapYes = findViewById(R.id.rd_wrapYesOF);
        wrapNo = findViewById(R.id.rd_wrapNoOF);
        cash = findViewById(R.id.rd_cashOF);
        card = findViewById(R.id.rd_cardOF);
        add = findViewById(R.id.btn_submitOF);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(wrapYes.isChecked()){

                    wrap = "Yes";
                }
                else if (wrapNo.isChecked()){
                    wrap = "No";
                }

                if(cash.isChecked()){
                    payment = "Cash";
                }
                else if(card.isChecked()){
                    payment = "Card";
                }

                ///////////////////////////////////////////////////////////
                if(!validateCusName() |!validateCusContact() | !validateCusAddress() | !validatePostal() | !validateCusDate()){
                    return;
                }


                DBHandler dbHandler = new DBHandler(getApplicationContext());
                long newID = dbHandler.addData(
                        customer.getText().toString(),
                        contact.getText().toString(),
                        address.getText().toString(),
                        postal.getText().toString(),
                        date.getText().toString(),
                        wrap,
                        payment);

                Toast.makeText(orderActivityFirst.this,"Successful !! Your Order Placed , Order ID: "+newID, Toast.LENGTH_LONG).show();

                Intent i = new Intent(getApplicationContext(),OrderSplashScreen.class);
                startActivity(i);

                customer.setText(null);
                contact.setText(null);
                address.setText(null);
                postal.setText(null);
                date.setText(null);
                wrapYes.setChecked(true);
                wrapNo.setChecked(false);
                cash.setChecked(true);
                card.setChecked(false);

            }
        });

    }

    ///////////////////////////////////////////////////////

    private Boolean validateCusName() {

        String val = customer.getText().toString();

        if (val.isEmpty()) {
            customer.setError("Field cannot be empty");
            Toast.makeText(orderActivityFirst.this,"Fields cannot be empty !", Toast.LENGTH_LONG).show();
            return false;
        } else if(!val.matches("^[A-Za-z]+$")){
            customer.setError("Invalid Name");
            Toast.makeText(orderActivityFirst.this,"Invalid Name !", Toast.LENGTH_LONG).show();
            return false;

        } else {
            customer.setError(null);
            return true;


        }

    }

    private Boolean validateCusContact() {

        String val = contact.getText().toString();

        if(val.isEmpty()) {
            contact.setError("Field cannot be empty");
            Toast.makeText(orderActivityFirst.this,"Fields cannot be empty !", Toast.LENGTH_LONG).show();
            return false;
        }else if(val.length()!=10){
            contact.setError("Invalid Contact Number");
            Toast.makeText(orderActivityFirst.this,"Invalid Contact Number!", Toast.LENGTH_LONG).show();
            return false;
        } else {
            contact.setError(null);
            return true;

        }

    }

    private Boolean validateCusAddress() {

        String val = address.getText().toString();

        if (val.isEmpty()) {
            address.setError("Field cannot be empty");
            Toast.makeText(orderActivityFirst.this,"Fields cannot be empty !", Toast.LENGTH_LONG).show();
            return false;
        } else {
            address.setError(null);
            return true;

        }

    }

    private Boolean validatePostal() {

        String val = postal.getText().toString().trim();

        if (val.isEmpty()) {
            postal.setError("Field cannot be empty");
            Toast.makeText(orderActivityFirst.this,"Fields cannot be empty !", Toast.LENGTH_LONG).show();
            return false;
        } else if(val.length()!=3) {
            postal.setError("Invalid Postal Code");
            Toast.makeText(orderActivityFirst.this,"Invalid Postal Code!", Toast.LENGTH_LONG).show();
            return false;
        }else {
            postal.setError(null);
            return true;

        }

    }

    private Boolean validateCusDate() {

        String val = date.getText().toString();

        if (val.isEmpty()) {
            date.setError("Field cannot be empty");
            Toast.makeText(orderActivityFirst.this,"Fields cannot be empty !", Toast.LENGTH_LONG).show();
            return false;
        } else {
            date.setError(null);
            return true;

        }

    }



}