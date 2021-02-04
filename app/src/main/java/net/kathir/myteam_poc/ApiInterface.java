package net.kathir.myteam_poc;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("/track/mobile/v1/login/{deviceId}/2fa")
    Call<ResponseModel> getLogin(@Path("deviceId") String deviceId);


    @POST("/track/mobile/v1/verify/{deviceId}/2fa")
    Call<OTPResponseModel> sentOTP(@Path("deviceId") String deviceId , @Body OTPRequestModel otpRequestModel);


    @POST("/track/mobile/v1/allgeo/customers")
    Call<JobSiteResponseModel> createJobSite(@Header ("Authorization") String authorization, @Body OTPRequestModel otpRequestModel);


}
