package com.volunteeride.volunteeride;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.volunteeride.dto.Center;
import com.volunteeride.rest.RestQueryEngine;
import com.volunteeride.rest.RestQueryResult;
import com.volunteeride.rest.volunteeride.VolunteeRideRestQueryProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {


    private RestQueryEngine mQueryEngine;

    public void registerUser(View registerButton){

        final EditText usernameField = (EditText) findViewById(R.id.text_username);
        String username = usernameField.getText().toString();

        EditText passwordField = (EditText)findViewById(R.id.text_password);
        String password = passwordField.getText().toString();


        final EditText firstNameField = (EditText) findViewById(R.id.text_firstname);
        String firstname = firstNameField.getText().toString();

        final EditText lastNameField = (EditText) findViewById(R.id.text_lastname);
        String lastname = lastNameField.getText().toString();

        final EditText emailField = (EditText) findViewById(R.id.text_email);
        String email = emailField.getText().toString();

        final EditText phoneField = (EditText) findViewById(R.id.text_phone);
        String phone = emailField.getText().toString();

        final Spinner centerSpinner = (Spinner) findViewById(R.id.spinner_center);
        String center = centerSpinner.getSelectedItem().toString();

        RadioGroup userRoleRadioGroup =(RadioGroup) findViewById(R.id.userRole);
        RadioButton userRoleRadioButton = (RadioButton) findViewById(userRoleRadioGroup.getCheckedRadioButtonId());
        String userType = userRoleRadioButton.getText().toString();

        //Log.i("Register User : Username : " + username +"");



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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
                this, android.R.layout.simple_spinner_item, spinnerArray);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner sItems = (Spinner) findViewById(R.id.spinner_center);
        sItems.setAdapter(adapter);








//       FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }

}
