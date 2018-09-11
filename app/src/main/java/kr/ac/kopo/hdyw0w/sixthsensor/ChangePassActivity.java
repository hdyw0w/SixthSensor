package kr.ac.kopo.hdyw0w.sixthsensor;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;

import kr.ac.kopo.hdyw0w.sixthsensor.item.Code;
import kr.ac.kopo.hdyw0w.sixthsensor.item.PassChItem;
import kr.ac.kopo.hdyw0w.sixthsensor.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ChangePassActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_passwd_change);

        final EditText passwd = (EditText) findViewById(R.id.dpc_passwd);
        final EditText newpasswd = (EditText) findViewById(R.id.dpc_newPasswd);
        final EditText repasswd = (EditText) findViewById((R.id.dpc_rePasswd));

        findViewById(R.id.dpc_btnCancel).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        finish();
                    }
                }
        );

        findViewById(R.id.dpc_btnSubmit).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        String curpwd = passwd.getText().toString();
                        String newpwd = newpasswd.getText().toString();
                        String repwd = repasswd.getText().toString();

                        if (curpwd.length() == 0 || newpwd.length() == 0 || repwd.length() == 0){
                            Toast.makeText(ChangePassActivity.this, "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        SharedPreferences preferences = getSharedPreferences(Code.pref_id, 0);
                        String username = preferences.getString(Code.pref_user_id, "");

                        Toast.makeText(ChangePassActivity.this, "username : " + username, Toast.LENGTH_SHORT).show();


                        Retrofit retrofit = RetrofitService.retrofit;
                        RetrofitService service = retrofit.create(RetrofitService.class);
                        service.password(username, curpwd, newpwd).enqueue(new Callback<PassChItem>() {
                            @Override
                            public void onResponse(Call<PassChItem> call, Response<PassChItem> response) {
                                if (response.isSuccessful()) {
                                    PassChItem item = response.body();
                                    assert item != null;

                                    if (item.getStatus().equals("OK")){
                                        Toast.makeText(ChangePassActivity.this, "비밀번호가 변경되었습니다", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }

                                } else {
                                    Toast.makeText(ChangePassActivity.this, "response not successful", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<PassChItem> call, Throwable t) {
                                Toast.makeText(ChangePassActivity.this, "onFailure", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
        );

        repasswd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String password = newpasswd.getText().toString();
                String repassword = repasswd.getText().toString();

                if (password.equals(repassword)) {
                    newpasswd.setTextColor(Color.GREEN);
                    repasswd.setTextColor(Color.GREEN);
                } else {
                    newpasswd.setTextColor(Color.RED);
                    repasswd.setTextColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
