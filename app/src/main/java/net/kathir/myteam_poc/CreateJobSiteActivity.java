package net.kathir.myteam_poc;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import java.util.ArrayList;
import java.util.List;

public class CreateJobSiteActivity extends AppCompatActivity {

    EditText jsNameEtx,jsAddressEtx,jsRadiusEtx;
    AppCompatSpinner jsZoneTypeSpinner;
    Button createjobsiteBtn;
    String jsZoneType = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jobsite_layout);

        jsNameEtx = (EditText)findViewById(R.id.jobsite_name);
        jsAddressEtx = (EditText)findViewById(R.id.jobsite_address);
        jsRadiusEtx = (EditText)findViewById(R.id.jobsite_radius);
        createjobsiteBtn = (Button)findViewById(R.id.create_jsbtn);

        final List<String> zonetypeList = new ArrayList<String>();
        zonetypeList.add("Circle");
        zonetypeList.add("Polygon");


        jsZoneTypeSpinner = (AppCompatSpinner) findViewById(R.id.js_zonetype);

        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, zonetypeList);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jsZoneTypeSpinner.setAdapter(adp1);


        jsZoneTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
                // TODO Auto-generated method stub
                jsZoneType = jsZoneTypeSpinner.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });



        call_createJobsite();

    }

    private void call_createJobsite() {

        String jsName = jsNameEtx.getText().toString().trim();
        String jsAddress = jsAddressEtx.getText().toString().trim();
        String jsRadius = jsRadiusEtx.getText().toString().trim();

        JobSiteRequestModel jobSiteRequestModel = new JobSiteRequestModel();
        jobSiteRequestModel.setName(jsName);
        jobSiteRequestModel.setAddress(jsAddress);
        jobSiteRequestModel.setRadius(jsRadius);
        jobSiteRequestModel.setZoneType(jsZoneType);
        
        callJobSiteApi(jobSiteRequestModel);

    }

    private void callJobSiteApi(JobSiteRequestModel jobSiteRequestModel) {
    }
}
