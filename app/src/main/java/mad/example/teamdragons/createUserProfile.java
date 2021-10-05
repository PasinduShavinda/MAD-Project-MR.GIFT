package mad.example.teamdragons;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.xml.validation.Validator;

import mad.example.teamdragons.Database.DBHandlerUserPro;

public class createUserProfile extends AppCompatActivity {

    EditText name, age, contact, email, nic, address;
    Button btnChoose, btnSave, btnView;
    ImageView imageView;


    final int REQUEST_CODE_GALLERY = 999;

    public static DBHandlerUserPro dbHandlerUserPro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user_profile);

        init();


        dbHandlerUserPro = new DBHandlerUserPro(this, "userDB", null, 1);

        dbHandlerUserPro.queryData("CREATE TABLE IF NOT EXISTS USER(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, age INTEGER, contact VARCHAR, email VARCHAR, nic VARCHAR, address VARCHAR, image BLOG)");

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(
                        createUserProfile.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_GALLERY
                );

            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //////////////////////////////////////////////////////////////
                if(!validateName() |!validateAge() | !validateContact() | !validateEmail() | !validateNic() | !validateAddress()){
                    return;
                }


                try {
                    dbHandlerUserPro.insertData(
                            name.getText().toString().trim(),
                            age.getText().toString().trim(),
                            contact.getText().toString().trim(),
                            email.getText().toString().trim(),
                            nic.getText().toString().trim(),
                            address.getText().toString().trim(),
                            imageViewToByte(imageView)
                    );


                    Toast.makeText(getApplicationContext(), "Your profile created successfully !", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(getApplicationContext(),UserProSplashScreen.class);
                    startActivity(i);

                    name.setText("");
                    age.setText("");
                    contact.setText("");
                    email.setText("");
                    nic.setText("");
                    address.setText("");
                    imageView.setImageResource(R.mipmap.ic_launcher);


                } catch (Exception e) {
                    e.printStackTrace();
                }



            }


        });


        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(createUserProfile.this, UserList.class);
                startActivity(intent);

            }
        });

    }

    ///////////////////////////////////////////////////////////////////////////
    private Boolean validateName() {

        String val = name.getText().toString().trim();

        if (val.isEmpty()) {
            name.setError("Field cannot be empty");
            Toast.makeText(createUserProfile.this,"Fields Cannot be empty !", Toast.LENGTH_LONG).show();
            return false;
        } else {
            name.setError(null);
            return true;

        }

    }

    private Boolean validateAge() {

        String val = age.getText().toString().trim();


        if (val.isEmpty()) {
            age.setError("Field cannot be empty");
            Toast.makeText(createUserProfile.this,"Fields Cannot be empty !", Toast.LENGTH_LONG).show();
            return false;
        } else if(val.length()>2){
            age.setError("Invalid Age");
            Toast.makeText(createUserProfile.this,"Invalid age !", Toast.LENGTH_LONG).show();
            return false;
        } else {
            age.setError(null);
            return true;

        }

    }

    private Boolean validateContact() {

        String val = contact.getText().toString().trim();

        if(val.isEmpty()) {
            contact.setError("Field cannot be empty");
            Toast.makeText(createUserProfile.this,"Fields Cannot be empty !", Toast.LENGTH_LONG).show();
            return false;
        }else if(val.length()!=10){
            contact.setError("Invalid Contact Number");
            Toast.makeText(createUserProfile.this,"Invalid contact number !", Toast.LENGTH_LONG).show();
            return false;
        } else {
            contact.setError(null);
            return true;

        }

    }

    private Boolean validateEmail() {

        String val = email.getText().toString().trim();
        String emailPattern = "^[a-zA-Z0-9_.]+@[a-zA-Z.]+?\\.[a-zA-Z]{2,3}$";

        if (val.isEmpty()) {
            email.setError("Field cannot be empty");
            Toast.makeText(createUserProfile.this,"Fields Cannot be empty !", Toast.LENGTH_LONG).show();
            return false;
        } else if(!val.matches(emailPattern)) {
            email.setError("Invalid Email Address");
            Toast.makeText(createUserProfile.this,"Invalid Email Address !", Toast.LENGTH_LONG).show();
            return false;

        }else{
            email.setError(null);
            return  true;
        }

    }

    private Boolean validateNic() {

        String val = nic.getText().toString().trim();
        String nicPattern = "^([0-9]{9}[x|X|v|V]|[0-9]{12})$";

        if (val.isEmpty()) {
            nic.setError("Field cannot be empty");
            Toast.makeText(createUserProfile.this,"Fields Cannot be empty !", Toast.LENGTH_LONG).show();
            return false;
        } else if(!val.matches(nicPattern)) {
            nic.setError("Invalid NIC");
            Toast.makeText(createUserProfile.this,"Invalid NIC!", Toast.LENGTH_LONG).show();
            return false;
        } else {
            nic.setError(null);
            return true;

        }

    }

    private Boolean validateAddress() {

        String val = address.getText().toString().trim();

        if (val.isEmpty()) {
            address.setError("Field cannot be empty");
            Toast.makeText(createUserProfile.this,"Fields Cannot be empty !", Toast.LENGTH_LONG).show();
            return false;
        } else {
            address.setError(null);
            return true;

        }

    }


    public static byte[] imageViewToByte(ImageView image) {

        Bitmap bitmap = ((BitmapDrawable) image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == REQUEST_CODE_GALLERY) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            } else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access !!!", Toast.LENGTH_SHORT).show();
            }
            return;

        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {

            Uri uri = data.getData();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }

    private void init() {

        name = (EditText) findViewById(R.id.et_usernameCP);
        age = (EditText) findViewById(R.id.et_userageCP);
        contact = (EditText) findViewById(R.id.et_userPhoneCP);
        email = (EditText) findViewById(R.id.et_userEmailCP);
        nic = (EditText) findViewById(R.id.et_userNicCP);
        address = (EditText) findViewById(R.id.et_userAddressCP);
        btnChoose = (Button) findViewById(R.id.btn_userPicCP);
        btnSave = (Button) findViewById(R.id.btn_userSaveCP);
        btnView = (Button) findViewById(R.id.btn_userViewCP);
        imageView = (ImageView) findViewById(R.id.imageViewUserCP);


    }

}