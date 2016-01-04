package com.volunteeride.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.volunteeride.dto.Ride;
import com.volunteeride.volunteeride.R;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.List;


/**
 * Created by ayazlakdawala on 12/17/15.
 */
public class RideAdapter extends ArrayAdapter<Ride> {

    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

    private final Activity context;
    private final List<Ride> rides;

    static class ViewHolder {
        public TextView status;
        public TextView pickUpTime;
        public TextView pickUpLocation;
    }

    public RideAdapter(Activity context, List<Ride> rides) {
        super(context, R.layout.ride_item, rides);
        this.context = context;
        this.rides = rides;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            rowView = inflater.inflate(R.layout.ride_item, null);
            // configure view holder
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.status = (TextView) rowView.findViewById(R.id.textStatus);
            viewHolder.pickUpTime = (TextView) rowView.findViewById(R.id.textPckUpTime);
            viewHolder.pickUpLocation = (TextView) rowView.findViewById(R.id.textPckUpLoc);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();
        Ride ride = rides.get(position);
        holder.status.setText(ride.getStatus());
        holder.pickUpTime.setText(fmt.print(ride.getPickupTime()));
        holder.pickUpLocation.setText(ride.getPickupLoc().getLocationName());

        return rowView;
    }



}
