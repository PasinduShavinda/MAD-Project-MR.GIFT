package mad.example.teamdragons;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import mad.example.teamdragons.Database.DBHandlerProduct;

public class ClientProducts extends AppCompatActivity {

    ListView mListView;
    ArrayList<ProductModel> mList;
    ClientAdapter mAdapter = null;
    Button getNext;


    DBHandlerProduct db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);

        mListView = findViewById(R.id.clientproductlist);
        mList = new ArrayList<>();
        mAdapter = new ClientAdapter(this, R.layout.rowclient, mList);
        mListView.setAdapter(mAdapter);
        getNext = findViewById(R.id.buttonorder);

        db = new DBHandlerProduct(getApplicationContext());
        Cursor cursor = db.getData("SELECT * FROM Product");
        mList.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String des = cursor.getString(2);
            String price = cursor.getString(3);
            byte[] image = cursor.getBlob(4);
            mList.add(new ProductModel(id, name, des, price, image));
        }
        mAdapter.notifyDataSetChanged();
        if (mList.size() == 0) {
            //if there is no reord in table of database
            Toast.makeText(this, "No record found...", Toast.LENGTH_SHORT).show();


        }

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(ClientProducts.this, orderActivityFirst.class);
                startActivity(intent);

            }
        });

    }


    public void GetNext(View view) {
        Intent intent=new Intent(ClientProducts.this, orderActivityFirst.class);
        startActivity(intent);
    }

    public void GetFeedback(View view) {
        Intent intent=new Intent(ClientProducts.this, feedbackSplashScreen.class);
        startActivity(intent);
    }
}

