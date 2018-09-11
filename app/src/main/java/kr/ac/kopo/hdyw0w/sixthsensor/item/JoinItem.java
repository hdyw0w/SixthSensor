package kr.ac.kopo.hdyw0w.sixthsensor.item;

import com.google.gson.annotations.SerializedName;

public class JoinItem {

    @SerializedName("username")
    private String username;

    @SerializedName("user_id")
    private String user_id;

    @SerializedName("password")
    private String password;

    // Setter
    public void setUsername(String usernamed){
        this.username = username;
    }

    public void setUser_id(String user_id){
        this.user_id = user_id;
    }

    public void setPassword(String password){
        this.password = password;
    }

    // Getter
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
