package com.volunteeride.volunteeride;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.volunteeride.dto.Center;
import com.volunteeride.dto.Location;
import com.volunteeride.dto.Ride;
import com.volunteeride.dto.RideOperationEnum;
import com.volunteeride.dto.VolunteerideUser;
import com.volunteeride.rest.RestQueryEngine;
import com.volunteeride.rest.RestQueryEngineException;
import com.volunteeride.rest.RestQueryResult;
import com.volunteeride.rest.volunteeride.VolunteeRideRestQueryProvider;
import com.volunteeride.volunteeride.utility.LocalStoreUtility;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.volunteeride.common.VolunteeRideConstantsUtil.UrlNameConstants.EXECUTE_RIDE_OPERATION_URL_NAME;
import static com.volunteeride.common.VolunteeRideConstantsUtil.UrlNameConstants.RETRIEVE_CENTER_DETAILS_URL_NAME;
import static com.volunteeride.common.VolunteeRideConstantsUtil.UrlNameConstants.RETRIEVE_USER_DETAILS_URL_NAME;
import static com.volunteeride.common.VolunteeRideConstantsUtil.dateTimeFormatter;
import static com.volunteeride.common.VolunteeRideConstantsUtil.mapper;

public class RideDetailsActivity extends AppCompatActivity implements View.OnClickListener{

    private LocalStoreUtility localStoreUtility;
    private RestQueryEngine mQueryEngine;

    private Ride ride;

    private TextView statusTextView;
    private TextView pickUpTimeTextView;
    private TextView pickUpLocationTextView;
    private Button cancelRideBttn;
    private Button acceptRideBttn;
    private Button acknowledgeRideBttn;
    private TextView pickUpStreetAddTextView;
    private TextView dropOffStreeAddTextView;
    private TextView dropOffLocationTextView;
    private TextView totalNoOfRidersTextView;
    private TextView centerTextView;
    private TextView rideSeekerNameTextView;
    private TextView rideSeekerEmailTextView;
    private TextView rideSeekerPhnTextView;
    private TextView volunteerNameTextView;
    private TextView volunteerEmailTextView;
    private TextView volunteerPhnTextView;
    private RelativeLayout volunteerRelativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_details);

        this.initializeRideDetailViewElements();

        localStoreUtility = new LocalStoreUtility(this);
        mQueryEngine = new RestQueryEngine(new VolunteeRideRestQueryProvider());

        Bundle bundle = getIntent().getExtras();

        ride = (Ride) bundle.getSerializable("clickedRide");


        if(ride.getVolunteerId() != null){
            volunteerRelativeLayout.setVisibility(View.VISIBLE);
            VolunteerideUser volunteer =  this.retrieveUserDetails(ride.getVolunteerId());
            volunteerNameTextView.setText(volunteer.getFirstName() + " " + volunteer.getLastName());
            volunteerEmailTextView.setText(volunteer.getEmail());
            volunteerPhnTextView.setText(volunteer.getPhone());

        }

        if(ride.getCenterId() != null){
            Center center = this.retrieveCenterDetails(ride.getCenterId());
            centerTextView.setText(center.getName());
        }

        if(!CollectionUtils.isEmpty(ride.getRideSeekerIds())){
            VolunteerideUser rideSeeker = this.retrieveUserDetails(ride.getRideSeekerIds().get(0));
            rideSeekerNameTextView.setText(rideSeeker.getFirstName() + " " + rideSeeker.getLastName());
            rideSeekerEmailTextView.setText(rideSeeker.getEmail());
            rideSeekerPhnTextView.setText(rideSeeker.getPhone());
        }


        statusTextView.setText(ride.getStatus());
        pickUpTimeTextView.setText(dateTimeFormatter.print(ride.getPickupTime()));

        Location pickUpLocation = ride.getPickupLoc();

        pickUpLocationTextView.setText(pickUpLocation.getLocationName());

        StringBuilder pickUpAddress = new StringBuilder();
        pickUpAddress.append(pickUpLocation.getStreetAddress());
        pickUpAddress.append(", ");
        pickUpAddress.append(pickUpLocation.getCity());
        pickUpAddress.append(", ");
        pickUpAddress.append(pickUpLocation.getState());
        pickUpAddress.append("-");
        pickUpAddress.append(pickUpLocation.getZipcode());

        pickUpStreetAddTextView.setText(pickUpAddress.toString());

        Location dropOffLocation = ride.getDropoffLoc();

        StringBuilder dropOffAddress = new StringBuilder();
        dropOffAddress.append(dropOffLocation.getStreetAddress());
        dropOffAddress.append(", ");
        dropOffAddress.append(dropOffLocation.getCity());
        dropOffAddress.append(", ");
        dropOffAddress.append(dropOffLocation.getState());
        dropOffAddress.append("-");
        dropOffAddress.append(dropOffLocation.getZipcode());

        dropOffStreeAddTextView.setText(dropOffAddress.toString());

        dropOffLocationTextView.setText(dropOffLocation.getLocationName());

        totalNoOfRidersTextView.setText(String.valueOf(ride.getTotalNoOfRiders()));

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

    private Center retrieveCenterDetails(String centerId) {

        Center center = null;

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application", "json"));
        requestHeaders.add("Cookie", "JSESSIONID=" + localStoreUtility.getSessionId());
        Map urlParams = new HashMap<>();
        urlParams.put("centerId", centerId);

        try{

            RestQueryResult response = mQueryEngine.runSimpleQuery(RETRIEVE_CENTER_DETAILS_URL_NAME,
                    requestHeaders, null, urlParams, null);

            if (response.getstatusCode() == 200) {

                try {
                    center = mapper.readValue(response.getResponse(), Center.class);
                } catch (IOException e) {
                    //TODO Ayaz Handle exception
                    e.printStackTrace();
                }

            }else{
                //TODO Ayaz handle other response codes
            }

        }catch(RestQueryEngineException e){
            e.printStackTrace();
            //TODO Ayaz Handle Exception
            // Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return center;
    }

    private VolunteerideUser retrieveUserDetails(String userId) {

        VolunteerideUser user = null;

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application", "json"));
        requestHeaders.add("Cookie", "JSESSIONID=" + localStoreUtility.getSessionId());
        Map urlParams = new HashMap<>();
        urlParams.put("userId", userId);

        try{
            RestQueryResult response = mQueryEngine.runSimpleQuery(RETRIEVE_USER_DETAILS_URL_NAME,
                    requestHeaders, null, urlParams, null);

            if (response.getstatusCode() == 200) {

                try {
                    user = mapper.readValue(response.getResponse(), VolunteerideUser.class);
                } catch (IOException e) {
                    //TODO Ayaz Handle exception
                    e.printStackTrace();
                }

            }else{
                //TODO Ayaz handle other response codes
            }
        }catch(RestQueryEngineException e){
            e.printStackTrace();
            //TODO Ayaz Handle Exception
            // Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return user;
    }

    private void initializeRideDetailViewElements() {

        //Initialize Ride Operation Buttons
        cancelRideBttn = (Button)findViewById(R.id.bttnCnclRide);
        cancelRideBttn.setVisibility(View.INVISIBLE);
        cancelRideBttn.setOnClickListener(this);

        acceptRideBttn = (Button)findViewById(R.id.bttnAcptRide);
        acceptRideBttn.setVisibility(View.INVISIBLE);
        acceptRideBttn.setOnClickListener(this);

        acknowledgeRideBttn = (Button)findViewById(R.id.bttnAcknwldgeRide);
        acknowledgeRideBttn.setVisibility(View.INVISIBLE);
        acknowledgeRideBttn.setOnClickListener(this);

        volunteerRelativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        volunteerRelativeLayout.setVisibility(View.INVISIBLE);

        //Initialize Status element
        statusTextView = (TextView) findViewById(R.id.textStatus);

        //Initialize Pick up location elements
        pickUpTimeTextView = (TextView) findViewById(R.id.textPckUpTime);
        pickUpLocationTextView = (TextView) findViewById(R.id.textPckUpLoc);
        pickUpStreetAddTextView = (TextView) findViewById(R.id.textPckUpStreetAdd);


        //Initialize Drop off location elements
        dropOffStreeAddTextView = (TextView) findViewById(R.id.textDropOffStreetAdd);
        dropOffLocationTextView = (TextView) findViewById(R.id.textDropOffLoc);

        //Initialize no of riders element
        totalNoOfRidersTextView = (TextView) findViewById(R.id.textTotNoOfRiders);

        //Initialize Center Element
        centerTextView = (TextView) findViewById(R.id.textCenter);

        rideSeekerNameTextView = (TextView) findViewById(R.id.textRideSeekerName);
        rideSeekerEmailTextView = (TextView) findViewById(R.id.textRideSeekerEmail);
        rideSeekerPhnTextView = (TextView) findViewById(R.id.textRideSeekerPhn);
        volunteerNameTextView = (TextView) findViewById(R.id.textVolunteerName);
        volunteerEmailTextView = (TextView) findViewById(R.id.textVolunteerEmail);
        volunteerPhnTextView = (TextView) findViewById(R.id.textVolunteerPhn);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.bttnCnclRide :
                    this.executeRideOperation(RideOperationEnum.CANCEL);
                break;

            case R.id.bttnAcptRide:
                this.executeRideOperation(RideOperationEnum.ACCEPT);
                break;

            case R.id.bttnAcknwldgeRide:
                this.executeRideOperation(RideOperationEnum.ACKNOWLEDGE);
                break;
        }
    }

    private void executeRideOperation(RideOperationEnum rideOperation) {

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application", "json"));
        requestHeaders.add("Cookie", "JSESSIONID=" + localStoreUtility.getSessionId());
        Map urlParams = new HashMap<>();
        urlParams.put("centerId", ride.getCenterId());
        urlParams.put("rideId", ride.getId());
        urlParams.put("operationName", rideOperation.name());

        try{

            RestQueryResult response = mQueryEngine.runSimpleQuery(EXECUTE_RIDE_OPERATION_URL_NAME,
                    requestHeaders, null, urlParams, null);

            if (response.getstatusCode() == 200) {

                try {
                    ride = mapper.readValue(response.getResponse(), Ride.class);
                } catch (IOException e) {
                    //TODO Ayaz Handle exception
                    e.printStackTrace();
                }

            }else{
                //TODO Ayaz handle other response codes
            }

        }catch(RestQueryEngineException e){
            e.printStackTrace();
            //TODO Ayaz Handle Exception
            // Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        finish();
        Intent intent = getIntent();
        intent.putExtra("clickedRide", ride);
        startActivity(intent);
        String rideOperationMessage = "You have successfully " + ride.getStatus() + " this ride";
        Toast toast = Toast.makeText(this, rideOperationMessage, Toast.LENGTH_LONG);
        toast.show();
    }
}
