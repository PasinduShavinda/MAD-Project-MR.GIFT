package mad.example.teamdragons;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Product_Calculator extends AppCompatActivity {

    EditText calc_Price_Buy, Qunatity_calc_Buy, Qunatity_calc_Sell, calc_Price_Sell;
    Button calc_button3, calc_btn_clear, btn_sell;
    TextView calc_Answer_Buy, calc_Answer_Sell, calc_Answer3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_calculator);

        calc_Price_Buy = findViewById(R.id.calc_Price_Buy );
        Qunatity_calc_Buy = findViewById(R.id.Qunatity_calc_Buy);
        calc_button3 = findViewById(R.id.calc_button3);
        calc_btn_clear = findViewById(R.id.calc_btn_clear);
        calc_Answer_Buy = findViewById(R.id.calc_Answer_Buy);
        calc_Answer_Sell = findViewById(R.id.calc_Answer_Sell);
        calc_Answer3 = findViewById(R.id.calc_Answer3);
        btn_sell = findViewById(R.id.btn_sell);
        Qunatity_calc_Sell = findViewById(R.id.Qunatity_calc_Sell);
        calc_Price_Sell = findViewById(R.id.calc_Price_Sell);


        calc_btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });
        calc_Answer_Buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buyingCal();
            }
        });
        btn_sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                damageCal();
            }
        });
        calc_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                remainCal();
            }
        });
    }
    public void clear(){
        calc_Price_Buy.setText("");
        Qunatity_calc_Buy.setText("");
        calc_Answer_Buy.setText("");
        calc_Answer_Sell.setText("");
        calc_Answer3.setText("");
        calc_Price_Sell.setText("");
        Qunatity_calc_Sell.setText("");

    }
    //product buying calculation
    public void buyingCal(){
        double Price_Buy;
        int Qunatity_Buy;
        double tot;

        Price_Buy =  Integer.parseInt(calc_Price_Buy .getText().toString());
        Qunatity_Buy = Integer.parseInt(Qunatity_calc_Buy.getText().toString());

        tot = Price_Buy * Qunatity_Buy;
        calc_Answer_Buy.setText(String.valueOf(tot));

    }

    //returns calculation
    public void damageCal(){
        double Price_Damage;
        int Qunatity_Damage;
        double tot2;

        Price_Damage =  Integer.parseInt(calc_Price_Sell .getText().toString());
        Qunatity_Damage = Integer.parseInt(Qunatity_calc_Sell.getText().toString());

        tot2 = Price_Damage * Qunatity_Damage;
        calc_Answer_Sell.setText(String.valueOf(tot2));

    }

    public void remainCal(){
        double buy;
        int buyqty;
        double damage;
        int damageqty;
        double tot3;

        buy = Integer.parseInt(calc_Price_Buy.getText().toString());
        buyqty = Integer.parseInt(Qunatity_calc_Buy.getText().toString());
        damage = Integer.parseInt(calc_Price_Sell .getText().toString());
        damageqty = Integer.parseInt(Qunatity_calc_Sell.getText().toString());

        tot3=(buy*buyqty)-(damage*damageqty);
        calc_Answer3.setText((String.valueOf(tot3)));
    }
}