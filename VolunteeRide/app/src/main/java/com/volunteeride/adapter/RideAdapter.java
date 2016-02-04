package com.volunteeride.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.volunteeride.dto.Ride;
import com.volunteeride.volunteeride.R;

import java.util.List;

import static com.volunteeride.common.VolunteeRideConstantsUtil.dateTimeFormatter;


/**
 * Created by ayazlakdawala on 12/17/15.
 */
public class RideAdapter extends ArrayAdapter<Ride> {

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
            viewHolder.status = (TextView) rowView.findViewById(R.id.textListStatus);
            viewHolder.pickUpTime = (TextView) rowView.findViewById(R.id.textListPckUpTime);
            viewHolder.pickUpLocation = (TextView) rowView.findViewById(R.id.textListPckUpLoc);
            rowView.setTag(viewHolder);
        }

        // fill data
        ViewHolder holder = (ViewHolder) rowView.getTag();
        Ride ride = rides.get(position);
        holder.status.setText(ride.getStatus());
        holder.pickUpTime.setText(dateTimeFormatter.print(ride.getPickupTime()));
        holder.pickUpLocation.setText(ride.getPickupLoc().getLocationName());

        return rowView;
    }


}
