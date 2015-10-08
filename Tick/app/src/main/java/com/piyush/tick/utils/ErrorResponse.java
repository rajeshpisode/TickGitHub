package com.piyush.tick.utils;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hp on 7/20/2015.
 */


public class ErrorResponse {
    private Boolean success;
    @SerializedName("general_message")
    private String generalMessage;
    private Errors errors;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getGeneralMessage() {
        return generalMessage;
    }

    public void setGeneralMessage(String generalMessage) {
        this.generalMessage = generalMessage;
    }

    public Errors getErrors() {
        return errors;
    }

    public void setError(Errors errors) {
        this.errors = errors;
    }

}
