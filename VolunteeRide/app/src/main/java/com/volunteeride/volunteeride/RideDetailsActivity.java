package com.volunteeride.volunteeride;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.volunteeride.dto.Location;
import com.volunteeride.dto.Ride;
import com.volunteeride.dto.RideOperationEnum;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.util.CollectionUtils;

public class RideDetailsActivity extends AppCompatActivity {

    DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");

    private Ride ride;

    private TextView statusTextView;
    private TextView pickUpTimeTextView;
    private TextView pickUpLocationTextView;
    private Button cancelRideBttn;
    private Button acceptRideBttn;
    private Button acknowledgeRideBttn;
    private TextView pickUpStreetAddTextView;
    private TextView pickUpCityTextView;
    private TextView pickUpZipCodeTextView;
    private TextView pickUpStateTextView;
    private TextView dropOffStreeAddTextView;
    private TextView dropOffCityTextView;
    private TextView dropOffZipCodeTextView;
    private TextView dropOffStateTextView;
    private TextView dropOffLocationTextView;
    private TextView totalNoOfRidersTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Bundle bundle = getIntent().getExtras();

        statusTextView = (TextView) findViewById(R.id.textStatus);
        pickUpTimeTextView = (TextView) findViewById(R.id.textPckUpTime);
        pickUpLocationTextView = (TextView) findViewById(R.id.textPckUpLoc);

        pickUpStreetAddTextView = (TextView) findViewById(R.id.textPckUpStreetAdd);
        pickUpCityTextView = (TextView) findViewById(R.id.textPckUpCity);
        pickUpZipCodeTextView = (TextView) findViewById(R.id.textPckUpZipCode);
        pickUpStateTextView = (TextView) findViewById(R.id.textPckUpState);
        dropOffStreeAddTextView = (TextView) findViewById(R.id.textDropOffStreetAdd);
        dropOffCityTextView = (TextView) findViewById(R.id.textDropOffCity);
        dropOffZipCodeTextView = (TextView) findViewById(R.id.textDropOffZipCode);
        dropOffStateTextView = (TextView) findViewById(R.id.textDropOffState);
        dropOffLocationTextView = (TextView) findViewById(R.id.textDropOffLoc);
        totalNoOfRidersTextView = (TextView) findViewById(R.id.textTotNoOfRiders);

        ride = (Ride) bundle.getSerializable("clickedRide");

        statusTextView.setText(ride.getStatus());
        pickUpTimeTextView.setText(fmt.print(ride.getPickupTime()));

        Location pickUpLocation = ride.getPickupLoc();

        pickUpLocationTextView.setText(pickUpLocation.getLocationName());
        pickUpStreetAddTextView.setText(pickUpLocation.getStreetAddress());
        pickUpCityTextView.setText(pickUpLocation.getCity());
        pickUpZipCodeTextView.setText(pickUpLocation.getZipcode());
        pickUpStateTextView.setText(pickUpLocation.getState());

        Location dropOffLocation = ride.getDropoffLoc();

        dropOffLocationTextView.setText(dropOffLocation.getLocationName());
        dropOffStreeAddTextView.setText(dropOffLocation.getStreetAddress());
        dropOffCityTextView.setText(dropOffLocation.getCity());
        dropOffZipCodeTextView.setText(dropOffLocation.getZipcode());
        dropOffStateTextView.setText(dropOffLocation.getState());

        totalNoOfRidersTextView.setText(String.valueOf(ride.getTotalNoOfRiders()));

        this.initializeRideOperationButtons();

        if(!CollectionUtils.isEmpty(ride.getNextRideUserOperations())){
            for(String rideOperation : ride.getNextRideUserOperations()){

                RideOperationEnum rideOperationEnum = null;
                try{
                    rideOperationEnum = RideOperationEnum.valueOf(rideOperation);
                }catch(IllegalArgumentException iae){
                    System.out.println("No Ride operation enum found for key " + rideOperation);
                    //TODO Ayaz Log the exception
                }

                if(rideOperationEnum != null){
                    switch (rideOperationEnum){
                        case CANCEL:
                            cancelRideBttn.setVisibility(View.VISIBLE);
                            break;
                        case ACCEPT:
                            acceptRideBttn.setVisibility(View.VISIBLE);
                            break;
                        case ACKNOWLEDGE:
                            acknowledgeRideBttn.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            }
        }
    }

    private void initializeRideOperationButtons() {
        cancelRideBttn = (Button)findViewById(R.id.bttnCnclRide);
        cancelRideBttn.setVisibility(View.INVISIBLE);

        acceptRideBttn = (Button)findViewById(R.id.bttnAcptRide);
        acceptRideBttn.setVisibility(View.INVISIBLE);

        acknowledgeRideBttn = (Button)findViewById(R.id.bttnAcknwldgeRide);
        acknowledgeRideBttn.setVisibility(View.INVISIBLE);
    }

}
