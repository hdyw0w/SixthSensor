package kr.ac.kopo.hdyw0w.sixthsensor.retrofit;

import kr.ac.kopo.hdyw0w.sixthsensor.item.JoinItem;
import kr.ac.kopo.hdyw0w.sixthsensor.item.LoginItem;
import kr.ac.kopo.hdyw0w.sixthsensor.item.PassChItem;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitService {

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://sixth-sensor-test.appspot.com/").addConverterFactory(GsonConverterFactory.create())
            .client(new OkHttpClient.Builder().addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)).build())
            .build();

    //로그인
    @FormUrlEncoded
    @POST("users/login")
    Call<LoginItem> login (
            @Field("user_id") String user_id,
            @Field("password") String password
    );

    //비밀번호 변경
    @FormUrlEncoded
    @PATCH("users/{user_id}/password/")
    Call<PassChItem> password (
            @Path("user_id") String user_id,
            @Field("curPwd") String curPwd,
            @Field("newPwd") String newPwd
    );

    // POST방식 (회원가입)
    @FormUrlEncoded // 수정
    @POST("users")
    Call<JoinItem> users(
            @Field("username") String username,
            @Field("user_id") String user_id,
            @Field("password") String password
    );


}
