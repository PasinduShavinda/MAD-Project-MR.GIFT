package mad.example.teamdragons;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import mad.example.teamdragons.Database.DBHandlerFeedback;

public class feedbackList extends AppCompatActivity {


    ListView feedbackList;
    ArrayList dataList;
    ArrayAdapter adapter;

    DBHandlerFeedback db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback_list);

        feedbackList = findViewById(R.id.li_feedback);

        db = new DBHandlerFeedback(getApplicationContext());

        dataList = db.readData();

        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,dataList);

        feedbackList.setAdapter(adapter);

        feedbackList.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String text = feedbackList.getItemAtPosition(i).toString();

                Toast.makeText(feedbackList.this, "Feedback :" +text, Toast.LENGTH_SHORT).show();
            }
        });

    }
}