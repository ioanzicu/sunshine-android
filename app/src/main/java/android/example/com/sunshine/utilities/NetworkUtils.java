package android.example.com.sunshine.utilities;

import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();
    private static final String DYNAMIC_WEATHER_URL = "https://andfun-weather.udacity.com/weather";
    private static final String STATIC_WEATHER_URL = "https://andfun-weather.udacity.com/staticweather";
    private final static String FORECAST_BASE_URL = STATIC_WEATHER_URL;

    private static final String format = "json";
    private static final String units = "metric";
    private static final int numDays = 14;

    private final static String QUERY_PARAM = "q";
    private final static String LAT_PARAM = "lat";
    private final static String LON_PARAM = "lon";
    private final static String FORMAT_PARAM = "mode";
    private final static String UNITS_PARAM = "units";
    private final static String DAYS_PARAM = "cnt";

    public static URL buildUrl(String locationQuery) {
        Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, locationQuery)
                .appendQueryParameter(FORMAT_PARAM, format)
                .appendQueryParameter(UNITS_PARAM, units)
                .appendQueryParameter(DAYS_PARAM, Integer.toString(numDays))
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "buildUrl: " + url);

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            return readInputStream(urlConnection);
        } finally {
            urlConnection.disconnect();
        }
    }

    private static String readInputStream(HttpURLConnection urlConnection) throws IOException {
        InputStream inputStream = urlConnection.getInputStream();

        Scanner scanner = new Scanner(inputStream);
        scanner.useDelimiter("\\A");

        boolean hasInput = scanner.hasNext();

        return hasInput ? scanner.next() : null;
    }
}
