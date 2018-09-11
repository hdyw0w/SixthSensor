package kr.ac.kopo.hdyw0w.sixthsensor;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import kr.ac.kopo.hdyw0w.sixthsensor.item.JoinItem;
import kr.ac.kopo.hdyw0w.sixthsensor.retrofit.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join_activity);

        final EditText username = (EditText) findViewById(R.id.ja_username);
        final EditText user_id = (EditText) findViewById(R.id.ja_user_id);
        final EditText password = (EditText) findViewById(R.id.ja_password);
        final EditText passwordConfirm = (EditText) findViewById(R.id.ja_passwordconfirm);

        Button btnSubmit = (Button) findViewById(R.id.ja_btnSubmit);
        Button btnmlogin = (Button) findViewById(R.id.ja_btnlogin);

        // 최소 글자수 제한
        EditText passwordLength = (EditText) findViewById(R.id.ja_password);
        // password.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
        if (passwordLength.getText().length() < 10) {
            Toast.makeText(this, "비밀번호를 10자리 이상으로 입력해주세요! ", Toast.LENGTH_SHORT)
                    .show();
        }

        // 비밀번호 재확인
        passwordConfirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                String passwordInput = password.getText().toString();
                String confirm = passwordConfirm.getText().toString();

                if (passwordInput.equals(confirm)) {
                    passwordConfirm.setTextColor(Color.GREEN);
                    // 배경 바꾸기
                    // passwordConfirm.setBackgroundColor(Color.GREEN);
                    Toast.makeText(JoinActivity.this, "비밀번호가 일치합니다", Toast.LENGTH_SHORT).show();


                } else {
                    passwordConfirm.setTextColor(Color.RED);
                    Toast.makeText(JoinActivity.this, "비밀번호가 서로 다릅니다!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        btnSubmit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {

                final String name = username.getText().toString();
                final String id = user_id.getText().toString();
                String passWd = password.getText().toString();

                // 이름 입력 확인
                if (name.length() == 0) {
                    Toast.makeText(JoinActivity.this, "이름을 입력해주세요!", Toast.LENGTH_SHORT).show();
                    username.requestFocus();
                    return;
                }

                // 아이디 입력 확인
                if (id.length() == 0) {
                    Toast.makeText(JoinActivity.this, "아이디를 입력해주세요!", Toast.LENGTH_SHORT).show();
                    user_id.requestFocus();
                    return;
                }

                // 비밀번호 입력 확인
                if (passWd.length() == 0) {
                    Toast.makeText(JoinActivity.this, "비밀번호를 입력해주세요!", Toast.LENGTH_SHORT).show();
                    password.requestFocus();
                    return;
                }

                // 비밀번호 확인란 입력 확인
                if (passwordConfirm.length() == 0) {
                    Toast.makeText(JoinActivity.this, "비밀번호를 다시 한번 입력해주세요!", Toast.LENGTH_SHORT).show();
                    passwordConfirm.requestFocus();
                    return;
                }

                // 비밀번호 일치 확인
                if (!passWd.equals(passwordConfirm.getText().toString())) {
                    Toast.makeText(JoinActivity.this, "비밀번호가 일치하지 않습니다!", Toast.LENGTH_SHORT).show();
                    password.requestFocus();
                    return;
                }

                // Retrofit 요청
                Retrofit retrofit = RetrofitService.retrofit;
                RetrofitService service = retrofit.create(RetrofitService.class);
                service.users(name, id, passWd).enqueue(new Callback<JoinItem>() {

                    @Override
                    public void onResponse(Call<JoinItem> call, Response<JoinItem> response) {
                        // 요청 성공 (User 데이터 가져오기)
                        if (response.isSuccessful()) {
                            try {
                                JSONObject obj = new JSONObject(response.body().toString());
                                Toast.makeText(JoinActivity.this, "onResponse is successful\n" + obj.toString(), Toast.LENGTH_SHORT).show();
                                // 완료시
                                Toast.makeText(JoinActivity.this, "회원가입이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                                finish();

                                Intent intent_act = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent_act);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            JSONObject obj = null;
                            Toast.makeText(JoinActivity.this, "onResponse no successful", Toast.LENGTH_SHORT).show();
//                            try {
//                                obj = new JSONObject(response.body().string());
//                                Toast.makeText(JoinActivity.this, "onResponse no successful\n"+obj.toString(), Toast.LENGTH_SHORT).show();
//                            } catch (JSONException | IOException e) {
//                                e.printStackTrace();
//                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<JoinItem> call, Throwable t) {
                        Toast.makeText(JoinActivity.this, "onFailure : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        // 요청 실패 (Dlog 배우기)
                        Log.v("TAG", "Error Message");

                    }
                });

            }
        });

        // 로그인하기 버튼
        btnmlogin.setOnClickListener(new Button.OnClickListener() {

            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);

            }
        });

    }
}
