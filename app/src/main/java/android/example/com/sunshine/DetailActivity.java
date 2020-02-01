package android.example.com.sunshine;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.core.app.ShareCompat;

public class DetailActivity extends AppCompatActivity {

    public static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";
    private TextView mWeatherDisplay;
    private String weatherForDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mWeatherDisplay = findViewById(R.id.tv_display_weather);

        Intent intentFromMainActivity = getIntent();

        if (intentFromMainActivity != null &&
                intentFromMainActivity.hasExtra(intentFromMainActivity.EXTRA_TEXT)) {

            weatherForDay = intentFromMainActivity.getStringExtra(Intent.EXTRA_TEXT);

            mWeatherDisplay.setText(weatherForDay);
        }
    }

    /**
     * Uses the ShareCompat Intent builder to create our Forecast intent for sharing. We set the
     * type of content that we are sharing (just regular text), the text itself, and we return the
     * newly created Intent.
     *
     * @return The Intent to use to start our share.
     */
    private Intent buildShareForecastIntent() {
        return ShareCompat.IntentBuilder.from(this)
                .setType("text/plain")
                .setText(weatherForDay + FORECAST_SHARE_HASHTAG)
                .getIntent();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu, menu);

        MenuItem menuItem = menu.findItem(R.id.action_share);

        menuItem.setIntent(buildShareForecastIntent());
        /* Return true so that the menu is displayed in the Toolbar */
        return true;
    }
}
