package com.volunteeride.volunteeride.registerfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.volunteeride.dto.Center;
import com.volunteeride.rest.RestQueryEngine;
import com.volunteeride.rest.RestQueryResult;
import com.volunteeride.rest.volunteeride.VolunteeRideRestQueryProvider;
import com.volunteeride.volunteeride.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mthosani on 12/21/15.
 */
public class FragmentRideSeeker extends Fragment {

    private RestQueryEngine mQueryEngine;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment Fragment1.
     */
    public static FragmentRideSeeker newInstance() {
        return new FragmentRideSeeker();
    }

    public FragmentRideSeeker() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_ride_seeker, container, false);

                mQueryEngine = new RestQueryEngine(new VolunteeRideRestQueryProvider());
        RestQueryResult response = mQueryEngine.runSimpleQuery("centers");
        List<String> spinnerArray =  new ArrayList<String>();

        if(response.getstatusCode() == 200){
            ObjectMapper mapper = new ObjectMapper();
            try{
                //Center myCenter = mapper.readValue(response.getResponse(),Center.class);
                List<Center> myCenters = mapper.readValue(response.getResponse(), new TypeReference<List<Center>>(){});
                for(Center myCenter : myCenters) {
                    spinnerArray.add(myCenter.getName());
                    System.out.println(" Centers : " + myCenter);
                }
            } catch (JsonGenerationException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }



        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this.getActivity(), android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) rootView.findViewById(R.id.spinner_center);
        sItems.setAdapter(adapter);


        return rootView;
    }
}
