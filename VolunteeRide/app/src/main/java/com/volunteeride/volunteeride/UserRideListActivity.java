package com.volunteeride.volunteeride;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.fasterxml.jackson.core.type.TypeReference;
import com.volunteeride.adapter.RideAdapter;
import com.volunteeride.dto.Ride;
import com.volunteeride.dto.VolunteerideUser;
import com.volunteeride.rest.RestQueryEngine;
import com.volunteeride.rest.RestQueryEngineException;
import com.volunteeride.rest.RestQueryResult;
import com.volunteeride.rest.volunteeride.VolunteeRideRestQueryProvider;
import com.volunteeride.volunteeride.utility.LocalStoreUtility;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.volunteeride.dto.RideStatusEnum.REQUESTED;
import static com.volunteeride.rest.volunteeride.VolunteeRideConstantsUtil.QueryParamsConstants.RIDE_STATUS;
import static com.volunteeride.rest.volunteeride.VolunteeRideConstantsUtil.UrlNameConstants.SEARCH_RIDES_URL_NAME;
import static com.volunteeride.rest.volunteeride.VolunteeRideConstantsUtil.mapper;

public class UserRideListActivity extends ListActivity {

    private RestQueryEngine mQueryEngine;
    private Button bttnRetrieveCenters;
    private List<Ride> rides;
    private LocalStoreUtility localStoreUtility;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ride_list);

        /*bttnRetrieveCenters = (Button)findViewById(R.id.bttnRtrvRides);

        bttnRetrieveCenters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setListAdapter(new RideAdapter(UserRideListActivity.this, rides));
            }
        });*/

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application", "json"));

        try {

            localStoreUtility = new LocalStoreUtility(this);

            requestHeaders.add("Cookie", "JSESSIONID=" + localStoreUtility.getSessionId());

            VolunteerideUser loggedInUser = localStoreUtility.getLoggedInUserInfo();

            Map urlParams = new HashMap<>();
            urlParams.put("centerId", loggedInUser.getCenterId());

            Map queryParams = new HashMap<>();
            queryParams.put(RIDE_STATUS, REQUESTED.name());

            mQueryEngine = new RestQueryEngine(new VolunteeRideRestQueryProvider());

            RestQueryResult response = mQueryEngine.runSimpleQuery(SEARCH_RIDES_URL_NAME,
                    requestHeaders, null, urlParams, queryParams);

            if (response.getstatusCode() == 200) {

                try {
                    rides = mapper.
                            readValue(response.getResponse(), new TypeReference<List<Ride>>() {});
                } catch (IOException e) {
                    e.printStackTrace();
                }

                setListAdapter(new RideAdapter(UserRideListActivity.this, rides));
            }
        }catch(RestQueryEngineException e){
           // Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        try {
            Ride clickedRide = (Ride) getListAdapter().getItem(position);
            Intent intent = new Intent(v.getContext(), RideDetailsActivity.class);
            intent.putExtra("clickedRide", clickedRide);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
