package mad.example.teamdragons;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class ProductManagementHome extends AppCompatActivity {

    Button add, view, button4, btn_EditView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_management_home);

        add = findViewById(R.id.btn_Home_Add);
        view = findViewById(R.id.btn_Home_View);
        button4 = findViewById(R.id.button4);
        btn_EditView = findViewById(R.id.btn_EditView);



        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity2();
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductManagementHome.this, ViewProducts.class));
            }
        });
        btn_EditView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductManagementHome.this, EditProducts.class));
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductManagementHome.this, Product_Calculator.class));
            }
        });
    }
    public void openActivity2(){
        Intent intent = new Intent(this, AddProducts.class);
        startActivity(intent);
    }



}