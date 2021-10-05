package mad.example.teamdragons;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import mad.example.teamdragons.Database.DBHandlerProduct;

public class ClientHome extends AppCompatActivity {

    ListView mListView1;
    ArrayList<ProductModel> mList1;
    ProductAdapter mAdapter1 = null;

    DBHandlerProduct db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);

        mListView1 = findViewById(R.id.productlist);
        mList1 = new ArrayList<>();
        mAdapter1 = new ProductAdapter(this, R.layout.row, mList1);
        mListView1.setAdapter(mAdapter1);

        db = new DBHandlerProduct(getApplicationContext());
        Cursor cursor = db.getData("SELECT * FROM Product");
        mList1.clear();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String des = cursor.getString(2);
            String price = cursor.getString(3);
            byte[] image = cursor.getBlob(4);
            mList1.add(new ProductModel(id, name, des, price, image));
        }
        mAdapter1.notifyDataSetChanged();
        if (mList1.size() == 0) {
            //if there is no reord in table of database
            Toast.makeText(this, "No record found...", Toast.LENGTH_SHORT).show();


        }
    }
}