package kr.ac.kopo.hdyw0w.sixthsensor.item;

import com.google.gson.annotations.SerializedName;

public class LoginItem {

    @SerializedName("status")
    private String status;
    @SerializedName("username")
    private String username;
    @SerializedName("message")
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
