package mad.example.teamdragons;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import mad.example.teamdragons.Database.DBHandlerpayment;

public class PaymentInsert extends AppCompatActivity {

    EditText cardnum,cashholderN,expird,cvc;
    Button proceed;


    //validation start here
    private Boolean validateCardNumber(){
       String cardN = cardnum.getText().toString().toString();



        if(cardN.isEmpty()){
            cardnum.setError("Field cannot be empty");
            Toast.makeText(PaymentInsert.this, "Feields Cannot be Empty! ", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(cardN.length()>16) {
            cardnum.setError("Card number is too long");
            return false;

        }

      else{
            cardnum.setError(null);
            return true;
        }

    }

    private Boolean validatecardholder(){
        String cashhlder = cashholderN.getText().toString().toString();

            String noWhitespace = "^[A-Za-z]+$";

        if(cashhlder.isEmpty()){
            cashholderN.setError("Field cannot be empty");
            Toast.makeText(PaymentInsert.this, "Feields Cannot be Empty! ", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(cashhlder.length()>=26){
            cashholderN.setError("Cash holder name is too long");
            return false;
        } else if(cashhlder.length()<=2){
            cashholderN.setError("Cash holder name is too short");
            return false;
        }
        else if(!cashhlder.matches(noWhitespace)){
            cashholderN.setError("Check your name again");
            Toast.makeText(PaymentInsert.this, "Check your name again! ", Toast.LENGTH_SHORT).show();
            return false;
        }
        else{
            cashholderN.setError(null);
            return true;
        }

    }

    private Boolean validateExp(){
        String exp = expird.getText().toString().toString();

        String date = "([0-9]{2}[/]?){2}";

        if(exp.isEmpty()){
            expird.setError("Field cannot be empty");
            Toast.makeText(PaymentInsert.this, "Feields Cannot be Empty! ", Toast.LENGTH_SHORT).show();
            return false;
        } else if(exp.length()>=5) {
            expird.setError("Invalid expire date");
            return false;
        }else if(!exp.matches(date)){
            expird.setError("Please type this date format");
            Toast.makeText(PaymentInsert.this, "Please type this date format ", Toast.LENGTH_SHORT).show();
            return false;
        }else{
            cashholderN.setError(null);
            return true;
        }
    }
    private Boolean validateCvc(){
        String cvcnum = cvc.getText().toString().toString();


        if(cvcnum.isEmpty()){
            cvc.setError("Field cannot be empty");
            Toast.makeText(PaymentInsert.this, "Feields Cannot be Empty! ", Toast.LENGTH_SHORT).show();
            return false;

        } else if(cvcnum.length()>=4) {
            cvc.setError("Card number is too long");
            return false;
        }else if(cvcnum.length()<=2) {
            cvc.setError("Card number is too long");
            return false;
        }
        else{
            cvc.setError(null);
            return true;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_insert);

        cardnum = findViewById(R.id.etCardnumberPI);
        cashholderN = findViewById(R.id.etCashHolderNPI);
        expird = findViewById(R.id.etExpirePI);
        cvc = findViewById(R.id.etCvcPI);
        proceed = findViewById(R.id.btnpay);





       proceed.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               //calling validations
               if(!validateCardNumber() | !validatecardholder() | !validateExp()| !validateCvc())
               {
                   return;
               }

               DBHandlerpayment dbHandlerpayment = new DBHandlerpayment(getApplicationContext());

               //get all varibles name passing db handler 
               long newID = dbHandlerpayment.paymentAdd(cardnum.getText().toString(),cashholderN.getText().toString(),expird.getText().toString(),cvc.getText().toString());
               
               //create toast m.g
               Toast.makeText(PaymentInsert.this, "Payment Details are Added! Payment ID"+ newID, Toast.LENGTH_SHORT).show();

               Intent i = new Intent(getApplicationContext(),SpashscreenPay.class);

               startActivity(i);
               cardnum.setText(null);
               cashholderN.setText(null);
               expird.setText(null);
               cvc.setText(null);
           }
       });


    }


}