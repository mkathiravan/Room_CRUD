package net.kathir.myteam_poc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerifyActivity extends AppCompatActivity {

    public static final String TAG = VerifyActivity.class.getSimpleName();

    EditText otpEtx;

    Button submit;

    private SharedPreferences sh_prefs;
    private SharedPreferences.Editor sh_prefs_edit;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.verify_layout);

        otpEtx = (EditText)findViewById(R.id.otpEdTxt);

        submit = (Button)findViewById(R.id.savePhoneBtn);


        sh_prefs = getSharedPreferences("myteam_prefs", 0);
        sh_prefs_edit = sh_prefs.edit();

        final String device_number = sh_prefs.getString("device_number","");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String otpText = otpEtx.getText().toString().trim();

                Log.d(TAG,"OTP_TEXT " + otpText);

                sendOtpToServer(otpText,device_number);

            }
        });


    }

    private void sendOtpToServer(String otpText, String device_mumber) {

        OTPRequestModel otpRequestModel = new OTPRequestModel();
        otpRequestModel.setOtp(otpText);

        final ApiInterface requestInterface = ApiClient.getClient();

        Call<OTPResponseModel> call = requestInterface.sentOTP(device_mumber,otpRequestModel);
        call.enqueue(new Callback<OTPResponseModel>() {
            @Override
            public void onResponse(Call<OTPResponseModel> call, Response<OTPResponseModel> response) {

                Log.d(TAG,"ONRESPONSE_CALLED ");
                if(response.body().getStatusCode() == 200)
                {
                    Log.d(TAG,"GET_TOKEN_FROM_SERVER " + response.body().getToken());
                    Toast.makeText(VerifyActivity.this,response.body().getSucessMsg(),Toast.LENGTH_SHORT).show();

                    sh_prefs_edit.putString("token_key",response.body().getToken());
                    sh_prefs_edit.apply();

                    Intent intent = new Intent(VerifyActivity.this, MainActivity.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(VerifyActivity.this,response.body().getSucessMsg(),Toast.LENGTH_SHORT).show();
                }



            }

            @Override
            public void onFailure(Call<OTPResponseModel> call, Throwable t) {

                Log.d(TAG,"ONFALIURE_CALLED ");
            }
        });


    }
}
