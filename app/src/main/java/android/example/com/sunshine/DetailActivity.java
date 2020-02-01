package android.example.com.sunshine;

import android.content.Intent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    public static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private TextView mWeatherDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mWeatherDisplay = findViewById(R.id.tv_display_weather);

        Intent intentFromMainActivity = getIntent();

        if (intentFromMainActivity != null &&
                intentFromMainActivity.hasExtra(intentFromMainActivity.EXTRA_TEXT)) {

            String weatherForDay = intentFromMainActivity.getStringExtra(Intent.EXTRA_TEXT);

            mWeatherDisplay.setText(weatherForDay);
        }
    }
}
