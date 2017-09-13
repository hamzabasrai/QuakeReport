package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;


/**
 * Loads a list of earthquakes by using an AsyncTask to perform the
 * network request to the given URL.
 */
public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Earthquake>> {

    /** Query URL */
    private String mURL;

    /**
     * Constructs a new EarthquakeLoader.
     *
     * @param context of the activity
     * @param url to load data from
     */
    public EarthquakeLoader(Context context, String url) {
        super(context);
        mURL = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    /**
     * This is on a background thread
     */
    @Override
    public ArrayList<Earthquake> loadInBackground() {
        if (mURL == null) {
            return null;
        }

        // Perform the network request, parse the response, and extract a list of earthquakes.
        ArrayList<Earthquake> earthquakes = QueryUtils.fetchEarthquakeData(mURL);
        return earthquakes;
    }
}
