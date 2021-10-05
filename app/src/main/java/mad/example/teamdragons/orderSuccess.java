package mad.example.teamdragons;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class orderSuccess extends AppCompatActivity {

    Button edit, view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);

        edit = findViewById(R.id.btn_editOF);
        view = findViewById(R.id.btn_viewOF);

        view.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), orderList.class);
                startActivity(i);
            }
        });


        edit.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view){
                Intent i = new Intent(getApplicationContext(), orderEdit.class);
                startActivity(i);
            }
        });


    }
}