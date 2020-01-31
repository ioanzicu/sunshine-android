package android.example.com.sunshine;

import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class NetworkUtils {

    private final static String BASE_URL = "";
    private final static String PARAM_QUERY = "q";
    private final static String PARAM_SORT = "sort";

    public static URL buildUrl(String query) {
        Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, query)
                .appendQueryParameter(PARAM_SORT, PARAM_SORT)
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

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
