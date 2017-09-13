package com.example.android.quakereport;

public class Earthquake {

    private double mMag;
    private String mCity;
    private long mDate;
    private String mURL;

    /**
     * Creates an Earthquake object consisting of magnitude, city, and date of an earthquake
     *
     * @param mMag Magnitude of earthquake
     * @param mCity City of earthquake
     * @param mDate Date of earthquake
     */
    public Earthquake(double mMag, String mCity, long mDate, String mURL){
        this.mMag = mMag;
        this.mCity = mCity;
        this.mDate = mDate;
        this.mURL = mURL;
    }

    /**
     *
     * @return String containing the magnitude of earthquake
     */
    public double getmMag() {
        return mMag;
    }

    /**
     *
     * @return String containing city of earthquake
     */
    public String getmCity() {
        return mCity;
    }

    /**
     *
     * @return long containing date of earthquake in UNIX time
     */
    public long getmDate() {
        return mDate;
    }

    /**
     *
     * @return String containing URL for the earthquake
     */
    public String getmURL(){
        return mURL;
    }
}
