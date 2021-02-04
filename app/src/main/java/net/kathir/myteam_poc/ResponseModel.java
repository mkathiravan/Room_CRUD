package net.kathir.myteam_poc;

import com.google.gson.annotations.SerializedName;

public class ResponseModel {

    @SerializedName("Status")
    public String Status;

    @SerializedName("SucessMsg")
    public String SucessMsg;

    @SerializedName("StatusCode")
    public int StatusCode;

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getSucessMsg() {
        return SucessMsg;
    }

    public void setSucessMsg(String sucessMsg) {
        SucessMsg = sucessMsg;
    }

    public int getStatusCode() {
        return StatusCode;
    }

    public void setStatusCode(int statusCode) {
        StatusCode = statusCode;
    }







}
