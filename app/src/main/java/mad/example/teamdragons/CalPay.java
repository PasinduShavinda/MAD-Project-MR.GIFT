package mad.example.teamdragons;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CalPay extends AppCompatActivity {

    EditText buying_price, qty,sellprice;
    Button calculator,clearcal2;
    TextView prodctamunt,pductIncome,discountprodct;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cal_pay);

        buying_price = findViewById(R.id.buying_price);
        qty = findViewById(R.id.qty);
        sellprice = findViewById(R.id.sellprice);
        calculator = findViewById(R.id.calculator);
        prodctamunt = findViewById(R.id.prodctamunt);
        pductIncome = findViewById(R.id.pductIncome);
        discountprodct = findViewById(R.id.discountprodct);
        clearcal2 = findViewById(R.id.clearcal2);

        calculator.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               sumofcall();
           }
       });

        clearcal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearpay();
            }
        });




    }


    public void sumofcall(){
        double productprice;
        int quantity;
        double productA;
        double productInc;
        double productDiscounts;
        double selp;



        productprice = Integer.parseInt(buying_price.getText().toString());
        quantity = Integer.parseInt(qty.getText().toString());
        selp = Integer.parseInt(sellprice.getText().toString());


        productDiscounts = (selp * quantity * 20/100);
        discountprodct.setText(String.valueOf(productDiscounts));

        productA= (productprice*quantity);
        prodctamunt.setText(String.valueOf(productA));

        productInc = (productA-productDiscounts);
        pductIncome.setText(String.valueOf(productInc));
    }

    public void clearpay(){
        buying_price.setText("");
        qty.setText("");
        sellprice.setText("");
    }


}




