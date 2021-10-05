package mad.example.teamdragons;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import mad.example.teamdragons.Database.DBHandlerProduct;

public class EditProducts extends AppCompatActivity {

    EditText product, description,price;
    Button submit, search, delete;
    ImageView cover;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_products);

        product = findViewById(R.id.edt_edt_txt_Pname);
        description = findViewById(R.id.edt_edt_txt_description);
        price = findViewById(R.id.edt_edt_txt_price);
         submit = findViewById(R.id.btn_update);
         search = findViewById(R.id.btn_search);
        delete = findViewById(R.id.btn_delete);
        cover = findViewById(R.id.edit_edit_image);

         search.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 DBHandlerProduct dbHandlerProduct=new DBHandlerProduct(getApplicationContext());
                List p= dbHandlerProduct.readAllInfo(product.getText().toString());

                if(p.isEmpty()){
                    Toast.makeText(EditProducts.this, "No product", Toast.LENGTH_SHORT).show();
                    product.setText(null);
                }
                else{
                    Toast.makeText(EditProducts.this, "Product Found. User: "+p.get(0).toString(), Toast.LENGTH_SHORT).show();
                    product.setText(p.get(0).toString());
                    description.setText(p.get(1).toString());
                    price.setText(p.get(2).toString());



                }

             }


         });


         submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 DBHandlerProduct dbHandlerProduct=new DBHandlerProduct(getApplicationContext());

                 Boolean status = dbHandlerProduct.updateInfo(product.getText().toString(), description.getText().toString(), price.getText().toString());
                 if (status){
                    Toast.makeText(EditProducts.this, "Prduct Update", Toast.LENGTH_SHORT).show();
                 }
                 else{
                     Toast.makeText(EditProducts.this, "Update failed", Toast.LENGTH_SHORT).show();
                 }

             }
         });


        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHandlerProduct dbHandlerProduct = new DBHandlerProduct(getApplicationContext());
                dbHandlerProduct.deleteInfo(product.getText().toString());

                Toast.makeText(EditProducts.this, "Product Deleted", Toast.LENGTH_SHORT).show();

                product.setText(null);
                description.setText(null);
                price.setText(null);

            }
        });
    }


    private Bitmap convertByteArrayToBitmap(byte[] bytes){
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }


}