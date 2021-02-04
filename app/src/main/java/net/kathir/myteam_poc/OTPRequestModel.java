package net.kathir.myteam_poc;

import com.google.gson.annotations.SerializedName;

public class OTPRequestModel {

    @SerializedName("otp")
    public String otp;

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }




}
