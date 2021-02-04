package net.kathir.myteam_poc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhoneNumberActivity extends AppCompatActivity {

    private static final String TAG = PhoneNumberActivity.class.getSimpleName();
    EditText et_phoneNo;
    Button saveBtn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phoneno_layout);

        et_phoneNo = (EditText)findViewById(R.id.phoneNumberText);
        saveBtn = (Button)findViewById(R.id.savePhoneBtn);


        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String device_number = et_phoneNo.getText().toString().trim();

                Log.d(TAG,"GET_DEVICE_NUMBER " + device_number);

                call_loginAPI(device_number);
            }
        });





    }

    private void call_loginAPI(final String device_number) {

        final ApiInterface requestInterface = ApiClient.getClient();

        Call<ResponseModel> call = requestInterface.getLogin(device_number);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {

                if(response.body().getStatusCode() == 200)
                {
                    SharedPreferences mda_prefs = getSharedPreferences("myteam_prefs", 0);
                    SharedPreferences.Editor prefs_edit = mda_prefs.edit();
                    prefs_edit.putString("device_number", device_number);
                    prefs_edit.apply();

                    Intent intent = new Intent(PhoneNumberActivity.this, VerifyActivity.class);
                    startActivity(intent);
                }

                Log.d(TAG,"ONRESPONSE_CALLED ");

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {

                Log.d(TAG,"ONFALIURE_CALLED ");
            }
        });




    }
}
