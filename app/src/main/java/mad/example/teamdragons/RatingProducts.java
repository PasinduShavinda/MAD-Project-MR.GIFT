package mad.example.teamdragons;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RatingProducts extends AppCompatActivity {

    TextView rateCount, showRating;
    EditText review;
    Button submitReview;
    RatingBar ratingBar;
    float rateValue;
    String temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_products);

        rateCount = findViewById(R.id.rateCount);
        ratingBar = findViewById(R.id.ratingBar);
        review = findViewById(R.id.et_writeReview);
        submitReview = findViewById(R.id.btn_submitReview);
        showRating = findViewById(R.id.showRating);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                rateValue = ratingBar.getRating () ;

                if (rateValue<=1 && rateValue>0)
                    rateCount.setText("Bad" +rateValue +"/5");
                else if (rateValue<=2 && rateValue>1)
                    rateCount.setText("OK" +rateValue +"/5");
                else if (rateValue<=3 && rateValue>2)
                    rateCount.setText("Good" +rateValue +"/5");
                else if (rateValue<=4 && rateValue>3)
                    rateCount.setText("Very Good" +rateValue +"/5");
                else if (rateValue<=5 && rateValue>4)
                    rateCount.setText("Excellent" +rateValue +"/5");

            }
            });
              submitReview.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      temp = rateCount.getText().toString();
                      showRating.setText("Your Rating: \n" +temp +"\n" +review.getText());
                      review.setText("");
                      ratingBar.setRating(0);
                      rateCount.setText("");

                  }
              });


        }
}