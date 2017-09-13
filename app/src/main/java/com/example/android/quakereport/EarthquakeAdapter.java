package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * EarthquakeAdapter is an ArrayAdapter that can provide the layout for each list
 * based on a data source, which is a list of Earthquake objects.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    /**
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context     The current context. Used to inflate the layout file.
     * @param earthquakes A List of Earthquake objects to display in a list
     */
    public EarthquakeAdapter(Activity context, ArrayList<Earthquake> earthquakes){
        super(context, 0, earthquakes);
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //Check if existing view is being reused, otherwise inflate new view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //Getting current Earthquake object from ArrayList
        Earthquake currentQuake = getItem(position);

        //Declaring TextViews
        TextView mag = (TextView) listItemView.findViewById(R.id.mag);
        TextView locationOffset = (TextView) listItemView.findViewById(R.id.location_offset);
        TextView location = (TextView) listItemView.findViewById(R.id.location);
        TextView date = (TextView) listItemView.findViewById(R.id.date);
        TextView time = (TextView) listItemView.findViewById(R.id.time);

        //Fetching background circle from TextView and storing in a GradientDrawable
        GradientDrawable drawable = (GradientDrawable) mag.getBackground();
        //Determining correct color through helper method
        int magColor = getMagColor(currentQuake.getmMag());
        //Setting the correct color on background circle
        drawable.setColor(magColor);

        //Formatting the date and time based on UNIX timestamp from Earthquake object
        Date dateObject = new Date(currentQuake.getmDate());
        String formattedDate = formatDate(dateObject);
        String formattedTime = formatTime(dateObject);

        //Formatting the location data into Strings locationOffset and location
        String locationData = currentQuake.getmCity();
        String formattedLocation = formatLocation(locationData);
        String formattedOffset = formatOffset(locationData);

        //Formatting the magnitude to show only 1 decimal place
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        String formattedMag = decimalFormat.format(currentQuake.getmMag());


        //Initializing TextViews with data from currentQuake
        mag.setText(formattedMag);
        locationOffset.setText(formattedOffset);
        location.setText(formattedLocation);
        date.setText(formattedDate);
        time.setText(formattedTime);

        return listItemView;
    }

    /**
     * Formats a date object into a readable date of the format LLL dd, yyyy
     *
     * @param date Date object to be formatted
     * @return Formatted String containing date (LLL dd, yyyy)
     */
    private String formatDate(Date date){
        SimpleDateFormat dateFormat = new SimpleDateFormat("LLL dd, yyyy");
        return dateFormat.format(date);
    }

    /**
     * Formats a date object into a readable time of the format h:mm a
     *
     * @param date Date object to be formatted
     * @return Formatted String containing time (h:mm a)
     */
    private  String formatTime(Date date){
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a");
        return timeFormat.format(date);
    }

    /**
     * Formats a location String into a singular location
     *
     * @param location String object to be formatted
     * @return Formatted String containing earthquake location
     */
    private String formatLocation(String location){
        if (location.contains("km ")){
            int t = location.indexOf("of ") + 3;
            return location.substring(t, location.length()).trim();
        }
        else
            return location;
    }

    /**
     * Formats a location String into a location offset
     *
     * @param location String object to be formatted
     * @return Formatted String containing earthquake location offset
     */
    private String formatOffset(String location){
        if (location.contains("km ")){
            int t = location.indexOf("of ") + 2;
            return location.substring(0, t).trim();
        }
        else
            return "Near the";
    }

    private int getMagColor(double mag){
        int magColorId;
        int magFloor = (int) Math.floor(mag);

        switch(magFloor){
            case 0:
            case 1:
                magColorId = R.color.magnitude1;
                break;
            case 2:
                magColorId = R.color.magnitude2;
                break;
            case 3:
                magColorId = R.color.magnitude3;
                break;
            case 4:
                magColorId = R.color.magnitude4;
                break;
            case 5:
                magColorId = R.color.magnitude5;
                break;
            case 6:
                magColorId = R.color.magnitude6;
                break;
            case 7:
                magColorId = R.color.magnitude7;
                break;
            case 8:
                magColorId = R.color.magnitude8;
                break;
            case 9:
                magColorId = R.color.magnitude9;
                break;
            default:
                magColorId = R.color.magnitude10plus;
                break;
        }
        //Converts the color resource ID into a color integer value
        return ContextCompat.getColor(getContext(), magColorId);
    }
}
