package kr.ac.kopo.hdyw0w.sixthsensor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        findViewById(R.id.btnLogin).setOnClickListener(
                new Button.OnClickListener(){
                    public void onClick(View v){
                        Intent intent_act = new Intent(getApplicationContext(),NavActivity.class);
                        startActivity(intent_act);
                    }
                }
        );

    }
}
