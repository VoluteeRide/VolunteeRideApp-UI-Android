package com.volunteeride.volunteeride.registerfragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.volunteeride.dto.Center;
import com.volunteeride.dto.UserRoleEnum;
import com.volunteeride.dto.VolunteerideUser;
import com.volunteeride.rest.RestQueryEngine;
import com.volunteeride.rest.RestQueryEngineException;
import com.volunteeride.rest.RestQueryResult;
import com.volunteeride.common.VolunteeRideConstantsUtil;
import com.volunteeride.rest.volunteeride.VolunteeRideRestQueryProvider;
import com.volunteeride.volunteeride.R;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mthosani on 12/21/15.
 */
public class FragmentRideSeeker extends Fragment implements View.OnClickListener{

    private RestQueryEngine mQueryEngine;
    Button buttonRegisterRideSeeker;

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

        View rootView = inflater.inflate(R.layout.fragment_ride_seeker, container, false);
        loadCenters(rootView);

        buttonRegisterRideSeeker = (Button) rootView.findViewById(R.id.button_register_ride_seeker);
        buttonRegisterRideSeeker.setOnClickListener(this);

        return rootView;
    }

    private void loadCenters(View rootView){

        mQueryEngine = new RestQueryEngine(new VolunteeRideRestQueryProvider());

        RestQueryResult response = mQueryEngine.runSimpleQuery(VolunteeRideConstantsUtil.GET_CENTERS,null);
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

    }

    @Override
    public void onClick(View v) {

        VolunteerideUser testUser = new VolunteerideUser();
        testUser.setUsername("abc");
        testUser.setPassword("test123");
        testUser.setFirstName("TestFirstName");
        testUser.setLastName("TestLastName");
        testUser.setEmail("test@testemail.com");
        testUser.setPhone("1023456789");
        List<UserRoleEnum> useRole = new ArrayList<UserRoleEnum>();
        useRole.add(UserRoleEnum.RIDE_SEEKER);
        testUser.setUserRoles(useRole);

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(new MediaType("application","json"));

        try {
            mQueryEngine = new RestQueryEngine(new VolunteeRideRestQueryProvider());
            RestQueryResult response = mQueryEngine.runSimpleQuery(VolunteeRideConstantsUtil.REGISTER_USER, requestHeaders,testUser);
            if (response.getstatusCode() == 200) {

            }
        }catch(RestQueryEngineException e){
            Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }



    }
}
