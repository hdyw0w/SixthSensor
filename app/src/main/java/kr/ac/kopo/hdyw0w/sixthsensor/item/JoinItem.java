package kr.ac.kopo.hdyw0w.sixthsensor.item;

import com.google.gson.annotations.SerializedName;

public class JoinItem {

    @SerializedName("status")
    private String status;

    @SerializedName("username")
    private String username;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("password")
    private String password;

    // Setter
    public void setStatus(String status){
        this.status = status;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setUser_id(String user_id){
        this.user_id = user_id;
    }

    public void setPassword(String password){
        this.password = password;
    }

    // Getter
    public String getStatus(){
        return status;
    }

    public String getUsername(){
        return username;
    }

    public String getUser_id(){
        return user_id;
    }

    public String getPassword(){
        return password;
    }

}
