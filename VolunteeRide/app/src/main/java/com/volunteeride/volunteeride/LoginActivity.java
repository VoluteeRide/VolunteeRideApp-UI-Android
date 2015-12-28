package com.volunteeride.volunteeride;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.volunteeride.rest.RestQueryEngine;
import com.volunteeride.rest.RestQueryEngineException;
import com.volunteeride.rest.RestQueryResult;
import com.volunteeride.rest.volunteeride.VolunteeRideConstantsUtil;
import com.volunteeride.rest.volunteeride.VolunteeRideRestQueryProvider;

import org.springframework.http.HttpAuthentication;
import org.springframework.http.HttpBasicAuthentication;
import org.springframework.http.HttpHeaders;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    Button buttonRegister, buttonLogin;
    EditText textUserName, textPassword;
    private RestQueryEngine mQueryEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textUserName = (EditText)findViewById(R.id.editText_username);
        textPassword = (EditText)findViewById(R.id.editText_password);

        buttonRegister = (Button) findViewById(R.id.button_register);
        buttonRegister.setOnClickListener(this);

        buttonLogin = (Button) findViewById(R.id.button_login);
        buttonLogin.setOnClickListener(this);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.button_register:
                final Context context = this;
                Intent intent = new Intent(context, RegisterActivity.class);
                startActivity(intent);

                break;
            case R.id.button_login:
                HttpHeaders requestHeaders = new HttpHeaders();
                HttpAuthentication authHeader = new HttpBasicAuthentication(textUserName.toString().trim(), textPassword.toString().trim());
                requestHeaders.setAuthorization(authHeader);
                try {
                    mQueryEngine = new RestQueryEngine(new VolunteeRideRestQueryProvider());
                    RestQueryResult response = mQueryEngine.runSimpleQuery(VolunteeRideConstantsUtil.LOGIN, requestHeaders);
                    if (response.getstatusCode() == 200) {
                        startActivity(new Intent(this, MainActivity.class));
                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Login", Toast.LENGTH_LONG).show();
                    }
                }catch(RestQueryEngineException e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

                break;
        }
    }
}
